package com.example.tugasdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahBuku extends AppCompatActivity {

    EditText judul, rak;
    Button submit;
    private DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_buku);

        judul = findViewById(R.id.input_judul);
        rak = findViewById(R.id.input_rak);
        submit = findViewById(R.id.btn_submit);

        mDatabaseHelper = new DatabaseHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }
    public void insertData() {
        Product product = new Product();
        product.setJudul(judul.getText().toString());
        product.setRak(Integer.valueOf(rak.getText().toString()));

        mDatabaseHelper.insertRecord(product);
        Toast.makeText(TambahBuku.this, "success", Toast.LENGTH_SHORT).show();

//        mDatabaseHelper.insertRecord(product);
//        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();

    }
}