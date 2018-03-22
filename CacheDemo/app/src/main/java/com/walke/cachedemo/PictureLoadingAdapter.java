package com.walke.cachedemo;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.walke.cachedemo.example1.LocalCacheUtil;
import com.walke.cachedemo.example1.MemoryCacheUtil;
import com.walke.cachedemo.example1.NetCacheUtil;
import com.walke.cachedemo.loadimg.ImgLoader;

import java.util.List;

/**
 * Created by walke on 2018/2/4.
 * email:1032458982@qq.com
 */

public class PictureLoadingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> imgUrls;
    private String infoAdd = "112233";//占位
    private Context mContext;

    private MemoryCacheUtil mMemoryCacheUtil;
    private LocalCacheUtil mLocalCacheUtil;
    private NetCacheUtil mNetCacheUtil;

    /**
     * 文件保存路径
     */
    public static String mSaveDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AHui";


    public PictureLoadingAdapter(Context context, List<String> mImages) {
        mContext = context;
        imgUrls =mImages;
        imgUrls.add(0,infoAdd);
        mMemoryCacheUtil=new MemoryCacheUtil();
        mLocalCacheUtil=new LocalCacheUtil(mMemoryCacheUtil);
        mNetCacheUtil=new NetCacheUtil(mMemoryCacheUtil,mLocalCacheUtil);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.gv_ad_picture_item, parent, false);
        RVPictureHolder pictureHolder = new RVPictureHolder(inflate);
        return pictureHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final RVPictureHolder pictureHolder = (RVPictureHolder) holder;
        if (position==0) {
            pictureHolder.ivCancel.setVisibility(View.GONE);
            pictureHolder.vCancelClick.setVisibility(View.GONE);
            pictureHolder.ivPicture.setImageResource(R.mipmap.picture_uploading_add);
        }else {
            pictureHolder.ivCancel.setVisibility(View.VISIBLE);
            pictureHolder.vCancelClick.setVisibility(View.VISIBLE);
            String imageUrl = imgUrls.get(position);
//            ImgLoader.getInstance().setSaveDir(mSaveDir).loadImage(mContext,imageUrl,imageUrl,pictureHolder.ivPicture);

            ImgLoader.getInstance().setSaveDir(mSaveDir).loadImage(mContext, imageUrl, imageUrl + "11111", pictureHolder.ivPicture, new ImgLoader.LoadingListener() {
                /**
                 * @param current
                 * @param total   当total=-1，从网络连接中获取内容长度失败，个别文件例如 http://pic1.win4000.com/wallpaper/8/51bb08e7a700a.jpg
                 */
                @Override
                public void loading(int current, int total) {

                }

                @Override
                public void loadError(Exception exc) {

                }

                @Override
                public void loadFinish() {

                }
            });
//            Glide.with(mContext).load(imageUrl).into(pictureHolder.ivPicture);


        }
    }

    @Override
    public int getItemCount() {
        return imgUrls.size();
    }

    class RVPictureHolder extends RecyclerView.ViewHolder {

//        private SimpleDraweeView mSimpleDraweeView;
        private ProgressBar pbLoading;
        private ImageView ivCancel, ivPicture;
        private View vCancelClick;

        public RVPictureHolder(View view) {
            super(view);
            ivPicture = ((ImageView) view.findViewById(R.id.gapi_ivPicture));
//            mSimpleDraweeView = ((SimpleDraweeView) view.findViewById(R.id.gapi_SimpleDraweeView));
            ivCancel = ((ImageView) view.findViewById(R.id.gapi_ivCancel));
            vCancelClick = view.findViewById(R.id.gapi_vCancelClick);
            pbLoading = (ProgressBar) view.findViewById(R.id.gapi_pbLoading);
            ivPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 2018/2/5 获取手机相册图片或者启动相机拍照上传
                    int layoutPosition = getLayoutPosition();
                    if (layoutPosition==0) {

                        if (mOnPictureSelectListener != null)
                            mOnPictureSelectListener.onAdd(layoutPosition);
                    }
                }
            });
            vCancelClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = getLayoutPosition();
                    if (layoutPosition!=0) {
                        if (mOnPictureSelectListener != null)
                            mOnPictureSelectListener.onDelete(layoutPosition);
                    }
                }
            });
        }
    }

    public interface OnPictureSelectListener {
        void onAdd(int position);

        void onDelete(int position);
    }

    private OnPictureSelectListener mOnPictureSelectListener;

    public void setOnPictureSelectListener(OnPictureSelectListener onPictureSelectListener) {
        mOnPictureSelectListener = onPictureSelectListener;
    }

    public void addUrl(String url) {
        imgUrls.add(url);
        notifyDataSetChanged();

    }

    public void delete(int position) {
        if (position >= 0 && position < imgUrls.size()) {
            imgUrls.remove(position);
            notifyDataSetChanged();
        }
    }


}
