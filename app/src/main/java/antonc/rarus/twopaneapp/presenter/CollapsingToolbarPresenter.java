package antonc.rarus.twopaneapp.presenter;

public class CollapsingToolbarPresenter {
    private CollapsingToolbarView mView;
    private String mTitle;

    public CollapsingToolbarPresenter(String title) {
        mTitle = title;
    }

    public void setView(CollapsingToolbarView view) {
        mView = view;
    }

    public void getData() {
        if (mView!=null)
            mView.setData(mTitle);
    }


}
