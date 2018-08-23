package e.wolfsoft1.tickets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import e.wolfsoft1.tickets.Database.TicketHelper;

public class InsertActivity extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper ticketHelper;
    int id=0;
    TextView et1,et2,et3,et4,et5,et6,et7,et8,et9;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        ticketHelper=new TicketHelper(this);
        db=ticketHelper.getWritableDatabase();
        et1=findViewById(R.id.editText);
        et1.setEnabled(false);
        et2=findViewById(R.id.editText2);
        et3=findViewById(R.id.editText3);
        et4=findViewById(R.id.editText4);
        et5=findViewById(R.id.editText5);
        et6=findViewById(R.id.editText6);
        et7=findViewById(R.id.editText7);
        et8=findViewById(R.id.editText8);
        et9=findViewById(R.id.editText9);
        button=findViewById(R.id.button);

        Cursor cursor=db.rawQuery("select _id from flights",null);
        if(cursor.moveToLast())
            id=cursor.getInt(cursor.getColumnIndex("_id"));
        et1.setText((id+1)+"");

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               insert();
               Toast.makeText(InsertActivity.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
               finish();
           }
       });

    }

    public void insert()
    {


        ContentValues flightValues=new ContentValues();
        flightValues.put("_id",id+1);
        flightValues.put("source",et3.getText().toString());
        flightValues.put("name",et2.getText().toString());
        flightValues.put("destination",et4.getText().toString());
        flightValues.put("dtime",et5.getText().toString());
        flightValues.put("atime",et6.getText().toString());
        flightValues.put("price",Integer.parseInt(et8.getText().toString()));
        flightValues.put("brand_id",Integer.parseInt(et9.getText().toString()));
        flightValues.put("duration",Integer.parseInt(et7.getText().toString()));
        db.insert("flights",null,flightValues);
    }
}
