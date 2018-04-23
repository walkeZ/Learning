package com.walke.xietiaozhebuju;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class Urlutils {
   //----首页轮播地址
   public static String F1_FIRST_AD_URL="http://a.meidebi.com/new.php/Share-getslide?version=3.0.8&devicetoken=866609022244983&devicetype=2";
   //--f1搜索
   public static String F1_SEARCH_URL="http://a.meidebi.com/new.php/Share-getcatgorys?version=3.0.8&ismain=1&devicetoken=866609022244983&devicetype=2";
   /*--（海淘直邮）（中第一页数据）
   http://a.meidebi.com/new.php/Share-haitaodirect?
   version=3.0.8&devicetoken=866609022244983&limit=20&p=-1-&devicetype=2

   --（海淘直邮）（中第二页数据）
   http://a.meidebi.com/new.php/Share-haitaodirect?
   version=3.0.8&devicetoken=866609022244983&limit=20&p=-2-&devicetype=2*/
   //----首页海淘直邮地址 ---//%d--页数
   public static String F1_FIRST_ZHIYOU_URL="http://a.meidebi.com/new.php/Share-haitaodirect?version=3.0.8&devicetoken=866609022244983&limit=20&p=%d&devicetype=2";
   //----首页白菜价 地址 ---//%d--页数
   public static String F1_FIRST_BAICAI_UR="http://a.meidebi.com/new.php/Share-baicai?version=3.0.8&devicetoken=866609022244983&limit=20&p=%d&devicetype=2";

   /*--首页（中第一页数据）
   http://a.meidebi.com/new.php/Share-allhotlist?
   version=3.0.8&devicetoken=866609022244983&limit=20&p=1&type=jing&devicetype=2
    --首页（中第二页数据）
   http://a.meidebi.com/new.php/Share-allhotlist?
   version=3.0.8&devicetoken=866609022244983&limit=20&p=2&type=jing&devicetype=2
   --国内特惠（中第一页数据）
   http://a.meidebi.com/new.php/Share-allhotlist?
   version=3.0.8&devicetoken=866609022244983&limit=20&p=1&type=guo&devicetype=2 */
   //%d--页数，@--type
   public static String F1_FIRST_DATA_URL="http://a.meidebi.com/new.php/Share-allhotlist?version=3.0.8&devicetoken=866609022244983&limit=20&p=%d&type=@&devicetype=2";


   public static String formatUrl(String oldUrl, String type, int page){
      if (type==null){
         String newUrl = oldUrl.replace("%d", page + "");
         return newUrl;
      }else {
         String replace = oldUrl.replace("%d", page + "").replace("@", type);
         return replace;
      }
   }
}
