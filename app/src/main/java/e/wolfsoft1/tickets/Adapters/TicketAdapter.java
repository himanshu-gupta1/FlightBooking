package e.wolfsoft1.tickets.Adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import e.wolfsoft1.tickets.ModelClasses.RecyclerModel1;
import e.wolfsoft1.tickets.ModelClasses.TicketsModel;
import e.wolfsoft1.tickets.R;

/**
 * Created by wolfsoft1 on 24/2/18.
 */

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {

    Context context;


    private List<TicketsModel> OfferList;


    public class MyViewHolder extends RecyclerView.ViewHolder {



        TextView time1,time2,timeStatus,amount,platNo;
        LinearLayout ll1;

        public MyViewHolder(View view) {
            super(view);

            time1 = (TextView) view.findViewById(R.id.time1);
            time2=(TextView) view.findViewById(R.id.time2);
            timeStatus=(TextView) view.findViewById(R.id.timeStatus);
            amount=(TextView) view.findViewById(R.id.ticketAmount);
            platNo=(TextView) view.findViewById(R.id.platNo);



        }

    }


    public TicketAdapter(Context mainActivityContacts, List<TicketsModel> offerList) {
        this.OfferList = offerList;
        this.context = mainActivityContacts;
    }

    @Override
    public TicketAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_recycler, parent, false);


        return new TicketAdapter.MyViewHolder(itemView);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final TicketAdapter.MyViewHolder holder, int position) {
        TicketsModel lists = OfferList.get(position);

        holder.time1.setText(lists.getTime1());
        holder.time2.setText(lists.getTime2());
        holder.timeStatus.setText(lists.getTimeStatus());
        holder.platNo.setText(lists.getPlatNo());
        holder.amount.setText(lists.getAmount());

    }

    @Override
    public int getItemCount() {
        return OfferList.size();

    }
}
