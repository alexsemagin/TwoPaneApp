package android.rarus.twopaneapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;



public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTextView;
    private Callbacks mCallbacks;


    public  RecyclerHolder(View itemView, Context context) {
        super(itemView);
        mTextView = (TextView) itemView;
        if(context instanceof Callbacks)
            mCallbacks = (Callbacks) context;
        itemView.setOnClickListener(this);

    }

    public TextView getView() {
        return  mTextView;
    }

    @Override
    public void onClick(View v) {
        String text = ((TextView) v).getText().toString();
        mCallbacks.onItemSelected(text);
    }
}
