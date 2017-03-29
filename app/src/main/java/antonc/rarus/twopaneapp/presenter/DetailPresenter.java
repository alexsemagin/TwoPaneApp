package antonc.rarus.twopaneapp.presenter;

public class DetailPresenter {
    private DetailView mView;
    private String mTitle;
    private String mInfo;

    public DetailPresenter(String title, String info) {
        mTitle = title;
        mInfo = info;
    }

    public void setView(DetailView view) {
        mView = view;
    }

    public void getData() {
        if (mView!=null)
            mView.setData(mTitle, mInfo);
    }
}
