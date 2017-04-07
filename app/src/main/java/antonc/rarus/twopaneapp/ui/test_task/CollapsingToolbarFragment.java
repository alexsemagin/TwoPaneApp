package antonc.rarus.twopaneapp.ui.test_task;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mikepenz.materialdrawer.Drawer;

import antonc.rarus.twopaneapp.R;
import antonc.rarus.twopaneapp.presenter.CollapsingToolbarPresenter;
import antonc.rarus.twopaneapp.presenter.CollapsingToolbarView;
import antonc.rarus.twopaneapp.ui.AppActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CollapsingToolbarFragment extends Fragment implements CollapsingToolbarView {
    private static final String ARG_TITLE = "title_text";
    private CollapsingToolbarPresenter mPresenter;


    @BindView(R.id.toolbar_collapsing_fragment)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.backdrop)
    ImageView imageView;
    @BindView(R.id.floating_action_button)
    FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        String title = getArguments() == null ? ARG_TITLE : getArguments().getString(ARG_TITLE);
        mPresenter = new CollapsingToolbarPresenter(title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collapsing_toolbar, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Drawer drawer = ((AppActivity) getActivity()).getDrawer();
        drawer.setToolbar(getActivity(), toolbar, true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);

        imageView = (ImageView) view.findViewById(R.id.backdrop);
        imageView.setImageResource(R.drawable.backdrop);

        fab = (FloatingActionButton) view.findViewById(R.id.floating_action_button);
        fab.setImageResource(R.drawable.ic_fab);

        mPresenter.setView(this);
        mPresenter.getData();
    }


    @Override
    public void setData(String title) {
        collapsingToolbarLayout.setTitle(title);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.setView(null);
    }


}
