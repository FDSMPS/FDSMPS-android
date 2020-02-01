package ca.ualberta.dorsa.seccam.activities;

import android.app.Instrumentation;
import android.content.Context;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import ca.ualberta.dorsa.seccam.activities.LogActivity;
import ca.ualberta.dorsa.seccam.activities.LoginActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class LoginActivityTest {


    private Context context;

    @Before
    public void setup() {

        context = ApplicationProvider.getApplicationContext();
        assertNotNull(context);
    }

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void ensureActivityChange() {
        // Type text and then press the button.
        onView(withId(R.id.emailLogin))
                .perform(typeText("d@yahoo.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordLogin))
                .perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.signInButton)).perform(click());
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(LogActivity.class.getName(), null, false);

        LoginActivity myActivity = this.mActivityRule.getActivity();

        LogActivity nextActivity = (LogActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertEquals(nextActivity.getClass().getName(), LogActivity.class.getName());
        nextActivity.finish();
    }
}