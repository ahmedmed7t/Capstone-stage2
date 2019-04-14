package omdvet.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import omdvet.com.WebServices.Models.DetailsOrder;
import omdvet.com.WebServices.Models.billes;
import omdvet.com.WebServices.Requests.getProductsClints;
import omdvet.com.WebServices.Responses.getProductClints;
import omdvet.com.WebServices.RetrofitService;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {

    TextView C_name , phone , date ;
    TextView cost , finalCost ;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyTheme);
        setContentView(R.layout.activity_order_details);
        progressBar = findViewById(R.id.progress_order_details);
        progressBar.setVisibility(View.VISIBLE);

        C_name = findViewById(R.id.txt_name_value);
        phone = findViewById(R.id.txt_phone_value);
        date = findViewById(R.id.txt_date_value);
        cost = findViewById(R.id.txt_value_cost);
        finalCost = findViewById(R.id.txt_value_final_cost);
        recyclerView = findViewById(R.id.details_item_recycler);

        Intent i = getIntent();
        if (i.hasExtra("clientID"))
        {
            id = i.getStringExtra("clientID");
            final int BillId = i.getIntExtra("BillID",-1);

            RetrofitWebService.getService().GetPClint(new getProductsClints(id))
            .enqueue(new Callback<getProductClints>() {
                @Override
                public void onResponse(Call<getProductClints> call, Response<getProductClints> response) {
                    int status = response.body().getStatus();
                    if(status == 200)
                    {
                        ArrayList<billes> bills = response.body().getBilles();
                        billes myBill = new billes();
                        for(int i = 0 ; i < bills.size() ; i++){
                            if(bills.get(i).getId() == BillId){
                                myBill = bills.get(i) ;
                                break;
                            }
                        }
                        showUserData(myBill);
                    }
                }

                @Override
                public void onFailure(Call<getProductClints> call, Throwable t) {
                }
            });
        }

    }

    public void showUserData(billes bill){

        progressBar.setVisibility(View.INVISIBLE);
        C_name.setText(bill.getName());
        phone.setText(bill.getPhone().toString());
        date.setText(bill.getDate().toString());
        cost.setText(bill.getCost().toString());
        finalCost.setText(bill.getAfterDiscount().toString());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DetailsOrderAdapter adapter = new DetailsOrderAdapter(bill.getOrders());

        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,getOrdersActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
