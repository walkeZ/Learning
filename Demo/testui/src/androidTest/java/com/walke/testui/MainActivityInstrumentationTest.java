package com.walke.testui;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by walke.Z on 2018/6/11.
 * 作者：起了
 * 链接：https://www.jianshu.com/p/67c2f4ae9a6e
 * 遇到：Caused by: java.lang.RuntimeException: Unable to resolve activity for: Intent { act=android.intent.action.MAIN flg=0x14000000 cmp=com.walke.testui/.MainActivity }
 * 原因：没注册MainActivity
 */

public class MainActivityInstrumentationTest {

    private static final String STRING_TO_BE_TYPED = "Peter";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sayHello(){
        onView(withId(R.id.editText)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard()); //line 1
        System.out.println("-----------1----------->end");
        onView(withText("Say hello!")).perform(click()); //line 2

        String expectedText = "Hello, " + STRING_TO_BE_TYPED + "!";
        System.out.println("-----------2----------->end");
        onView(withId(R.id.textView)).check(matches(withText(expectedText))); //line 3
        System.out.println("-----------3----------->end");
    }


}
