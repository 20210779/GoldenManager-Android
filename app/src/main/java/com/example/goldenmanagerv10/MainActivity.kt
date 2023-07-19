package com.example.goldenmanagerv10

import ConnectSql
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.goldenmanagerv10.Controller.Activity_Principal
import com.example.goldenmanagerv10.Controller.Recuperar
import com.example.goldenmanagerv10.Model.Encrypt
import com.example.goldenmanagerv10.Model.Validate
import com.example.goldenmanagerv10.databinding.ActivityMainBinding
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException


lateinit var CajaTexto1: EditText
lateinit var CajaTexto2:EditText
lateinit var Boton: Button
lateinit var BotonR: Button

class MainActivity : AppCompatActivity() {

    private var connectSql=ConnectSql()
    private var Kript=Encrypt()
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        CajaTexto1=findViewById(R.id.txtEmail)
        CajaTexto2 = findViewById(R.id.txtPassword)
        Boton =findViewById(R.id.btnLogin)
        BotonR=findViewById(R.id.btnForgot)


        val ContraTexto= CajaTexto1.text.toString()
        val ContraPass= CajaTexto2.text.toString()

        //Comando para animación de login
        var fade_in= AnimationUtils.loadAnimation(this,R.anim.fade_in)
        var botton_down= AnimationUtils.loadAnimation(this,R.anim.botton_down)

        //Ajustando animacion para el panel superior en degradado del titulo de la app

        binding.topLinearLayout.animation= botton_down

        //creando controlador para otros layouts

        var handler=Handler()
        var runnable = Runnable{
            //Colocando la animation para la parte donde interactua el usuario
            binding.cardView.animation=fade_in
            binding.logo.animation=fade_in
            binding.textView.animation=fade_in
        }
        handler.postDelayed(runnable,1000)
        //Espero funcione

        Boton.setOnClickListener {

            var textoEmail= CajaTexto1.text.toString().trim()
            var textoPass= CajaTexto2.text.toString().trim()

            if(textoEmail.isEmpty()){
                CajaTexto1.error="Este campo esta vacio"
            }
            else if (textoPass.isEmpty()){
                CajaTexto2.error="Este campo esta vacio"

            }
            else{
                try {
                    //creamos el comando dentro de una variable
                    val cadena:String="SELECT Gmail,Contraseña from tbSesion  WHERE Gmail=? AND Contraseña=?;"
                    //Se conecta a la base de datos
                    val ps: PreparedStatement = connectSql.dbConn()?.prepareStatement(cadena)!!

                    val st: ResultSet
                    //usamos numeracion para obtener el texto de los datos

                    ps.setString(1, CajaTexto1.text.toString())
                    ps.setString(2, Kript.encriptar(CajaTexto2.text.toString(),"Key"))
                    //Ejecutamos la consulta
                    st = ps.executeQuery()
                    st.next()


                    val found = st.row

                    //Buscamos en la fila del campo ID, donde coincida con el usuario (1)

                    if (found == 1) {
                        var intent = Intent(this,Activity_Principal::class.java);
                        startActivity(intent)

                        Toast.makeText(applicationContext, "Usuario encontrado Kript: ", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "Datos incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
                catch (ex: SQLException)
                {
                    Log.e("Error de conexion: ",ex.message!!)
                }
            }

        }

        BotonR.setOnClickListener {
            var intent = Intent(this,Recuperar::class.java);
            startActivity(intent)
        }

        }

    }