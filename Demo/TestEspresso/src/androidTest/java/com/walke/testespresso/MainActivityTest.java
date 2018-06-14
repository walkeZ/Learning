package com.walke.testespresso;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.web.assertion.WebViewAssertions;
import android.support.test.espresso.web.webdriver.DriverAtoms;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;


/**
 * Created by shihao on 2017/3/2.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void test() {
        System.out.println("walke----------------->test");
        //通过id找到edittext，在里面输入2并关闭输入法
        Espresso.onView(withId(R.id.etNumber1)).perform(typeText("2"), closeSoftKeyboard());
        //通过id找到edittext，在里面输入5并关闭输入法
        Espresso.onView(withId(R.id.etNumber2)).perform(typeText("5"), closeSoftKeyboard());
        //通过id找到button，执行点击事件
        Espresso.onView(withId(R.id.btCalculate)).perform(click());
        //通过id找到textview，并判断是否与文本匹配
        ViewInteraction check = Espresso.onView(withId(R.id.tvResult)).check(matches(withText("计算结果：7")));
        System.out.println("-----check success");
//        ViewInteraction check2 = Espresso.onView(withId(R.id.tvResult)).check(matches(withText("计算结果：6")));
//        System.out.println("-----check2 fail");
    }

    @Test
    public void testRecycleView() {
        System.out.println("walke----------------->testRecycleView");
        //通过文本RecycleView找到按钮，并执行点击事件，跳转到RecycleviewActivity
        Espresso.onView(withText("RecyclerView")).perform(click());
        //通过文本"Item 0"找到view，并检查是否显示，然后执行点击事件，此时会弹出对话框
        Espresso.onView(withText("Item 0")).check(matches(isDisplayed())).perform(click());
        //通过文本"确定"找到对话框上的确定按钮，执行点击事件，关闭对话框
        Espresso.onView(withText("确定")).perform(click());
        //通过文本"Item 2"找到view，并检查是否显示，然后执行点击事件，此时会弹出对话框
        Espresso.onView(withText("Item 2")).check(matches(isDisplayed())).perform(click());
        //执行点击返回按钮事件，关闭对话框
        Espresso.pressBack();
        //通过id找到recycleview，然后执行滑动事件，滑动到21项
        Espresso.onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition(21));
        //通过文本"Item 20"找到view，并检查是否显示，然后执行点击事件，此时会弹出对话框
        Espresso.onView(withText("Item 20")).check(matches(isDisplayed())).perform(click());
        //通过文本"确定"找到对话框上的确定按钮，执行点击事件，关闭对话框
        Espresso.onView(withText("确定")).perform(click());
        //执行点击返回按钮事件，关闭跳转到RecycleviewActivity
        Espresso.pressBack();
    }

    @Test
    public void testWebView() {
        System.out.println("walke----------------->testWebView");
        //通过文本RecycleView找到按钮，并执行点击事件，跳转到WebViewActivity
        Espresso.onView(withText("WebView")).perform(click());
        //通过name为"word"找到搜索输入框
        onWebView().withElement(findElement(Locator.NAME, "word"))
                //往输入框中输入字符串"android"
                .perform(DriverAtoms.webKeys("android"))
                //通过id为"index-bn"找到"百度一下"按钮
                .withElement(DriverAtoms.findElement(Locator.ID, "index-bn"))
                //执行点击事件
                .perform(webClick())
                //通过id为"results"找到结果div
                .withElement(DriverAtoms.findElement(Locator.ID, "results"))
                //检查div中是否包含字符串"android"
                .check(WebViewAssertions.webMatches(DriverAtoms.getText(), Matchers.containsString("android")));
        //执行点击返回按钮事件，关闭跳转到WebViewActivity
        Espresso.pressBack();
        test();
        testRecycleView();
        testWebView();
    }

}