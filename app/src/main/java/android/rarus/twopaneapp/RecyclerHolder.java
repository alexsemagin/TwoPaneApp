package android.rarus.twopaneapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class RecyclerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTextView;
    private CallbacksClick mCallbacksClick;


    public  RecyclerHolder(View itemView, Context context) {
        super(itemView);
       // mTextView = (TextView) itemView;
        mTextView = (TextView) itemView.findViewById(R.id.info_text);

        if(context instanceof CallbacksClick)
            mCallbacksClick = (CallbacksClick) context;
        itemView.setOnClickListener(this);

    }

    public TextView getTextView() {
        return  mTextView;
    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView) v.findViewById(R.id.info_text);
        String text = textView .getText().toString();
        mCallbacksClick.onItemSelected(text);
    }
}
