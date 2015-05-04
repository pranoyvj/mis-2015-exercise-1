package mmbuw.com.brokenproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class BrokenActivity extends Activity {
    public final static String EXTRA_MESSAGE = "mmbuw.com.brokenproject.MESSAGE";
    private EditText auntEdith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broken);
        auntEdith = (EditText)findViewById(R.id.edittext);
        final Button button = (Button) findViewById(R.id.buttonid);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                brokenFunction();
            }
        });
    }


    @Override //same no change
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.broken, menu);
        return true;
    }

    @Override//same no change
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

    public void brokenFunction(){
        //I was once, perhaps, possibly a functioning function
        if (auntEdith.getText().toString().equals("")){
            auntEdith.setText("http://www.google.com");
        }

        System.out.println("If this appears in your console, you fixed a bug.");
        Intent intent = new Intent(this,AnotherBrokenActivity.class);
        String message = auntEdith.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
