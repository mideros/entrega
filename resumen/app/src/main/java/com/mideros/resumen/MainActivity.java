package com.mideros.resumen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre, apellido, web, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        telefono = findViewById(R.id.telefono);
        web = findViewById(R.id.web);
    }

    public void goToSecondActivity(View view) {

        if (checkFields()) {
            Toast.makeText(this, getString(R.string.userOK), Toast.LENGTH_SHORT).show();
            String text_nombre= nombre.getText().toString();
            String text_apellido=apellido.getText().toString();
            String text_web=web.getText().toString();
            int text_telefono=Integer.parseInt(telefono.getText().toString());

            Intent intent = new Intent(MainActivity.this, secondActivity.class);
            intent.putExtra("nombre",text_nombre);
            intent.putExtra("apellido",text_apellido );
            intent.putExtra("web",text_web);
            intent.putExtra("telefono",text_telefono);

            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.errorEmpy), Toast.LENGTH_SHORT).show();
        }


    }

    public boolean checkFields() {
        boolean fieldsOk = true;

        if ("".equals(nombre.getText().toString())) {
            fieldsOk = false;
            nombre.setError(getString(R.string.errorEmpyNombre));
        }
        if ("".equals(apellido.getText().toString())) {
            fieldsOk = false;
            apellido.setError(getString(R.string.errorEmpyApellido));
        }
        if ("".equals(web.getText().toString())) {
            fieldsOk = false;
            web.setError(getString(R.string.errorEmpyWeb));
        }
        if ("".equals(telefono.getText().toString())) {
            fieldsOk = false;
            telefono.setError(getString(R.string.errorEmpyTelefono));
        }

        return fieldsOk;
    }

    private void deleteFields() {
        nombre.setText("");
        apellido.setText("");
        web.setText("");
        telefono.setText("");
    }

    public void clearMessage(View view) {
        openDialog();
    }

    public void openDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle(getString(R.string.app_name));
        alertDialogBuilder.setMessage("¿Seguro que desea borrar todos los campos?")
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       deleteFields();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
