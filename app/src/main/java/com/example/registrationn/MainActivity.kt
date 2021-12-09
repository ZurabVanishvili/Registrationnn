package com.example.registrationn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var repeatpassword : EditText
    private lateinit var loginbutton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        registration()

    }
    private fun init(){
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        repeatpassword = findViewById(R.id.repeatpassword)
        loginbutton = findViewById(R.id.login)
    }

    private fun registration(){
        loginbutton.setOnClickListener(){
            val reademail = email.text.toString().trim()
            val readpassword = password.text.toString().trim()
            val readpassword2 = repeatpassword.text.toString().trim()


            if (reademail.isEmpty()|| readpassword.isEmpty() || readpassword2.isEmpty()){
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }else if (readpassword != readpassword2){
                Toast.makeText(this, "Passwords doesn't match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }else if (!reademail.contains("@.".toRegex())){
                Toast.makeText(this, "Incorrect E-mail", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }else if (!readpassword.contains("[a-z]".toRegex() ) || (!readpassword.contains(Regex("[0-9]"))) || (!readpassword.contains(".*[#@!$%^&*()].*".toRegex())) ){
                Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }else if(readpassword.length<9){
                Toast.makeText(this, "Password must contain minimum 9 character", Toast.LENGTH_SHORT).show()
                
            }else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(reademail , readpassword)
                    .addOnCompleteListener(this){task ->
                        if (task.isSuccessful){       
                            Toast.makeText(this, "Registration was Succesful", Toast.LENGTH_SHORT).show()
                        }

                    }

            }
        }

    }

}
            
