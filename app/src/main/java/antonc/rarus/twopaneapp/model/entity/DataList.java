package antonc.rarus.twopaneapp.model.entity;

import java.util.ArrayList;

/**
 * Created by antonc on 17.03.2017.
 */

public class DataList {
    private static DataList sDataList;
    private ArrayList<DataItem> mDataItems;

    private static String title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis leo libero, mollis eu mollis in, ultricies sed nisl.";
    private static String info = "In hac habitasse platea dictumst. Suspendisse eleifend erat eu malesuada accumsan. Proin ut cursus tellus, elementum consectetur risus. Suspendisse ullamcorper mattis fermentum. Cras fermentum commodo volutpat. Praesent non maximus odio. Aliquam sed tellus efficitur, consectetur elit eget, consectetur elit. Donec ac mauris eros. Integer non commodo orci. Nulla ut finibus nunc.";

    private DataList() {
        mDataItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                mDataItems.add(new DataItem(title + i, info + i));
            else mDataItems.add(new DataItem(title + i, ""));
        }
    }

    public static DataList get() {
        if (sDataList == null) {
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
