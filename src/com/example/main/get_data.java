package com.example.main;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by ALVA on 10/9/2016.
 */

public class get_data {

    private JSONObject jObject;
    Handler mHandler;
    private String p="",xResult ="";

 //   public String url = "http://catering.gti-digital.com/api/master/users";

    public String url = "http://catering.gti-digital.com/json/voyage_planning.json";
    private Context context;

//save the context recievied via constructor in a local variable

    public void YourNonActivityClass(Context context){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder() .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        this.context=context;
    }

    public void login(final String username, String password   ) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder() .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                loadingDialog = ProgressDialog.show(context, "Please wait", "Loading...");
            }

            @Override
            protected String doInBackground(String... params) {
                String uname = params[0];
                String pass = params[1];

                InputStream is = null;
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("username", uname));
                nameValuePairs.add(new BasicNameValuePair("password", pass));
                String result = null;

                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(url);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    httpPost.addHeader("token","eyJ0eXAiOiJKV1QiLCJhbGci9.eyJzdWIiOjEsImlzcyI6Imh0dHA6XC9cL2NhdGVyaW5nLmd0aS1kaWdpdGFsLmNvbVwvYXBpXC9hdXRoZW50aWNhdGUiLCJpYXQiOjE0NzQ2OTEzNzIsImV4cCI6MTQ3NDY5NDk3MiwibmJmIjoxNDc0NjkxMzcyLCJqdGkiOiJlMzM5ZmFhMzUyYjJhMTYwYTRhZDgzYzNjYjM5ZmNhNCJ9._Rt1BIZmbxBSPusDSm1eMaKlLnbPt0D9pH-pkSkmVUw");
                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();

  //String strJson="{\"id\":\"01\",\"name\":\"GopalVarma\",\"salary\":\"500000\"},{\"id\":\"02\",\"name\":\"Sairamkrishna\",\"salary\":\"500000\"},{\"id\":\"03\",\"name\":\"Sathishkallakuri\",\"salary\":\"600000\"}";
                    String strJson=result;
                    String data = "";
                    try {
                        JSONObject  jsonRootObject = new JSONObject(strJson);

                        //Get the instance of JSONArray that contains JSONObjects
                        JSONArray jsonArray = jsonRootObject.optJSONArray("data");

                        //Iterate the jsonArray and print the info of JSONObjects
                        for(int i=0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String id =  jsonObject.optString("departure").toString() ;
                            String name = jsonObject.optString("origin").toString();
                            String salary =  jsonObject.optString("destination").toString() ;

                            data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
                        }
                        Log.i("datasssssssssss",data);
                    } catch (JSONException e) {e.printStackTrace();}




                    Log.i("tes", result);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                String s = result.trim();
                Log.i("data",s);

                String val[]=s.split("/");

                loadingDialog.dismiss();
Log.i("tes","tes");



               /*
                if(val[0].equalsIgnoreCase("s")){

                    if(val[2].equals("3")){

                        Intent intent = new Intent(login.this, menu.class);

                        finish();
                        startActivity(intent);}
                    else{
                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse("http://36.66.89.187/isopro4/public/webservice/g2.php?username="+user.getText().toString()+"&password="+pwd.getText().toString()+""));
                        startActivity(viewIntent);
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                }*/
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(username, password);

    }
}
