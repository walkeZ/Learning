package com.walker.mvvmlearn.net.retrofit2;

import android.os.Environment;

import com.walker.mvvmlearn.net.retrofit2.api.APIService2;
import com.walker.mvvmlearn.net.retrofit2.callback.IResponseByteCallBack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/** retrofit2 简单封装，基于https://blog.csdn.net/duoduo_11011/article/details/77530947
 * @author walker
 * @date 2024/5/21
 * @description 网络请求，对外的规范工具类
 * NetNetRequest.getApi(c).get业务
 */
public class NetRequest {

    public static Retrofit mRetrofit;

    /**
     * 为我们的Client配置参数，使用静态语句块来配置
     * 只执行一次，运行一开始就开辟了内存，内存放在全局
     */
    static {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(NetConfig.BASE_URL)//添加BaseUrl
                .client(NetConfig.GetOkHttpClient())//添加OkhttpClient]
                .addConverterFactory(GsonConverterFactory.create())//添加Gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//  添加 rxjava 支持
                .build();
    }

    /**
     * 所有请求都放在一个接口中
     */
    public static APIService2 createApi() {
        return mRetrofit.create(APIService2.class);
    }

    /**
     * 不同请求放不同接口中
     * @param service 指定接口
     */
    public static <T> T createApi(Class<T> service) {
        return mRetrofit.create(service);
    }

    /**
     * 统一下载图片共用
     * @param observable 被观察者
     * @param callback 回调接口
     * @param imgPath 存储地址
     */
    public static void downImg(Observable<ResponseBody> observable, final IResponseByteCallBack callback, final String imgPath) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        File file = null;
                        try {
                            InputStream is = responseBody.byteStream();
                            int len = 0;
                            // 文件夹路径
                            String pathUrl =
                                    Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                                            + "/PJS/";
                            File filepath = new File(pathUrl);
                            if (!filepath.exists()) {
                                filepath.mkdirs();// 创建文件夹
                            }
                            file = new File(pathUrl, imgPath);

                            FileOutputStream fos = new FileOutputStream(file);

                            byte[] buf = new byte[2048];
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                            }
                            fos.flush();
                            fos.close();
                            is.close();
                            callback.onSuccess(file);
                        } catch (final Exception e) {
                            callback.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

}
