package antonc.rarus.twopaneapp.presenter;


public class DetailPresenter {
    private DetailView mView;

    public void setView(DetailView view) {
        mView = view;
    }

    public void setData(String title, String info) {
        if (mView!=null)
            mView.setData(title, info);
    }
}
