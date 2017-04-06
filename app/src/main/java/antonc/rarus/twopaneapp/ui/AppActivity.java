package antonc.rarus.twopaneapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

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
import antonc.rarus.twopaneapp.ui.test_task.DetailFragment;
import antonc.rarus.twopaneapp.ui.test_task.ListFragment;


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
                        new PrimaryDrawerItem().withName(R.string.drawer_item_free_play).withIcon(FontAwesome.Icon.faw_gamepad).withIdentifier(2),
                        new SectionDrawerItem().withName(R.string.drawer_item_settings),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_cog),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_github)
                )
                .withOnDrawerItemClickListener((view1, position, drawerItem) -> {
                    if (drawerItem != null) {
                        switch ((int) drawerItem.getIdentifier()) {
                            case 1: {
                                Fragment fragment = fragmentManager.findFragmentByTag(ListFragment.class.getName());
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
                                /*if (fragmentManager.findFragmentByTag(CollapsingToolbarFragment.class.getName()) == null) {*/
                                    CollapsingToolbarFragment collapsingToolbarFragment = new CollapsingToolbarFragment();
                                    fragmentManager.beginTransaction().replace(antonc.rarus.twopaneapp.R.id.fragment_container, collapsingToolbarFragment, CollapsingToolbarFragment.class.getName()).commit();
                                //}
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

        //Если фрагмента нет в списке, то запрашиваем новую транзакцию, которая добавляет его в список
        //FragmentManager сохраняет список фрагментов, поэтому следующие строки выпролняются только при запуске приложения
        if (fragment == null) {
            fragment = new ListFragment();
            //Создать новую транзакцию фрагмента, включить в нее операцию add, а затем закрепить
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
        Fragment fragment;

        if (mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        } else if (fragmentManager.findFragmentByTag(DetailFragment.class.getName()) != null) {
            fragment = fragmentManager.findFragmentByTag(DetailFragment.class.getName());
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                fragmentManager.beginTransaction().remove(fragment).commit();
            else {
                super.onBackPressed();
            }
        } else if (fragmentManager.findFragmentByTag(CollapsingToolbarFragment.class.getName()) != null) {
            fragment = fragmentManager.findFragmentByTag(CollapsingToolbarFragment.class.getName());
            fragmentManager.beginTransaction().remove(fragment).commit();

        } else super.onBackPressed();
    }

}
