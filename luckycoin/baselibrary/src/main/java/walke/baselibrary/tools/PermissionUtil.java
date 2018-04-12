package walke.baselibrary.tools;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

/**
 * @author View
 * @date 2016/12/12 11:42
 */
public class PermissionUtil {

//
    public static final int DEFUALT_REQUEST_CODE = 0x00;
    public static final int REQUEST_CAMERA = 0x01;
    public static final int REQUEST_SDCARD = 0x02;
    public static final int REQUEST_LOCATION = 0x03;
    public static final int REQUEST_PHONE_STATE = 0x04;
//
    /**
     * 配置app需要的所有权限集
     *  为了适配LG G5 SE手机，经检测权限集应当与该手机上显式提示需要动态申请的权限集一致
     */
    public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,

//            Manifest.permission.ACCESS_NETWORK_STATE,
//            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.READ_PHONE_STATE,
//            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
//            Manifest.permission.ACCESS_FINE_LOCATION,
//            Manifest.permission.READ_LOGS,
    };
//
//    /**
//     * 配置SD卡需要的权限集
//     */
//    public static final String[] PERMISSION_SDCARD = new String[]{
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//
//    /**
//     * 配置摄像头需要的权限集
//     */
//    public static final String[] PERMISSION_CAMERA = new String[]{Manifest.permission.CAMERA};



    /**
     * 检查系统权限，判断当前是否缺少权限(PERMISSION_DENIED:权限是否足够)
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean isLackPermission(Context context, String permission) {
        boolean hasPermission = ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED;//denied--拒绝
        return hasPermission;
    }


    /**
     * 检查权限时，判断该app的权限集合,是否有缺少的权限
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean checkPermissionSetLack(Context context, String... permissions) {
        for (String permission : permissions) {
            if (isLackPermission(context, permission)) {//是否添加完全部权限集合
                return true;
            }
        }
        return false;//表示全部权限已有齐
    }

//    /**
//     * Requests permissions.
//     * @param activity
//     */
//    public static void requestPermissions(final Activity activity, String[] permissions) {
//        if (Build.VERSION.SDK_INT >= 23) {
//            boolean b = checkPermissionSetLack(activity, permissions);
//            if (b) {
//                activity.requestPermissions(permissions, CODE_REQUEST_PERMISSION);
//            }
//        }
//    }
//
//    /**  Lack 缺乏
//     * 检查系统权限，判断当前是否缺少权限(PERMISSION_DENIED:权限是否足够)
//     *
//     * @param activity
//     * @param permission
//     * @return
//     */
//    @TargetApi(Build.VERSION_CODES.M)
//    public static void checkAndRequestPermission(AppCompatActivity activity, String permission) {
//       if (isLackPermission(activity, permission)){
//           activity.requestPermissions(new String[]{permission}, CODE_REQUEST_PERMISSION);
//       }
//    }



}
