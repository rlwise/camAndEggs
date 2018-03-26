package net.mobiledevelopment.camandeggs;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AddEggsActivity extends MainActivity {

    //declare class variables
    public TextView eggTotalTextView, agnesTotalTextView, irmaTotalTextView, petuniaTotalTextView;
    public int agnesTotal = 0, irmaTotal = 0, petuniaTotal = 0;
    public int eggTotal = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_eggs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize the db
        final CamAndEggsSQLiteHelper db = new CamAndEggsSQLiteHelper(this);


        //clearDB button
        final Button clearDB = findViewById(R.id.buttonClearDB);

        //name variable from the widgets
        eggTotalTextView            = findViewById(R.id.numberAddEggsCount);
        agnesTotalTextView          = findViewById(R.id.textViewAgnesTotal);
        irmaTotalTextView           = findViewById(R.id.textViewIrmaTotal);
        petuniaTotalTextView        = findViewById(R.id.textViewPetuniaTotal);
        ImageButton agnesButton     = findViewById(R.id.buttonAddEggsAgnes);
        ImageButton irmaButton      = findViewById(R.id.buttonAddEggsIrma);
        ImageButton petuniaButton   = findViewById(R.id.buttonAddEggsPetunia);

        /*******************
        *  CRUD operations
        *
        * ******************/
        db.addChicken(new Chicken("Barred Rock", "Agnes", 0));
        db.addChicken(new Chicken("Buff Orpington", "Irma", 0));
        db.addChicken(new Chicken("Buff Orpington", "Petunia", 0));

        final Chicken agnes = db.getChicken("Agnes");
        final Chicken irma = db.getChicken("Irma");
        final Chicken petunia = db.getChicken("Petunia");

        //initialize egg totals
        updateEggTotal(agnes, irma, petunia);


        /********************************
         *
         *   Add Egg Buttons Listeners
         *
         *********************************/
        //delete button
        clearDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearDatabase(db, agnes);
                clearDatabase(db, irma);
                clearDatabase(db, petunia);

                //refresh the activity
                finish();
                startActivity(getIntent());
            }
        });

        /**
         *  Agnes
         */
        //Agnes click to add egg
        agnesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agnes.setEggs(agnes.getEggs() + 1);
                db.updateChicken(agnes);

                agnesTotalTextView.setText(Integer.toString(agnes.getEggs()));
                updateEggTotal(agnes, irma, petunia);
                Message.message(getApplicationContext(), agnes.getName() + " added an egg!");
            }
        }); //end agnes add egg button

        //Agnes long click to subtract an egg
        agnesButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //check to make sure eggs are >= 0
                if(agnes.getEggs() > 0){
                    agnes.setEggs(agnes.getEggs() - 1);
                }
                else{
                    agnes.setEggs(0);
                }

                db.updateChicken(agnes);
                agnesTotalTextView.setText(Integer.toString(agnes.getEggs()));
                updateEggTotal(agnes, irma, petunia);
                Message.message(getApplicationContext(), agnes.getName() + " subtracted an egg!");
                return true;
            }
        });

        /**
         * Irma
         */

        irmaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irma.setEggs(irma.getEggs() + 1);
                db.updateChicken(irma);
                irmaTotalTextView.setText(Integer.toString(irma.getEggs()));
                updateEggTotal(agnes, irma, petunia);
                Message.message(getApplicationContext(), irma.getName() + " added an egg!");
            }
        }); //end agnes add egg button

        irmaButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(irma.getEggs() > 0){
                    irma.setEggs(irma.getEggs() - 1);
                }
                else{
                    irma.setEggs(0);
                }
                db.updateChicken(irma);
                irmaTotalTextView.setText(Integer.toString(irma.getEggs()));
                updateEggTotal(agnes, irma, petunia);
                Message.message(getApplicationContext(), irma.getName() + " subtracted an egg!");
                return true;
            }
        });

        /********************************
         * Petunia
         ********************************/
        petuniaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petunia.setEggs(petunia.getEggs() + 1);
                db.updateChicken(petunia);
                petuniaTotalTextView.setText(Integer.toString(petunia.getEggs()));
                updateEggTotal(agnes, irma, petunia);
                Message.message(getApplicationContext(), petunia.getName() + " added an egg!");
            }
        }); //end agnes add egg button

        petuniaButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(petunia.getEggs() > 0){
                    petunia.setEggs(petunia.getEggs() - 1);
                }
                else{
                    petunia.setEggs(0);
                }
                db.updateChicken(petunia);
                petuniaTotalTextView.setText(Integer.toString(petunia.getEggs()));
                updateEggTotal(agnes, irma, petunia);
                Message.message(getApplicationContext(), petunia.getName() + " subtracted an egg!");
                return true;
            }
        });

    }

    //update the egg totals
    public void updateEggTotal(Chicken agnes, Chicken irma, Chicken petunia){
        eggTotal = agnes.getEggs() + irma.getEggs() + petunia.getEggs();
        eggTotalTextView.setText(Integer.toString(eggTotal));

        //Agnes
        agnesTotal = agnes.getEggs();
        agnesTotalTextView.setText(Integer.toString(agnesTotal));

        //Irma
        irmaTotal = irma.getEggs();
        irmaTotalTextView.setText(Integer.toString(irmaTotal));

        //Petunia
        petuniaTotal = petunia.getEggs();
        petuniaTotalTextView.setText(Integer.toString(petuniaTotal));
    }

    //clear DB button
    public void clearDatabase(CamAndEggsSQLiteHelper db, Chicken name){
        db.deleteChicken(name);

    }

}
