package com.example.nequiz_omen.sql_lite_ejemplo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nequiz_omen.sql_lite_ejemplo.utilidades.Utilidades;

public class RegistroUsuariosActivity extends AppCompatActivity {

    //se declaran las variables
    EditText campoId,campoNombre,campoTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        //se buscan los ID
        campoId = (EditText)findViewById(R.id.campoId);
        campoNombre = (EditText) findViewById(R.id.campoNombre);
        campoTelefono = (EditText) findViewById(R.id.campoTelefono);

    }

    public void onClick(View view){
        //registrarUsuarios();     //SE CREA UN METODO DE LA ACCION QUE HARA  CUANDO SE DE CLICK
        registrarUsuariosSQL();   //SE CRE AUN METODO PARA INSERTAR DATOS MEDIANTE SQL
    }

    private void registrarUsuariosSQL() {
         //Se define cual e sla base de datos
           ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        //Abrir conexion para escritura
          SQLiteDatabase  db = conn .getWritableDatabase();

          //insert into usuario (id,nombre,telefono) value (123,'Cristian','123456789')
       /* String insert ="INSERT INTO "+Utilidades.TABLA_USUARIO+" ("+
                                      Utilidades.CAMPO_ID+","+
                                      Utilidades.CAMPO_NOMBRE+","+
                                      Utilidades.CAMPO_TELEFONO+" )" +
                "VALUES("+campoId.getText().toString() +",'"+
                          campoNombre.getText().toString()+ "','"+
                          campoTelefono.getText().toString()+"' )" ;*/

        String insert="INSERT INTO "+Utilidades.TABLA_USUARIO+" ( " +Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+")" +
                " VALUES ("+campoId.getText().toString()+", '"+campoNombre.getText().toString()+"','"
                +campoTelefono.getText().toString()+"')";

        db.execSQL(insert);  //ejecuta lo que hay en el insert

        Toast.makeText(getApplicationContext(),"USUARIO REGISTRADO",Toast.LENGTH_SHORT).show();
        System.out.println("*********  Query ejecutado en la base  ====>  " + insert);
        System.out.println("*********  BASE DE DATOS ====>   " + conn);
        db.close();

    }


    private void registrarUsuarios() {
        /*SE INSTANCIA UNA CONEXION Y SE LE COLOCAN LOS PARAMETROS */
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        //Se abre la conexion para poder der editada
        SQLiteDatabase  db = conn .getWritableDatabase();

        ContentValues values= new ContentValues();    //con el content y el put se va agregar una clave y un valor  COMO EN EL HASH
        values.put(Utilidades.CAMPO_ID,campoId.getText().toString());            //De utilidades escirbe en CAMPO_ID  lo que este en el Texto de campoId
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());       //De utilidades escirbe en CAMPO_NOMBRE  lo que este en el Texto de campoNombre
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());   //De utilidades escirbe en CAMPO_TELEFONO  lo que este en el Texto de campoTelefono

        //INSERTAR EN LA BASE DE DATOS
        Long idResultante = db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);  //con values le mandamos todos los aparametros correspondientes a ese ID

        Toast.makeText(getApplicationContext(),"Id Registro:" + idResultante,Toast.LENGTH_SHORT).show();
        db.close();   //se cierra la conexion
    }


}
