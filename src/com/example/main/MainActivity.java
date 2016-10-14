package com.example.main;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


 
@SuppressLint("NewApi") public class MainActivity extends AppCompatActivity {
	HttpURLConnection connection = null;
	DataOutputStream outputStream = null;
	DataInputStream inputStream = null;
	String urlServer = "http://nfc.langgengpariwara.com/pages/check.php";
	String urlServercek = "http://nfc.langgengpariwara.com/pages/cek.php";
	String namatext,asaltext,warnagatetext,nomejatext,waktu;
	InputStream is=null;
	String result=null;
	String line=null;
	int code;
	NfcAdapter mAdapter;
	Tag mTag;
	PendingIntent mPI;
	IntentFilter[] mFilter;
	EditText mData, mRespond;
	Button read, write;
	String userData;

	SQLiteDatabase mydb;
	private static String DBNAME = "guestbook.db";    // THIS IS THE SQLITE DATABASE FILE NAME.
    private static String TABLE = "guestbook";  
	    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		 StrictMode.ThreadPolicy policy = new	StrictMode.ThreadPolicy.Builder() .permitAll().build();
	        StrictMode.setThreadPolicy(policy);
	 /*** Inisialisasi Variabel ***/
		mAdapter = NfcAdapter.getDefaultAdapter(this);
		mPI = PendingIntent.getActivity(getApplicationContext(), 0,
				new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0	);
		IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
		IntentFilter filter2 = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
		mFilter = new IntentFilter[]{tagDetected,filter2};
		

	//   asal=(TextView)findViewById(R.id.Asal);
	 
 	  String timeStamp = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss").format(new Date());
 		String u[]=timeStamp.split("-");
 		int cektanggal=Integer.parseInt(u[0]);
 		int cekbulan=Integer.parseInt(u[1]);
 		int cektahun=Integer.parseInt(u[2]);
 	 
