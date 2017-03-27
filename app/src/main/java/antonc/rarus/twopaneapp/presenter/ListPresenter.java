package antonc.rarus.twopaneapp.presenter;

import antonc.rarus.twopaneapp.model.entity.DataList;

public class ListPresenter {

    private ListView mView;

    public void setView(ListView view) {
        mView = view;
    }

    public void getData() {
        if (mView!=null)
            mView.setData(DataList.get());
    }

    public void onItemSelected(String title, String info) {
         mView.openDetailFragment(title, info);
    }
}
