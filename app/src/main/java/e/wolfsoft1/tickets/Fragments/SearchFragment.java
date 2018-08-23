package e.wolfsoft1.tickets.Fragments;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import e.wolfsoft1.tickets.Adapters.Recycler1Adapter;
import e.wolfsoft1.tickets.BookingActivity;
import e.wolfsoft1.tickets.Database.TicketHelper;
import e.wolfsoft1.tickets.ModelClasses.RecyclerModel1;
import e.wolfsoft1.tickets.R;


public class SearchFragment extends Fragment {
    private View view;
//    TextView dates,months;
    public static String date;
    public static String seat;
    private TextView et_datePick,tv_source,tv_destination;
    private Button bt_searchFlight;
    LinearLayout ll_swap;


    private ArrayList<RecyclerModel1> homeListModelClassArrayList;
    private RecyclerView recyclerView;
    private Recycler1Adapter mAdapter;
//
    String src;
    String dest;
    String seats[]={"1","2","3","4","5","6","7","8","9"};
    String sourceArr[];
    String destinationArr[];
    HashSet<String> source=new HashSet<>();
    HashSet<String> destination=new HashSet<>();
//    String date[]={"23","24","25","26","27","28","29"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        et_datePick=view.findViewById(R.id.et_datePick);
        tv_source=view.findViewById(R.id.tv_source);
        ll_swap=view.findViewById(R.id.ll_swap);
        tv_destination=view.findViewById(R.id.tv_destination);
        et_datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker=new DatePickerFragment();
                datePicker.show(getActivity().getSupportFragmentManager(),"date_picker");

            }
        });

        bt_searchFlight=view.findViewById(R.id.bt_searchFlight);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        homeListModelClassArrayList = new ArrayList<>();

        for (int i = 0; i < seats.length; i++) {
            RecyclerModel1 beanClassForRecyclerView_contacts = new RecyclerModel1(seats[i]);

            homeListModelClassArrayList.add(beanClassForRecyclerView_contacts);
        }

        mAdapter = new Recycler1Adapter(getActivity(),homeListModelClassArrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        bt_searchFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                src=tv_source.getText().toString();
                dest=tv_destination.getText().toString();
                if(validate()) {
                    //date and seat
                    Intent intent = new Intent(getActivity(), BookingActivity.class);
                    intent.putExtra("source", src);
                    intent.putExtra("destination", dest);
                    intent.putExtra("date", date);
                    intent.putExtra("seat", seat);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getActivity(),"Please, fill all the entries",Toast.LENGTH_LONG).show();
                }
            }
        });
        ll_swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source=tv_source.getText().toString();
                String destination=tv_destination.getText().toString();
                tv_source.setText(destination);
                tv_destination.setText(source);
            }
        });


        populateData();
        tv_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("starting onclick");
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Choose Source");
                sourceArr = new String[source.size()];
                sourceArr = source.toArray(sourceArr);
                mBuilder.setSingleChoiceItems(sourceArr, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       tv_source.setText(sourceArr[which]);
                       dialog.dismiss();
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mDialog=mBuilder.create();
                mDialog.show();
            }
        });

        tv_destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("starting onclick");
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Choose Destination");
                destinationArr = new String[source.size()];
                destinationArr = destination.toArray(destinationArr);
                mBuilder.setSingleChoiceItems(destinationArr, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tv_destination.setText(destinationArr[which]);
                        dialog.dismiss();
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mDialog=mBuilder.create();
                mDialog.show();
            }
        });


        return view;

    }

    public void populateData()
    {
        SQLiteOpenHelper  ticketHelper=new TicketHelper(getActivity());
        SQLiteDatabase db=ticketHelper.getReadableDatabase();
        Cursor cursor=db.query("flights",new String[]{"source","destination"},null,null,null,null,null);
        while(cursor.moveToNext())
        {
            source.add(cursor.getString(cursor.getColumnIndex("source")));
            destination.add(cursor.getString(cursor.getColumnIndex("destination")));

        }
        cursor.close();
        db.close();

    }

    public boolean validate()
    {
        if(src.contains("Location")||dest.contains("Location")||seat==null||date==null)
            return false;
        return true;

    }




}
