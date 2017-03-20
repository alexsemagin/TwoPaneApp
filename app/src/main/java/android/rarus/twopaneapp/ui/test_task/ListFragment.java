package android.rarus.twopaneapp.ui.test_task;

import android.os.Bundle;
import android.rarus.twopaneapp.model.entity.DataList;
import android.rarus.twopaneapp.R;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private static final String TAG_FRAGMENT = "tag_detail_fragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final RecyclerAdapter adapter = new RecyclerAdapter(getContext(), DataList.get(), this);
        mRecyclerView.setAdapter(adapter);
    }


    public void onItemSelected(String title, String info) {
        DetailFragment detailFragment = new DetailFragment();

        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment_detail_container, detailFragment, TAG_FRAGMENT).commit();
        detailFragment.updateText(title, info);


    }
}
