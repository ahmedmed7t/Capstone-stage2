package omdvet.com;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;
import java.util.zip.DataFormatException;

import javax.xml.datatype.DatatypeConfigurationException;

import omdvet.com.WebServices.Models.ClientOrder;
import omdvet.com.WebServices.Models.Orders;
import omdvet.com.WebServices.Models.Product;

public class ProductAdapter2 extends RecyclerView.Adapter<ProductAdapter2.ViewHolder> {

    static ArrayList<Orders> allOrders;
    static ArrayList<Product> clientProducts ;
    static float totalSum ;
    static ViewHolder holder;
    static Context context;

    public ProductAdapter2(Context context, ArrayList<Orders> orders)
    {
        allOrders = orders;
        ProductAdapter2.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_item2,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter2.ViewHolder holder, final int position) {

        holder.productName.setText(allOrders.get(position).getName().toString());
        ProductAdapter2.holder = holder;
        clientProducts = new ArrayList<>();

        if(position == allOrders.size()-1){
            holder.view.setVisibility(View.INVISIBLE);
        }

        holder.productQnt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                int quantity = 0 , id = 0;
                try {
                    if(!holder.productQnt.getText().toString().equals("")) {
                        quantity = Integer.valueOf(holder.productQnt.getText().toString());
                    }else {
                        quantity = 0 ;
                    }
                     id = allOrders.get(position).getId();
                }catch (FormatFlagsConversionMismatchException ex){
                    Toast.makeText(context,context.getResources().getString(R.string.enter_valid_number),Toast.LENGTH_LONG).show();
                }

                if(quantity > Integer.valueOf(allOrders.get(position).getQuantity())){
                    quantity = 0 ;
                    Toast.makeText(context,context.getResources().getString(R.string.quantity_not_available),Toast.LENGTH_LONG).show();
                }

                Boolean flag = false;
                for(int i = 0 ; i < clientProducts.size() ; i++){
                    if(id == clientProducts.get(i).getId()) {
                        clientProducts.get(i).setQuantity(quantity);
                        flag = true;
                    }
                }
                if (!flag){
                    clientProducts.add(new Product(quantity, id));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return allOrders.size();
    }

    public static float CalcuteTotalSum ()
    {
        totalSum = 0;
        int id  , quantity;
        float price ;
        for(int i=0;i<clientProducts.size();i++){

            id = clientProducts.get(i).getId();
            quantity = clientProducts.get(i).getQuantity();

            for(int j = 0 ; j < allOrders.size() ; j++){
                if( allOrders.get(j).getId() == id ){
                    totalSum += ( Integer.valueOf(allOrders.get(j).getPrice()) * quantity ) ;
                    break;
                }
            }
        }

        return totalSum;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName ;
        EditText productQnt;
        View view;
        public ViewHolder(View v) {
            super(v);
            productName = v.findViewById(R.id.productNameSale);
            productQnt  = v.findViewById(R.id.productQntSale);
            view = v.findViewById(R.id.view);
        }
    }
}
