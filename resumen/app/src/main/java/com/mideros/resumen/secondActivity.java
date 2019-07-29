package com.mideros.resumen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {

    TextView text_nombre, text_apellido, text_web, text_telefono, text_contador;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        prefs = getSharedPreferences("MyPreferences",MODE_PRIVATE);
        int counter= prefs.getInt("counter", 0);

        counter = counter + 1;

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("counter", counter);
        editor.commit();


        text_nombre=findViewById(R.id.text_nombre);
        text_apellido=findViewById(R.id.text_apellido);
        text_web= findViewById(R.id.text_web);
        text_telefono= findViewById(R.id.text_telefono);
        text_contador=findViewById(R.id.text_contador);



        String nombre= getIntent().getStringExtra("nombre");
        String apellido= getIntent().getStringExtra("apellido");
        String web=getIntent().getStringExtra("web");
        int telefono =getIntent().getIntExtra("telefono",0);


        text_nombre.setText(nombre);
        text_apellido.setText(apellido);
        text_web.setText(web);
        text_telefono.setText(String.valueOf(telefono));
        text_contador.setText(String.valueOf(counter));
    }

    public void clear(View view) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        text_contador.setText(String.valueOf(prefs.getInt("counter", 0)));
    }

    public void abrirDial(View view) {

        int telefono =getIntent().getIntExtra("telefono",0);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(String.valueOf("tel:"+telefono)));
        startActivity(intent);
    }

    public void abrirN(View view) {
        String web=getIntent().getStringExtra("web");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://"+web));
        startActivity(intent);
    }
}
