package com.example.nequiz_omen.sql_lite_ejemplo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nequiz_omen.sql_lite_ejemplo.utilidades.Utilidades;

public class ConsultarUsuariosActivity extends AppCompatActivity {

    //Declaracion de variables
    EditText campoId,campoNombre,campoTelefono;

    //Se genera LA INSTANCIA para ocnexion para la conexion ala BD
    ConexionSQLiteHelper conn ;   /*A LA CONEXION SE LE NOMBRE conn   Y ESA VARIABLE SE DECLARA NUEVAMENTE MAS ABAJO */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);

        //VARIABLE  conn    y dentro de los corchetes  van los PARAMETROS de la base de datos
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios",null,1);


        //BUSQUEDA DE BOTONES
        campoId = (EditText) findViewById(R.id.documentoId);
        campoNombre = (EditText) findViewById(R.id.campoNombreConsulta);
        campoTelefono = (EditText) findViewById(R.id.campoTelefonoConsulta);


    }


        //Evento on click
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnConsultar:
               // consultar();    /*METODO CONSULTAR*/
                consultarSQL();
                break;
            case R.id.btnActualizar:
                actualizar_Usuario();
                break;
            case R.id.btnEliminar:
                eliminar_Usuario();
                break;
        }//END SWITCH
    }

    private void eliminar_Usuario() {
        SQLiteDatabase db = conn.getReadableDatabase();

        String [] parametros = {campoId.getText().toString()};  /*PARAMETROS DE CONSULTA*/

        //metodo de eliminar el registro y que tabla se eliminara
        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID + "=?" ,parametros);  //se eliminara lo que hay en parametros  que seria el dato que contenga el ID
        Toast.makeText(getApplicationContext(),"USUARIO ELIMINADO",Toast.LENGTH_SHORT).show();
        db.close(); //cerrar conexion

        campoId.setText("");
        limpiar();
    }


    private void actualizar_Usuario() {
           //s abre conexion y que sea READ por que queremos escribir sobre la base de datos
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {campoId.getText().toString()};
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString()); // LA LLAVE SERIA EL NOMBRE  COLOCANDO EL DATO  QUE ESTA EN campoNombre
        values.put(Utilidades.CAMPO_TELEFONO,campoTelefono.getText().toString());

        //El que se encargara de ese proceso de actualizacion sera
        db.update(Utilidades.TABLA_USUARIO, values,Utilidades.CAMPO_ID+"=?",parametros);  //se manda el parametro que este dentor de campo ID

        System.out.println("*********Actualizacion de BD =======> " + values);
        Toast.makeText(getApplicationContext(),"SE ACTUALIZO",Toast.LENGTH_SHORT).show();
        db.close(); //cerrar conexion
    }



    private void consultarSQL() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {campoId.getText().toString()};   //se incluye al final del query

        try {  /*si  sale bien */
            //select nombre,telefono from tablausuario where codigo = y
            /*Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+
                    " FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_ID+"=? ",parametros);*/

            Cursor cursor=db.rawQuery("SELECT " +Utilidades.CAMPO_NOMBRE+ "," +Utilidades.CAMPO_TELEFONO +" FROM " +Utilidades.TABLA_USUARIO+ " WHERE " +Utilidades.CAMPO_ID+ "=?",parametros);

            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));     //lo primero que nos v a adevolver es CAMPO_NOMBRE
            campoTelefono.setText(cursor.getString(1));   //lo primero que nos v a adevolver es CAMPO_TELEFONO

        } catch (Exception e) {   /* SI sale cualquier error */
            Toast.makeText(getApplicationContext(), "EL documento no existe", Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }



    private void consultar() {
        /*Crear un SQL database  que se llamara  db  que sea igual a ese ojeto conexion   */
        SQLiteDatabase db = conn.getReadableDatabase();
        String [] parametros ={campoId.getText().toString()};    /*se genrera un arreglo en los corchetes      si quisieramos mas parametros en la consutla se pondria una  coma ejemplo String [] parametros ={campoId.getText().toString() , "aqui va le parametro" };*/
        //definir los campos que nos devolvera esa consulta   se crea un arreglo pero ahora sera de campos
        String [ ] campos = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};


        try {  /*si  sale bien */
                //construir structura necesaria para mandar los campos         CON ESTE =?  SE REMPLAZARAN LOS PARAMETROS
                Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID + "=?" , parametros,null,null,null);
                //le indicamos que se coloque en el primer registro que se devuleva y asignarlo a los primeros parametros
                cursor.moveToFirst();
                campoNombre.setText(cursor.getString(0));     //lo primero que nos v a adevolver es CAMPO_NOMBRE
                campoTelefono.setText(cursor.getString(1));   //lo primero que nos v a adevolver es CAMPO_TELEFONO
                cursor.close();

        }catch  (Exception e){   /* SI sale cualquier error */
            Toast.makeText(getApplicationContext(),"EL documento no existe",Toast.LENGTH_SHORT).show();
            //se crea un metodo para limpiar todos los campos
            limpiar();
        }
    }//END CONSULTAR



    private void limpiar() {
        campoNombre.setText("");
        campoTelefono.setText("");
    }



}