 		if(cektanggal>20 && cekbulan>8 && cektahun>=2016){
 			finish();
 			
 		}


		
	  createTable();
	  showTableValues_update();
		
	}

	
	
	private NdefRecord createRecord(byte[] payload)
	{
		NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
										   NdefRecord.RTD_TEXT,
										   new byte[0],
										   payload
										   );
		return record;
	}
	
	
	
	NdefMessage[] getNdefMessage(Intent intent)
	{
		NdefMessage[] msgs = null;
		
		Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		if(rawMsgs != null)
		{
			msgs = new NdefMessage[rawMsgs.length];
			for(int i=0; i<rawMsgs.length; i++)
			{
				msgs[i] = (NdefMessage)rawMsgs[i];
			}
		}
		//Log.i("Length Raw Message", Integer.toString(rawMsgs.length));
		return msgs;
	}
	
	
	
	
	 
	 

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		
		if(intent.getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED))
		{			
			Toast.makeText(getApplicationContext(),"Ndefdiscovered",Toast.LENGTH_SHORT).show();
			
		}else if(intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED))
		{
			mTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			Toast.makeText(getApplicationContext(),"Smartcard detected",Toast.LENGTH_SHORT).show();
			//Toast.makeText(getApplicationContext(),intent.getExtras().toString(),Toast.LENGTH_SHORT).show();
			NdefMessage[] messages = getNdefMessage(intent);			
			if(messages == null)
			{
				Toast.makeText(getApplicationContext(),"Data di dalam kartu kosong",Toast.LENGTH_SHORT).show();
				return;
			}			
			byte[] payload = messages[0].getRecords()[0].getPayload();			
			userData = new String(payload);		
			String u[]=userData.split("/");

			// asal.setText(""+u[1]);
	 
				
			  try {
				 namatext=u[0];
				 asaltext=u[1];
				 warnagatetext=u[2];
				 nomejatext=u[3];
				 
					String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
					  waktu = timeStamp;
					  cek(namatext,  asaltext,warnagatetext,nomejatext,waktu);
					 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			//Toast.makeText(getApplicationContext(),intent.getExtras().toString(),Toast.LENGTH_SHORT).show();
		}
	} 

	 public void createTable(){
	        try{
	        mydb = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
	        mydb.execSQL("CREATE TABLE IF  NOT EXISTS "+ TABLE +" (ID INTEGER PRIMARY KEY ASC,nama TEXT, asal TEXT, warnagate TEXT,nomeja TEXT,waktu TEXT,status TEXT);");
	        mydb.close();
	        Toast.makeText(getApplicationContext(), "sukses", Toast.LENGTH_LONG);
 
	        
	        }catch(Exception e){
	            Toast.makeText(getApplicationContext(), "Error in creating table", Toast.LENGTH_LONG);
	        }
	    }
	 public  void showTableValues_update(){
		  mydb = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
          Cursor allrows  = mydb.rawQuery("SELECT * FROM "+  TABLE , null);
          System.out.println("COUNTsss : " + allrows.getCount());
         	   
         	 if(allrows.moveToFirst()){
               do{
               	  
                   Log.i("id","id : " + allrows.getString(0));
    	           Log.i("nama","nama : " + allrows.getString(1));
    	           Log.i("alamat","alamat : " + allrows.getString(2));
    	           Log.i("toko","toko : " + allrows.getString(3));
    	           Log.i("waktu","waktu : " + allrows.getString(4));
    	           Log.i("stat","stat : " + allrows.getString(5));
    	           
               }	while(allrows.moveToNext());
           }
            
         
           
           mydb.close();
	        
	    }
		public void insertIntoTable1(String nama2,   String asal2,String warnagate2,String nomeja2,String waktu2,String stat2){
	        try{
	            mydb = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
	            mydb.execSQL("INSERT INTO guestbook ( nama, asal,warnagate,nomeja,waktu,status) VALUES( '"+nama2+"','"+asal2+"','"+warnagate2+"','"+nomeja2+"','"+waktu2+"','"+stat2+"')");
	            mydb.close();
	          
	           
	        }catch(Exception e){
	            Toast.makeText(getApplicationContext(), "Error in inserting into table", Toast.LENGTH_LONG);
	        }
	    } 
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mAdapter.disableForegroundDispatch(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mAdapter.enableForegroundDispatch(this, mPI, mFilter, null);
	}
	  public void insert(String nama1, String asal1,String warnagate1,String nomeja1,String waktu1 )
	    {
		
	    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	 
	   	nameValuePairs.add(new BasicNameValuePair("nama",nama1));
	   	nameValuePairs.add(new BasicNameValuePair("asal",asal1));
		nameValuePairs.add(new BasicNameValuePair("warnagate",warnagate1));
		nameValuePairs.add(new BasicNameValuePair("noundangan",nomeja1));
		 	 	nameValuePairs.add(new BasicNameValuePair("waktu",waktu1));
		   	
	    	try
	    	{
			HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost(urlServer);
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost); 
		        HttpEntity entity = response.getEntity();
		        is = entity.getContent();
		        Log.e("pass 1", "connection success ");
		}
	        catch(Exception e)
		{
	        	insertIntoTable1(nama1, asal1,warnagate1,nomeja1 ,waktu1,"0");
	        	Log.e("Fail 1", e.toString());
		    	Toast.makeText(getApplicationContext(), "Invalid IP Address",
				Toast.LENGTH_LONG).show();
		}     
	        
	        try
	        {
	            BufferedReader reader = new BufferedReader
				(new InputStreamReader(is,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            while ((line = reader.readLine()) != null)
		    {
	                sb.append(line + "\n");
	            }
	            is.close();
	            result = sb.toString();
		    Log.e("pass 2", result);
		}
	        catch(Exception e)
		{
	            Log.e("Fail 2", e.toString());
		}     
	       
		try
		{
	            JSONObject json_data = new JSONObject(result);
	            code=(json_data.getInt("code"));
				
	            if(code==1)
	            {
			Toast.makeText(getBaseContext(), "Inserted Successfully",
				Toast.LENGTH_SHORT).show();
	            }
	            else
	            {
			 Toast.makeText(getBaseContext(), "Sorry, Try Again",
				Toast.LENGTH_LONG).show();
	            }
		}
		catch(Exception e)
		{
	            Log.e("Fail 3", e.toString());
		}
	    }
	  public void cek(String nama1, String asal1,String warnagate1,String nomeja1,String waktu1 )
	    {
		
	    	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	 
	   	nameValuePairs.add(new BasicNameValuePair("nama",nama1));
	   	nameValuePairs.add(new BasicNameValuePair("asal",asal1));
		nameValuePairs.add(new BasicNameValuePair("warnagate",warnagate1));
		nameValuePairs.add(new BasicNameValuePair("noundangan",nomeja1));
		 	 	nameValuePairs.add(new BasicNameValuePair("waktu",waktu1));
		   	
	    	try
	    	{
			HttpClient httpclient = new DefaultHttpClient();
		        HttpPost httppost = new HttpPost(urlServercek);
		        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		        HttpResponse response = httpclient.execute(httppost); 
		        HttpEntity entity = response.getEntity();
		        is = entity.getContent();
		        Log.e("pass 1", "connection success ");
		}
	        catch(Exception e)
		{
	        	insertIntoTable1(nama1, asal1,warnagate1,nomeja1 ,waktu1,"0");
	        	Log.e("Fail 1", e.toString());
		    	Toast.makeText(getApplicationContext(), "Invalid IP Address",
				Toast.LENGTH_LONG).show();
		}     
	        
	        try
	        {
	            BufferedReader reader = new BufferedReader
				(new InputStreamReader(is,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            while ((line = reader.readLine()) != null)
		    {
	                sb.append(line);
	            }
	            is.close();
	            result = sb.toString();
	            if(result.equals("s")){

	            }else if (result.equals("b")){

	            }else if   (result.equals("e")){

	            }
		    Log.i("pass 2", result);
		}
	        catch(Exception e)
		{
	            Log.e("Fail 2", e.toString());
		}     
	       
	 
	    }
	

}
