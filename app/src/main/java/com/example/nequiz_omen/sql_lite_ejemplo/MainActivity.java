package com.example.nequiz_omen.sql_lite_ejemplo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*SE INSTANCIA UNA CONEXION Y SE LE COLOCAN LOS PARAMETROS */
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

    }


    //METODO  ONCLICK
    public void onClick(View view) {
        Intent miIntent = null;
        switch (view.getId()) {
            case R.id.btnOpcionRegistro:
                miIntent = new Intent(MainActivity.this, RegistroUsuariosActivity.class);
                break;
            case R.id.btnRegistroMascota:
                miIntent=new Intent(MainActivity.this,RegistroMascotaActivity.class);
                break;
            case R.id.btnConsultaIndividual:
                miIntent=new Intent(MainActivity.this,ConsultarUsuariosActivity.class);
                break;
            case R.id.btnConsultaSpinner:
                miIntent=new Intent(MainActivity.this,ConsultaComboActivity.class);
                break;
            case R.id.btnConsultaLista:
                miIntent=new Intent(MainActivity.this,ConsultarListaListViewActivity.class);
                break;
            case R.id.btnConsultaListaMascota:
                miIntent=new Intent(MainActivity.this,ListaMascotasActivity.class);
                break;
            case R.id.btnConsultaListaPersonasRecycler:
                miIntent=new Intent(MainActivity.this,ListaPersonasRecycler.class);
                break;
        }
        if (miIntent != null) {
            startActivity(miIntent);
        }
    }


}//END ACTIVITY MAIN
