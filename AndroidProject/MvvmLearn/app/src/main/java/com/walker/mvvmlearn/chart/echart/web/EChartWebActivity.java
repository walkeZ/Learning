package com.walker.mvvmlearn.chart.echart.web;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.walker.mvvmlearn.R;
import com.walker.mvvmlearn.databinding.ActivityEchartWebBinding;

/**
 * @author walker
 * @date 2024/6/5
 * @description 图表练习
 * https://blog.csdn.net/gemgaozhen/article/details/131376384
 * https://blog.csdn.net/LQ_001/article/details/139068686
 * 1.1 下载 Echarts 安卓资源
 * 下载地址：https://github.com/apache/echarts/tree/5.5.0/dist，打开这个官网链接之后，下载echarts.min.js文件，则为Echarts全部资源包。
 * 1.2 新建assets文件
 * 新建 app\src\main\assets文件，将刚才下载的echarts.min.js文件放到此文件夹下。
 * <p>
 * 1.3 新建布局文件
 * 在 app\src\main\assets文件夹下新一个.html的文件，名称自己取。(新建一个file把后缀改成.html就行)
 * ————————————————
 * <p>
 * 版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
 * <p>
 * 原文链接：https://blog.csdn.net/LQ_001/article/details/139068686
 */
public class EChartWebActivity extends AppCompatActivity {

