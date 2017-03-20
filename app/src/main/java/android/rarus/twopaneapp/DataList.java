package android.rarus.twopaneapp;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by antonc on 17.03.2017.
 */

public class DataList {
    private static DataList sDataList;
    private ArrayList<DataItem> mDataItems;

    private DataList() {
        mDataItems = new ArrayList<>();
        for(int i = 0; i<100; i++) {
            mDataItems.add(new DataItem("Text " + i));
        }
    }

    public static DataList get() {
        if(sDataList == null)
            sDataList = new DataList();
        return sDataList;
    }

    public String getTitle(int position) {
        return mDataItems.get(position).getTitle();
    }

    public int size() {
        return mDataItems.size();
    }





}
