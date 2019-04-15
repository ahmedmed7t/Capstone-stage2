package omdvet.com;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import omdvet.com.WebServices.Requests.LoginRequest;
import omdvet.com.WebServices.Responses.LoginResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText phoneNumber,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyTheme);
        setContentView(R.layout.activity_login);

        MultiDex.install(this);

        MobileAds.initialize(this,"ca-app-pub-8606368708228706~7837891872");


        AdView mAdView = (AdView) findViewById(R.id.adViewLogin);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);



        phoneNumber = (EditText)findViewById(R.id.phoneNumbr);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumberStr = phoneNumber.getText().toString();
                String passwordStr = password.getText().toString();
                if(phoneNumberStr.equals("")||phoneNumberStr==null
                        ||passwordStr.equals("")||passwordStr==null)
                {

                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.fill_fields), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RetrofitWebService.getService().LOGIN_CALL(new LoginRequest(phoneNumberStr,passwordStr))
                            .enqueue(new Callback<LoginResponse>() {
                                @Override
                                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                    int status = response.body().getStatus();
                                    if(status==200)
                                    {
                                       // finish();
                                        Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                                        startActivity(i);
                                        SharedPreferences prefs = getSharedPreferences(
                                                "USER", Context.MODE_PRIVATE);
                                        String s = response.body().getEmp().id;
                                        String n = response.body().getEmp().name;
                                        String e = response.body().getEmp().email;
                                        prefs.edit().putString("NAME",response.body().getEmp().name).apply();
                                        prefs.edit().putString("apiToken",response.body().getEmp().apiToken).apply();
                                        prefs.edit().putString("EMAIL",response.body().getEmp().email).apply();
                                        prefs.edit().putString("PHONE",response.body().getEmp().phone).apply();
                                        prefs.edit().putString("IS_ADMIN",response.body().getEmp().is_admin).apply();
                                        prefs.edit().putString("ADDRESS",response.body().getEmp().Address).apply();
                                        prefs.edit().putString("ID",response.body().getEmp().id).apply();

                                    }
                                }

                                @Override
                                public void onFailure(Call<LoginResponse> call, Throwable t) {

                                }
                            });
                }



            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        System.exit(0);
    }


}
