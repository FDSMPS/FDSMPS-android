package ca.ualberta.dorsa.seccam.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import ca.ualberta.dorsa.seccam.R;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        sharedPref = getApplicationContext().getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void login(View view) {

        email = ((EditText)(findViewById(R.id.emailLogin))).getText().toString();
        password = ((EditText)(findViewById(R.id.passwordLogin))).getText().toString();
        if ((email.trim()).length()==0 | password.trim().length()==0){
            Toast.makeText(LoginActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
        }
        else {
            // [START create_user_with_email]
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MYTAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            editor = sharedPref.edit();
                            editor.putBoolean(getString(R.string.saved_high_score_key), true);
                            editor.putString("UID", user.getUid());
                            editor.apply();

                            Intent logedInIntent = new Intent(getBaseContext(), LogActivity.class);
                            logedInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(logedInIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MYTAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            editor = sharedPref.edit();
                            editor.putBoolean(getString(R.string.saved_high_score_key), false);
                            editor.apply();

                        }
                    });
        }
    }

    public void signUp(View view) {
        Intent signUpIntent = new Intent(getBaseContext(),   SignUpActivity.class);
        signUpIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(signUpIntent);
        finish();

    }
}
