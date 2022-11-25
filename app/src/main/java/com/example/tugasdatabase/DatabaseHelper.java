package com.example.tugasdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "toko-db";
    private static final int DATABASE_VERSION = 1;

    private static final String PRODUCT_TABLE = "products";
    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_JUDUL = "judul";
    private static final String PRODUCT_RAK = "rak";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertRecord(Product product) {
        //untuk mengubah data
        SQLiteDatabase db = getWritableDatabase();
        //untuk insert
        ContentValues values = new ContentValues();
        values.put(PRODUCT_JUDUL, product.getJudul());
        values.put(PRODUCT_RAK, product.getRak());

        db.insert(PRODUCT_TABLE, null, values);
        db.close();
    }
    public Product getProductFromJudul(String name) {
        SQLiteDatabase db = getReadableDatabase();
        //pilih ngambil tabel apa aja
        Cursor cursor = db.query(PRODUCT_TABLE,
                new String[] {PRODUCT_ID, PRODUCT_JUDUL, PRODUCT_RAK},
                PRODUCT_JUDUL + "=?",
                new String[] {name}, null, null, null);
        if (cursor != null) {
            //mengambil data pertama
            cursor.moveToFirst();
        }
        Product product = new Product();
        product.setId(cursor.getInt(0));
        product.setJudul(cursor.getString(1));
        product.setRak(cursor.getInt(2));
        return product;
    }

    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * from " + PRODUCT_TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setJudul(cursor.getString(1));
                product.setRak(cursor.getInt(2));

                allProducts.add(product);
            } while (cursor.moveToNext());
        }
        return allProducts;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_PRODUCT = " CREATE TABLE "  + PRODUCT_TABLE +
                "(" + PRODUCT_ID + " INTEGER PRIMARY KEY, "+
                PRODUCT_JUDUL + " TEXT, " +
                PRODUCT_RAK + " INTEGER)";

        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }
}
