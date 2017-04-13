package antonc.rarus.twopaneapp.ui.test_task;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.mikepenz.materialdrawer.Drawer;

import antonc.rarus.twopaneapp.R;
import antonc.rarus.twopaneapp.presenter.MapPresenter;
import antonc.rarus.twopaneapp.presenter.MapView;
import antonc.rarus.twopaneapp.ui.AppActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MapFragment extends Fragment implements MapView, OnMapReadyCallback {

    private MapPresenter mPresenter;
    private GoogleMap mMap;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mPresenter = new MapPresenter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapFragment.this);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        Drawer drawer = ((AppActivity) getActivity()).getDrawer();
        toolbar.setTitle("Карта");
        drawer.setToolbar(getActivity(), toolbar, true);


        mPresenter.setView(this);
        mPresenter.getData();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d("TEST", "Its ok");
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void onCreateOptionsMenu() {
        //Menu menu = toolbar.getMenu();

       // menu.


        /*MenuItem normTypeMenuItem = menu.findItem(R.id.action_type_norm);
        normTypeMenuItem.setOnMenuItemClickListener(item -> {

            return false;
        });

        MenuItem sortTimeMenuItem = menu.findItem(R.id.action_type_hybrid);
        sortTimeMenuItem.setOnMenuItemClickListener(item -> {

            return false;
        });

        MenuItem sortTimeMenuItem = menu.findItem(R.id.action_type_satellite);
        sortTimeMenuItem.setOnMenuItemClickListener(item -> {

            return false;
        });


        MenuItem sortTimeMenuItem = menu.findItem(R.id.action_type_terrain);
        sortTimeMenuItem.setOnMenuItemClickListener(item -> {

            return false;
        });*/
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.setView(null);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //mMap.setMapType();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

        } else {
            mMap.setMyLocationEnabled(true);
        }
    }

}
