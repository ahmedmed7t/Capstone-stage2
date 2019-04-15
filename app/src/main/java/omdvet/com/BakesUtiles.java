package omdvet.com;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import omdvet.com.WebServices.Models.AllBilles;

/**
 * Created by CreazyNet on 19/03/2019.
 */

public class BakesUtiles {
        private static URL createUrl(String Url)
        {
            URL url=null;
            try {
                url = new URL(Url);
            } catch (MalformedURLException e) {
                Log.e(BakesUtiles.class.getName(), "Problem building the URL ", e);
            }
            return url;
        }

        private static ArrayList<AllBilles> extraxtMovies(String json){
            ArrayList<AllBilles>BakesArray = new ArrayList<>();

            try {

                JSONObject result=new JSONObject(json);

                JSONArray array = result.optJSONArray("billes");

                for (int i=0;i<array.length();i++)
                {
                    JSONObject recipeObject  = array.optJSONObject(i);

                    int id = recipeObject.optInt("id",-1);
                    String name = recipeObject.optString("name","no");
                    String phone = recipeObject.optString("phone","no");
                    String address = recipeObject.optString("address","no");
                    String cost = recipeObject.optString("cost","no");
                    String afterDiscount = recipeObject.optString("afterDiscount","no");
                    String date = recipeObject.optString("date","no");
                    String created_at = recipeObject.optString("created_at","no");
                    String type = recipeObject.optString("type","no");
                    String is_cach = recipeObject.optString("is_cach","no");
                    String mony_agel = recipeObject.optString("mony_agel","no");
                    String emp_id = recipeObject.optString("emp_id","no");
                    String client_id = recipeObject.optString("client_id","no");


                    BakesArray.add(new AllBilles(id,name,phone,address,cost,afterDiscount,date,created_at
                            ,type,is_cach,mony_agel,emp_id,client_id)
                    );
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return BakesArray;

        }
        private static String makeHttpRequest(URL url) throws IOException {
            String JsonResponse ="";
            if (url==null)
                return null;
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream=null;
            try {
                httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                if (httpURLConnection.getResponseCode() == 200)
                {
                    inputStream=httpURLConnection.getInputStream();
                    JsonResponse=readFromStream(inputStream);
                }
                else {
                    Log.e(BakesUtiles.class.getName(), "Error response code: " +httpURLConnection.getResponseCode());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStream != null) {
                    // Closing the input stream could throw an IOException, which is why
                    // the makeHttpRequest(URL url) method signature specifies than an IOException
                    // could be thrown.
                    inputStream.close();
                }
            }

            return JsonResponse;
        }
        private static String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }
        public static ArrayList<AllBilles> fetchdata(String Url)
        {

            URL url =createUrl(Url);
            String jsonData=null;
            try {
                jsonData=makeHttpRequest(url);
            } catch (IOException e) {
               // Log.e(MoviesUtils.class.getName(), "Problem making the HTTP request.", e);
            }

            return extraxtMovies(jsonData);
        }
    }


