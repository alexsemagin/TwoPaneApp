package antonc.rarus.twopaneapp.model.entity;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import antonc.rarus.twopaneapp.R;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by antonc on 17.03.2017.
 */

public class DataList {
    private static DataList sDataList;
    private ArrayList<DataItem> mDataItems;
    private static Activity mContext;

    @BindString(R.string.title) String title;
    @BindString(R.string.info) String info;

    private DataList() {
        ButterKnife.bind(this, mContext);
        mDataItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                mDataItems.add(new DataItem(title+i,info + i));
            else mDataItems.add(new DataItem(title + i, ""));
        }
    }

    public static DataList get(Activity context) {
        if (sDataList == null) {
            mContext = context;
            sDataList = new DataList();
        }
        return sDataList;
    }

    public String getTitle(int position) {
        return mDataItems.get(position).mTitle;
    }

    public String getInfo(int position) {
        return mDataItems.get(position).mInfo;
    }

    /*public String getTime(int position) {
        return mDataItems.get(position).mTime;
    }*/

    public int size() {
        return mDataItems.size();
    }


    private class DataItem {
        private String mTitle;
        private String mInfo;
       // private String mTime;

        public DataItem(String title, String info) {
            mTitle = title;
            mInfo = info;
           // mTime = time;
        }
    }
}
