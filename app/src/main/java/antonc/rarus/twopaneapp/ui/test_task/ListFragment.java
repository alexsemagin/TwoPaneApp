package antonc.rarus.twopaneapp.ui.test_task;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import antonc.rarus.twopaneapp.R;
import antonc.rarus.twopaneapp.model.entity.DataList;
import antonc.rarus.twopaneapp.presenter.ListPresenter;
import antonc.rarus.twopaneapp.presenter.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ListFragment extends Fragment implements ListView, RecyclerAdapter.OnItemSelected {
    private static final String ARG_TITLE = "title_text";
    private static final String ARG_INFO = "info_text";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerAdapter mAdapter;
    private ListPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mAdapter = new RecyclerAdapter(getContext(), this);
        mPresenter = new ListPresenter();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener( v -> getActivity().finish());
        ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.setView(this);
        mPresenter.getData();
    }


    @Override
    public void setData(DataList dl) {
        mAdapter.setList(dl);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.setView(null);
    }

    @Override
    public void onItemSelected(String title, String info) {
        mPresenter.onItemSelected(title, info);
    }


    public void openDetailFragment(String title, String info) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_INFO, info);

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(args);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(antonc.rarus.twopaneapp.R.id.fragment_detail_container, detailFragment, DetailFragment.class.getName()).commit();
    }
}
