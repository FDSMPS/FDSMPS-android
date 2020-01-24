package ca.ualberta.dorsa.seccam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import ca.ualberta.dorsa.myapplication.R;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private String phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }



    public void signUp(View view) {

        email = ((EditText)(findViewById(R.id.emailSignUp))).getText().toString();
        password = ((EditText)(findViewById(R.id.passwordSignUp))).getText().toString();
        phone = ((EditText)(findViewById(R.id.phoneSignUp))).getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("MYTAG", "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();


                        Intent signedUpIntent = new Intent(getBaseContext(),   LogActivity.class);
                        startActivity(signedUpIntent);

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("MYTAG", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUp.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                    // ...
                });


    }

    public void login(View view) {
        Intent loginIntent = new Intent(getBaseContext(),   LoginActivity.class);
        startActivity(loginIntent);

    }
}
