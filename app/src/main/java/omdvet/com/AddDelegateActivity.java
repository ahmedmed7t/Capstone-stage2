package omdvet.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import omdvet.com.WebServices.Requests.RegistRequest;
import omdvet.com.WebServices.Responses.RegistResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDelegateActivity extends AppCompatActivity {
EditText name,phone,email,password,confirm,id,address;
ProgressBar progressBar;
Button save,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyTheme);
        setContentView(R.layout.activity_signup);

        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.password);
        confirm=(EditText)findViewById(R.id.confirm);
        id=(EditText)findViewById(R.id.id);
        address=(EditText)findViewById(R.id.address);
        save=(Button)findViewById(R.id.save);
        cancel=(Button)findViewById(R.id.cancel);
        progressBar = findViewById(R.id.progress);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                email.setText("");
                phone.setText("");
                password.setText("");
                confirm.setText("");
                id.setText("");
                id.setText("");
                address.setText("");
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr=name.getText().toString();
                String emailStr=email.getText().toString();
                String phoneStr=phone.getText().toString();
                String passStr=password.getText().toString();
                String confirmStr=confirm.getText().toString();
                String idStr=id.getText().toString();
                String addressStr=address.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                if(nameStr.equals("")||nameStr==null
                        ||emailStr.equals("")||emailStr==null
                        ||phoneStr.equals("")||phoneStr==null
                        ||passStr.equals("")||passStr==null
                        ||confirmStr.equals("")||confirmStr==null
                        ||idStr.equals("")||idStr==null
                        ||addressStr.equals("")||addressStr==null)
                {
                    Toast.makeText(AddDelegateActivity.this, getResources().getString(R.string.fill_fields), Toast.LENGTH_SHORT).show();
                }

                else if(!passStr.equals(confirmStr))
                    Toast.makeText(AddDelegateActivity.this, getResources().getString(R.string.password_not_compatable), Toast.LENGTH_SHORT).show();

                else
                {
                    RetrofitWebService.getService().REGIST_CALL(new RegistRequest(
                            nameStr,emailStr,phoneStr,passStr,idStr,addressStr
                    )).enqueue(new Callback<RegistResponse>() {
                        @Override
                        public void onResponse(Call<RegistResponse> call, Response<RegistResponse> response) {
                            int status = response.body().getStatus();
                            if(status==200)
                            {
                                Toast.makeText(AddDelegateActivity.this, getResources().getString(R.string.account_created), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                finish();
                                Intent i =new Intent(AddDelegateActivity.this,DelegatesActivity.class);
                                startActivity(i);
                            }
                            else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(AddDelegateActivity.this, getResources().getString(R.string.enter_valid_number), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegistResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i =new Intent(this,DelegatesActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
