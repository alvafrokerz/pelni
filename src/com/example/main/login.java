package com.example.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by ALVA on 8/28/2016.
 */

public class login extends Activity {
    EditText user,pwd;
    private JSONObject jObject;
    Handler mHandler;
    private String p="",xResult ="";
    get_data a=new get_data();
    //Seusuaikan url dengan nama domain yang anda gunakan
    private String url = "http://catering.gti-digital.com/api/master/users";
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        a.YourNonActivityClass(this);
        user=(EditText) findViewById(R.id.usertext);
        pwd=(EditText) findViewById(R.id.pwdtext);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder() .permitAll().build();
        StrictMode.setThreadPolicy(policy);
user.setOnFocusChangeListener(new View.OnFocusChangeListener()
{
    @Override
    public void onFocusChange(View v, boolean hasFocus)
    {
        Toast.makeText(login.this, "Selected: " , Toast.LENGTH_LONG).show();

    }
});
        Button start = (Button) findViewById(R.id.button);
        start.setOnClickListener(   new View.OnClickListener() {

            public void onClick(View arg0) {

                String usertext=user.getText().toString();
                String pwdtext=pwd.getText().toString();

                a.login(usertext,pwdtext);
                Intent i = new Intent(login.this , menu.class);
                finish();
                startActivity(i);

            }
        });

    }
    /*
    private void login(final String username, String password) {

        class LoginAsync extends AsyncTask<String, Void, String> {

            private Dialog loadingDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loadingDialog = ProgressDialog.show(login.this, "Please wait", "Loading...");
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

                Intent intent = new Intent(login.this, menu.class);

                finish();
                startActivity(intent);
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
                }
            }
        }

        LoginAsync la = new LoginAsync();
        la.execute(username, password);

    }*/
}


