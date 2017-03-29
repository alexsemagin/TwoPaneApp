package antonc.rarus.twopaneapp.ui.test_task;

import android.widget.Filter;

import antonc.rarus.twopaneapp.model.entity.DataList;
import antonc.rarus.twopaneapp.presenter.ListPresenter;


public class MyFilter extends Filter {


    private final DataList originalList;
    private final DataList filteredList;
    private ListPresenter presenter;

    public MyFilter(ListPresenter presenter) {
        super();
        this.originalList = DataList.get();
        this.filteredList = DataList.getEmptyList();
        this.presenter = presenter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        filteredList.clear();
        final FilterResults results = new FilterResults();

        if (charSequence.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            final String filterPattern = charSequence.toString().toLowerCase().trim();


            for (int i = 0; i < originalList.size(); i++) {

                if (originalList.getTitle(i).toLowerCase().contains(filterPattern) || originalList.getInfo(i).toLowerCase().contains(filterPattern)) {
                    filteredList.add(originalList.getTitle(i), originalList.getInfo(i));
                }
            }
        }

        results.values = filteredList;
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        presenter.updateDataList((DataList) filterResults.values);
    }
}