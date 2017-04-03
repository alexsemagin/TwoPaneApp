package antonc.rarus.twopaneapp.ui.test_task;

import java.util.Collections;

import antonc.rarus.twopaneapp.model.entity.DataList;
import antonc.rarus.twopaneapp.presenter.ListPresenter;

public class Sorting {
    private ListPresenter mPresenter;
    private final DataList originalList;
    private final DataList filteredList;

    public Sorting(ListPresenter presenter) {
        mPresenter = presenter;
        this.originalList = mPresenter.getDataList();
        this.filteredList = DataList.getEmptyList();
    }

    public void sortByTime() {
        filteredList.clear();
        /**Collections.sort(categories, new Comparator<AnyClass>() {
            @Override
            public int compare(AnyClasslhs, AnyClassrhs) {
                return lhs.label.compareTo(rhs.label);
            }
        });*/
    }



}
