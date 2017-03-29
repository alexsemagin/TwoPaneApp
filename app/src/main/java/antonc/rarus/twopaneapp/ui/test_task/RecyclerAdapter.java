package antonc.rarus.twopaneapp.ui.test_task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import antonc.rarus.twopaneapp.R;
import antonc.rarus.twopaneapp.model.entity.DataList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {
    private DataList mStringList;
    private ViewHolder mViewHolder;
    private Context mContext;
    private OnItemSelected itemSelected;
    private MyFilter filter;

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new MyFilter(this);
        }
        return filter;
    }

    public interface OnItemSelected {
        void onItemSelected(String title, String info);
    }


    public RecyclerAdapter(Context context, OnItemSelected onItemSelected) {
        mContext = context;
        itemSelected = onItemSelected;
    }

    public void setList(DataList strings) {
        mStringList = strings;
    }


    //Связывает представление view c объектом модели
    //При вызове получает ViewHolder и позицию в наборе данных
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewTitle.setText(mStringList.getTitle(position));
        String info = mStringList.getInfo(position);
        holder.textViewInfo.setVisibility(info.equals("") ? View.GONE : View.VISIBLE);
        holder.textViewInfo.setText(mStringList.getInfo(position));
        holder.imageView.setImageResource(R.mipmap.ic_face);
    }


    //Вызывается виджетов RecyclerView, когда ему потребуется новое представлние для
    //отображения элемента. Мы создаем объект view и упаковываем его в ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }


    //Метод для получения количества элементов
    @Override
    public int getItemCount() {
        return mStringList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_face)
        ImageView imageView;
        @BindView(R.id.title_text)
        TextView textViewTitle;
        @BindView(R.id.info_text)
        TextView textViewInfo;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.list_item)
        public void onClick(View view) {
            TextView textViewTitle = ButterKnife.findById(view, R.id.title_text);
            TextView textViewInfo = ButterKnife.findById(view, R.id.info_text);
            String title = textViewTitle.getText().toString();
            String info = textViewInfo.getText().toString();
            itemSelected.onItemSelected(title, info);

        }
    }

    private static class MyFilter extends Filter {

        private final RecyclerAdapter adapter;
        private final DataList originalList;
        private final DataList filteredList;

        private MyFilter(RecyclerAdapter adapter) {
            super();
            this.adapter = adapter;
            this.originalList = DataList.get();
            this.filteredList = DataList.getEmptyList();
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
           // results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            adapter.setList((DataList) filterResults.values);
            adapter.notifyDataSetChanged();
        }
    }

}