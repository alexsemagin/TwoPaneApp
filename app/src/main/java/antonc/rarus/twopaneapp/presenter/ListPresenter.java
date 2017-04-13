package antonc.rarus.twopaneapp.presenter;

import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import antonc.rarus.twopaneapp.model.entity.DataItem;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListPresenter {

    private ListView mView;
    private List<DataItem> data;
    private List<DataItem> partialData;

    private String query;

    private boolean az;
    private boolean time;

    public ListPresenter() {
        partialData = data = new ArrayList<>();
    }

    public void setView(ListView view) {
        mView = view;
    }

    public void getData() {
        if (data.size() == 0) {
            data = new ArrayList<>();
            Observable.range(0, 100)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(i ->
                                    data.add(new DataItem("TTILE" + i, "INFO" + i)),
                            e -> {}, () -> updateData(data));

        } else
            updateData(data);
    }

    public void search(String query) {
        this.query = query;
        setVisibilityProgressBar(ProgressBar.VISIBLE);
        final String filterPattern = query.toLowerCase().trim();
        Observable.fromIterable(data)
                .filter(o -> o.getTitle().toLowerCase().contains(filterPattern) || o.getInfo().toLowerCase().contains(filterPattern))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateData, throwable -> {
                });
      }


    private void updateData(List list) {
        if (mView != null) {
            mView.setData(list);
            setVisibilityProgressBar(ProgressBar.INVISIBLE);
        }
        partialData = list;
    }

    private void setVisibilityProgressBar(int visible) {
        mView.setVisibilityProgressBar(visible);
    }

    public void onDestroyView() {
        setView(null);
    }

    public void sortingByTime() {
        setVisibilityProgressBar(ProgressBar.VISIBLE);
        Observable.fromIterable(partialData)
                .sorted((a, b) -> time ? (int) (b.getTimeLong() - a.getTimeLong()) : (int) (a.getTimeLong() - b.getTimeLong()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateData, throwable -> {
                });
        time = !time;
    }


    public void sortingByAZ() {
        setVisibilityProgressBar(ProgressBar.VISIBLE);
        Observable.fromIterable(partialData)
                .sorted((a, b) -> az ? b.getTitle().compareToIgnoreCase(a.getTitle()) : a.getTitle().compareToIgnoreCase(b.getTitle()))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateData, throwable -> {});
        az = !az;
    }


    public void onItemSelected(String title, String info) {
        mView.openDetailFragment(title, info);
    }


}


