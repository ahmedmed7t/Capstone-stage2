package omdvet.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import omdvet.com.WebServices.Requests.clientInfoRequest;
import omdvet.com.WebServices.Requests.reportRequest;
import omdvet.com.WebServices.Responses.clientInfoResponse;
import omdvet.com.WebServices.Responses.reportResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomersAdapter  extends RecyclerView.Adapter<CustomersAdapter.ViewHolder> {

    private ArrayList<String> customerNameList;
    private ArrayList<String> customerPhoneList;
    private ArrayList<String> customerAddressList;
    private ArrayList<Integer> customerIdList;
    private ArrayList<String> customerApiTokenList;
    private ArrayList<Integer> customerempIdList;
    private ArrayList<Integer> customerReport;

    Context context;
    private int is_report;

    public CustomersAdapter(Context context,
                             ArrayList customerIdList,
                            ArrayList customerempIdList,
                            ArrayList customerApiTokenList,
                            ArrayList customerNameList,
                            ArrayList customerPhoneList,
                            ArrayList customerAddressList,
                            ArrayList customerReport) {
        this.customerNameList=customerNameList;
        this.customerAddressList=customerAddressList;
        this.customerPhoneList=customerPhoneList;
        this.customerempIdList=customerempIdList;
        this.customerApiTokenList=customerApiTokenList;
        this.customerIdList=customerIdList;
        this.customerReport=customerReport;
        this.context=context;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView customerName;
        public TextView customerPhone;
        public TextView customerAddress;
        public Button order;
        public Button bankUp;
        public Button report, recpit;
        View view;
        public ViewHolder(View v) {
            super(v);
            customerName = (TextView) v.findViewById(R.id.customerName);
            customerPhone = (TextView) v.findViewById(R.id.customerPhone);
            customerAddress=(TextView) v.findViewById(R.id.customerAddress);
            order =(Button)v.findViewById(R.id.order);
            bankUp =(Button)v.findViewById(R.id.bankUp);
            report = (Button)v.findViewById(R.id.report);
            recpit = v.findViewById(R.id.recipet);
            view = v.findViewById(R.id.view2);

        }
    }
    @Override
    public CustomersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customers_item,parent,false);
        return new CustomersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CustomersAdapter.ViewHolder holder, final int position) {

        if(position == customerAddressList.size()-1){
            holder.view.setVisibility(View.INVISIBLE);
        }

        holder.recpit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,getOrdersActivity.class);
                i.putExtra("client_id",customerIdList.get(position));
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
                ((Activity)context).finish();

            }
        });
        holder.customerName.setText(customerNameList.get(position));
        holder.customerPhone.setText(customerPhoneList.get(position));
        holder.customerAddress.setText(customerAddressList.get(position));
        if(Integer.valueOf(customerReport.get(position)) == 1)
        {
            holder.customerName.setBackgroundColor(Color.RED);
            holder.customerName.setTextColor(Color.parseColor("#FFFFFF"));

        }
        else
        {
            holder.customerName.setBackgroundColor(Color.TRANSPARENT);
            holder.customerName.setTextColor(Color.parseColor("#46B4DA"));
        }
        SharedPreferences prefs = context.getSharedPreferences(
                "USER", Context.MODE_PRIVATE);
        String s = prefs.getString("IS_ADMIN","");
        if(s.equals("0"))
            holder.report.setVisibility(View.GONE);

        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,OrderActivity.class);
                i.putExtra("NAME",customerNameList.get(position));
                i.putExtra("PHONE",customerPhoneList.get(position));
                i.putExtra("ADDRESS",customerAddressList.get(position));
                i.putExtra("CUSTOMER_ID",customerIdList.get(position));
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
                ((Activity)context).finish();
            }
        });

        holder.bankUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitWebService.getService().CLIENT_INFO_CALL(new clientInfoRequest
                        (customerIdList.get(position))).enqueue(new Callback<clientInfoResponse>() {
                    @Override
                    public void onResponse(Call<clientInfoResponse> call, Response<clientInfoResponse> response) {
                        int status=response.body().status;
                        if(status==200)
                        {

                            Intent i = new Intent(context,CollectionActivity.class);
                            i.putExtra("NAME",response.body().client.name);
                            i.putExtra("PHONE",response.body().client.phone);
                            i.putExtra("ADDRESS",response.body().client.address);
                            i.putExtra("DEPT",response.body().client.pay);
                            i.putExtra("ID",customerIdList.get(position));
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            context.startActivity(i);
                            ((Activity)context).finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<clientInfoResponse> call, Throwable t) {

                    }
                });

            }
        });

        holder.report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.customerName.getCurrentTextColor()==Color.parseColor("#FFFFFF"))
                {
                    is_report = 0;
                }
                else
                    is_report =1;
                customerReport.set(position,is_report);

                RetrofitWebService.getService().REPORT_CALL(new reportRequest(customerIdList.get(position),is_report))
                        .enqueue(new Callback<reportResponse>() {
                            @Override
                            public void onResponse(Call<reportResponse> call, Response<reportResponse> response) {

                              int status = response.body().status;
                              if(status==200)
                              {
                                  if(holder.customerName.getCurrentTextColor()==Color.parseColor("#FFFFFF"))
                                  {
                                      holder.customerName.setBackgroundColor(Color.TRANSPARENT);
                                      holder.customerName.setTextColor(Color.parseColor("#46B4DA"));
                                  }

                                  else
                                  {

                                      holder.customerName.setBackgroundColor(Color.RED);
                                      holder.customerName.setTextColor(Color.parseColor("#FFFFFF"));
                                  }


                              }
                            }

                            @Override
                            public void onFailure(Call<reportResponse> call, Throwable t) {

                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerNameList.size();
    }


    public void setValues( Context context,
                           ArrayList customerIdList,
                           ArrayList customerempIdList,
                           ArrayList customerApiTokenList,
                           ArrayList customerNameList,
                           ArrayList customerPhoneList,
                           ArrayList customerAddressList,
                           ArrayList customerReport ){

        this.customerNameList=customerNameList;
        this.customerAddressList=customerAddressList;
        this.customerPhoneList=customerPhoneList;
        this.customerempIdList=customerempIdList;
        this.customerApiTokenList=customerApiTokenList;
        this.customerIdList=customerIdList;
        this.customerReport=customerReport;
        this.context=context;

        notifyDataSetChanged();
    }

}
