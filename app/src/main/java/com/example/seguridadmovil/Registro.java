package com.example.seguridadmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private static SecretKeySpec secret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Button registrar_usuario = (Button) findViewById(R.id.button_registrar);
        Button volver = (Button) findViewById(R.id.button_volver_login);
        registro_email = findViewById(R.id.editText_correo);
        registro_contraseña = findViewById(R.id.editText_contraseña);
        verificar = findViewById(R.id.editText_verificar_contraseña);


        registrar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario_dato = registro_email.getText().toString();
                String clave = registro_contraseña.getText().toString();
                String verificar_dato = verificar.getText().toString();

                if (usuario_dato.equals("")||clave.equals("")||verificar_dato.equals("")){
                    validacion();

                    if(clave == verificar_dato){

                        verificacionContraseña();
                    }
                }
                else{
                    if (clave.equals("1")){
                        Intent camara = new Intent( Registro.this, MainActivity.class);
                        startActivity(camara);

                    }else{
                        Toast.makeText(getApplicationContext(),"Usuario Invalido", Toast.LENGTH_SHORT).show();
                    }
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
