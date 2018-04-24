package net.mobiledevelopment.camandeggs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    public WebView webViewCam;
    public String urlString;
    public String homeDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init db
        final CamAndEggsSQLiteHelper db = new CamAndEggsSQLiteHelper(this);


        //get reference to cam1 widget id (doesn't handle authentication)
        webViewCam = findViewById(R.id.cam1);
        homeDomain = "camandeggs.hopto.org";
        urlString = "http://" + homeDomain + "/Status.php";
        webCamLoad(webViewCam, urlString);

        //display egg total summary
        TextView agnesEggs = findViewById(R.id.textViewMainAgnesEggs);
        TextView irmaEggs = findViewById(R.id.textViewMainIrmaEggs);
        TextView petuniaEggs = findViewById(R.id.textViewMainPetuniaEggs);
        Chicken agnes = db.getChicken("Agnes");
        Chicken irma = db.getChicken("Irma");
        Chicken petunia = db.getChicken("Petunia");
        agnesEggs.setText(Integer.toString(agnes.getEggs()));
        irmaEggs.setText(Integer.toString(irma.getEggs()));
        petuniaEggs.setText(Integer.toString(petunia.getEggs()));


        //add Eggs Floating Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening Add Eggs View", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent addEggsIntent = new Intent(MainActivity.this, AddEggsActivity.class);
                //startActivity(new Intent(MainActivity.this, AddEggsActivity.class));
                finish();
                startActivity(addEggsIntent);
            }
        });

    }//end onCreate



    //load webcam
    public void webCamLoad(WebView wv, String url) {
        //wv.destroy();
        webViewCam.loadUrl(url);
        //Message.message(getApplicationContext(), "WebCam address changed.");
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
