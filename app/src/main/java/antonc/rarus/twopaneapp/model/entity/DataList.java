package antonc.rarus.twopaneapp.model.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


public class DataList {
    private static DataList sDataList;
    private ArrayList<DataItem> mDataItems;

    private static String title = "TITLE";
    private static String info = "INFO";
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


    private String getStringTime() {
        Date now = new Date();
        now.setTime(getRandomTime());
        return mFormat.format(now);
    }

    private String getStringTime(long lTime) {
        Date now = new Date();
        now.setTime(lTime);
        return mFormat.format(now);
    }

    private long getRandomTime() {
        long lTime = (long) (Math.random() * 75600000);
        return lTime;
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
            Collections.sort(mDataItems, new SortByTimeComparator());
        else
            Collections.sort(mDataItems, new ReverseByTimeComparator());
        time = !time;


    }

    public void sortByAZ() {
        if (!az)
            Collections.sort(mDataItems, new SortByTitleComparator());
        else
            Collections.sort(mDataItems, new ReverseByTitleComparator());
        az = !az;
    }


    public int size() {
        return mDataItems.size();
    }


    private class DataItem {
        private String mTitle;
        private String mInfo;
        private long mTime;

        public DataItem(String title, String info, long time) {
            mTitle = title;
            mInfo = info;
            mTime = time;
        }
    }

    private class SortByTimeComparator implements Comparator<DataItem> {
        public int compare(DataItem a, DataItem b) {
            return (int) (a.mTime - b.mTime);
        }
    }

    private class ReverseByTimeComparator implements Comparator<DataItem> {
        public int compare(DataItem a, DataItem b) {
            return (int) (b.mTime - a.mTime);
        }
    }

    private class SortByTitleComparator implements Comparator<DataItem> {
        public int compare(DataItem a, DataItem b) {
            return a.mTitle.compareToIgnoreCase(b.mTitle);
        }
    }

    private class ReverseByTitleComparator implements Comparator<DataItem> {
        public int compare(DataItem a, DataItem b) {
            return b.mTitle.compareToIgnoreCase(a.mTitle);
        }
    }
}
