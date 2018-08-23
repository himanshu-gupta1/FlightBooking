package e.wolfsoft1.tickets.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TicketHelper extends SQLiteOpenHelper {
private static final String DB_NAME="Ticket";
private static final int DB_VERSION=1;

    public TicketHelper(Context context)
    {

        super(context,DB_NAME,null,DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("creating database");
        String sql="create table flights (_id integer primary key,name text,source text,destination text,dtime text,atime text,duration integer,price integer,brand_id integer)";
        String sql1="create table booking(_id integer primary key autoincrement,flight_no integer,ddate text,seats integer)";
        db.execSQL(sql);
        db.execSQL(sql1);
        insertFlights(db,101,"Indigo","Chennai","Jaipur","23:20","02:00",160,6217,1);
        insertFlights(db,102,"Indigo","Chennai","Jaipur","10:35","13:25",170,6657,1);
        insertFlights(db,103,"Air Asia","Delhi","Kolkata","04:55","07:10",135,4481,2);
        insertFlights(db,104,"Air Asia","Delhi","Kolkata","19:40","22:10",150,4481,2);
        insertFlights(db,105,"Air Asia","Chennai","Jaipur","23:20","02:00",160,6217,2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertFlights(SQLiteDatabase db,int id,String name,String source,String destination,String dtime,String atime,int duration,int price,int brandid)
    {
        System.out.println("inserting records");
        ContentValues flightValues=new ContentValues();
        flightValues.put("_id",id);
        flightValues.put("source",source);
        flightValues.put("name",name);
        flightValues.put("destination",destination);
        flightValues.put("dtime",dtime);
        flightValues.put("atime",atime);
        flightValues.put("price",price);
        flightValues.put("brand_id",brandid);
        flightValues.put("duration",duration);
        db.insert("flights",null,flightValues);
    }


}
