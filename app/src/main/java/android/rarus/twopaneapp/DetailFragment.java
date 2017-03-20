package android.rarus.twopaneapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailFragment extends Fragment implements OnBackPressedListener {

    private TextView mTextView;
    private String mText;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_detail, container, false);
        setRetainInstance(true);

        mTextView = (TextView) view.findViewById(R.id.text_detail);

        return view;
    }

    public void updateText(String text) {
        mText = text;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView.setText(mText);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().remove(this).commit();


        Fragment fragment =  getFragmentManager().findFragmentById(R.id.fragment_list_container);
        if(fragment.isHidden()) {
        getFragmentManager().beginTransaction().show(fragment).commit();}
    }
}
