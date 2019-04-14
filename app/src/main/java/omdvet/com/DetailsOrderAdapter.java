package omdvet.com;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import omdvet.com.WebServices.Models.DetailsOrder;

/**
 * Created by CrazyNet on 18/03/2019.
 */

public class DetailsOrderAdapter  extends RecyclerView.Adapter<DetailsOrderAdapter.viewHolder>{

    ArrayList<DetailsOrder> arrayList;

    public DetailsOrderAdapter(ArrayList<DetailsOrder> arrayList) {
        this.arrayList = arrayList;
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView productName , quantity;
        View view;

        public viewHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.item_product_name);
            quantity = itemView.findViewById(R.id.quantity);
            view = itemView.findViewById(R.id.vView);
        }
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_order_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.productName.setText(arrayList.get(position).getDetailsProducts().getName());
        holder.quantity.setText(arrayList.get(position).getQuantity());

        if(position == arrayList.size()-1) {
            holder.view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
