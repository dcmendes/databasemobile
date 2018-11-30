package br.edu.ifsc.database.feature;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = openOrCreateDatabase("mydatabase", MODE_PRIVATE, null);

        db.execSQL("DROP TABLE IF EXISTS alunos");
        db.execSQL("CREATE TABLE IF NOT EXISTS alunos (id INTEGER  PRIMARY KEY AUTOINCREMENT, nome VARCHAR)");

        db.execSQL("INSERT INTO alunos ( nome) VALUES ('Diego')");
        db.execSQL("INSERT INTO alunos ( nome) VALUES ('Tereza Cruz')");
        db.execSQL("INSERT INTO alunos ( nome) VALUES ('Aurora Mendes')");

        Cursor cursor = db.rawQuery("SELECT id, nome  FROM  alunos ", null);
        cursor.moveToFirst();
        ArrayList<String> item = new ArrayList<>();

        do {
            String s = cursor.getString(cursor.getColumnIndex("nome"));
            Log.i(" Resultado Sql :", s);
            item.add(s);
        } while (cursor.moveToNext());

        ListView list;
        list = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                item
        );

        list.setAdapter(adapter);

    }
}
