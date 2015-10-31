package vandy.mooc.utils.loader;

import android.graphics.Bitmap;

/**
 * POJO that stores the information for a single
 * ImageLoaderTask's processing result
 */
public class ImageLoaderWorkResult {

    /**
     * ImageViewHolder wrapping the ImageView that
     * will display the image
     */
    private ImageViewHolder mImageViewHolder;

    /**
     * The filepath of the image to load
     */
    private String mFilePath;

    /**
     * The loaded bitmap
     */
    private Bitmap mBitmap;

    /**
     * Constructor initializes fields
     */
    public ImageLoaderWorkResult(ImageViewHolder imageViewHolder, String filepath, Bitmap bitmap) {
        mImageViewHolder = imageViewHolder;
        mFilePath = filepath;
        mBitmap = bitmap;
    }

    public ImageViewHolder getImageViewHolder() {
        return mImageViewHolder;
    }

    public void setImageViewHolder(ImageViewHolder mImageViewHolder) {
        this.mImageViewHolder = mImageViewHolder;
    }

    public String getFilePath() {
        return mFilePath;
    }

    public void setFilePath(String mFilePath) {
        this.mFilePath = mFilePath;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }
}
