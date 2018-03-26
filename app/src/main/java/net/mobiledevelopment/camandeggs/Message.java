package net.mobiledevelopment.camandeggs;

/**
 * Created by robbwise on 3/23/18.
 */

import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}

