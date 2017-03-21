package antonc.rarus.twopaneapp.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


import antonc.rarus.twopaneapp.R;
import antonc.rarus.twopaneapp.ui.test_task.DetailFragment;
import antonc.rarus.twopaneapp.ui.test_task.ListFragment;


public class AppActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_list_container);

        //Если фрагмента нет в списке, то запрашиваем новую транзакцию, которая добавляет его в список
        //FragmentManager сохраняет список фрагментов, поэтому следующие строки выпролняются только при запуске приложения
        if (fragment == null) {
            fragment = new ListFragment();
            //Создать новую транзакцию фрагмента, включить в нее операцию add, а затем закрепить
            fragmentManager.beginTransaction().replace(R.id.fragment_list_container, fragment).commit();
        }
    }


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Fragment detail = fragmentManager.findFragmentByTag(DetailFragment.class.getName());
            fragmentManager.beginTransaction().remove(detail).commit();
        } else {
            super.onBackPressed();
        }
    }

}
