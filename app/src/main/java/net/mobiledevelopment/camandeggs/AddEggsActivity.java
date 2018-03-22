package net.mobiledevelopment.camandeggs;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class AddEggsActivity extends MainActivity {

    //declare class variables
    public TextView eggTotalTextView;
    public EditText agnesTotalString, irmaTotalString, petuniaTotalString;
    public int agnesTotal = 0, irmaTotal = 0, petuniaTotal = 0;
    public int eggTotal = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_eggs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //create chickens
        final Chicken agnesChicken = new Chicken();
        agnesChicken.breed = "Barred Rock";
        agnesChicken.name = "Agnes";
        agnesChicken.setEggs(0);

        final Chicken irmaChicken = new Chicken();
        irmaChicken.breed = "Buff Orpington";
        irmaChicken.name = "Irma";
        irmaChicken.setEggs(0);

        final Chicken petuniaChicken = new Chicken();
        petuniaChicken.breed = "Buff Orpington";
        petuniaChicken.name = "Petunia";
        petuniaChicken.setEggs(0);




        //declare all of the egg total fields: Total, Agnes, Irma, and Petunia
        //Total
        eggTotalTextView = (TextView) findViewById(R.id.numberAddEggsCount);
        //eggTotal = Integer.parseInt(eggTotalTextView.getText().toString());
        eggTotal = agnesChicken.getEggs() + irmaChicken.getEggs();

        //Agnes
        agnesTotalString = (EditText) findViewById(R.id.editTextAgnesTotal);
        agnesChicken.setEggs(Integer.parseInt(agnesTotalString.getText().toString()));

        //Irma
        irmaTotalString = (EditText) findViewById(R.id.editTextIrmaTotal);
        irmaTotal = Integer.parseInt(irmaTotalString.getText().toString());

        //Petunia
        petuniaTotalString = (EditText) findViewById(R.id.editTextPetuniaTotal);
        petuniaTotal = Integer.parseInt(petuniaTotalString.getText().toString());


        //Editable Text --EditText-- views
        //listener for the Agnes total EditText number to change to the value entered when doing keyboard entry
        agnesTotalString.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // set the value of eggTotalTextView
                try {
                    agnesTotal = Integer.parseInt(editable.toString());
                    agnesChicken.setEggs(agnesTotal);
                    eggTotal = agnesChicken.getEggs() + irmaChicken.getEggs() + petuniaChicken.getEggs();
                    eggTotalTextView.setText(Integer.toString(eggTotal));

                }

                catch(Exception e){
                    agnesTotal = agnesChicken.getEggs();
                }

            }
        });

        //listener for the Irma total EditText number to change to the value entered when doing keyboard entry
        irmaTotalString.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // set the value of eggTotalTextView
                try {
                    irmaTotal = Integer.parseInt(editable.toString());
                    irmaChicken.setEggs(irmaTotal);
                    eggTotal = agnesChicken.getEggs() + irmaChicken.getEggs() + petuniaChicken.getEggs();
                    eggTotalTextView.setText(Integer.toString(eggTotal));

                }

                catch(Exception e){
                    agnesTotal = agnesChicken.getEggs();
                }

            }
        });

        //listener for the Petunia total EditText number to change to the value entered when doing keyboard entry
        petuniaTotalString.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // set the value of eggTotalTextView
                try {
                    petuniaTotal = Integer.parseInt(editable.toString());
                    petuniaChicken.setEggs(petuniaTotal);
                    eggTotal = agnesChicken.getEggs() + irmaChicken.getEggs() + petuniaChicken.getEggs();
                    eggTotalTextView.setText(Integer.toString(eggTotal));

                }

                catch(Exception e){
                    agnesTotal = agnesChicken.getEggs();
                }

            }
        });

        //handler for Agnes button to add eggs to total
        ImageButton agnesButton = (ImageButton) findViewById(R.id.buttonAddEggsAgnes);
        agnesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int addAgnesEgg = agnesChicken.getEggs() + 1;
                agnesChicken.setEggs(addAgnesEgg);
                agnesTotalString.setText(Integer.toString(agnesChicken.getEggs()));
                eggTotal = agnesChicken.getEggs() + irmaChicken.getEggs() + petuniaChicken.getEggs();
                eggTotalTextView.setText(Integer.toString(eggTotal));
            }
        }); //end agnes add egg button

        //handler for Irma button to add eggs to total
        ImageButton irmaButton = (ImageButton) findViewById(R.id.buttonAddEggsIrma);
        irmaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int addIrmaEgg = irmaChicken.getEggs() + 1;
                irmaChicken.setEggs(addIrmaEgg);
                irmaTotalString.setText(Integer.toString(irmaChicken.getEggs()));
                eggTotal = agnesChicken.getEggs() + irmaChicken.getEggs() + petuniaChicken.getEggs();
                eggTotalTextView.setText(Integer.toString(eggTotal));

            }
        }); //end irma add egg button

        //handler for Petunia button to add eggs to total
        ImageButton petuniaButton = (ImageButton) findViewById(R.id.buttonAddEggsPetunia);
        petuniaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int addPetuniaEgg = petuniaChicken.getEggs() + 1;
                petuniaChicken.setEggs(addPetuniaEgg);
                petuniaTotalString.setText(Integer.toString(petuniaChicken.getEggs()));
                eggTotal = agnesChicken.getEggs() + irmaChicken.getEggs() + petuniaChicken.getEggs();
                eggTotalTextView.setText(Integer.toString(eggTotal));
            }
        });//end petunia add egg button

    }

}