    String demoOption = "{\n" +
            "  series: [\n" +
            "    {\n" +
            "      type: 'gauge',\n" +
            "      startAngle: 180,\n" +
            "      endAngle: 0,\n" +
            "      center: ['50%', '75%'],\n" +
            "      radius: '90%',\n" +
            "      min: 0,\n" +
            "      max: 1,\n" +
            "      splitNumber: 8,\n" +
            "      axisLine: {\n" +
            "        lineStyle: {\n" +
            "          width: 6,\n" +
            "          color: [\n" +
            "            [0.25, '#FF6E76'],\n" +
            "            [0.5, '#FDDD60'],\n" +
            "            [0.75, '#58D9F9'],\n" +
            "            [1, '#7CFFB2']\n" +
            "          ]\n" +
            "        }\n" +
            "      },\n" +
            "      pointer: {\n" +
            "        icon: 'path://M12.8,0.7l12,40.1H0.7L12.8,0.7z',\n" +
            "        length: '12%',\n" +
            "        width: 20,\n" +
            "        offsetCenter: [0, '-60%'],\n" +
            "        itemStyle: {\n" +
            "          color: 'inherit'\n" +
            "        }\n" +
            "      },\n" +
            "      axisTick: {\n" +
            "        length: 12,\n" +
            "        lineStyle: {\n" +
            "          color: 'inherit',\n" +
            "          width: 2\n" +
            "        }\n" +
            "      },\n" +
            "      splitLine: {\n" +
            "        length: 20,\n" +
            "        lineStyle: {\n" +
            "          color: 'inherit',\n" +
            "          width: 5\n" +
            "        }\n" +
            "      },\n" +
            "      axisLabel: {\n" +
            "        color: '#464646',\n" +
            "        fontSize: 16,\n" +
            "        distance: -60,\n" +
            "        rotate: 'tangential',\n" +
            "        formatter: function (value) {\n" +
            "          if (value === 0.875) {\n" +
            "            return '优秀';\n" +
            "          } else if (value === 0.625) {\n" +
            "            return '良好';\n" +
            "          } else if (value === 0.375) {\n" +
            "            return '一般';\n" +
            "          } else if (value === 0.125) {\n" +
            "            return '差';\n" +
            "          }\n" +
            "          return '';\n" +
            "        }\n" +
            "      },\n" +
            "      title: {\n" +
            "        offsetCenter: [0, '-10%'],\n" +
            "        fontSize: 14\n" +
            "      },\n" +
            "      detail: {\n" +
            "        fontSize: 30,\n" +
            "        offsetCenter: [0, '-35%'],\n" +
            "        valueAnimation: true,\n" +
            "        formatter: function (value) {\n" +
            "          return Math.round(value * 100) + '';\n" +
            "        },\n" +
            "        color: 'inherit'\n" +
            "      },\n" +
            "      data: [\n" +
            "        {\n" +
            "          value: 0.7,\n" +
            "          name: '发电完成率'\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    String optionDemo2 = "{\n" +
            "  color: ['#80FFA5', '#00DDFF', '#37A2FF', '#FF0087', '#FFBF00'],\n" +
            "  title: {\n" +
            "    text: 'Gradient Stacked Area Chart'\n" +
            "  },\n" +
            "  tooltip: {\n" +
            "    trigger: 'axis',\n" +
            "    axisPointer: {\n" +
            "      type: 'cross',\n" +
            "      label: {\n" +
            "        backgroundColor: '#6a7985'\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  legend: {\n" +
            "    data: ['Line 1', 'Line 2', 'Line 3', 'Line 4', 'Line 5']\n" +
            "  },\n" +
            "  toolbox: {\n" +
            "    feature: {\n" +
            "      saveAsImage: {}\n" +
            "    }\n" +
            "  },\n" +
            "  grid: {\n" +
            "    left: '3%',\n" +
            "    right: '4%',\n" +
            "    bottom: '3%',\n" +
            "    containLabel: true\n" +
            "  },\n" +
            "  xAxis: [\n" +
            "    {\n" +
            "      type: 'category',\n" +
            "      boundaryGap: false,\n" +
            "      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']\n" +
            "    }\n" +
            "  ],\n" +
            "  yAxis: [\n" +
            "    {\n" +
            "      type: 'value'\n" +
            "    }\n" +
            "  ],\n" +
            "  series: [\n" +
            "    {\n" +
            "      name: 'Line 1',\n" +
            "      type: 'line',\n" +
            "      stack: 'Total',\n" +
            "      smooth: true,\n" +
            "      lineStyle: {\n" +
            "        width: 0\n" +
            "      },\n" +
            "      showSymbol: false,\n" +
            "      areaStyle: {\n" +
            "        opacity: 0.8,\n" +
            "        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [\n" +
            "          {\n" +
            "            offset: 0,\n" +
            "            color: 'rgb(128, 255, 165)'\n" +
            "          },\n" +
            "          {\n" +
            "            offset: 1,\n" +
            "            color: 'rgb(1, 191, 236)'\n" +
            "          }\n" +
            "        ])\n" +
            "      },\n" +
            "      emphasis: {\n" +
            "        focus: 'series'\n" +
            "      },\n" +
            "      data: [140, 232, 101, 264, 90, 340, 250]\n" +
            "    },\n" +
            "    {\n" +
            "      name: 'Line 2',\n" +
            "      type: 'line',\n" +
            "      stack: 'Total',\n" +
            "      smooth: true,\n" +
            "      lineStyle: {\n" +
            "        width: 0\n" +
            "      },\n" +
            "      showSymbol: false,\n" +
            "      areaStyle: {\n" +
            "        opacity: 0.8,\n" +
            "        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [\n" +
            "          {\n" +
            "            offset: 0,\n" +
            "            color: 'rgb(0, 221, 255)'\n" +
            "          },\n" +
            "          {\n" +
            "            offset: 1,\n" +
            "            color: 'rgb(77, 119, 255)'\n" +
            "          }\n" +
            "        ])\n" +
            "      },\n" +
            "      emphasis: {\n" +
            "        focus: 'series'\n" +
            "      },\n" +
            "      data: [120, 282, 111, 234, 220, 340, 310]\n" +
            "    },\n" +
            "    {\n" +
            "      name: 'Line 3',\n" +
            "      type: 'line',\n" +
            "      stack: 'Total',\n" +
            "      smooth: true,\n" +
            "      lineStyle: {\n" +
            "        width: 0\n" +
            "      },\n" +
            "      showSymbol: false,\n" +
            "      areaStyle: {\n" +
            "        opacity: 0.8,\n" +
            "        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [\n" +
            "          {\n" +
            "            offset: 0,\n" +
            "            color: 'rgb(55, 162, 255)'\n" +
            "          },\n" +
            "          {\n" +
            "            offset: 1,\n" +
            "            color: 'rgb(116, 21, 219)'\n" +
            "          }\n" +
            "        ])\n" +
            "      },\n" +
            "      emphasis: {\n" +
            "        focus: 'series'\n" +
            "      },\n" +
            "      data: [320, 132, 201, 334, 190, 130, 220]\n" +
            "    },\n" +
            "    {\n" +
            "      name: 'Line 4',\n" +
            "      type: 'line',\n" +
            "      stack: 'Total',\n" +
            "      smooth: true,\n" +
            "      lineStyle: {\n" +
            "        width: 0\n" +
            "      },\n" +
            "      showSymbol: false,\n" +
            "      areaStyle: {\n" +
            "        opacity: 0.8,\n" +
            "        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [\n" +
            "          {\n" +
            "            offset: 0,\n" +
            "            color: 'rgb(255, 0, 135)'\n" +
            "          },\n" +
            "          {\n" +
            "            offset: 1,\n" +
            "            color: 'rgb(135, 0, 157)'\n" +
            "          }\n" +
            "        ])\n" +
            "      },\n" +
            "      emphasis: {\n" +
            "        focus: 'series'\n" +
            "      },\n" +
            "      data: [220, 402, 231, 134, 190, 230, 120]\n" +
            "    },\n" +
            "    {\n" +
            "      name: 'Line 5',\n" +
            "      type: 'line',\n" +
            "      stack: 'Total',\n" +
            "      smooth: true,\n" +
            "      lineStyle: {\n" +
            "        width: 0\n" +
            "      },\n" +
            "      showSymbol: false,\n" +
            "      label: {\n" +
            "        show: true,\n" +
            "        position: 'top'\n" +
            "      },\n" +
            "      areaStyle: {\n" +
            "        opacity: 0.8,\n" +
            "        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [\n" +
            "          {\n" +
            "            offset: 0,\n" +
            "            color: 'rgb(255, 191, 0)'\n" +
            "          },\n" +
            "          {\n" +
            "            offset: 1,\n" +
            "            color: 'rgb(224, 62, 76)'\n" +
            "          }\n" +
            "        ])\n" +
            "      },\n" +
            "      emphasis: {\n" +
            "        focus: 'series'\n" +
            "      },\n" +
            "      data: [220, 302, 181, 234, 210, 290, 150]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    String optionDemo3 = "{\n" +
            "  title: {\n" +
            "    text: 'Rainfall vs Evaporation',\n" +
            "    subtext: 'Fake Data'\n" +
            "  },\n" +
            "  tooltip: {\n" +
            "    trigger: 'axis'\n" +
            "  },\n" +
            "  legend: {\n" +
            "    data: ['Rainfall', 'Evaporation']\n" +
            "  },\n" +
            "  toolbox: {\n" +
            "    show: true,\n" +
            "    feature: {\n" +
            "      dataView: { show: true, readOnly: false },\n" +
            "      magicType: { show: true, type: ['line', 'bar'] },\n" +
            "      restore: { show: true },\n" +
            "      saveAsImage: { show: true }\n" +
            "    }\n" +
            "  },\n" +
            "  calculable: true,\n" +
            "  xAxis: [\n" +
            "    {\n" +
            "      type: 'category',\n" +
            "      // prettier-ignore\n" +
            "      data: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']\n" +
            "    }\n" +
            "  ],\n" +
            "  yAxis: [\n" +
            "    {\n" +
            "      type: 'value'\n" +
            "    }\n" +
            "  ],\n" +
            "  series: [\n" +
            "    {\n" +
            "      name: 'Rainfall',\n" +
            "      type: 'bar',\n" +
            "      data: [\n" +
            "        2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3\n" +
            "      ],\n" +
            "      markPoint: {\n" +
            "        data: [\n" +
            "          { type: 'max', name: 'Max' },\n" +
            "          { type: 'min', name: 'Min' }\n" +
            "        ]\n" +
            "      },\n" +
            "      markLine: {\n" +
            "        data: [{ type: 'average', name: 'Avg' }]\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      name: 'Evaporation',\n" +
            "      type: 'bar',\n" +
            "      data: [\n" +
            "        2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3\n" +
            "      ],\n" +
            "      markPoint: {\n" +
            "        data: [\n" +
            "          { name: 'Max', value: 182.2, xAxis: 7, yAxis: 183 },\n" +
            "          { name: 'Min', value: 2.3, xAxis: 11, yAxis: 3 }\n" +
            "        ]\n" +
            "      },\n" +
            "      markLine: {\n" +
            "        data: [{ type: 'average', name: 'Avg' }]\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    private ActivityEchartWebBinding viewDataBinding;
    private String optionDemo4 = "{\n" +
            "  tooltip: {\n" +
            "    trigger: 'item'\n" +
            "  },\n" +
            "  legend: {\n" +
            "    top: '5%',\n" +
            "    left: 'center'\n" +
            "  },\n" +
            "  series: [\n" +
            "    {\n" +
            "      name: 'Access From',\n" +
            "      type: 'pie',\n" +
            "      radius: ['40%', '70%'],\n" +
            "      center: ['50%', '70%'],\n" +
            "      // adjust the start and end angle\n" +
            "      startAngle: 180,\n" +
            "      endAngle: 360,\n" +
            "      data: [\n" +
            "        { value: 1048, name: 'Search Engine' },\n" +
            "        { value: 735, name: 'Direct' },\n" +
            "        { value: 580, name: 'Email' },\n" +
            "        { value: 484, name: 'Union Ads' },\n" +
            "        { value: 300, name: 'Video Ads' }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_echart_web);
        viewDataBinding.setLifecycleOwner(this);

//        viewDataBinding.aewWvEChart.getSettings().setJavaScriptEnabled(true);
//        viewDataBinding.aewWvEChart.getSettings().setAllowFileAccess(true);


    }

    @Override
    protected void onResume() {
        super.onResume();
        viewDataBinding.aewWvEChart.setData(demoOption);
        viewDataBinding.aewWvEChart2.setData(optionDemo2);
        viewDataBinding.aewWvEChart3.setData(demoOption);
        viewDataBinding.aewWvEChart4.setData(optionDemo4);
    }
}
