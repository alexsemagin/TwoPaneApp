package android.rarus.twopaneapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by antonc on 14.03.2017.
 */

public class DetailFragment extends Fragment {

    private TextView mTextView;
    private String mText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_detail, container, false);
        setRetainInstance(true);
        mTextView = (TextView) view.findViewById(R.id.text_detail);

        if(mText != null)
        mTextView.setText(mText);
        else mTextView.setText("Текст 0");

        return view;
    }

    public void updateText(String text) {
        mText = text;
        mTextView.setText(text);
    }
}
