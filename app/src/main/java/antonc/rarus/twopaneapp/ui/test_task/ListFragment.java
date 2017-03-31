package antonc.rarus.twopaneapp.ui.test_task;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import antonc.rarus.twopaneapp.R;
import antonc.rarus.twopaneapp.model.entity.DataList;
import antonc.rarus.twopaneapp.presenter.ListPresenter;
import antonc.rarus.twopaneapp.presenter.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ListFragment extends Fragment implements ListView, RecyclerAdapter.OnItemSelected, SearchView.OnQueryTextListener{
    private static final String ARG_TITLE = "title_text";
    private static final String ARG_INFO = "info_text";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private RecyclerAdapter mAdapter;
    private ListPresenter mPresenter;
    private ProgressBar mProgressBar;

    private Toolbar mToolbar;

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



        //Скрываем toolbar Listfragment в LANDSCAPE ориентации
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.app_bar_list);
            appBarLayout.setVisibility(AppBarLayout.GONE);
        }


        mToolbar = ButterKnife.findById(getActivity(),R.id.toolbar );
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());
        mToolbar.inflateMenu(R.menu.menu_toolbar);
        onCreateOptionsMenu();

        ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        mPresenter.setView(this);
        mPresenter.getData();
    }


    public void onCreateOptionsMenu() {
        Menu menu = mToolbar.getMenu();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        ImageView searchCloseIcon = (ImageView)searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchCloseIcon.setImageResource(R.drawable.ic_close);
    }


    @Override
    public void setData(DataList dl) {
        mAdapter.setList(dl);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDestroyView();
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


    public void setVisibilityProgessBar(int visibility) {
        mProgressBar.setVisibility(visibility);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        mPresenter.search(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mPresenter.search(newText);
        return false;
    }
}
