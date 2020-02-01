package ca.ualberta.dorsa.seccam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import ca.ualberta.dorsa.seccam.R;
import ca.ualberta.dorsa.seccam.entities.User;

/**
 * The type Sign up activity.This activity allows the user to sign up to create an account
 * Executed UI tested yet to be unit tested
 *  @author Dorsa Nahid
 *  @date 2020-1-31
 *  Project: ECE 492 Group 1
 */
public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private String phone;
    private String confirmPassword;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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


    /**
     * Sign up, the main logic to sign up a user and put the information on database
     *
     * @param view the view
     */
    public void signUp(View view) {

        //use to actually create accounts

        email = ((EditText) (findViewById(R.id.emailSignUp))).getText().toString();
        password = ((EditText) (findViewById(R.id.passwordSignUp))).getText().toString();
        phone = ((EditText) (findViewById(R.id.phoneSignUp))).getText().toString();
        confirmPassword = ((EditText) (findViewById(R.id.passwordConfirm))).getText().toString();
        name = ((EditText) (findViewById(R.id.userFullName))).getText().toString();

        if (phone.length() != 10) {
            Toast.makeText(SignUpActivity.this, "Please enter your phone!", Toast.LENGTH_SHORT).show();
        }
        if ((email.trim()).length()==0 | password.trim().length()==0 | phone.trim().length()== 0 | confirmPassword.trim().length() ==0  | name.trim().length() == 0){
            Toast.makeText(SignUpActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
        }

        else {

            if (password.equals(confirmPassword)) {

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("MYTAG", "createUserWithEmail:success");


                                FirebaseUser user = mAuth.getCurrentUser();
                                User userObj = new User(name, email, phone, user.getUid(), null, null, null);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(userObj);

                                Intent signedUpIntent = new Intent(getBaseContext(), LogActivity.class);
                                signedUpIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(signedUpIntent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("MYTAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(SignUpActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();

            }
        }


    }

    /**
     * Login, switches the activity to the login activity
     *
     * @param view the view
     */
    public void login(View view) {
        Intent loginIntent = new Intent(getBaseContext(),   LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();

    }
}
