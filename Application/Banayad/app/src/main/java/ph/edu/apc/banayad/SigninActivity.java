package ph.edu.apc.banayad;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        final EditText signupEmail = (EditText) findViewById(R.id.editText_signup_email);
        final EditText signupPassword = (EditText) findViewById(R.id.editText_signup_password);
        Button btnSignup = (Button) findViewById(R.id.button_login);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(signupEmail.getText());
                String password = String.valueOf(signupPassword.getText());
                createAccount(email, password);
            }
        });
    }

    private void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SigninActivity.this, "Sign in failed",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("Signin", "createUserWithEmail:onComplete:" + task.isSuccessful());
                            Intent intent = new Intent(SigninActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(SigninActivity.this,
                                    "Sign in successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
