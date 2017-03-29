package antonc.rarus.twopaneapp.presenter;

import antonc.rarus.twopaneapp.model.entity.DataList;
import antonc.rarus.twopaneapp.ui.test_task.RecyclerAdapter;

public class ListPresenter {

    private ListView mView;
    private RecyclerAdapter mAdapter;
    private DataList mDataList;

    public ListPresenter(RecyclerAdapter adapter) {
        mAdapter = adapter;
    }

    public void setView(ListView view) {
        mView = view;
    }

    public void getData() {
        if (mView != null) {
            mDataList = DataList.get();
            mView.setData(mDataList);
        }

    }

    public void onItemSelected(String title, String info) {
         mView.openDetailFragment(title, info);
    }

}
