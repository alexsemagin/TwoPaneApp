package android.rarus.twopaneapp.ui.test_task;

import android.content.Context;
import android.rarus.twopaneapp.model.entity.DataList;
import android.rarus.twopaneapp.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public  class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private DataList mStringList;
    private ViewHolder mViewHolder;
    private Context mContext;
    private OnItemSelected itemSelected;

    public interface OnItemSelected {
        void onItemSelected(String title, String info);
    }



    public  RecyclerAdapter(Context context, DataList strings, OnItemSelected onItemSelected) {
        mContext = context;
        mStringList = strings;
        itemSelected = onItemSelected;
    }

    //Связывает представление view c объектом модели
    //При вызове получает ViewHolder и позицию в наборе данных
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextViewTitle.setText(mStringList.getTitle(position));
        holder.mTextViewInfo.setText(mStringList.getInfo(position));
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


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTextViewTitle;
        public TextView mTextViewInfo;




        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.title_text);
            mTextViewInfo = (TextView) itemView.findViewById(R.id.info_text);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TextView textViewTitle = (TextView) v.findViewById(R.id.title_text);
            TextView textViewInfo = (TextView) v.findViewById(R.id.info_text);
            String title = textViewTitle.getText().toString();
            String info = textViewInfo.getText().toString();
            itemSelected.onItemSelected(title, info);
        }


    }

}