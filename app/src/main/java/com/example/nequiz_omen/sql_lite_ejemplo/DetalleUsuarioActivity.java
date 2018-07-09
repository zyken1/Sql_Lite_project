package com.example.nequiz_omen.sql_lite_ejemplo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nequiz_omen.sql_lite_ejemplo.entidades.Usuario;

public class DetalleUsuarioActivity extends AppCompatActivity {

    TextView campoId, campoNombre, campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);


        campoId = (TextView) findViewById(R.id.campoId);
        campoNombre = (TextView) findViewById(R.id.campoNombre);
        campoTelefono = (TextView) findViewById(R.id.campoTelefono);

        Bundle objetoEnviado=getIntent().getExtras();  //instanciar el Bundle
        Usuario user=null;

        if(objetoEnviado!=null){
            user= (Usuario) objetoEnviado.getSerializable("usuario");
            campoId.setText(user.getId().toString());
            campoNombre.setText(user.getNombre().toString());
            campoTelefono.setText(user.getTelefono().toString());

            System.out.println("********Objeto Recibido ====>  " +objetoEnviado);
            System.out.println("********Bundle Recibido ====>  " + user);
        }


    }
}
