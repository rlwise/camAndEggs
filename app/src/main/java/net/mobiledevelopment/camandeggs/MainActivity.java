package net.mobiledevelopment.camandeggs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class MainActivity extends AppCompatActivity {
    public WebView webViewCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get reference to cam1 widget id (doesn't handle authentication)
        webViewCam = findViewById(R.id.cam1);
        webViewCam.loadUrl("http://192.168.1.229/Status.php");


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

    //clear DB button



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
        if (id == R.id.action_settings) {
            return true;
        }

        else if (id == R.id.action_addEggs) {
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
