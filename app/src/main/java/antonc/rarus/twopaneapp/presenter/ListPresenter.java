package antonc.rarus.twopaneapp.presenter;

import android.widget.ProgressBar;

import antonc.rarus.twopaneapp.model.entity.DataList;
import antonc.rarus.twopaneapp.ui.test_task.MyFilter;
/*
Сортирвока реверс и по времени
меню справа от поиска
 */

public class ListPresenter {

    private ListView mView;
    private String query;
    private DataList dl;
    private MyFilter filter;
    private int mVisible;

    public ListPresenter() {
        filter = new MyFilter(this);
        mVisible = ProgressBar.INVISIBLE;
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
        mView.setVisibilityProgessBar(visible);
        mVisible = visible;
    }

    public void onDestroyView() {
        setView(null);
    }

    public void sortingAZ() {

    }

    public void onItemSelected(String title, String info) {
        mView.openDetailFragment(title, info);
    }
}


