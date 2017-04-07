package antonc.rarus.twopaneapp.ui.test_task;

import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

import antonc.rarus.twopaneapp.model.entity.DataItem;
import antonc.rarus.twopaneapp.model.entity.MyModel;
import antonc.rarus.twopaneapp.presenter.ListPresenter;


public class MyFilter extends Filter {


    private List <DataItem> originalList;
    private List <DataItem> filteredList;
    private ListPresenter presenter;

    public MyFilter(ListPresenter presenter) {
        super();
        this.originalList =  MyModel.get();
        this.filteredList = new ArrayList<>();
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
                if (originalList.get(i).getTitle().toLowerCase().contains(filterPattern) || originalList.get(i).getInfo().toLowerCase().contains(filterPattern)) {
                    filteredList.add(new DataItem(originalList.get(i).getTitle(), originalList.get(i).getInfo(), originalList.get(i).getTimeLong()));
                }
            }
        }
        results.values = filteredList;
        return results;
    }



    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        presenter.updateDataList((List) filterResults.values);
    }
}