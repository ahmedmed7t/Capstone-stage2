package omdvet.com;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import omdvet.com.WebServices.Requests.addClientRequest;
import omdvet.com.WebServices.Responses.addClientResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCustomerActivity extends AppCompatActivity {

    EditText name,phone,address;
    Button save;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyTheme);
        setContentView(R.layout.activity_add_customer);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        address=(EditText)findViewById(R.id.address);
        save=(Button)findViewById(R.id.save);
        progressBar = findViewById(R.id.progress_cust);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr=name.getText().toString();
                String phoneStr=phone.getText().toString();
                String addressStr=address.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                if(nameStr.equals("")||nameStr==null
                        ||phoneStr.equals("")||phoneStr==null
                        ||addressStr.equals("")||addressStr==null)
                {
                    Toast.makeText(AddCustomerActivity.this, getResources().getString(R.string.fill_fields), Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        SharedPreferences prefs = getSharedPreferences(
                                "USER", Context.MODE_PRIVATE);
                        String apiToken = prefs.getString("apiToken","");
                        RetrofitWebService.getService().ADD_CLIENT_CALL(new addClientRequest(apiToken,nameStr,phoneStr
                        ,addressStr)).enqueue(new Callback<addClientResponse>() {
                            @Override
                            public void onResponse(Call<addClientResponse> call, Response<addClientResponse> response) {
                                int status = response.body().getStatus();
                                if(status==200)
                                {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    finish();
                                    Toast.makeText(AddCustomerActivity.this, getResources().getString(R.string.customer_added), Toast.LENGTH_SHORT).show();
                                    Intent i=new Intent(AddCustomerActivity.this,CustomersActivity.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(i);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<addClientResponse> call, Throwable t) {

                            }
                        });
                    }
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
