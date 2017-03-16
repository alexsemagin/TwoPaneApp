package android.rarus.twopaneapp;

import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AppActivity extends AppCompatActivity implements Callbacks{

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

       /* DetailFragment detailFragment = (DetailFragment) mFragmentManager.findFragmentById(R.id.fragment_detail_container);
        if(detailFragment == null) {
            detailFragment = new DetailFragment();
            //Создать новую транзакцию фрагмента, включить в нее операцию add, а затем закрепить
            mFragmentManager.beginTransaction().replace(R.id.fragment_detail_container, detailFragment).commit();
        }*/
    }


    @Override
    public void onItemSelected(String text) {
        DetailFragment detailFragment = (DetailFragment) mFragmentManager.findFragmentById(R.id.fragment_detail_container);
        detailFragment.updateText(text);
    }

    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

}
