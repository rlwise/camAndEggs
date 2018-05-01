package net.mobiledevelopment.camandeggs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class ChickenBioActivity extends AppCompatActivity {
    WebView bioView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_bio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opening Add Eggs View", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent addEggsIntent = new Intent(ChickenBioActivity.this, AddEggsActivity.class);
                //startActivity(new Intent(MainActivity.this, AddEggsActivity.class));
                finish();
                startActivity(addEggsIntent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
