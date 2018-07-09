package com.example.nequiz_omen.sql_lite_ejemplo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nequiz_omen.sql_lite_ejemplo.entidades.Usuario;  /*SE TRAE DE ENTIDADES  USUARIOS*/
import com.example.nequiz_omen.sql_lite_ejemplo.utilidades.Utilidades;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ConsultaComboActivity extends AppCompatActivity {

    //declaracion de variable
    Spinner comboPersonas;
    TextView txtNombre,txtDocumento,txtTelefono;
    ArrayList <String> listaPersonas;  //representara la informacion del combo
    ArrayList <Usuario> personasList;  /*SE TRAE DE ENTIDADES  USUARIOS*/

    //SE INSTANCIA LA CONEXION CON EL NOMBRE DE conn
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);

        //busqueda del spinner que se  encuentra en el XML
        comboPersonas = (Spinner) findViewById(R.id.comboPersonas);

        txtNombre = (TextView) findViewById(R.id.txtDocumento);
        txtDocumento = (TextView) findViewById(R.id.txtNombre);
        txtTelefono = (TextView) findViewById(R.id.txtTelefono);


        consultarListaPersonas();   /*Metodo */

        //contruya el arrayadapter y le va a cargar  los datos
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);

        comboPersonas.setAdapter(adaptador);




        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /*GENERA LOS METODOS DEL ON ITEM  AL CARLE CLCIK AL SPINNER QUE ES LO QUE HARA*/
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 /*txtDocumento.setText(personasList.get(position).getId().toString());
                txtNombre.setText(personasList.get(position).getNombre());
                txtTelefono.setText(personasList.get(position).getTelefono());*/

                if (position!=0){
                    txtDocumento.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtTelefono.setText(personasList.get(position-1).getTelefono());
                    System.out.println(personasList + " ==> " + position  );
                }else{
                    txtDocumento.setText("");
                    txtNombre.setText("");
                    txtTelefono.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }//end on create



        //metodo
       private void consultarListaPersonas(){
           //traera los datos y declaramos un read pro que leera la base de datos    }
           SQLiteDatabase db = conn.getReadableDatabase();

           Usuario persona = null;
           personasList = new ArrayList<Usuario>();

           //SELECT * from usuarios:
           Cursor cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_USUARIO,null);  /*se pone null por que no estamos mandandole parametros */

           //SI HAY MAS DE UN REGISTRO ITERE SOBRE ESA COLECCION DE INFORMACION
           while(cursor.moveToNext()){
               persona=new Usuario();  //se va empezar a llenar y asignar  en  persona lo que llegue a nuestra base de datos  //viene desde  entidades de USUARIOS

               persona.setId(cursor.getInt(0));
               persona.setNombre(cursor.getString(1));
               persona.setTelefono(cursor.getString(2));

               Log.i("id ",persona.getId().toString());
               Log.i("Nombre ",persona.getNombre());
               Log.i("Tel ",persona.getTelefono());



               personasList.add(persona);
           }

           obtenerLista();
    }



    private void obtenerLista() {
           listaPersonas = new ArrayList<String>();
           listaPersonas.add("Seleccione");


           for (int i=0 ;i<personasList.size();i++){
               listaPersonas.add(personasList.get(i).getId()+" ==> " + personasList.get(i).getNombre());   //de la lista de personas obtener del primer objeot el id y lo concatene el nombre y se mostrara en el combo
            }
    }//END OBTENER LISTA



}
