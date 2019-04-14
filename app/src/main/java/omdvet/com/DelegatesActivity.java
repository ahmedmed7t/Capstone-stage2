package omdvet.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import omdvet.com.WebServices.Requests.EmpRequest;
import omdvet.com.WebServices.Responses.EmpResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DelegatesActivity extends AppCompatActivity {
    private ArrayList<String> delegateNameList;
    private ArrayList<String> delegatePhoneList;
    private ArrayList<String> delegateMailList;
    private ArrayList<String> delegateIDList;
    private ArrayList<String> delegateAddressList;
    private ArrayList<String> delegateSalesList;
    private ArrayList<String> delegateSalesValueList;
    private ImageButton addDelegate;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyTheme);
        setContentView(R.layout.activity_delegates);
        progressBar = findViewById(R.id.progress_delegate);
        progressBar.setVisibility(View.VISIBLE);
        addDelegate = (ImageButton)findViewById(R.id.addDelegate);

        addDelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DelegatesActivity.this,AddDelegateActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });
        delegateNameList = new ArrayList<>();
        delegatePhoneList = new ArrayList<>();
        delegateMailList = new ArrayList<>();
        delegateIDList = new ArrayList<>();
        delegateAddressList = new ArrayList<>();
        delegateSalesList = new ArrayList<>();
        delegateSalesValueList = new ArrayList<>();
        init();
    }

    public void init(){
        RetrofitWebService.getService().EMP_CALL(new EmpRequest())
                .enqueue(new Callback<EmpResponse>() {
                    @Override
                    public void onResponse(Call<EmpResponse> call, Response<EmpResponse> response) {
                        int status=response.body().status;
                        if(status==200) {
                            progressBar.setVisibility(View.INVISIBLE);
                            for (int i = 0; i < response.body().emps.size(); i++) {



                                delegateNameList.add(response.body().emps.get(i).name);
                                delegatePhoneList.add(response.body().emps.get(i).phone);
                                delegateMailList.add(response.body().emps.get(i).email);
                                delegateIDList.add(response.body().emps.get(i).User_id);
                                delegateAddressList.add(response.body().emps.get(i).Address);
                                delegateSalesList.add(response.body().emps.get(i).clients);
                                delegateSalesValueList.add(response.body().emps.get(i).gain);
                                RecyclerView recyclerView = (RecyclerView)findViewById(R.id.delegateRecycler);
                                recyclerView.setHasFixedSize(true);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(layoutManager);



                                RecyclerView.Adapter adapter = new DelegatesAdapter(DelegatesActivity.this,delegateNameList,delegatePhoneList,delegateMailList,delegateIDList
                                        ,delegateAddressList , delegateSalesList , delegateSalesValueList);
                                recyclerView.setAdapter(adapter);

                                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                                    @Override
                                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                                        View child = rv.findChildViewUnder(e.getX(),e.getY());
                                        if(child!=null )
                                        {
                                            int position = rv.getChildAdapterPosition(child);
                                        }
                                        return false;
                                    }

                                    @Override
                                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                                    }

                                    @Override
                                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                                    }
                                });
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<EmpResponse> call, Throwable t) {

                    }
                });

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
