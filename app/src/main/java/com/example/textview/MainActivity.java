package com.example.textview;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.SearchView;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ListView l1;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn5;

    private EditText t1;
    private ArrayList<String> arr;
    private SearchView t2;

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = findViewById(R.id.list_view);
        btn1 = findViewById(R.id.idBtnAdd);
        btn2 = findViewById(R.id.idBtnSearch);
        btn3 = findViewById(R.id.idBtnDelete);
        btn5 = findViewById(R.id.idBtnrefresh);
        t1 = findViewById(R.id.idtext01);
        t2 = findViewById(R.id.searchView);
        arr = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>((Context) this, android.R.layout.simple_list_item_1, arr);
        arr.add("Hammad");
        arr.add("Ali");
        arr.add("Asad");
        arr.add("Ahsan");
        arr.add("Arsal");
        l1.setAdapter(adapter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = t1.getText().toString();
                if (!item.isEmpty()) {
                    arr.add(item);

                    adapter.notifyDataSetChanged();
                }

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
                l1.invalidateViews();
                l1.refreshDrawableState();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr.size()>0) {
                    if (!t1.getText().toString().isEmpty()) {
                        arr.remove(t1.getText().toString());
                        l1.setAdapter(adapter);
                        Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "There is no element to delete", Toast.LENGTH_LONG).show();
                }
            }
        });

        t2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);

                return false;
            }
        });
    }
}