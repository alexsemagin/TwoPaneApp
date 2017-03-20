package android.rarus.twopaneapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public  class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private DataList mStringList;
    private RecyclerHolder mRecyclerHolder;
    private Context mContext;
    private TextView mTextView;


    public  RecyclerAdapter(Context context, DataList strings) {
        mStringList = strings;
        mContext = context;
    }

    //Связывает представление view c объектом модели
    //При вызове получает ViewHolder и позицию в наборе данных
    @Override
    public void onBindViewHolder(android.rarus.twopaneapp.RecyclerHolder holder, int position) {
        mTextView= holder.getTextView();
        mTextView.setText(mStringList.getTitle(position));
    }


    //Вызывается виджетов RecyclerView, когда ему потребуется новое представлние для
    //отображения элемента. Мы создаем объект view и упаковываем его в ViewHolder
    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        mRecyclerHolder = new RecyclerHolder(view, mContext);
        return mRecyclerHolder;
    }



    //Метод для получения количества элементов
    @Override
    public int getItemCount() {
        return mStringList.size();
    }



}