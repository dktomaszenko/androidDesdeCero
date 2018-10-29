package com.example.dktomaszenko.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_codigo, et_descripcion, et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_codigo = (EditText)findViewById(R.id.et_codigo);
        et_descripcion = (EditText)findViewById(R.id.et_descripcion);
        et_precio = (EditText)findViewById(R.id.et_precio);
    }

    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            db.insert("articulos", null, registro);

            db.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

        } else {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila = db.rawQuery
                    ("select descripcion, precio from articulos where codigo =" + codigo, null);

            if(fila.moveToFirst()){
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));
                db.close();
            } else {
                Toast.makeText(this, "No se ha encontrado producto con este código.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Debes introducir código", Toast.LENGTH_LONG).show();
        }
    }
}
