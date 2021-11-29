package com.example.comp3606_assingnment2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ReceivingStocks extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ProductDatabase db;

    Spinner spinner;
    Button update;
    EditText add_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receiving_stocks_activity);

        db = new ProductDatabase(this);


        db.insertProduct( "Toilet Paper", 10, 20, 10.00, 10,7);
        db.insertProduct( "Hand Soap", 8, 20, 10.00, 10,7);
        db.insertProduct( "Hand Sanitizer", 7, 20, 10.00, 10,7);


        spinner = findViewById(R.id.names);
        update = findViewById(R.id.addbtn);
        add_num = findViewById(R.id.editTextNumber);

        spinner.setOnItemSelectedListener(this);

        ///TestDatabase d = new TestDatabase(getApplicationContext());
       // d.insertLabel("Home");
/*
        SQLiteOpenHelper pd = new ProductDatabase(this);
        try{
            SQLiteDatabase db = pd.getReadableDatabase();
            Cursor cursor = db.query("PRODUCT",
                    new String[]{"NAME", "STOCKONHAND", "STOCKINTRANSIT" "REORDERQTY", "REORDERAMOUNT"},
                    "_id = ?",
                    new String[]{Integer.toString(productId)},
                    null, null, null, null);

            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String stockOnHandText = cursor.getString(1);
                String stockInTransText = cursor.getString(2);
                String reorderQtyText = cursor.getString(4);
                String reorderAmtText = cursor.getString(5);

                TextView name = (TextView)findViewById(R.id.nameView);
                name.setText(nameText);

                TextView stockOnHand = (TextView)findViewById(R.id.stockView;
                stockOnHand.setText(stockOnHandText);

                TextView stockInTrans = (TextView)findViewById(R.id.intransitView);
                stockInTrans.setText(stockInTransText);

                TextView reorderQty = (TextView)findViewById(R.id.preorder_qtyView);
                reorderQty.setText(reorderQtyText);

                TextView reorderAmt = (TextView)findViewById(R.id.preorder_amtView);
                reorderAmt.setText(reorderAmtText);

            }
            cursor.close();
            db.close();*/

            loadSpinnerData();
            String n = "1";
            update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String num = add_num.getText().toString();
                if(num.trim().length() > 0){
                    ProductDatabase db = new ProductDatabase(getApplicationContext());
                    db.insertnum(num);
                    add_num.setText("");
                    loadSpinnerData();
                }else{
                    Toast toast =  Toast.makeText(getApplicationContext(), "Database unavailable", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }

    private void loadSpinnerData(){
        ProductDatabase db = new ProductDatabase(getApplicationContext());

        List<String> products = db.getProductsLabel();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String product = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "You selected: " + product,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //TODO
    }
}

