package antonc.rarus.twopaneapp.ui.test_task;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import antonc.rarus.twopaneapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {
    private static final String ARG_TITLE = "title_text";
    private static final String ARG_INFO = "info_text";

    @BindView(R.id.text_detail)
    TextView mTextDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_detail);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener( v -> getFragmentManager().beginTransaction().remove(DetailFragment.this).commit());
        toolbar.setTitle(getArguments() == null? ARG_TITLE :getArguments().getString(ARG_TITLE));
        mTextDetail.setText(getArguments() == null? ARG_INFO :getArguments().getString(ARG_INFO));
    }
}
