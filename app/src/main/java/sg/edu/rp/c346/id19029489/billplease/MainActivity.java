package sg.edu.rp.c346.id19029489.billplease;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amt, pax, disc;
    ToggleButton svs, gst;
    TextView bill, indivPay;
    Button btnsplit, btnclear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = findViewById(R.id.enterAmt);
        pax = findViewById(R.id.enterPax);
        svs = findViewById(R.id.SVS);
        gst = findViewById(R.id.GST);
        disc = findViewById(R.id.enterDiscount);
        bill = findViewById(R.id.bill);
        indivPay = findViewById(R.id.eachPay);
        btnsplit = findViewById(R.id.split);
        btnclear = findViewById(R.id.reset);

        btnsplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double totalbill = 0.00;

                if (amt.getText().toString().length() > 0 && pax.getText().toString().length() > 0) {
                    if (svs.isChecked() == false && gst.isChecked() == false) {
                        totalbill = Double.parseDouble(amt.getText().toString());
                    } else if (svs.isChecked() == true && gst.isChecked() == false) {
                        totalbill = Double.parseDouble(amt.getText().toString()) * (1.1);
                    } else if (svs.isChecked() == false && gst.isChecked() == true) {
                        totalbill = Double.parseDouble(amt.getText().toString()) * (1.07);
                    } else if (svs.isChecked() == true && gst.isChecked() == true) {
                        totalbill = Double.parseDouble(amt.getText().toString()) * (1.17);
                    }
                } else{
                    if (amt.getText().toString().length() == 0) {
                        Toast.makeText(MainActivity.this, "Amount is required", Toast.LENGTH_LONG).show();
                    }
                    if (pax.getText().toString().length() == 0) {
                        Toast.makeText(MainActivity.this, "Number of People is required", Toast.LENGTH_LONG).show();
                    }
                    if (amt.getText().toString().length() == 0 && pax.getText().toString().length() == 0) {
                        Toast.makeText(MainActivity.this, "Amount and Number of People is required", Toast.LENGTH_LONG).show();
                    }
                }

                if (disc.getText().toString().length() > 0){
                    totalbill = totalbill * (1 - Double.parseDouble(disc.getText().toString())/100);
                }

                bill.setText("Total Bill: $" + String.format("%.2f", totalbill));

                int people = Integer.parseInt(pax.getText().toString());

                if (people > 0){
                    indivPay.setText("Each Pays: $" + String.format("%.2f", totalbill/people));
                }
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amt.setText("");
                pax.setText("");
                disc.setText("");

                svs.setChecked(false);
                gst.setChecked(false);

                bill.setText("Total Bill: $0.00");
                indivPay.setText("Each Pays: $0.00");
            }
        });

    }
}


