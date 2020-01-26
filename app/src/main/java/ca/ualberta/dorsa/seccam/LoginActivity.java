package ca.ualberta.dorsa.seccam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
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
        // [START create_user_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("MYTAG", "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent logedInIntent = new Intent(getBaseContext(),   LogActivity.class);
                        startActivity(logedInIntent);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("MYTAG", "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                    // ...
                });


    }

    public void signUp(View view) {
        Intent signUpIntent = new Intent(getBaseContext(),   SignUp.class);
        startActivity(signUpIntent);



    }
}
