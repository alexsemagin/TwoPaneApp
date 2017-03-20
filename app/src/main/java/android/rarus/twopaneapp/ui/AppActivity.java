package android.rarus.twopaneapp.ui;

import android.content.res.Configuration;
import android.rarus.twopaneapp.ui.test_task.CallbacksClick;
import android.rarus.twopaneapp.ui.test_task.DetailFragment;
import android.rarus.twopaneapp.ui.test_task.ListFragment;
import android.rarus.twopaneapp.OnBackPressedListener;
import android.rarus.twopaneapp.R;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AppActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_list_container);

        //Если фрагмента нет в списке, то запрашиваем новую транзакцию, которая добавляет его в список
        //FragmentManager сохраняет список фрагментов, поэтому следующие строки выпролняются только при запуске приложения
        if(fragment == null) {
            fragment = new ListFragment();
            //Создать новую транзакцию фрагмента, включить в нее операцию add, а затем закрепить
            fragmentManager.beginTransaction().add(R.id.fragment_list_container, fragment).commit();
       }
    }


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Fragment detail = fragmentManager.findFragmentByTag("tag_detail_fragment");
            fragmentManager.beginTransaction().remove(detail).commit();
        } else {
            super.onBackPressed();
        }
    }

}
