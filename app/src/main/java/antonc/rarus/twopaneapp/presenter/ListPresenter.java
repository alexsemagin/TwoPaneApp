package antonc.rarus.twopaneapp.presenter;

import android.os.AsyncTask;
import antonc.rarus.twopaneapp.model.entity.DataList;
import antonc.rarus.twopaneapp.ui.test_task.MyFilter;

public class ListPresenter {

    private ListView mView;
    private String query;
    private DataList dl;
    private MyFilter filter;

    public ListPresenter() {
        filter = new MyFilter(this);
    }


    public void setView(ListView view) {
        mView = view;
    }

    public void getData() {
        if (mView != null) {
            if (dl == null) mView.setData(DataList.get());
            else mView.setData(dl);
        }
    }

    public void search(String query) {
        this.query = query;
        Task task = new Task();
        task.execute();
    }

    private void search() {
        filter.filter(query);

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateDataList(DataList dl) {
        this.dl = dl;
    }


    public void onItemSelected(String title, String info) {
         mView.openDetailFragment(title, info);
    }





   private class Task extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            search();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            getData();
        }
    }

}
