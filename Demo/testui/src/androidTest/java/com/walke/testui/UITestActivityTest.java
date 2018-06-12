package com.walke.testui;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by walke.Z on 2018/6/6.
 * https://www.jianshu.com/p/67c2f4ae9a6e
 *
 * https://www.jianshu.com/p/b373aec43c01
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITestActivityTest {

    private static final String STRING_TO_BE_TYPE="Jon";

    @Rule
    public ActivityTestRule<UITestActivity> mActivityTestRule=new ActivityTestRule<>(UITestActivity.class);

    @Test
    public void sayHello(){
//
//        // 1.找到ID为editText的view，输入Peter，然后关闭键盘
//        onView(withId(R.id.ui_editText)).perform(typeText(STRING_TO_BE_TYPE), closeSoftKeyboard()); //line 1
//
//        // 2.接下来，点击Say hello!的View，我们没有在布局的XML中为这个Button设置id，因此，通过搜索它上面的文字来找到它；
////        onView(withText("Say hello!")).perform(click()); //line 2
//        onView(withId(R.id.ui_button)).perform(ViewActions.click()); //line 2
//
//        // 3.最后，将TextView上的文本同预期结果对比，如果一致则测试通过；
//        String expectedText = "Hello," + STRING_TO_BE_TYPE + "!";
////        Matcher<View> viewMatcher = withText(expectedText);
////        ViewAssertion mc = matches(viewMatcher);
//        System.out.println("--------> expectedText = "+expectedText);
//        try {
//            onView(withId(R.id.ui_textView)).check(matches(withText(expectedText))); //line 3
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("--------");
////        Matcher<View> viewMatcher = withText(expectedText);
////        ViewAssertion matches = matches(viewMatcher);
////        Matcher<View> viewMatcher1 = withId(R.id.ui_textView);
////        onView(viewMatcher1).check(matches(isDisplayed())); //line 3


        // 1.找到ID为editText的view，输入Peter，然后关闭键盘
        onView(withId(R.id.ui_editText)).perform(typeText(STRING_TO_BE_TYPE), closeSoftKeyboard()); //line 1
        System.out.println("-----------1----------->end");
        //2.接下来，点击Say hello!的View，我们没有在布局的XML中为这个Button设置id，因此，通过搜索它上面的文字来找到它；
        //.perform(click())： click() 方法模拟点击该按钮；//故在Activity找那个响应该文本对应的控件的点击事件
        onView(withText("Say hello!")).perform(click()); //line 2

        String expectedText = "Hello, " + STRING_TO_BE_TYPE + "!";
        System.out.println("-----------2----------->end");
        // 3.最后，将TextView上的文本同预期结果对比，如果一致则测试通过；否则报错
        onView(withId(R.id.ui_textView111)).check(matches(withText(expectedText))); //line 3
        System.out.println("-----------3----------->end");

    }



}
