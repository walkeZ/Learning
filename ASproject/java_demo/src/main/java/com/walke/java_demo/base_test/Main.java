package com.walke.java_demo.base_test;

/**
 * author Walke - 2021/6/21 11:43 PM
 * email 1126648815@qq.ocm
 * dec :
 */
class Main {

    public static void main(String[] args) {
        int day = getDay(2020, 2, 5);
        System.out.println("------>day = "+ day);
    }



    //判断某年某月某日是这年的第几天
    public static int getDay(int year, int month, int day ){

        int towMonth=28;
        //判断平年还是闰年
        if((year%4==0&&year%100!=0)||(year%400==0)){
            towMonth=29;//闰年多一天
        }
        int totalDay=0;
        int[] months={31,towMonth,31,30,31,30,31,31,30,31,30,31};
        //前几个月加上本月的天数
        for(int i=0;i<months.length;i++){
            if(month>=i+1){
                if(month==i+1){
                    totalDay += day;
                    break;
                }else{
                    totalDay+=months[i];
                }
            }
        }
        //输出结果
        System.out.println(totalDay);
        return totalDay;
    }

}
