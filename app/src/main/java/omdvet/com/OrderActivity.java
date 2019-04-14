package omdvet.com;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import omdvet.com.WebServices.Models.ClientOrder;
import omdvet.com.WebServices.Models.Emp;
import omdvet.com.WebServices.Models.Orders;
import omdvet.com.WebServices.Models.Product;
import omdvet.com.WebServices.Requests.EmpRequest;
import omdvet.com.WebServices.Requests.addOrderRequest;
import omdvet.com.WebServices.Requests.getProductsRequest;
import omdvet.com.WebServices.Responses.EmpResponse;
import omdvet.com.WebServices.Responses.addOrderResponse;
import omdvet.com.WebServices.Responses.getProductsResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class OrderActivity extends AppCompatActivity {
    private ArrayList<Orders> allOrders ;
    int discount = -1 ;
    float finalTotalCostWithoutDiscount , finalTotalCost;
    int is_cash = -1 ;
    String type = "";
    Intent mIntent ;


    private TextView name,phone,address,cost,finalCost;
    private EditText day,month,year;
    Button done,calculate;
    private RecyclerView recyclerView;
    RadioButton radioButton,radioButton2;
    private RecyclerView ProductRecycler2;
    public float add,finalCostF;
    ProductAdapter2 adapter;
    ProgressBar progressBar;
    Boolean flag = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyTheme);
        setContentView(R.layout.activity_order);
        progressBar = findViewById(R.id.progress_order);
        progressBar.setVisibility(View.VISIBLE);

        mIntent = getIntent();

        name=(TextView)findViewById(R.id.name);
        phone=(TextView)findViewById(R.id.phone);
        address=(TextView)findViewById(R.id.address);
        cost=(TextView)findViewById(R.id.cost);
        finalCost=(TextView)findViewById(R.id.finalCost);
        day=(EditText) findViewById(R.id.day);
        month=(EditText) findViewById(R.id.month);
        year=(EditText) findViewById(R.id.year);
        done=(Button) findViewById(R.id.done);
        calculate=(Button) findViewById(R.id.calculate);
        radioButton = (RadioButton)findViewById(R.id.radioButton);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);


        ProductRecycler2=(RecyclerView)findViewById(R.id.ProductRecycler2);
        name.setText(getResources().getString(R.string.name) + "    "+getIntent().getExtras().getString("NAME"));
        phone.setText(getResources().getString(R.string.phone) + "    "+getIntent().getExtras().getString("PHONE"));
        address.setText(getResources().getString(R.string.address) + "    "+ getIntent().getExtras().getString("ADDRESS"));

        init();

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton2.setChecked(false);
                is_cash = 1 ;
                type = "cach";
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioButton.setChecked(false);
                is_cash = 0 ;
                type = "agel";
            }
        });



        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                calcCosts();
            }
          });




        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if(!flag){
                    calcCosts();
                }
                SharedPreferences prefs = getSharedPreferences("USER", Context.MODE_PRIVATE);
                String apiToken = prefs.getString("apiToken","");

                String date = year.getText()+"-"+month.getText()+"-"+day.getText();

                int emp_id = Integer.valueOf(prefs.getString("ID",""));

                int cust_id = mIntent.getIntExtra("CUSTOMER_ID",-1);


                ClientOrder clientOrder = new ClientOrder();

                List<Product> products = cleanProductArray(ProductAdapter2.clientProducts);


                if(is_cash == -1 || year.getText().equals("") || month.getText().equals("")
                        || day.getText().equals("") ){
                   showError();
                }else {
                    clientOrder.setApiToken(apiToken);
                    clientOrder.setDate(date);
                    clientOrder.setCost(finalTotalCostWithoutDiscount);
                    clientOrder.setAfterDiscount(finalTotalCost);
                    clientOrder.setProduct(products);
                    clientOrder.setIsCach(is_cash);
                    clientOrder.setType(type);
                    clientOrder.setEmpId(emp_id);
                    clientOrder.setClientId(cust_id);


                    RetrofitWebService.getService().ADD_ORDER_CALL(clientOrder).enqueue(new Callback<addOrderResponse>() {
                        @Override
                        public void onResponse(Call<addOrderResponse> call, Response<addOrderResponse> response) {
                            int status = response.body().status;
                            if(status == 200) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.done), Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                back();
                            }
                        }

                        @Override
                        public void onFailure(Call<addOrderResponse> call, Throwable t) {

                        }
                    });

                }

                getData();

            }

        });


    }
    public void showError(){
        Toast.makeText(this,getResources().getString(R.string.fill_fields) ,Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.INVISIBLE);
    }
    public void init(){

        recyclerView = (RecyclerView)findViewById(R.id.ProductRecycler2);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        SharedPreferences prefs = getSharedPreferences(
                "USER", Context.MODE_PRIVATE);
        String s = prefs.getString("apiToken","");
        RetrofitWebService.getService().GET_PRODUCT_CALL(new getProductsRequest(s))
                .enqueue(new Callback<getProductsResponse>() {
                    @Override
                    public void onResponse(Call<getProductsResponse> call, Response<getProductsResponse> response) {
                        int status=response.body().getStatus();
                        if(status==200)
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            allOrders = response.body().orders;
                        }

                        adapter = new ProductAdapter2(getApplicationContext(),allOrders);
                        recyclerView.setAdapter(adapter);

                        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                            @Override
                            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                                return false;
                            }

                            @Override
                            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

//                    int position = rv.getChildAdapterPosition(child);
//                    View child = rv.getChildAt(position);


                            }

                            @Override
                            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {


                            }
                        });

                    }
                    @Override
                    public void onFailure(Call<getProductsResponse> call, Throwable t) {

                    }
                });
    }

    public List<Product> cleanProductArray(List<Product> array){

        List<Product> newList = new ArrayList<>();

        for(int i=0 ; i<array.size() ; i++){
            if(array.get(i).getQuantity() > 0){
                newList.add(array.get(i));
            }
        }

        return newList;
    }

    public void back(){
        Intent intent = new Intent(this,CustomersActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,CustomersActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void showTypeError(){
        Toast.makeText(this,getResources().getString(R.string.enter_payment_type),Toast.LENGTH_LONG).show();
    }

    public void calcCosts(){

        if(radioButton.isChecked() == true || radioButton2.isChecked() == true) {

            finalTotalCostWithoutDiscount = ProductAdapter2.CalcuteTotalSum();
            cost.setText(String.valueOf(finalTotalCostWithoutDiscount));

            if (is_cash == 1) {
                float discountValue = (finalTotalCostWithoutDiscount * 15) / 100;
                finalTotalCost = finalTotalCostWithoutDiscount - discountValue;

                float discountValue2 = (finalTotalCost * 5) / 100;
                finalTotalCost -= discountValue2;

                float discountValue3 = (finalTotalCost * 5) / 100;
                finalTotalCost -= discountValue3;

                float discountValue4 = (finalTotalCost * 10) / 100;
                finalTotalCost -= discountValue4;

                DecimalFormat df = new DecimalFormat("0.00");
                String v = df.format(finalTotalCost);

                finalTotalCost = Float.valueOf(v);
            } else {

                float discountValue = (finalTotalCostWithoutDiscount * 15) / 100;
                finalTotalCost = finalTotalCostWithoutDiscount - discountValue;

                float discountValue2 = (finalTotalCost * 5) / 100;
                finalTotalCost -= discountValue2;

                float discountValue3 = (finalTotalCost * 5) / 100;
                finalTotalCost -= discountValue3;

                DecimalFormat df = new DecimalFormat("0.00");
                String v = df.format(finalTotalCost);

                finalTotalCost = Float.valueOf(v);
            }

            finalCost.setText(String.valueOf(finalTotalCost));
        } else {
            showTypeError();
        }

    }

    public void  getData(){

        RetrofitWebService.getService().EMP_CALL(new EmpRequest()).enqueue(new Callback<EmpResponse>() {
            @Override
            public void onResponse(Call<EmpResponse> call, Response<EmpResponse> response) {
                ArrayList<Emp> arrayList = response.body().emps;
                String mText = getResources().getString(R.string.delegate_name)+"\n";
                String text1 = getResources().getString(R.string.n_customers)+"\n";
                for(int i = 0 ; i < arrayList.size() ; i++){
                    mText +=  arrayList.get(i).getName()+"\n";
                    text1 +=  arrayList.get(i).getClients()+"\n";

                }

                addToWidget(mText,text1);
            }

            @Override
            public void onFailure(Call<EmpResponse> call, Throwable t) {

            }
        });

    }

    public void addToWidget(String text,String text1 ){

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, NewAppWidget.class));
        NewAppWidget.update_widget(this,appWidgetManager,appWidgetIds,text,text1);

    }
}
