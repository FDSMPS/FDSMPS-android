package ca.ualberta.dorsa.seccam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import ca.ualberta.dorsa.myapplication.R;

public class SignUp extends AppCompatActivity {
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

        //Use to test!!!! If you are lazy like me

//        email = Math.random() * 1000 + 50 + "dorsa@yahoo.cpm";
//        password = "123456";
//        phone = "7807809123";
//        confirmPassword = "123456";
//        name = "joh smith";

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }



    public void signUp(View view) {

        //use to actually create accounts

        email = ((EditText) (findViewById(R.id.emailSignUp))).getText().toString();
        password = ((EditText) (findViewById(R.id.passwordSignUp))).getText().toString();
        phone = ((EditText) (findViewById(R.id.phoneSignUp))).getText().toString();
        confirmPassword = ((EditText) (findViewById(R.id.passwordConfirm))).getText().toString();
        name = ((EditText) (findViewById(R.id.userFullName))).getText().toString();

        if (phone.length() != 10) {
            Toast.makeText(SignUp.this, "Please enter your phone!", Toast.LENGTH_SHORT).show();
        }

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
                            startActivity(signedUpIntent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MYTAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed." + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(SignUp.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();

        }


    }

    public void login(View view) {
        Intent loginIntent = new Intent(getBaseContext(),   LoginActivity.class);
        startActivity(loginIntent);

    }
}
