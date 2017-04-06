package antonc.rarus.twopaneapp.model.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class DataList {
    private static DataList sDataList;
    private ArrayList<DataItem> mDataItems;

    private final SimpleDateFormat mFormat =
            new SimpleDateFormat("HH:mm:ss");

    private boolean az;
    private boolean time;

    private DataList() {
        mDataItems = new ArrayList<>();
        az = false;
        time = false;
    }

    private void initialization() {
        String title = "TITLE";
        String info = "INFO";
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                add(title + i, info + i);
            else add(title + i, "");
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


    private String getStringTime(long lTime) {
        Date now = new Date();
        now.setTime(lTime);
        return mFormat.format(now);
    }

    private long getRandomTime() {
        return (long) (Math.random() * 75600000);
    }


    public void add(String title, String info) {
        mDataItems.add(new DataItem(title, info, getRandomTime()));
    }

    public void add(String title, String info, long time) {
        mDataItems.add(new DataItem(title, info, time));
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

    public String getTime(int position) {
        return getStringTime(mDataItems.get(position).mTime);
    }

    public long getTimeLong(int position) {
        return mDataItems.get(position).mTime;
    }


    public void sortByTime() {
        if (!time)
            Collections.sort(mDataItems, (a, b) -> (int) (a.mTime - b.mTime));
        else
            Collections.sort(mDataItems, (a, b) -> (int) (b.mTime - a.mTime));
        time = !time;
    }

    public void sortByAZ() {
        if (!az)
            Collections.sort(mDataItems, (a, b) -> a.mTitle.compareToIgnoreCase(b.mTitle));
        else
            Collections.sort(mDataItems, (a, b) -> b.mTitle.compareToIgnoreCase(a.mTitle));
        az = !az;
    }


    public int size() {
        return mDataItems.size();
    }


    private class DataItem {
        private String mTitle;
        private String mInfo;
        private long mTime;

        DataItem(String title, String info, long time) {
            mTitle = title;
            mInfo = info;
            mTime = time;
        }
    }
}
