package antonc.rarus.twopaneapp.presenter;

import antonc.rarus.twopaneapp.model.entity.DataList;

public interface ListView {
    void setData(DataList dataList);
    void openDetailFragment(String title, String info);
    void setVisibilityProgressBar(int visibility);
}
