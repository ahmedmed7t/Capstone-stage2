package omdvet.com;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import omdvet.com.WebServices.Requests.updateProductRequest;
import omdvet.com.WebServices.Responses.updateProductResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    private ArrayList<String> productNameList;
    private ArrayList<String> productQntList;
    private ArrayList<String> productPriceList;
    private ArrayList<Integer> productImgList;
    ArrayList<Integer> productIdList;

public ProductAdapter( Context context,ArrayList<String> productNameList,
         ArrayList<String> productQntList,
         ArrayList<String> productPriceList,
         ArrayList<Integer> productImgList,ArrayList<Integer> productIdList)
{
    this.context=context;
    this.productNameList=productNameList;
    this.productQntList=productQntList;
    this.productPriceList=productPriceList;
    this.productImgList=productImgList;
    this.productIdList=productIdList;
}

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView productQnt;
        public TextView productPrice;
        public ImageView productImg;
        public TextView editQnt,editPrice;
        public EditText newQnt,newPrice;
        View view;

        public ViewHolder(View v) {
            super(v);
            productName = (TextView) v.findViewById(R.id.productName);
            productQnt = (TextView) v.findViewById(R.id.productQuantity);
            productPrice=(TextView) v.findViewById(R.id.productPrice);
            productImg =(ImageView) v.findViewById(R.id.productImg);
            editQnt = (TextView) v.findViewById(R.id.editQnt);
            editPrice =(TextView) v.findViewById(R.id.editPrice);
            newQnt =(EditText) v.findViewById(R.id.newQnt);
            newPrice =(EditText)v.findViewById(R.id.newPrice);
            view = v.findViewById(R.id.mView);
        }
    }
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ViewHolder holder, final int position) {
        holder.productName.setText(productNameList.get(position));
        holder.productQnt.setText(context.getResources().getString(R.string.quantity)+"   "+productQntList.get(position));
        holder.productPrice.setText(context.getResources().getString(R.string.price)+"   "+productPriceList.get(position));
        Picasso.with(context).load("http://iomd.info/public/"+productImgList.get(position)).into(holder.productImg);

        if(position == productNameList.size()-1){
            holder.view.setVisibility(View.INVISIBLE);
        }

        //holder.productImg.setImageResource(productImgList.get(position));
        SharedPreferences prefs =context.getSharedPreferences(
                "USER", Context.MODE_PRIVATE);
        String s = prefs.getString("IS_ADMIN","");
        if(s.equals("0"))
        {
            holder.editPrice.setVisibility(View.INVISIBLE);
            holder.editQnt.setVisibility(View.INVISIBLE);
        }

        holder.editQnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.editQnt.getText().toString().equals(context.getResources().getString(R.string.edit)))
                {
                    holder.newQnt.setVisibility(View.VISIBLE);
                    holder.productQnt.setVisibility(View.INVISIBLE);
                    holder.editQnt.setText(context.getResources().getString(R.string.done));
                }
                else
                {
                    holder.newQnt.setVisibility(View.INVISIBLE);
                    holder.productQnt.setVisibility(View.VISIBLE);
                    holder.editQnt.setText(context.getResources().getString(R.string.edit));
                    final String newQntStr = holder.newQnt.getText().toString();
                    if(newQntStr.equals("")||newQntStr==null);
                    else
                    {
                        int id = productIdList.get(position);
                        RetrofitWebService.getService().UPDATE_PRODUCT_CALL(new updateProductRequest(id,holder.productName.getText().toString(),
                                Integer.parseInt(newQntStr), Integer.parseInt(productPriceList.get(position))))
                                .enqueue(new Callback<updateProductResponse>() {
                                    @Override
                                    public void onResponse(Call<updateProductResponse> call, Response<updateProductResponse> response) {
                                        int status = response.body().getStatus();
                                        if(status==200)
                                        {
                                            holder.productQnt.setText(context.getResources().getString(R.string.quantity)+newQntStr);
                                            productQntList.set(position,newQntStr);

                                            //HomeActivity.adapter.notifyDataSetChanged();

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<updateProductResponse> call, Throwable t) {

                                    }
                                });

                    }
                }}
        });

        holder.editPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.editPrice.getText().toString().equals(context.getResources().getString(R.string.edit)))
                {
                    holder.newPrice.setVisibility(View.VISIBLE);
                    holder.productPrice.setVisibility(View.INVISIBLE);
                    holder.editPrice.setText(context.getResources().getString(R.string.done));
                }
                else
                {
                    holder.newPrice.setVisibility(View.INVISIBLE);
                    holder.productPrice.setVisibility(View.VISIBLE);
                    holder.editPrice.setText(context.getResources().getString(R.string.edit));
                    final String newPriceStr = holder.newPrice.getText().toString();
                    if(newPriceStr.equals("")||newPriceStr==null);
                    else
                    {
                        int id = productIdList.get(position);
                        RetrofitWebService.getService().UPDATE_PRODUCT_CALL(new updateProductRequest(id,holder.productName.getText().toString(),
                                Integer.parseInt(newPriceStr), Integer.parseInt(productQntList.get(position))))
                                .enqueue(new Callback<updateProductResponse>() {
                                    @Override
                                    public void onResponse(Call<updateProductResponse> call, Response<updateProductResponse> response) {
                                        int status = response.body().getStatus();
                                        if(status==200)
                                        {
                                            holder.productPrice.setText(context.getResources().getString(R.string.price)+newPriceStr);
                                            productPriceList.set(position,newPriceStr);

                                            //HomeActivity.adapter.notifyDataSetChanged();

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<updateProductResponse> call, Throwable t) {

                                    }
                                });

                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return productNameList.size();
    }

}
