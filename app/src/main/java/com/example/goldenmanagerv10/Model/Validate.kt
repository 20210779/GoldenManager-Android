package com.example.goldenmanagerv10.Model

class  Validate {
    private var Access=false
    fun validarEmail(textE:String): String {
        var Mensaje=""
        if (textE==""){
            Access=true
            Mensaje="Ninguno de los campos no debe estar vacio"

        }
        return Mensaje
    }

    constructor(){
        var Mensaje=""
    }

    fun validarLogin(textE:String,textP:String): String {
        var Mensaje=""
        if (textE==""||textP==""){
            Access=true
            Mensaje="Ninguno de los campos no debe estar vacio"
        }
        return Mensaje
    }

    fun validarnewPass(textP:String): String {
        var Mensaje=""
        if (textP==""){

            Mensaje="El campo no debe estar vacio"

        }
        else if (textP.length<=8){
            Mensaje="La contraseña no debe ser larga"
        }
        return Mensaje
    }

}