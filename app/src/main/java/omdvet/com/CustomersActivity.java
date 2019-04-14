package omdvet.com;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import omdvet.com.WebServices.Requests.clientsRequest;
import omdvet.com.WebServices.Requests.getProductsRequest;
import omdvet.com.WebServices.Responses.clientsResponse;
import omdvet.com.WebServices.Responses.getProductsResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomersActivity extends AppCompatActivity {
    private ArrayList customerNameList;
    private ArrayList customerPhoneList;
    private ArrayList customerAddressList;
    private ArrayList customerIdList;
    private ArrayList customerApiTokenList;
    private ArrayList customerempIdList;
    private ArrayList customerReport;
    ImageButton addCustomer;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    EditText search ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyTheme);
        setContentView(R.layout.activity_customers);
        search = findViewById(R.id.search_customer);
        progressBar = findViewById(R.id.progress_customer);
        progressBar.setVisibility(View.VISIBLE);
        addCustomer=(ImageButton)findViewById(R.id.addCustomer);
        SharedPreferences prefs =getSharedPreferences(
                "USER", Context.MODE_PRIVATE);
        String s = prefs.getString("IS_ADMIN","");


        if(s.equals("0"))
            addCustomer.setVisibility(View.GONE);
        init();


        addCustomer = (ImageButton) findViewById(R.id.addCustomer);
        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomersActivity.this,AddCustomerActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();

            }
        });


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.equals("")){

                    ArrayList s_customerNameList = new ArrayList<>();
                    ArrayList s_customerPhoneList = new ArrayList<>();
                    ArrayList s_customerAddressList = new ArrayList<>();
                    ArrayList s_customerIdList = new ArrayList<>();
                    ArrayList s_customerApiTokenList = new ArrayList<>();
                    ArrayList s_customerempIdList = new ArrayList<>();
                    ArrayList s_customerReport = new ArrayList<>();


                    String searchValue = "(?i).*"+s+".*" ;
                    Pattern r = Pattern.compile(searchValue);
                    Matcher m  , m1;

                    for (int i = 0 ; i < customerNameList.size() ; i++ ){
                        m = r.matcher(customerNameList.get(i).toString());
                        m1 = r.matcher(customerAddressList.get(i).toString());

                        if(m.find() || m1.find()){
                            s_customerNameList.add(customerNameList.get(i));
                            s_customerPhoneList.add(customerPhoneList.get(i));
                            s_customerAddressList.add(customerAddressList.get(i));
                            s_customerIdList.add(customerIdList.get(i));
                            s_customerApiTokenList.add(customerApiTokenList.get(i));
                            s_customerempIdList.add(customerempIdList.get(i));
                            s_customerReport.add(customerReport.get(i));
                        }
                    }
                    if(s_customerAddressList.size() > 0 ){

                        CustomersAdapter adapter = new CustomersAdapter(
                                CustomersActivity.this,
                                s_customerIdList,
                                s_customerempIdList,
                                s_customerApiTokenList,
                                s_customerNameList,
                                s_customerPhoneList,
                                s_customerAddressList,
                                s_customerReport);
                        recyclerView.setAdapter(adapter);
                    }else{
                        showNoItem();
                        CustomersAdapter adapter = new CustomersAdapter(
                                CustomersActivity.this,
                                customerIdList,
                                customerempIdList,
                                customerApiTokenList,
                                customerNameList,
                                customerPhoneList,
                                customerAddressList,
                                customerReport);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });

    }

    public void init(){
        recyclerView = findViewById(R.id.customerRecycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        customerNameList = new ArrayList<>();
        customerPhoneList = new ArrayList<>();
        customerAddressList = new ArrayList<>();
        customerIdList=new ArrayList<>();
        customerempIdList=new ArrayList();
        customerApiTokenList=new ArrayList<>();
        customerReport=new ArrayList<>();


        SharedPreferences prefs = getSharedPreferences(
                "USER", Context.MODE_PRIVATE);
        String s = prefs.getString("apiToken","");
        RetrofitWebService.getService().CLIENTS_CALL(new clientsRequest(s))
                .enqueue(new Callback<clientsResponse>() {
                    @Override
                    public void onResponse(Call<clientsResponse> call, Response<clientsResponse> response) {
                        int status=response.body().getStatus();
                        if(status==200) {

                            progressBar.setVisibility(View.INVISIBLE);
                            for (int i = 0; i < response.body().getClients().size(); i++) {

                                customerNameList.add(response.body().getClients().get(i).name);
                                customerApiTokenList.add(response.body().getClients().get(i).apiToken);
                                customerAddressList.add(response.body().getClients().get(i).address);
                                customerIdList.add(response.body().getClients().get(i).id);
                                customerempIdList.add(response.body().getClients().get(i).emp_id);
                                customerPhoneList.add(response.body().getClients().get(i).phone);
                                customerReport.add(response.body().getClients().get(i).is_report);
                            }
                        }

                        CustomersAdapter adapter = new CustomersAdapter(
                                CustomersActivity.this,
                                 customerIdList,
                                 customerempIdList,
                                 customerApiTokenList,
                                 customerNameList,
                                 customerPhoneList,
                                customerAddressList,
                                customerReport);
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onFailure(Call<clientsResponse> call, Throwable t) {

                    }

                });



    }

    public void showNoItem(){
        Toast.makeText(this,getResources().getString(R.string.no_result),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(this,HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
