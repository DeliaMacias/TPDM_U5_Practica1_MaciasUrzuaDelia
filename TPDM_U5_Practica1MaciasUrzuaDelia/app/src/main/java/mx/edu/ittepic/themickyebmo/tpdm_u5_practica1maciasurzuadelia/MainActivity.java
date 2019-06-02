package mx.edu.ittepic.themickyebmo.tpdm_u5_practica1maciasurzuadelia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnInbox;
    Button btnSent;
    Button btnPermisos;
    TableLayout tblMain;
    Uri smsUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        smsUri = Uri.parse("content://sms/");

        btnPermisos = findViewById(R.id.permisos);

        btnPermisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solictarPermisos();
            }
        });

        try {

            tblMain = findViewById(R.id.tblMain);
            btnInbox = findViewById(R.id.btnInbox);
            btnInbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    smsUri = Uri.parse("content://sms/inbox");
                    Cursor cursor = getContentResolver().query(smsUri, null, null, null, null);

                    Cursor2TableLayout(cursor, tblMain);
                }
            });
            btnSent = findViewById(R.id.btnSent);
            btnSent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    smsUri = Uri.parse("content://sms/sent");
                    Cursor cursor = getContentResolver().query(smsUri, null, null, null, null);
                    Cursor2TableLayout(cursor, tblMain);

                }
            });
        } catch (Exception ex) {
            Toast.makeText(this,
                    "Error in MainActivity.onCreate: " + ex.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }


    }
    private void Cursor2TableLayout(Cursor cur, TableLayout tblLayout) {
        /* Clearing Table If Contains Any Rows/Headers */
        tblLayout.removeAllViews();
        /* Moving To First */
        if (!cur.moveToFirst()) { /* false = cursor is empty */
            return;
        }
        /* Column Headers */

        TableRow headersRow = new TableRow(this);
        for (int j = 0; j < cur.getColumnCount(); j++) {
            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setText(cur.getColumnName(j));
            textView.setPadding(0, 0, 5, 0);
            textView.setAlpha(0.8f);
            headersRow.addView(textView);
        }
        headersRow.setPadding(10, 10, 10, 10);
        tblLayout.addView(headersRow);
        /* Rows */

        for (int i = 0; i < cur.getCount(); i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < cur.getColumnCount(); j++) {
                TextView textView = new TextView(this);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setText(cur.getString(j));
                textView.setPadding(0, 0, 5, 0);
                tableRow.addView(textView);
            }
            tableRow.setPadding(10, 10, 10, 10);
            tblLayout.addView(tableRow);
            cur.moveToNext();
        }
        cur.close();
    }

    public void solictarPermisos() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_SMS},1);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS},2);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS},4);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_PHONE_STATE},3);
        }
    }

}
