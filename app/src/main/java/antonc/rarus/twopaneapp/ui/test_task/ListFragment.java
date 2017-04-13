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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.holder.StringHolder;

import java.util.List;

import antonc.rarus.twopaneapp.R;
import antonc.rarus.twopaneapp.presenter.ListPresenter;
import antonc.rarus.twopaneapp.presenter.ListView;
import antonc.rarus.twopaneapp.ui.AppActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ListFragment extends Fragment implements ListView, RecyclerAdapter.OnItemSelected, SearchView.OnQueryTextListener {
    private static final String ARG_TITLE = "title_text";
    private static final String ARG_INFO = "info_text";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private Toolbar toolbar;

    private RecyclerAdapter mAdapter;
    private ListPresenter mPresenter;
    private ProgressBar mProgressBar;

    private Drawer mDrawer;

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

        ButterKnife.bind(this, view);

        //Скрываем toolbar Listfragment в LANDSCAPE ориентации
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(R.id.app_bar_list);
            appBarLayout.setVisibility(AppBarLayout.GONE);
        }

        toolbar = ButterKnife.findById(getActivity(),R.id.toolbar_list );
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.menu_toolbar);

        mDrawer = ((AppActivity) getActivity()).getDrawer();
        mDrawer.setToolbar(getActivity(), toolbar);

        onCreateOptionsMenu();


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);


        mPresenter.setView(this);
        mPresenter.getData();
    }


    private void onCreateOptionsMenu() {
        Menu menu = toolbar.getMenu();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(this);


        MenuItem sortAZMenuItem = menu.findItem(R.id.action_sort_az);
        sortAZMenuItem.setOnMenuItemClickListener(item -> {
            mPresenter.sortingByAZ();
            return false;
        });

        MenuItem sortTimeMenuItem = menu.findItem(R.id.action_sort_time);
        sortTimeMenuItem.setOnMenuItemClickListener(item -> {
            mPresenter.sortingByTime();
            return false;
        });
    }


    @Override
    public void setData(List dataItems) {
        getActivity().runOnUiThread(() -> {
            mAdapter.setList(dataItems);
            mDrawer.updateBadge(1, new StringHolder(String.valueOf(dataItems.size())));
        });
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

        CollapsingToolbarFragment collapsingToolbarFragment = new CollapsingToolbarFragment();
        collapsingToolbarFragment.setArguments(args);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(antonc.rarus.twopaneapp.R.id.fragment_detail_container, collapsingToolbarFragment, CollapsingToolbarFragment.class.getName()).commit();
    }


    public void setVisibilityProgressBar(int visibility) {
        getActivity().runOnUiThread(() ->
                mProgressBar.setVisibility(visibility));
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
