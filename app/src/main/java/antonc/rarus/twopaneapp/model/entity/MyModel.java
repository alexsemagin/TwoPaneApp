package antonc.rarus.twopaneapp.model.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MyModel {
    private static ArrayList<DataItem> mDataItems;


    private MyModel() {
        mDataItems = new ArrayList<>();
        initialization();
    }


    public static ArrayList<DataItem> get() {
        if (mDataItems == null)
            new MyModel();
        return mDataItems;
    }

    private void initialization() {
        String title = "TITLE";
        String info = "INFO";
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                mDataItems.add(new DataItem(title + i, info + i, 0));
            else mDataItems.add(new DataItem(title + i, ""));
        }
    }
}
