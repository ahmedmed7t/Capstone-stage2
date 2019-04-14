package omdvet.com;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set Theme with no ActionBar (required in splash screen)
        setTheme(R.style.MyTheme);
        setContentView(R.layout.activity_splash);
        //create a timer that shows splash screen Activity for 3 seconds, then opens the appropriate activity
new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
        Intent ChooseLangIntent = new Intent(SplashActivity.this,LoginActivity.class);
        startActivity(ChooseLangIntent);
        finish();
    }
},3000);
    }
}
