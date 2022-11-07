package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, ete1, etpt1, etmt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1   = (EditText) findViewById  (R.id.plt_1);
        ete1  = (EditText)findViewById(R.id.txt_mail);
        etpt1 = (EditText) findViewById (R.id.txt_pt);
        etmt  = (EditText)findViewById(R.id.txt_multi);

        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        ete1.setText(preferencias.getString("mail", ""));
    }

    public void navegar_web (View view) {
        Intent web_ir = new Intent (this, MainActivity2.class);
        web_ir.putExtra("sitioWeb", et1.getText().toString());
        startActivity(web_ir);
    }
    public void button_Email(View ver) {
        SharedPreferences preferencias_2 = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editar = preferencias_2.edit();
        editar.putString("mail", ete1.getText() .toString());
        editar.commit();
        finish();
    }
    // de aca para abajo es un metodo para almacenar datos
    public void Guardar (View view) {
        String nombre = etpt1.getText().toString();
        String datos  =  etmt.getText().toString();

        SharedPreferences obj_preferencias = getSharedPreferences("Listado", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = obj_preferencias.edit();
        obj_editor.putString(nombre,datos);
        obj_editor.commit();

        Toast.makeText(this, "El contacto se a Guardado", Toast.LENGTH_SHORT).show();
    }
    public void Buscar (View ver) {
        String nombre = etpt1.getText().toString();

        SharedPreferences preferencias_obj = getSharedPreferences("Listado", Context.MODE_PRIVATE);
        String datos = preferencias_obj.getString(nombre,"");
        
        if (datos.length() == 0) {
            Toast.makeText(this, "No se a encontrado ningun registro", Toast.LENGTH_SHORT).show();
        } else {
            etmt.setText(datos);
        }
    }
}