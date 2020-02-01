package ca.ualberta.dorsa.seccam.activities;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import ca.ualberta.dorsa.seccam.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
    public void ensureTextChangesWork() {
        // Type text and then press the button.
        onView(withId(R.id.emailLogin))
                .perform(typeText("d@yahoo.com"), closeSoftKeyboard());
        onView(withId(R.id.passwordLogin))
                .perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.signInButton)).perform(click());

        // Check that the text was changed.
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        boolean sign_in = sharedPref.getBoolean(context.getString(R.string.saved_high_score_key), false);
        assertTrue(sign_in);

    }
}