package antonc.rarus.twopaneapp.presenter;

import android.widget.ProgressBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import antonc.rarus.twopaneapp.model.entity.DataList;
import antonc.rarus.twopaneapp.ui.test_task.MyFilter;

public class ListPresenter {

    private ListView mView;
    private String query;
    private DataList dl;
    private MyFilter filter;
    private int mVisible;

    public ListPresenter() {
        filter = new MyFilter(this);
        mVisible = ProgressBar.INVISIBLE;
        dl = DataList.get();
    }


    public void setView(ListView view) {
        mView = view;
    }

    public void getData() {
        if (mView != null) {
            if (dl == null) mView.setData(DataList.get());
            else mView.setData(dl);
            setVisibilityProgressBar(mVisible);
        }
    }

    public void search(String query) {
        this.query = query;
        setVisibilityProgressBar(ProgressBar.VISIBLE);
        filter.filter(query);
      }

    public void updateDataList(DataList dl) {
        this.dl = dl;
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
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.execute(() -> {
            dl.sortByTime();
            getData();
        });

    }

    public void sortingByAZ() {
        ExecutorService ex = Executors.newSingleThreadExecutor();
        ex.execute(() -> {
            dl.sortByAZ();
            getData();
        });
    }

    public void onItemSelected(String title, String info) {
        mView.openDetailFragment(title, info);
    }
}


