package com.example.encryption;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.encrypt.Encrypt;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Encrypt encrypt = new Encrypt();
        String name = "Abhay";

        System.out.println(encrypt.AESEncrypt(name) + " Encrypted string");
        try {
            System.out.println(encrypt.AESDecrypt(name) + " Decrypted string");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}