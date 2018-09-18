package com.example.werho.call;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.alhazmy13.gota.Gota;
import net.alhazmy13.gota.GotaResponse;

public class MainActivity extends AppCompatActivity implements Gota.OnRequestPermissionsBack {
    Button button;
    EditText phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Gota.Builder(this)
                .withPermissions(Manifest.permission.CALL_PHONE)
                .requestId(1)
                .setListener(this)
                .check();
        button = (Button)findViewById(R.id.button);
        phone_number = (EditText)findViewById(R.id.exitText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone_number.getText().toString()));
                if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED)
                    startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestBack(int requestId, @NonNull GotaResponse gotaResponse) {
        if(gotaResponse.isGranted(Manifest.permission.CALL_PHONE)){
            // Your Code
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
