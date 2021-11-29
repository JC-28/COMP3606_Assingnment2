package com.example.comp3606_assingnment2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "Products";
    public static final int DB_VERSION = 1;
    private static final String PRODUCT = "PRODUCT";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final int STOCK_ON_HAND = 0;
    private static final int STOCK_IN_TRANSIT = 0;
    private static final double PRICE = 0.0;
    private static final int REORDER_QTY = 5;
    private static  final int REORDER_AMT = 10;

    ProductDatabase(Context context){

        super(context, DB_NAME, null,  DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE  PRODUCT("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "NAME TEXT,"
                + "STOCKONHAND INTEGER,"
                + "STOCKINTRANSIT INTEGER,"
                + "PRICES DOUBLE,"
                + "REORDERQTY INTEGER,"
                + "REORDERAMOUNT INTEGER);"
        );

        insertProduct( "Toilet Paper", 10, 20, 10.00, 10,7);
        insertProduct( "Hand Soap", 8, 20, 10.00, 10,7);
        insertProduct( "Hand Sanitizer", 7, 20, 10.00, 10,7);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT);

        // Create tables again
        onCreate(db);
    }

    public void insertProduct(String name,int stock_hand, int stock_in_transit, double prices, int reorderqty, int reorderamt){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ProductValue = new ContentValues();
        ProductValue.put("NAME", name);
        ProductValue.put("STOCKONHAND", stock_hand);
        ProductValue.put("STOCKINTRANSIT", stock_in_transit);
        ProductValue.put("PRICE", prices);
        ProductValue.put("REORDEROTY", reorderqty);
        ProductValue.put("REORDERAMOUNT", reorderamt);
        db.insert("PRODUCT", null, ProductValue);
        db.close();
    }
/*
    public void insertStock(String stock){
        int onHand = 0;
        int intransit = 0;

        String selectQuery = "SELECT * FROM " + PRODUCT;
        int RowID = Integer.parseInt("1");

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            onHand = Integer.parseInt(c.getString(2));
            intransit = Integer.parseInt(c.getString(3));
        }
         int temp = Integer.parseInt(stock);
         onHand += temp;
         intransit -= temp;

         c.close();
         db.close();

         db = this.getWritableDatabase();
         ContentValues val  = new ContentValues();
         val.put("STOCKONHAND", onHand);
        val.put("STOCKINTRANSIT", intransit);
        db.update(PRODUCT, val,  KEY_ID + " = ?", new String[] { String.valueOf(RowID) });
        db.close();

    }*/

    public void insertnum(String num){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ProductValue = new ContentValues();
        ProductValue.put("STOCKINHAND", num);
        db.insert("PRODUCT", null, ProductValue);
    }

    public List<String> getProductsLabel(){
        List<String> names = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + PRODUCT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                names.add(c.getString(1));
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return names;
    }
}
