package omdvet.com;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import omdvet.com.WebServices.Models.Delegate_order;

/**
 * Created by CrazyNet on 14/04/2019.
 */

public class DelegateOrderAdapter extends RecyclerView.Adapter<DelegateOrderAdapter.ViewHolder> {

    ArrayList<Delegate_order> arrayList;

    public DelegateOrderAdapter(ArrayList<Delegate_order> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView customerName;
        public TextView customerPhone;
        public TextView customerAddress;
        public TextView date;
        public TextView mcost;
        public TextView finalCost;
        View view;

        public ViewHolder(View v) {
            super(v);
            customerName =  v.findViewById(R.id.delegate_order_customer_name);
            customerPhone =  v.findViewById(R.id.delegate_order_customer_phone);
            customerAddress= v.findViewById(R.id.delegate_order_customer_address);
            date = v.findViewById(R.id.delegate_order_date);
            mcost = v.findViewById(R.id.delegate_order_cost_before);
            finalCost = v.findViewById(R.id.delegate_order_cost);

            view = v.findViewById(R.id.delegate_view2);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delegate_order_item,parent,false);
        return new DelegateOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.customerAddress.setText(arrayList.get(position).getAddress());
        holder.customerName.setText(arrayList.get(position).getName());
        holder.customerPhone.setText(arrayList.get(position).getPhone());
        holder.date.setText(arrayList.get(position).getDate());
        holder.mcost.setText(String.valueOf(arrayList.get(position).getCost_before()));
        holder.finalCost.setText(String.valueOf(arrayList.get(position).getFinal_cost()));

        if(position == arrayList.size()-1){
            holder.view.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
