package omdvet.com;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omdvet.com.WebServices.Requests.addProductRequest;
import omdvet.com.WebServices.Responses.addProductResponse;
import omdvet.com.WebServices.RetrofitWebService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity {
    EditText productName,productSalary,productQnt;
    Button save;
    ImageView productImg;
    private String realImgPath;
    private MultipartBody.Part file;
    private static final int REQUEST_WRITE_PERMISSION = View.generateViewId();
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MyTheme);
        setContentView(R.layout.activity_add_product);
        productName=(EditText)findViewById(R.id.productName);
        productSalary=(EditText)findViewById(R.id.productPrice);
        productQnt=(EditText)findViewById(R.id.productQnt);
        productImg=(ImageView)findViewById(R.id.productImg);
        progressBar = findViewById(R.id.progress_prod);
        productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              openGallery();
            }
        });
        save=(Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String nameStr=productName.getText().toString();
               String priceStr= productSalary.getText().toString();
                String QntStr=productQnt.getText().toString();
                if(nameStr.equals("")||nameStr==null
                        ||priceStr.equals("")||priceStr==null
                        ||QntStr.equals("")||QntStr==null
                        ||realImgPath.equals("")||realImgPath==null) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(AddProductActivity.this, getResources().getString(R.string.fill_fields), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    RequestBody name;
                    name = RequestBody.create(MediaType.parse("text/plain"), nameStr);


                    RequestBody price;
                    price = RequestBody.create(MediaType.parse("text/plain"), priceStr);

                    RequestBody quantity;
                    quantity = RequestBody.create(MediaType.parse("text/plain"), QntStr);


                    //Toast.makeText(AddProductActivity.this, ""+realImgPath, Toast.LENGTH_SHORT).show();


                        File imgFile = new File(realImgPath);
                        RequestBody imgReqFile = RequestBody.create(MediaType.parse("image/*"), imgFile);
                        file = MultipartBody.Part.createFormData("photo", imgFile.getName(), imgReqFile);
                   //     Toast.makeText(AddProductActivity.this, ""+file, Toast.LENGTH_SHORT).show();
                   // Toast.makeText(AddProductActivity.this, ""+name, Toast.LENGTH_SHORT).show();



                    RetrofitWebService.getService().addProduct(name,price,quantity,file).enqueue(new Callback<addProductResponse>() {
                        @Override
                        public void onResponse(Call<addProductResponse> call, Response<addProductResponse> response) {
                            int status = response.body().getStatus();
                          //  Toast.makeText(AddProductActivity.this, ""+status, Toast.LENGTH_SHORT).show();
                            if(status==200)
                            {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(AddProductActivity.this, getResources().getString(R.string.product_added), Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i =new Intent(AddProductActivity.this,HomeActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<addProductResponse> call, Throwable t) {

                        }
                    });



                }


            }
        });

    }

    public void openGallery() {
        requestPermission();
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 100);
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

         if (requestCode == 100 && resultCode == RESULT_OK && null != data) {
             final Uri imageUri = data.getData();
              InputStream imageStream = null;
             try {
                 imageStream = getContentResolver().openInputStream(imageUri);
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
             final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
             productImg.setImageBitmap(selectedImage);


             Uri imagePath = data.getData();
             String[] filePathColumn = {MediaStore.Images.Media.DATA};
             Cursor cursor = getContentResolver().query(imagePath,
                     filePathColumn, null, null, null);
             cursor.moveToFirst();
             int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
             realImgPath = cursor.getString(columnIndex);
             cursor.close();
         }

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
