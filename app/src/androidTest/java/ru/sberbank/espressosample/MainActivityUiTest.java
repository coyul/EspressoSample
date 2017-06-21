package ru.sberbank.espressosample;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.sberbank.espressosample.activities.MainActivity;
import ru.sberbank.espressosample.activities.NewActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.*;


@RunWith(AndroidJUnit4.class)
public class MainActivityUiTest {

    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule = new IntentsTestRule<>(MainActivity.class);


    @Test
    public void testButtonClick() {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.center_text)).check(matches(withText("Hello World!")));
    }

    @Test
    public void testButtonLongClick() {
        onView(withId(R.id.button)).perform(longClick());
        onView(withId(R.id.invisible_text)).check(matches(isDisplayed()));
    }

    @Test
    public void testViewsFilling() {
        onView(withId(R.id.name)).perform(typeText("TesterName"));
        onView(withId(R.id.name)).check(matches(withText("TesterName")));

        onView(withId(R.id.checkbox)).perform(click());
        onView(withId(R.id.checkbox)).check(matches(isChecked()));
    }

    @Test
    public void testResultButtonClick() {
        onView(withId(R.id.result_button)).perform(click());
        intended(hasComponent(NewActivity.class.getName()));
    }
}
