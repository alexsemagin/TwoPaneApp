package antonc.rarus.twopaneapp.model.entity;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DataItem {

    private final SimpleDateFormat mFormat =
            new SimpleDateFormat("HH:mm:ss");


    private String mTitle;
    private String mInfo;
    private long mTime;


    public DataItem(String title, String info, long time) {
        mTitle = title;
        mInfo = info;
        mTime = time;
    }

    public DataItem(String title, String info) {
        mTitle = title;
        mInfo = info;
        mTime = getRandomTime();
    }


    public String getStringTime(long lTime) {
        Date now = new Date();
        now.setTime(lTime);
        return mFormat.format(now);
    }

    private long getRandomTime() {
        return (long) (Math.random() * 75600000);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getInfo() {
        return mInfo;
    }

    public String getTime() {
        return getStringTime(mTime);
    }

    public long getTimeLong() {
        return mTime;
    }


}