package antonc.rarus.twopaneapp.ui.test_task;

import android.os.Bundle;


import antonc.rarus.twopaneapp.R;
import antonc.rarus.twopaneapp.model.entity.DataList;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ListFragment extends Fragment implements RecyclerAdapter.OnItemSelected {
    private static final String ARG_TITLE = "title_text";
    private static final String ARG_INFO = "info_text";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerAdapter mAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mAdapter = new RecyclerAdapter(getContext(), this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(antonc.rarus.twopaneapp.R.string.app_name);
        ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setList(DataList.get(getActivity()));
    }


    @Override
    public void onItemSelected(String title, String info) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_INFO, info);

        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(antonc.rarus.twopaneapp.R.id.fragment_detail_container, detailFragment, DetailFragment.class.getName()).commit();
    }
}
