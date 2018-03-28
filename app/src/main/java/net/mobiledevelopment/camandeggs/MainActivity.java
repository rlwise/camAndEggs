package net.mobiledevelopment.camandeggs;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public WebView webViewCam;
    public String urlString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button reloadCam = findViewById(R.id.buttonRefresh);
        final Spinner spinner = findViewById(R.id.spinner);
        final Button addIP = findViewById(R.id.buttonAddIPAddress);
        final Button deleteIP = findViewById(R.id.buttonDeleteIP);
        final EditText addIPText = findViewById(R.id.editTextAddIPaddress);

        //String array to hold IP's added/deleted programmatitcally
        String[] ipaddresses = new String[]{
                "192.168.1.229",
        };

        final List<String> ipList = new ArrayList<>(Arrays.asList(ipaddresses));

        //initalize an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item,ipList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        addIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toBeAdded = addIPText.getText().toString();
                if(ipList.contains(toBeAdded)){
                    Message.message(getApplicationContext(), "Address already exists");
                }
                else{
                    ipList.add(toBeAdded);
                    spinnerArrayAdapter.notifyDataSetChanged();
                    Message.message(getApplicationContext(), "Address added!");
                }
                addIPText.setText("");
            }
        });

        //delete address from Spinner
        deleteIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toBeDeleted = spinner.getSelectedItem().toString();
                ipList.remove(toBeDeleted);
                spinnerArrayAdapter.notifyDataSetChanged();
                Message.message(getApplicationContext(), "Address removed!");
            }
        });


        //get reference to cam1 widget id (doesn't handle authentication)
        webViewCam = findViewById(R.id.cam1);
        urlString = "http://" + spinner.getSelectedItem() + "/Status.php";
        webCamLoad(webViewCam, urlString);

        //buttonlistener
        reloadCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urlString = "http://" + spinner.getSelectedItem().toString() + "/Status.php";
                webCamLoad(webViewCam, urlString);
            }
        });




        //add Eggs Floating Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening Add Eggs View", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent addEggsIntent = new Intent(MainActivity.this, AddEggsActivity.class);
                //startActivity(new Intent(MainActivity.this, AddEggsActivity.class));
                startActivity(addEggsIntent);

            }
        });


    }//end onCreate

    //load webcam
    public void webCamLoad(WebView wv, String url) {
        //wv.destroy();
        webViewCam.loadUrl(url);
        Message.message(getApplicationContext(), "WebCam address changed.");
    }




    /**
     * Tool bar options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_addEggs) {
            webViewCam.destroy();    //kill the connection to the server
            startActivity(new Intent(MainActivity.this, AddEggsActivity.class));
            finish();
        }

        else if (id == R.id.action_home){
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



}
