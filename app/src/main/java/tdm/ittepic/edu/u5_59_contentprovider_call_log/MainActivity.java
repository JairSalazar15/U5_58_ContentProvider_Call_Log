package tdm.ittepic.edu.u5_59_contentprovider_call_log;

import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button llamadas;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        llamadas = (Button) findViewById(R.id.llamadas);
        textView = (TextView) findViewById(R.id.texto);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObtenerDatos();
            }
        });
        llamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObtenerDatosLlamadas();
            }
        });
    }
    public void ObtenerDatosLlamadas() {

        Uri uri;

        uri = Uri.parse("content://call_log/calls");

        String[] projeccion = new String[]{CallLog.Calls.TYPE, CallLog.Calls.NUMBER, CallLog.Calls.DURATION};



        Cursor c = getContentResolver().query(
                uri,
                projeccion,
                null,
                null,
                null);

        textView.setText("");


        while(c.moveToNext()){
            textView.append("Tipo: " + c.getString(0) + " Número: " + c.getString(1) + " Duración: " + c.getString(2) +"\n");
        }
        c.close();


    }

    public void ObtenerDatos(){

        String[] projeccion = new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE };
        String selectionClause = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor c = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projeccion,
                selectionClause,
                null,
                sortOrder);

        textView.setText("");


        while(c.moveToNext()){
            textView.append("Identificador: " + c.getString(0) + " Nombre: " + c.getString(1) + " Número: " + c.getString(2)+  " Tipo: " + c.getString(3)+"\n");
        }
        c.close();



    }
}
