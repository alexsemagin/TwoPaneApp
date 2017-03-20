package android.rarus.twopaneapp;

import android.content.res.Configuration;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AppActivity extends AppCompatActivity implements CallbacksClick {

    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        mFragmentManager = getSupportFragmentManager();
        Fragment fragment = mFragmentManager.findFragmentById(R.id.fragment_list_container);

        //Если фрагмента нет в списке, то запрашиваем новую транзакцию, которая добавляет его в список
        if(fragment == null) {
            fragment = new ListFragment();
            //Создать новую транзакцию фрагмента, включить в нее операцию add, а затем закрепить
            mFragmentManager.beginTransaction().add(R.id.fragment_list_container, fragment).commit();
       }
    }


    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onItemSelected(String text) {
        DetailFragment detailFragment = new DetailFragment();

        mFragmentManager.beginTransaction().replace(R.id.fragment_detail_container, detailFragment).commit();
        detailFragment.updateText(text);

        if(isPortraitOrientation()) {
            Fragment fragment = mFragmentManager.findFragmentById(R.id.fragment_list_container);
            mFragmentManager.beginTransaction().hide(fragment).commit();
        }

    }

    @Override
    public void onBackPressed() {
        mFragmentManager = getSupportFragmentManager();
        OnBackPressedListener backPressedListener = null;
        for (Fragment fragment: mFragmentManager.getFragments()) {
            if (fragment instanceof  OnBackPressedListener) {
                backPressedListener = (OnBackPressedListener) fragment;
                break;
            }
        }

        if (backPressedListener != null) {
            backPressedListener.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    private boolean isPortraitOrientation(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return true;
        else
            return false;

    }

}
