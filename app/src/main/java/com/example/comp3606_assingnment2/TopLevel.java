package com.example.comp3606_assingnment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TopLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_level_activity);

        ListView lv = findViewById(R.id.list_options);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position == 0){
                    Intent intent = new Intent(view.getContext(), ReceivingStocks.class);
                    startActivity(intent);
                }
                /*
                if(position == 1){
                    Intent intent = new Intent(view.getContext(), ReceivingStocks.class);
                    startActivity(intent);
                }*/
            }
        });
    }

    public void onListItemClick(ListView lv, View itemView, int position, long id ){
        Intent intent = new Intent(TopLevel.this, ReceivingStocks.class);
        startActivity(intent);
    }
}