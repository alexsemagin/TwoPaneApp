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


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {
    private DataList mStringList;
    private ViewHolder mViewHolder;
    private Context mContext;
    private OnItemSelected itemSelected;


    public interface OnItemSelected {
        void onItemSelected(String title, String info);
    }


    public RecyclerAdapter(Context context, OnItemSelected onItemSelected) {
        mContext = context;
        itemSelected = onItemSelected;
    }

    public void setList(DataList strings) {
        mStringList = strings;
        notifyDataSetChanged();
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



}