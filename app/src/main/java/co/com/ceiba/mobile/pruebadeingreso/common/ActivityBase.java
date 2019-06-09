package co.com.ceiba.mobile.pruebadeingreso.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

import co.com.ceiba.mobile.pruebadeingreso.R;

public abstract class ActivityBase extends AppCompatActivity {

    protected abstract void setControls();

    public void makeErrorDialog(String message, Context c) {
        makeDialog("Error", message, c);
    }

    void makeDialog(String title, String message, Context c) {
        AlertDialog.Builder d = new AlertDialog.Builder(c);
        d.setTitle(title);
        d.setMessage(message);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            d.setIcon(getResources().getDrawable(R.mipmap.ic_launcher, getTheme()));
        } else {
            d.setIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        }
        d.setCancelable(false);
        d.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
            }
        });
        d.show();
    }
}
