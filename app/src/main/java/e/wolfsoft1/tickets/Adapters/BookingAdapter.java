package e.wolfsoft1.tickets.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import e.wolfsoft1.tickets.Database.TicketHelper;
import e.wolfsoft1.tickets.LiveItemsAndTickets;
import e.wolfsoft1.tickets.ModelClasses.BookingModel;
import e.wolfsoft1.tickets.R;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder>{

    Context context;
    String date;
    int seat;


    private List<BookingModel> bookingList;


    public class MyViewHolder extends RecyclerView.ViewHolder {



        ImageView iv_brand;
        TextView tv_flightName,tv_flightNo,tv_dtime,tv_atime,tv_price,tv_duration;
        Button bt_book;

        public MyViewHolder(View view) {
            super(view);

            iv_brand=view.findViewById(R.id.iv_brand);
            tv_flightName=view.findViewById(R.id.tv_flightName);
            tv_flightNo=view.findViewById(R.id.tv_flightNo);
            tv_dtime=view.findViewById(R.id.tv_dtime);
            tv_atime=view.findViewById(R.id.tv_atime);
            tv_price=view.findViewById(R.id.tv_price);
            bt_book=view.findViewById(R.id.bt_book);
            tv_duration=view.findViewById(R.id.tv_duration);


        }

    }


    public BookingAdapter(Context mainActivityContacts, List<BookingModel> bookingList,String date,int seat) {
        this.bookingList = bookingList;
        this.context = mainActivityContacts;
        this.date=date;
        this.seat=seat;
    }

    @Override
    public BookingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_recycler, parent, false);


        return new BookingAdapter.MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final BookingAdapter.MyViewHolder holder, int position) {
        BookingModel data = bookingList.get(position);

        final int no=data.getFlightNo();
        holder.iv_brand.setImageResource(data.getImageId());
        holder.tv_flightName.setText(data.getFlightName());
        holder.tv_flightNo.setText(data.getFlightNo()+"");
        holder.tv_dtime.setText(data.getdTime());
        holder.tv_atime.setText(data.getaTime());
        holder.tv_price.setText("Rs "+data.getPrice()+"");
        holder.tv_duration.setText(data.getDuration()/60+"h "+data.getDuration()%60+"min");
        holder.bt_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData(no,date,seat);
                 showBookingConfirmation();
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookingList.size();

    }

    public void insertData(int no,String date,int seat)
    {
        SQLiteOpenHelper ticketHelper=new TicketHelper(context);
        SQLiteDatabase db=ticketHelper.getWritableDatabase();
        ContentValues bookingValues=new ContentValues();
        bookingValues.put("flight_no",no);
        bookingValues.put("ddate",date);
        bookingValues.put("seats",seat);
        db.insert("booking",null,bookingValues);
    }

    public void showBookingConfirmation()
    {
        AlertDialog.Builder a_builder=new AlertDialog.Builder(context);
        a_builder.setMessage("Booking Successful")
                .setIcon(R.drawable.flight)
                .setCancelable(false)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(context,"cool", Toast.LENGTH_LONG).show();
                         context.startActivity(new Intent(context, LiveItemsAndTickets.class));
                    }
                });
        AlertDialog alert=a_builder.create();
        alert.setTitle("Booking");
        alert.show();
    }
}
