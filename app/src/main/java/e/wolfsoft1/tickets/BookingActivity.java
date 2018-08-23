package e.wolfsoft1.tickets;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import e.wolfsoft1.tickets.Adapters.BookingAdapter;
import e.wolfsoft1.tickets.Adapters.TicketAdapter;
import e.wolfsoft1.tickets.Database.TicketHelper;
import e.wolfsoft1.tickets.ModelClasses.BookingModel;
import e.wolfsoft1.tickets.ModelClasses.TicketsModel;

public class BookingActivity extends AppCompatActivity {

    private ArrayList<BookingModel> list;
    private RecyclerView recyclerView;
    private BookingAdapter mAdapter;

    private ImageView iv_back;
    private TextView tv_from,tv_to,tv_date;
    int imageId[]={R.drawable.indigo,R.drawable.indigo};
    String flightName[]={"Indigo","Indigo"};
    int flightNo[]={101,101};
    String dTime[]={"10:45","11:45"};
    String aTime[]={"11:45","12:45"};
    int duration[]={60,60};
    int price[]={6200,5600};
    private String source, destination, date;
    private int seat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        recyclerView=findViewById(R.id.recyclerView);
        list = new ArrayList<>();

        iv_back=findViewById(R.id.iv_back);
        tv_from=findViewById(R.id.tv_from);
        tv_to=findViewById(R.id.tv_to);
        tv_date=findViewById(R.id.tv_date);
        Intent intent=getIntent();
        source=intent.getStringExtra("source");
        destination=intent.getStringExtra("destination");
        date=intent.getStringExtra("date");
        seat=Integer.parseInt(intent.getStringExtra("seat"));
        tv_from.setText(source);
        tv_to.setText(destination);
        tv_date.setText(date);
//        for (int i = 0; i < flightName.length; i++) {
//            BookingModel model = new BookingModel(imageId[i],flightName[i],flightNo[i],dTime[i],aTime[i],duration[i],price[i]);
//            list.add(model);
//        }
        populateData();
        mAdapter = new BookingAdapter(this,list,date,seat);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public void populateData()
    {
        SQLiteOpenHelper ticketHelper=new TicketHelper(this);
        SQLiteDatabase db=ticketHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from flights where source = ? and destination=?",new String[]{source,destination});
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String source = cursor.getString(cursor.getColumnIndex("source"));
                String destination = cursor.getString(cursor.getColumnIndex("destination"));
                String dtime = cursor.getString(cursor.getColumnIndex("dtime"));
                String atime = cursor.getString(cursor.getColumnIndex("atime"));
                int price = cursor.getInt(cursor.getColumnIndex("price"));
                int duration = cursor.getInt(cursor.getColumnIndex("duration"));
                int brand_id = cursor.getInt(cursor.getColumnIndex("brand_id"));
                int image_id = 0;
                if (brand_id == 1)
                    image_id = R.drawable.indigo;
                else if (brand_id == 2)
                    image_id = R.drawable.airasia;
                BookingModel model = new BookingModel(image_id, name, id, dtime, atime, source, duration, destination, price);
                list.add(model);

            }
            cursor.close();
            db.close();
        }
        else
        {
            Toast.makeText(this,"Sorry no flights for the current configuration could be found",Toast.LENGTH_LONG).show();
        }
    }
}
