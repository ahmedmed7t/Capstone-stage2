package omdvet.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.DecimalFormat;
import java.util.ArrayList;

import omdvet.com.WebServices.Models.ClientOrder;
import omdvet.com.WebServices.Models.Delegate_order;
import omdvet.com.WebServices.Requests.clientInfoRequest;
import omdvet.com.WebServices.Responses.clientInfoResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DelegateOrdersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;
    private ProgressBar progressBar;
    final ArrayList<Delegate_order> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delegate_orders);

        progressBar = findViewById(R.id.prog);

        recyclerView = findViewById(R.id.oredrs_delegate_Recylcer);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        // get employee id
        SharedPreferences prefs = getSharedPreferences("USER", Context.MODE_PRIVATE);
        int emp_id = Integer.valueOf(prefs.getString("ID",""));


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Orders");


        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ClientOrder clientOrder = dataSnapshot.getValue(ClientOrder.class);
                getClientData(clientOrder.getClientId(),clientOrder);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };


        Query query = databaseReference.orderByChild("empId").equalTo(emp_id);
        query.addChildEventListener(childEventListener);
    }


    public void getClientData(int clientId, final ClientOrder mclientOrder){
        final Delegate_order delegate_order = new Delegate_order();
        RetrofitWebService.getService().CLIENT_INFO_CALL(new clientInfoRequest
                (clientId)).enqueue(new Callback<clientInfoResponse>() {
            @Override
            public void onResponse(Call<clientInfoResponse> call, Response<clientInfoResponse> response) {
                int status=response.body().status;
                if(status==200)
                {
                    delegate_order.setName(response.body().client.getName());
                    delegate_order.setAddress(response.body().client.getAddress());
                    delegate_order.setPhone(response.body().client.getPhone());

                    delegate_order.setDate(mclientOrder.getDate());
                    delegate_order.setCost_before(mclientOrder.getCost());

                    Double myCost = Double.valueOf(mclientOrder.getAfterDiscount()) ;
                    DecimalFormat df = new DecimalFormat("0.00");
                    String v = df.format(myCost);

                    myCost = Double.valueOf(v);


                    delegate_order.setFinal_cost(myCost);

                    progressBar.setVisibility(View.INVISIBLE);
                    arrayList.add(delegate_order);
                    DelegateOrderAdapter adapter = new DelegateOrderAdapter(arrayList);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<clientInfoResponse> call, Throwable t) {

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
