package com.example.seguridadmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                    if (usuario_dato.equals("1") && contraseña_dato.equals("1")){
                        Intent camara = new Intent( MainActivity.this, Home.class);
                        startActivity(camara);

                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario Invalido", Toast.LENGTH_SHORT).show();
                    }
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
        FirebaseUser currentUser = mAuth.getCurrentUser();
       /*if(currentUser != null){
           mAuth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               // Sign in success, update UI with the signed-in user's information
                               Log.d(TAG, "createUserWithEmail:success");
                               FirebaseUser user = mAuth.getCurrentUser();
                               updateUI(user);
                           } else {
                               // If sign in fails, display a message to the user.
                               Log.w(TAG, "createUserWithEmail:failure", task.getException());
                               Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                       Toast.LENGTH_SHORT).show();
                               updateUI(null);
                           }

                           // ...
                       }
                   });
       }*/
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
