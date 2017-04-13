package antonc.rarus.twopaneapp.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.List;

import antonc.rarus.twopaneapp.R;
import antonc.rarus.twopaneapp.ui.test_task.CollapsingToolbarFragment;
import antonc.rarus.twopaneapp.ui.test_task.ListFragment;
import antonc.rarus.twopaneapp.ui.test_task.MapFragment;


public class AppActivity extends AppCompatActivity {

    private Drawer mDrawer;

    private int selection;
    private static final String SELECTION = "selection";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);


        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) selection = savedInstanceState.getInt(SELECTION);
        else selection = 1;


        IProfile profile = new ProfileDrawerItem().withName("Anton Chernov").withEmail("antonc@rarus.ru").withIcon(getResources().getDrawable(R.drawable.profile));

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withSavedInstance(savedInstanceState)
                .addProfiles(profile).build();


        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.drawer_header)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_collapsing_toolbar).withIcon(FontAwesome.Icon.faw_gamepad).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_map).withIcon(FontAwesome.Icon.faw_map).withIdentifier(3),
                        new SectionDrawerItem().withName(R.string.drawer_item_settings),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_cog),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_github)
                )
                .withOnDrawerItemClickListener((view1, position, drawerItem) -> {
                    if (drawerItem != null) {
                        Fragment fragment;
                        switch ((int) drawerItem.getIdentifier()) {
                            case 1: {
                                fragment = fragmentManager.findFragmentByTag(ListFragment.class.getName());
                                List<Fragment> list = fragmentManager.getFragments();
                                if (list != null) {
                                    for (Fragment f : list) {
                                        if (f != fragment && f != null) {
                                            fragmentManager.beginTransaction().remove(f).commit();
                                        }
                                    }
                                }
                                break;
                            }
                            case 2: {
                                fragment = new CollapsingToolbarFragment();
                                fragmentManager.beginTransaction().replace(antonc.rarus.twopaneapp.R.id.fragment_container, fragment, CollapsingToolbarFragment.class.getName()).commit();
                                break;
                            }

                            case 3: {
                                fragment = new MapFragment();
                                fragmentManager.beginTransaction().replace(antonc.rarus.twopaneapp.R.id.fragment_container, fragment, MapFragment.class.getName()).commit();
                                break;
                            }
                        }
                    }
                    return false;
                })
                .withSavedInstance(savedInstanceState)
                .build();

        mDrawer.setSelection(selection);


        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_list_container);
        if (fragment == null) {
            fragment = new ListFragment();
            fragmentManager.beginTransaction().replace(R.id.fragment_list_container, fragment, ListFragment.class.getName()).commit();
        }

    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        selection = mDrawer.getCurrentSelectedPosition();
        outState.putInt(SELECTION, selection);
    }

    public Drawer getDrawer() {
        return mDrawer;
    }


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (mDrawer != null && mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        } else if (fragmentManager.findFragmentByTag(MapFragment.class.getName()) != null) {
            mDrawer.setSelection(1);
        } else if (fragmentManager.findFragmentByTag(CollapsingToolbarFragment.class.getName()) != null
                && getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mDrawer.setSelection(1);
        } else super.onBackPressed();
    }

}
