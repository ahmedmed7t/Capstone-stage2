package omdvet.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import omdvet.com.WebServices.Models.AllBilles;
import omdvet.com.WebServices.Requests.clientInfoRequest;
import omdvet.com.WebServices.Requests.reportRequest;
import omdvet.com.WebServices.Responses.clientInfoResponse;
import omdvet.com.WebServices.Responses.reportResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class getOrdersAdapter extends RecyclerView.Adapter<getOrdersAdapter.ViewHolder> {

    private ArrayList<AllBilles> allBilles;

    Context context;
    private int is_report;

    public getOrdersAdapter(Context context, ArrayList allBilles) {
        this.allBilles = allBilles;
        this.context=context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView customerName;
        public TextView customerPhone;
        public TextView customerAddress;
        public TextView date;
        ConstraintLayout mConstraintLayout;
        View view;

        public ViewHolder(View v) {
            super(v);
            customerName =  v.findViewById(R.id.customerName);
            customerPhone =  v.findViewById(R.id.customerPhone);
            customerAddress= v.findViewById(R.id.customerAddress);
            date = v.findViewById(R.id.date);
            mConstraintLayout = v.findViewById(R.id.myConstraintLayout);
            view = v.findViewById(R.id.view2);
        }
    }

    @Override
    public getOrdersAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        return new getOrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final getOrdersAdapter.ViewHolder holder, final int position) {
        holder.customerName.setText(allBilles.get(position).getName());
        holder.customerPhone.setText(allBilles.get(position).getPhone());
        holder.customerAddress.setText(allBilles.get(position).getAddress());
        holder.date.setText(allBilles.get(position).getDate());

        if(position == allBilles.size()-1){
            holder.view.setVisibility(View.INVISIBLE);
        }

        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("clientID", allBilles.get(position).getClient_id());
                intent.putExtra("BillID", allBilles.get(position).getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return allBilles.size();
    }


}
