package antonc.rarus.twopaneapp.ui.test_task;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.materialdrawer.Drawer;

import antonc.rarus.twopaneapp.R;
import antonc.rarus.twopaneapp.presenter.RxPresenter;
import antonc.rarus.twopaneapp.presenter.RxView;
import antonc.rarus.twopaneapp.ui.AppActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RxFragment extends Fragment implements RxView {

    private RxPresenter mPresenter;


    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mPresenter = new RxPresenter();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rx, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Drawer drawer = ((AppActivity) getActivity()).getDrawer();
        drawer.setToolbar(getActivity(), toolbar, true);

        mPresenter.setView(this);
        mPresenter.getData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.setView(null);
    }
}
