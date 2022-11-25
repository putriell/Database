package com.example.tugasdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CariBuku extends AppCompatActivity {

    EditText input;
    Button cari;
    TextView result;
    private DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_buku);
        input = findViewById(R.id.input);
        cari = findViewById(R.id.btn_cari);
        result = findViewById(R.id.result);
        mDatabaseHelper = new DatabaseHelper(this);


        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRakFromJudul(input.getText().toString());
            }
        });
    }

    private void showRakFromJudul(String toString) {
        Product product = mDatabaseHelper.getProductFromJudul(toString);
        result.setText("Buku tersebut berada di rak nomor: " + String.valueOf(product.getRak()));
    }
}