package austinlentzmobileapp.pickupi399;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class tempPage extends ActionBarActivity {
    private EditText mPhoneNumberEdit;
    private EditText mMessageEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_page);
        mPhoneNumberEdit = (EditText) findViewById(R.id.phoneNumberEdit);
        mMessageEdit = (EditText) findViewById(R.id.messageEdit);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temp_page, menu);
        return true;
    }

    public void onSend (View view) {
        String number = mPhoneNumberEdit.getText().toString();
        String message = mMessageEdit.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "SMS failed, please try again later.",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
    }

    public void onSMSIntent(View view) {
        String sms = mMessageEdit.getText().toString();

        if(mMessageEdit.length()==0) {
                Toast.makeText(getBaseContext(),
                        "Please enter message.",
                        Toast.LENGTH_SHORT).show();
        } else {
            try{
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);

                sendIntent.putExtra("sms_body", sms);
                sendIntent.setType("vnd.android-dir/mms-sms");
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "SMS intent failed, please try again later!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
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
        return super.onOptionsItemSelected(item);
    }
}
