package e.wolfsoft1.tickets.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

import e.wolfsoft1.tickets.Adapters.Recycler1Adapter;
import e.wolfsoft1.tickets.Adapters.TicketAdapter;
import e.wolfsoft1.tickets.ModelClasses.RecyclerModel1;
import e.wolfsoft1.tickets.ModelClasses.TicketsModel;
import e.wolfsoft1.tickets.R;

/**
 * Created by wolfsoft1 on 24/2/18.
 */

public class TicketFragment extends Fragment {
    private View view;


    private ArrayList<TicketsModel> homeListModelClassArrayList;
    private RecyclerView recyclerView;
    private TicketAdapter mAdapter;

    String time1[]={"10:45","11:00","12:00"};
    String time2[]={"12:30","12:45","12:55"};
    String timeStatus[]={"On time","On time","On time"};
    String amount[]={"$39.00","$20.50","$35:00"};
   String platNo[]={"Plat.1","Plat.1","Plat.1 est"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ticket, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        homeListModelClassArrayList = new ArrayList<>();

        for (int i = 0; i < timeStatus.length; i++) {
            TicketsModel beanClassForRecyclerView_contacts = new TicketsModel(time1[i],time2[i],platNo[i],timeStatus[i],amount[i]);

            homeListModelClassArrayList.add(beanClassForRecyclerView_contacts);
        }

        mAdapter = new TicketAdapter(getActivity(),homeListModelClassArrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        return view;

    }
}
