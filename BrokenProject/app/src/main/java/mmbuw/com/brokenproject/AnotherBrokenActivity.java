package mmbuw.com.brokenproject;

import android.app.Activity;
//import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import mmbuw.com.brokenproject.R;

public class AnotherBrokenActivity extends Activity {
   // private EditText textEntry;
    public static String url = "http://www.google.com";
    private TextView textViewHttp;
    private TextView textViewStatus;
    private String responseAsString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_broken);

        textViewHttp = (TextView)findViewById(R.id.brokenTextView2);
        textViewStatus = (TextView)findViewById(R.id.brokenTextView);

        Intent intent = getIntent();
        AnotherBrokenActivity.url = intent.getStringExtra(BrokenActivity.EXTRA_MESSAGE);
        textViewStatus.setText("start");
        //What happens here? What is this? It feels like this is wrong.
        //Maybe the weird programmer who wrote this forgot to do something?
        //textEntry = (EditText)findViewById(R.id.edittext);
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    //Your code goes here
                    try {
                        fetchHTML(url);
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    @Override // same no changes
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.another_broken, menu);
        return true;
    }

    @Override// same no changes
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void fetchHTML(String strurl) throws IOException {

        //According to the exercise, you will need to add a button and an EditText first.
        //Then, use this function to call your http requests
        //Following hints:
        //Android might not enjoy if you do Networking on the main thread, but who am I to judge?
        //An app might not be allowed to access the internet without the right (*hinthint*) permissions
        //Below, you find a staring point for your HTTP Requests - this code is in the wrong place and lacks the allowance to do what it wants
        //It will crash if you just un-comment it.


        //Beginning of helper code for HTTP Request.

        HttpClient client = new DefaultHttpClient();


            HttpResponse response;
        try
        {
            response  = client.execute(new HttpGet(strurl));
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                response.getEntity().writeTo(outStream);
                responseAsString = outStream.toString();

                runOnUiThread(new Runnable() {
                    public void run() {
                        // Update UI elements
                        textViewHttp.setText(responseAsString);
                        textViewStatus.setText("load completed");
                    }
                });

                System.out.println("Response string: " + responseAsString);
            } else {
                //Well, this didn't work.
                response.getEntity().getContent().close();
                throw new IOException(status.getReasonPhrase());
            }
        }
        catch(Exception e)
        {
            System.out.println("error ; exception");
        }          //End of helper code!


    }
}
