package com.example.seguridadmovil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

public class SQLite extends SQLiteOpenHelper {

    String sql = "CREATE TABLE Usuarios (Id TEXT, Correo TEXT, Nombre TEXT, Telefono INTEGER)";

    public SQLite(@Nullable OnCompleteListener<AuthResult> context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super((Context) context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuario");
        db.execSQL(sql);
    }
}
