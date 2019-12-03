package com.example.seguridadmovil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;




public class MainActivity extends AppCompatActivity {




    EditText email;
    EditText contraseña;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button iniusu = (Button) findViewById(R.id.button_iniciar);
        Button registrar = (Button) findViewById(R.id.button_registro);
        email = findViewById(R.id.edit_email);
        contraseña = findViewById(R.id.edit_password);

        //--------------------------------------------------------------------
        iniusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario_dato = email.getText().toString();
                String contraseña_dato = contraseña.getText().toString();

                if (usuario_dato.equals("")||contraseña_dato.equals("")){
                    validacion();
                }else{
                        signInWithEmailAndPassword(usuario_dato, contraseña_dato);
                }
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registrar = new Intent( MainActivity.this, Registro.class);
                startActivity(registrar);
            }
        });
        //----------------------------------------------------------------

        mAuth = FirebaseAuth.getInstance();

    }



    private void signInWithEmailAndPassword(String email2, String password2){
        Log.d("emailpass", "signInWithEmailAndPassword: "+email2+" - "+password2);
        mAuth.signInWithEmailAndPassword(email2, password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "signInWithEmail:success");

                            startActivity(new Intent( MainActivity.this, Home.class));
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w("", "signInWithEmail:failure", task.getException());
                            Log.d("halo", "signInWithEmailAndPassword: errrrrroooooor");
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }


    private void validacion() {

        String usuario_dato = email.getText().toString();
        String contraseña_dato = contraseña.getText().toString();

        if (usuario_dato.equals("")){
            email.setError("Required");
        }
        if (contraseña_dato.equals("")){
            contraseña.setError("Required");
        }
    }


}
