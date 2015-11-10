package at.makubi.notificationtest;

import android.os.AsyncTask;

public abstract class VoidAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected final Void doInBackground(Void... params) {
        run();
        return null;
    }

    protected abstract void run();

    public void executeSerially() {
        executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }
}
