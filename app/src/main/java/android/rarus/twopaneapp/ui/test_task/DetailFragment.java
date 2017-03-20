package android.rarus.twopaneapp.ui.test_task;

import android.os.Bundle;
import android.rarus.twopaneapp.R;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class DetailFragment extends Fragment   {

    private String mTitle;
    private String mInfo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    public void updateText(String title, String info) {
        mTitle = title;
        mInfo = info;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_detail);
        toolbar.setTitle(mTitle);

        TextView textViewDetail = (TextView) view.findViewById(R.id.text_detail);
        textViewDetail.setText(mInfo);
    }

   /* @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().remove(this).commit();
    }*/


}
