package vandy.mooc.presenter;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import vandy.mooc.MVP;
import vandy.mooc.R;
import vandy.mooc.common.util.Utils;

public class ImageDownloadAsyncTask extends AsyncTask<ImagePresenter, Runnable, Uri> {

    @SuppressWarnings("unused")
    private final static String TAG = ImageDownloadAsyncTask.class.getName();

    private final Uri mNetworkUrl;

    private final Uri mDirectoryUri;

    private ImagePresenter mPresenter;
    private Runnable noInternetConnectionRunnable = new Runnable() {
        @Override
        public void run() {
            if (mPresenter == null) return;
            Toast.makeText(mPresenter.getActivityContext(),
                    R.string.check_internet_connection,
                    Toast.LENGTH_SHORT).show();
        }
    };

    public ImageDownloadAsyncTask(Uri networkUri, Uri directoryUri) {
        mNetworkUrl = networkUri;
        mDirectoryUri = directoryUri;
    }

    @Override
    public Uri doInBackground(ImagePresenter... presenters) {
        mPresenter = presenters[0];
        MVP.ProvidedModelOps model = mPresenter.getModel();
        if (Utils.isNetworkAvailable(mPresenter.getActivityContext())) {
            return model.downloadImage(mPresenter.getApplicationContext(),
                    mNetworkUrl,
                    mDirectoryUri);
        } else {
            publishProgress(noInternetConnectionRunnable);
            return null;
        }
    }

    @Override
    protected void onProgressUpdate(Runnable... values) {
        super.onProgressUpdate(values);
        for (Runnable runnable : values) {
            runnable.run();
        }
    }

    @Override
    protected void onPostExecute(Uri imageUri) {
        super.onPostExecute(imageUri);
        startGreyScaleTask(imageUri);
    }

    private void startGreyScaleTask(Uri imageUri) {
        if (imageUri == null) {
            Toast.makeText(mPresenter.getActivityContext(),
                    R.string.grey_scale_task_cannot_be_started,
                    Toast.LENGTH_SHORT).show();
            mPresenter.getView().get().dismissProgressBar();
            return;
        }
        ImageGreyScaleAsyncTask task = new ImageGreyScaleAsyncTask(imageUri, mDirectoryUri);
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mPresenter);
    }
}
