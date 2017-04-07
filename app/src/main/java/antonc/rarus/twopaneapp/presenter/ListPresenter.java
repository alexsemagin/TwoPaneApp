package antonc.rarus.twopaneapp.presenter;

import android.widget.ProgressBar;

import java.util.Collections;
import java.util.List;

import antonc.rarus.twopaneapp.model.entity.DataItem;
import antonc.rarus.twopaneapp.model.entity.MyModel;
import antonc.rarus.twopaneapp.ui.test_task.MyFilter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ListPresenter {

    private ListView mView;
    private String query;
    private List mDataItems;
    private MyFilter filter;

    private int mVisible;
    private boolean az;
    private boolean time;

    public ListPresenter() {
        filter = new MyFilter(this);
        mVisible = ProgressBar.INVISIBLE;

        Observable.just(MyModel.get())
                .subscribe(dataList -> {
                    mDataItems = dataList;
                });
    }


    public void setView(ListView view) {
        mView = view;
    }

    public void getData() {
        if (mView != null) {
            mView.setData(mDataItems);
            setVisibilityProgressBar(mVisible);
        }
    }

    public void search(String query) {
        this.query = query;
        setVisibilityProgressBar(ProgressBar.VISIBLE);
        filter.filter(query);
      }

    public void updateDataList(List dataItems) {
        this.mDataItems = dataItems;
        getData();
        setVisibilityProgressBar(ProgressBar.INVISIBLE);
    }

    private void setVisibilityProgressBar(int visible) {
        mView.setVisibilityProgressBar(visible);
        mVisible = visible;
    }

    public void onDestroyView() {
        setView(null);
    }

    public void sortingByTime() {
       Observable.just(true)
                .map(aBoolean -> {
                    setVisibilityProgressBar(ProgressBar.VISIBLE);
                    if (!time)
                        Collections.sort(mDataItems, (a, b) -> {
                            DataItem aItem = (DataItem) a;
                            DataItem bItem = (DataItem) b;
                            return (int) (aItem.getTimeLong() - bItem.getTimeLong());
                        });
                    else
                        Collections.sort(mDataItems, (a, b) -> {
                            DataItem aItem = (DataItem) a;
                            DataItem bItem = (DataItem) b;
                            return (int) (bItem.getTimeLong() - aItem.getTimeLong());
                        });
                    time = !time;
                    return aBoolean;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                            getData();
                            setVisibilityProgressBar(ProgressBar.INVISIBLE);},
                        e -> setVisibilityProgressBar(ProgressBar.INVISIBLE), () -> {});
    }



    public void sortingByAZ() {
       /* ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.execute(() -> {
            mDataItems.sortByAZ();
            getData();
        });*/
    }

    public void onItemSelected(String title, String info) {
        mView.openDetailFragment(title, info);
    }


}


