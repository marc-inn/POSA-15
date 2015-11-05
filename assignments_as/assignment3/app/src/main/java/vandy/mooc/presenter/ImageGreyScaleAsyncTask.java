package vandy.mooc.presenter;

import android.net.Uri;
import android.os.AsyncTask;

import vandy.mooc.common.util.BitmapUtils;

public class ImageGreyScaleAsyncTask extends AsyncTask<ImagePresenter, Runnable, Uri> {

    @SuppressWarnings("unused")
    private final static String TAG = ImageGreyScaleAsyncTask.class.getName();

    private final Uri mImageUri;

    private final Uri mDirectoryUri;

    private ImagePresenter mPresenter;

    public ImageGreyScaleAsyncTask(Uri imageUri, Uri directoryUri) {
        mImageUri = imageUri;
        mDirectoryUri = directoryUri;
    }

    @Override
    public Uri doInBackground(ImagePresenter... presenters) {
        mPresenter = presenters[0];
        return BitmapUtils.grayScaleFilter(mPresenter.getApplicationContext(),
                mImageUri,
                mDirectoryUri);
    }

    @Override
    protected void onPostExecute(Uri uri) {
        super.onPostExecute(uri);
        mPresenter.onProcessingComplete(uri, uri);
    }
}
