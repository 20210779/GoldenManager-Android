package com.example.goldenmanagerv10.Model

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

class Encrypt {
    private var Acesso=false

    @RequiresApi(Build.VERSION_CODES.O)
    public fun Prueba(Permitir:String){
        if (Permitir=="1"){
            val datosEncriptados = "HysY7A/KFZ+d8GljSziNGA=="
            val datosDesEncriptados = desencriptar(datosEncriptados, "Totita12!")
            println(datosDesEncriptados)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun encriptar(Datos:String, Password:String):String{

        val cipher = Cipher.getInstance("Blowfish")
        val secretKey: SecretKey = SecretKeySpec(Password.toByteArray(),"Blowfish")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedBytes = cipher.doFinal(Datos.toByteArray())
        val base64Encoded = Base64.getEncoder().encodeToString(encryptedBytes)
        return base64Encoded
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public fun desencriptar(Datos:String, Password:String):String{
        val cipher = Cipher.getInstance("Blowfish")
        val secretKey: SecretKey = SecretKeySpec(Password.toByteArray(),"Blowfish")
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val encryptedBytes = Base64.getDecoder().decode(Datos)
        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes)
    }
}