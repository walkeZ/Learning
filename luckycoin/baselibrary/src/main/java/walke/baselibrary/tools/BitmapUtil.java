package walke.baselibrary.tools;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Walke.Z
 * on 2017/7/2. 01.
 * email：1126648815@qq.com
 */
public class BitmapUtil {

    public static Bitmap getBitmap(String filePath, int destWidth, int destHeight) {
        //第一采样
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        //BitmapFactory.decodeFile(filePath, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int sampleSize = 1;
        while ((outWidth / sampleSize > destWidth) || (outHeight / sampleSize > destHeight)) {
            sampleSize *= 2;
        }
        //第二次采样
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        return bitmap;
    }

    public static Bitmap getBitmap(String filePath, int destWidth) {
        //第一采样
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        //BitmapFactory.decodeFile(filePath, options);
        int oldWidth = options.outWidth;
        int outHeight = options.outHeight;
        int sampleSize = 1;
       /* while ((oldWidth / sampleSize > destWidth)) {
            sampleSize *= 2;
        }*/
        sampleSize=oldWidth/destWidth;
        //第二次采样
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
        return bitmap;
    }

    public static Bitmap getBitmap(byte[] bytes, int destWidth) {
        //第一采样
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        //BitmapFactory.decodeFile(filePath, options);
        int oldWidth = options.outWidth;
        int outHeight = options.outHeight;
        int sampleSize = 1;
       /* while ((oldWidth / sampleSize > destWidth)) {
            sampleSize *= 2;
        }*/
        sampleSize=oldWidth/destWidth;
        //第二次采样
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        return bitmap;
    }

    public static Bitmap compressBitmap(Bitmap bm, int destWidth) {
        double height = bm.getHeight();
        double width = bm.getWidth();
        double v = width / destWidth;
        double destHeigth = height / v;
        Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, destWidth, (int) destHeigth);
        return bitmap;
    }

    public static Bitmap decodeUriAsBitmap(Context context, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public static float getSizeOfBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//这里100的话表示不压缩质量
        long length = baos.toByteArray().length / 1024;//读出图片的kb大小
        LogUtil.i("------质量--------", "length  " + length);
        return length;
    }


    /**  // 读取uri所在的图片 ----- 比较简单的方法
     *  没有对图片的大小进行判断和处理，在图片太大的情况下，可能会出现OOM的问题。
     * @param context
     * @param uri
     * @return
     */
    public static Bitmap getBitmapFromUri(Context context,Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    /** // 读取uri所在的图片 ----- 科学方法
     * 读取一个缩放后的图片，限定图片大小，避免OOM
     * @param uri       图片uri，支持“file://”、“content://”
     * @param maxWidth  最大允许宽度
     * @param maxHeight 最大允许高度
     * @return  返回一个缩放后的Bitmap，失败则返回null
     */
    public static Bitmap decodeUri(Context context, Uri uri, int maxWidth, int maxHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; //只读取图片尺寸
        resolveUri(context, uri, options);
        //计算实际缩放比例
        int scale = 1;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if ((options.outWidth / scale > maxWidth &&
                    options.outWidth / scale > maxWidth * 1.4) ||
                    (options.outHeight / scale > maxHeight &&
                            options.outHeight / scale > maxHeight * 1.4)) {
                scale++;
            } else {
                break;
            }
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;//读取图片内容
        options.inPreferredConfig = Bitmap.Config.RGB_565; //根据情况进行修改
        Bitmap bitmap = null;
        try {
            bitmap = resolveUriForBitmap(context, uri, options);
            LogUtil.i("1111","1111");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private static void resolveUri(Context context, Uri uri, BitmapFactory.Options options) {
        if (uri == null) {
            return;
        }

        String scheme = uri.getScheme();
        if (ContentResolver.SCHEME_CONTENT.equals(scheme) ||
                ContentResolver.SCHEME_FILE.equals(scheme)) {
            InputStream stream = null;
            try {
                stream = context.getContentResolver().openInputStream(uri);
                BitmapFactory.decodeStream(stream, null, options);
            } catch (Exception e) {
                Log.w("resolveUri", "Unable to open content: " + uri, e);
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        Log.w("resolveUri", "Unable to close content: " + uri, e);
                    }
                }
            }
        } else if (ContentResolver.SCHEME_ANDROID_RESOURCE.equals(scheme)) {
            Log.w("resolveUri", "Unable to close content: " + uri);
        } else {
            Log.w("resolveUri", "Unable to close content: " + uri);
        }
    }

    private static Bitmap resolveUriForBitmap(Context context, Uri uri, BitmapFactory.Options options) {
        if (uri == null) {
            return null;
        }

        Bitmap bitmap = null;
        String scheme = uri.getScheme();
        if (ContentResolver.SCHEME_CONTENT.equals(scheme) ||
                ContentResolver.SCHEME_FILE.equals(scheme)) {
            InputStream stream = null;
            try {
                stream = context.getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(stream, null, options);
            } catch (Exception e) {
                Log.w("resolveUriForBitmap", "Unable to open content: " + uri, e);
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        Log.w("resolveUriForBitmap", "Unable to close content: " + uri, e);
                    }
                }
            }
        } else if (ContentResolver.SCHEME_ANDROID_RESOURCE.equals(scheme)) {
            Log.w("resolveUriForBitmap", "Unable to close content: " + uri);
        } else {
            Log.w("resolveUriForBitmap", "Unable to close content: " + uri);
        }

        return bitmap;
    }


}
