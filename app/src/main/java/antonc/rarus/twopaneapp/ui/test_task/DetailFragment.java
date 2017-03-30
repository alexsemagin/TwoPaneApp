package antonc.rarus.twopaneapp.ui.test_task;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import antonc.rarus.twopaneapp.R;
import antonc.rarus.twopaneapp.presenter.DetailPresenter;
import antonc.rarus.twopaneapp.presenter.DetailView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment implements DetailView {
    private static final String ARG_TITLE = "title_text";
    private static final String ARG_INFO = "info_text";

    private DetailPresenter mPresenter;
    private Toolbar mToolbar;

    @BindView(R.id.text_detail)
    TextView mTextDetail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        String title = getArguments() == null ? ARG_TITLE : getArguments().getString(ARG_TITLE);
        String info = getArguments() == null ? ARG_INFO : getArguments().getString(ARG_INFO);
        mPresenter = new DetailPresenter(title, info);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(v -> getFragmentManager().beginTransaction().remove(DetailFragment.this).commit());

        mPresenter.setView(this);
        mPresenter.getData();
    }

    @Override
    public void setData(String title, String info) {
        mToolbar.setTitle(title);
        mTextDetail.setText(info);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.setView(null);
    }
}
