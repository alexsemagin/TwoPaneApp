package android.rarus.twopaneapp;

import java.util.UUID;

/**
 * Created by antonc on 17.03.2017.
 */

public class DataItem {
    private UUID mId;
    private String mTitle;


    public DataItem(String title) {
        mTitle = title;
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }
}

