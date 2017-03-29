package antonc.rarus.twopaneapp.presenter;

import android.widget.Filter;

import antonc.rarus.twopaneapp.model.entity.DataList;
import antonc.rarus.twopaneapp.ui.test_task.MyFilter;
import antonc.rarus.twopaneapp.ui.test_task.RecyclerAdapter;

public class ListPresenter {

    private ListView mView;
    private RecyclerAdapter mAdapter;
    private String query;
    private DataList dl;
    private MyFilter filter;

    public ListPresenter(RecyclerAdapter adapter) {
        mAdapter = adapter;
    }

    public void setView(ListView view) {
        mView = view;
    }

    public void getData() {
        if (mView != null) {
            if (dl == null) mView.setData(DataList.get());
            else mView.setData(dl);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void search(String query) {
        this.query = query;
        getFilter().filter(query);
        getData();
    }

    public void updateDataList(DataList dl) {
        this.dl = dl;
    }

    public Filter getFilter() {
        if (filter == null) {
            filter = new MyFilter(this);
        }
        return filter;
    }

    public void onItemSelected(String title, String info) {
         mView.openDetailFragment(title, info);
    }

}
