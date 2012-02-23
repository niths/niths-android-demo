package no.niths.android.common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * @author NITHs
 *
 */
public abstract class ErrorHandler {

    public static void log(Context context, int tagId, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        Log.e(context.getString(tagId), msg);
    }
}