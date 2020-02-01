package ca.ualberta.dorsa.seccam.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import ca.ualberta.dorsa.seccam.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void ensureTextChangesWork() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        // Type text and then press the button.
        onView(withId(R.id.emailLogin))
                .perform(typeText("d@yahoo.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordLogin))
                .perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.signInButton)).perform(click());

        // Check that the text was changed.
        SharedPreferences sharedPref = appContext.getSharedPreferences(
                appContext.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        boolean sign_in = sharedPref.getBoolean(appContext.getString(R.string.saved_high_score_key),false);
        assertEquals(sign_in, "True");

    }
}