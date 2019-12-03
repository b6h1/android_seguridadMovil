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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Registro extends AppCompatActivity {

    static  String clave = "";
    EditText registro_email;
    EditText registro_contraseña;
    EditText verificar;
    EditText nombreText;
    EditText telefonoText;
    //-----------------------------------------------------
    private static SecretKeySpec secret;
    private FirebaseAuth mAuth;

    private DatabaseReference mDatabaseRef;//------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button registrar_usuario = (Button) findViewById(R.id.button_registrar);
        Button volver = (Button) findViewById(R.id.button_volver_login);
        registro_email = findViewById(R.id.editText_correo);
        registro_contraseña = findViewById(R.id.editText_contraseña);
        verificar = findViewById(R.id.editText_verificar_contraseña);
        nombreText = findViewById(R.id.editText_nombre);
        telefonoText = findViewById(R.id.editText_telefono);

        mAuth = FirebaseAuth.getInstance();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("usuarios");


        registrar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //

                String usuario_dato = registro_email.getText().toString();
                String clave = registro_contraseña.getText().toString();
                String verificar_dato = verificar.getText().toString();
                String nombre1 = nombreText.getText().toString();
                String telefono1 = telefonoText.getText().toString();

                if (usuario_dato!=""&&clave!=""&&verificar_dato!=""){
                    //validacion();






                        Toast.makeText(getApplicationContext(),"Usuario", Toast.LENGTH_SHORT).show();
                        createUserWithEmailAndPassword(usuario_dato, clave);




                }
                else{

                        Toast.makeText(getApplicationContext(),"Usuario Invalido", Toast.LENGTH_SHORT).show();

                }
            }
        });


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent volver = new Intent( Registro.this, MainActivity.class);
                startActivity(volver);
            }
        });


    }


    private void createUserWithEmailAndPassword(String email1,String password1){
        mAuth.createUserWithEmailAndPassword(email1, password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "createUserWithEmail:success");

                            String llave = mAuth.getUid();
                            String usuario_dato = registro_email.getText().toString();
                            String nombre1 = nombreText.getText().toString();
                            String telefono1 = telefonoText.getText().toString();


                            //verificacionContraseña();
                            Usuario u = new Usuario(llave, usuario_dato, nombre1, telefono1);
                           // u.setId("111");
                           // u.setCorreo(usuario_dato);
                            //u.setNombre(nombre1);
                           // u.setTelefono(telefono1);
                            Log.d("llave", "onComplete: "+llave);

                            mDatabaseRef.child(llave).setValue(u);

                            startActivity(new Intent( Registro.this, MainActivity.class));
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w("", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registro.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }


    //public static SecretKey generateKey() throws


    public static SecretKey generateKey() throws NoSuchAlgorithmException, InvalidKeyException {
        return secret = new SecretKeySpec(clave.getBytes(), "AES");
    }

    public static byte[] encryptMsg(String message, SecretKey secret) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] cipherText = cipher.doFinal(message.getBytes("UTF-8"));
        return cipherText;
    }


    public static String decrryptMsg(byte[] cipherText, SecretKey secret) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        String decryptString = new String(cipher.doFinal(cipherText), "UTF-8");
        return decryptString;
    }
//encryptMsg(contraseña,generateKey());


    private void validacion() {

        String usuario_dato = registro_email.getText().toString();
        String contraseña_dato = registro_contraseña.getText().toString();
        String verificar_dato = verificar.getText().toString();

        if (usuario_dato.equals("")){
            registro_email.setError("Required");
        }
        if (contraseña_dato.equals("")){
            registro_contraseña.setError("Required");
        }
        if (verificar_dato.equals("")){
            verificar.setError("Required");
        }
    }

    private void verificacionContraseña() {
        String contraseña_dato = registro_contraseña.getText().toString();
        String verificacion_dato = verificar.getText().toString();

        if (contraseña_dato != verificacion_dato) {
            verificar.setError("Required");
        }

    }

}
