package antonc.rarus.twopaneapp.presenter;

import java.util.List;


public interface ListView {
    void setData(List myModel);
    void openDetailFragment(String title, String info);
    void setVisibilityProgressBar(int visibility);
}
