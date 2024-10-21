package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editusername, edituserpass,getUsername;
    private String username,userpass;
    private CheckBox checkBox;
    private SharedPreferences preferences; // kayıt etmem gerek
    private SharedPreferences.Editor editor;
    private Boolean check;// editorde lazım


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editusername=findViewById(R.id.activity_Username);
        edituserpass=findViewById(R.id.activity_userpass);
        checkBox=findViewById(R.id.activity_checkbox);
        preferences=this.getSharedPreferences("com.example.sharedpreferences;", Context.MODE_PRIVATE); // paket id yaz telefonda sana alan açıyor.

       String getUsername=preferences.getString("username", null);
       check=preferences.getBoolean("checkbox", false);
       if (check &&  !TextUtils.isEmpty(getUsername)){
           editusername.setText(getUsername);
           checkBox.setChecked(check);


       }else {
           Toast.makeText(getApplicationContext(), "kayıt yok", Toast.LENGTH_SHORT).show();
       }

    }
    public  void btnsıgnIN(View v){
        // değer girişlerine bak
        username= editusername.getText().toString();
        userpass= edituserpass.getText().toString();
        // kontrolleri sağla
        if (!TextUtils.isEmpty(username)&& !TextUtils.isEmpty(userpass)){

            //checkbox kontrol et
            if (checkBox.isChecked()){
                //kayıt işlemini yap
                editor= preferences.edit();
                editor.putString("username " , username);
                editor.putBoolean("checkbox", true);
                editor.apply(); // veriyi içine kaydetmiş oluyor .


                Toast.makeText(getApplicationContext(),"başarıyla kayıt olunud", Toast.LENGTH_SHORT).show();


            } else{

                editor= preferences.edit();
                editor.putString("username " , null);
                editor.putBoolean("checkbox", false );
                editor.apply();
                Toast.makeText(getApplicationContext(), "başarıyla giriş yaptınız", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(getApplicationContext(), "boş değer olamaz", Toast.LENGTH_SHORT).show();
        }


    }
}