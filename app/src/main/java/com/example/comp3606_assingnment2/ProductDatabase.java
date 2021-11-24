package com.example.comp3606_assingnment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "Products";
    public static final int DB_VERSION = 1;

    ProductDatabase(Context context){
        super(context, DB_NAME, null,  DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        updateMyDatabase(db, oldversion, newversion);
    }

    private void insertProduct(SQLiteDatabase db,String name,int stock_hand, int stock_in_transit, double prices, int reorderqty, int reorderamt){
        ContentValues ProductValue = new ContentValues();
        ProductValue.put("NAME", name);
        ProductValue.put("STOCKONHAND", stock_hand);
        ProductValue.put("STOCKINTRANSIT", stock_in_transit);
        ProductValue.put("PRICE", prices);
        ProductValue.put("REORDEROTY", reorderqty);
        ProductValue.put("REORDERAMOUNT", reorderamt);
        db.insert("PRODUCT", null, ProductValue);
    }

    private void updateMyDatabase(SQLiteDatabase db,int oldversion, int newversion) {
        if (oldversion < 1) {
            db.execSQL("CREATE TABLE  PRODUCT("
                    + "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "NAME TEXT,"
                    + "STOCKONHAND INTEGER,"
                    + "STOCKINTRANSIT INTEGER,"
                    + "PRICES DOUBLE,"
                    + "REORDERQTY INTEGER,"
                    + "REORDERAMOUNT INTEGER);"
            );

        }
    }
}
