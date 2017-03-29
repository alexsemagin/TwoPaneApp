package antonc.rarus.twopaneapp.model.entity;

import java.util.ArrayList;


public class DataList {
    private static DataList sDataList;
    private ArrayList<DataItem> mDataItems;

    private static String title = "TITLE";
    private static String info = "INFO";

    private DataList() {
        mDataItems = new ArrayList<>();
    }

    private void initialization() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                mDataItems.add(new DataItem(title + i, info + i));
            else mDataItems.add(new DataItem(title + i, ""));
        }
    }

    public static DataList get() {
        if (sDataList == null) {
            sDataList = new DataList();
            sDataList.initialization();
        }
        return sDataList;
    }

    public static DataList getEmptyList() {
        return new DataList();
    }

    public void add(String title, String info) {
        mDataItems.add(new DataItem(title, info));
    }

    public void addAll(DataList dl) {
        for (int i = 0; i < dl.size(); i++) {
            add(dl.getTitle(i), dl.getInfo(i));
        }
    }

    public void clear() {
        mDataItems.clear();
    }

    public String getTitle(int position) {
        return mDataItems.get(position).mTitle;
    }

    public String getInfo(int position) {
        return mDataItems.get(position).mInfo;
    }


    public int size() {
        return mDataItems.size();
    }


    private class DataItem {
        private String mTitle;
        private String mInfo;

        public DataItem(String title, String info) {
            mTitle = title;
            mInfo = info;
        }
    }
}
