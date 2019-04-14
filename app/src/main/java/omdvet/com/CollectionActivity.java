package omdvet.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import omdvet.com.WebServices.Requests.payClientRequest;
import omdvet.com.WebServices.Responses.payClientResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionActivity extends AppCompatActivity {

    TextView name,phone,address,dept;
    public static EditText pay;
    Button done;
    public double old_cost = 0.0;
    private int id;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyTheme);
        setContentView(R.layout.activity_collection);
       String name =getIntent().getExtras().getString("NAME");
       String address =getIntent().getExtras().getString("ADDRESS");
       String phone =getIntent().getExtras().getString("PHONE");
        String dept="  ";
         id=getIntent().getExtras().getInt("ID");
         progressBar = findViewById(R.id.progress_colloction);
         progressBar.setVisibility(View.VISIBLE);
        this.name=(TextView)findViewById(R.id.name);
        this.phone=(TextView)findViewById(R.id.phone);
        this.address=(TextView)findViewById(R.id.address);
        this.dept=(TextView)findViewById(R.id.dept);
        this.pay=(EditText)findViewById(R.id.pay);
        done= (Button)findViewById(R.id.done); 
        this.name.setText(getResources().getString(R.string.customer_name)+"   "+name);
        this.phone.setText(getResources().getString(R.string.customer_phone)+"   "+phone);
        this.address.setText(getResources().getString(R.string.address)+"   "+address);
        this.dept.setText(getResources().getString(R.string.total_money)+"   "+dept);

        init();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String pay1=pay.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                if(pay1==null || pay1.equals("") || Double.valueOf(pay1) > old_cost){
                    Toast.makeText(CollectionActivity.this, getResources().getString(R.string.enter_valid_number), Toast.LENGTH_SHORT).show();
                   progressBar.setVisibility(View.INVISIBLE);
                }
                else
                {
                    RetrofitWebService.getService().PAY_CLIENT_CALL(new payClientRequest(id,Double.valueOf(pay1)))
                            .enqueue(new Callback<payClientResponse>() {
                                @Override
                                public void onResponse(Call<payClientResponse> call, Response<payClientResponse> response) {
                                    if(response.body().status==200)
                                    {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(CollectionActivity.this, getResources().getString(R.string.done), Toast.LENGTH_SHORT).show();
                                        Intent i=new Intent(CollectionActivity.this,CustomersActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(i);
                                        finish();
                                    }
                                }

                                @Override
                                public void onFailure(Call<payClientResponse> call, Throwable t) {

                                }
                            });
                }
            }
        });
        


    }

    public void init(){

        double init_cost = 0;
        RetrofitWebService.getService().PAY_CLIENT_CALL(new payClientRequest(id,init_cost))
                .enqueue(new Callback<payClientResponse>() {
                    @Override
                    public void onResponse(Call<payClientResponse> call, Response<payClientResponse> response) {
                        int s = response.body().status;
                        if(response.body().status == 200)
                        {
                            old_cost = (response.body().mony.getCost())-(response.body().mony.getMonyAgel()) ;
                            progressBar.setVisibility(View.INVISIBLE);
                            dept.setText(getResources().getString(R.string.Amount_of_defenses)+
                                    String.valueOf((response.body().mony.getCost())-(response.body().mony.getMonyAgel())));

                        }else if (response.body().status==204){
                            progressBar.setVisibility(View.INVISIBLE);
                            dept.setText(getResources().getString(R.string.Amount_of_defenses)+" 0.0  ");

                        }
                    }

                    @Override
                    public void onFailure(Call<payClientResponse> call, Throwable t) {
                    }
                });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(this,CustomersActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
