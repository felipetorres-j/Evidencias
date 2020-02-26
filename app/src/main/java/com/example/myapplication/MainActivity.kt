package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var  progressBar: ProgressBar
    var user: String =""

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtUser=findViewById(R.id.txtUser)
        txtPassword= findViewById(R.id.txtPassword)
        auth= FirebaseAuth.getInstance()

    }
    fun login(view:View){
        loginUser()
    }
    fun forgotPassword(view:View){
        startActivity(Intent(this,ForgotPassActivity::class.java ))
    }
    private fun loginUser(){
        user=txtUser.text.toString()
        val password:String=txtPassword.text.toString()

        if(!TextUtils.isEmpty(user)&& !TextUtils.isEmpty(password)){   //verifico que no esten vacios
            auth.signInWithEmailAndPassword(user,password)   //realizo la autenticacion en firebase
                .addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        action()
                    }
                    else{

                        Toast.makeText(this,"Error contraseña o email incorrectos",Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
    private fun action(){
        val intent = Intent(this,MenuSlideActivity::class.java)
        val b_user:Bundle = Bundle()
        b_user.putString("dt",user)
        intent.putExtras(b_user)  //mando variable a otra actividad
        startActivity(intent)
        //startActivity(Intent(this,MenuSlideActivity::class.java))
    }


}
