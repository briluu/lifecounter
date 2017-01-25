package edu.washington.briluu.lifecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    static final int MAX_HEALTH = 20;

    private static int p1Life = MAX_HEALTH;
    private static int p2Life = MAX_HEALTH;
    private static int p3Life = MAX_HEALTH;
    private static int p4Life = MAX_HEALTH;

    private static String alert = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // grab all buttons and TextViews
        Button p1_minus5 = (Button) findViewById(R.id.p1_minus5);
        Button p1_minus1 = (Button) findViewById(R.id.p1_minus1);
        Button p1_plus1 = (Button) findViewById(R.id.p1_plus1);
        Button p1_plus5 = (Button) findViewById(R.id.p1_plus5);

        Button p2_minus5 = (Button) findViewById(R.id.p2_minus5);
        Button p2_minus1 = (Button) findViewById(R.id.p2_minus1);
        Button p2_plus1 = (Button) findViewById(R.id.p2_plus1);
        Button p2_plus5 = (Button) findViewById(R.id.p2_plus5);

        Button p3_minus5 = (Button) findViewById(R.id.p3_minus5);
        Button p3_minus1 = (Button) findViewById(R.id.p3_minus1);
        Button p3_plus1 = (Button) findViewById(R.id.p3_plus1);
        Button p3_plus5 = (Button) findViewById(R.id.p3_plus5);

        Button p4_minus5 = (Button) findViewById(R.id.p4_minus5);
        Button p4_minus1 = (Button) findViewById(R.id.p4_minus1);
        Button p4_plus1 = (Button) findViewById(R.id.p4_plus1);
        Button p4_plus5 = (Button) findViewById(R.id.p4_plus5);

        TextView p1Hp = (TextView) findViewById(R.id.p1Life);
        TextView p2Hp = (TextView) findViewById(R.id.p2Life);
        TextView p3Hp = (TextView) findViewById(R.id.p3Life);
        TextView p4Hp = (TextView) findViewById(R.id.p4Life);

        p1Hp.setText(Integer.toString(p1Life));
        p2Hp.setText(Integer.toString(p2Life));
        p3Hp.setText(Integer.toString(p3Life));
        p4Hp.setText(Integer.toString(p4Life));

        TextView tvAlert = (TextView) findViewById(R.id.alert);
        tvAlert.setText(alert);

        TextView p1 = (TextView) findViewById(R.id.player1);
        TextView p2 = (TextView) findViewById(R.id.player2);
        TextView p3 = (TextView) findViewById(R.id.player3);
        TextView p4 = (TextView) findViewById(R.id.player4);

        // Setting up listeners for buttons
        p1_minus5.setOnClickListener(new MyListener(p1Hp, p1));
        p1_minus1.setOnClickListener(new MyListener(p1Hp, p1));
        p1_plus1.setOnClickListener(new MyListener(p1Hp,  p1));
        p1_plus5.setOnClickListener(new MyListener(p1Hp,  p1));

        p2_minus5.setOnClickListener(new MyListener(p2Hp, p2));
        p2_minus1.setOnClickListener(new MyListener(p2Hp, p2));
        p2_plus1.setOnClickListener(new MyListener(p2Hp, p2));
        p2_plus5.setOnClickListener(new MyListener(p2Hp, p2));

        p3_minus5.setOnClickListener(new MyListener(p3Hp, p3));
        p3_minus1.setOnClickListener(new MyListener(p3Hp, p3));
        p3_plus1.setOnClickListener(new MyListener(p3Hp, p3));
        p3_plus5.setOnClickListener(new MyListener(p3Hp, p3));

        p4_minus5.setOnClickListener(new MyListener(p4Hp, p4));
        p4_minus1.setOnClickListener(new MyListener(p4Hp, p4));
        p4_plus1.setOnClickListener(new MyListener(p4Hp, p4));
        p4_plus5.setOnClickListener(new MyListener(p4Hp, p4));

    }

    // Save state of game
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("player1", p1Life);
        savedInstanceState.putInt("player2", p2Life);
        savedInstanceState.putInt("player3", p3Life);
        savedInstanceState.putInt("player4", p4Life);
        savedInstanceState.putString("alert", alert);
        Log.d(TAG, "Saving state...");
    }

    // Restore state of game
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        p1Life = savedInstanceState.getInt("player1");
        p2Life = savedInstanceState.getInt("player2");
        p3Life = savedInstanceState.getInt("player3");
        p4Life = savedInstanceState.getInt("player4");
        alert = savedInstanceState.getString("alert");
        Log.d(TAG, "Restoring state...");
    }

    // custom listener: updates each player's corresponding HP based on
    // what button was clicked
    public class MyListener implements View.OnClickListener {

        private TextView tv;
        private TextView player;

        public MyListener(TextView tv, TextView player) {
            this.tv = tv;
            this.player = player;
        }

        // Want to decrement that corresponding player's health
        @Override
        public void onClick(View v) {
            int id = v.getId();
            int val = 0;
            String playerNumber = this.player.getText().toString();
            String buttonStr = (String) ((Button)v).getText();
            if (buttonStr.equals("-")) {
                val = -1;
            } else if (buttonStr.equals("+")) {
                val = 1;
            } else {
                val = Integer.parseInt(buttonStr);
            }
            Log.d(TAG, "Button clicked! Button ID: " + id + ", val = " + val);

            int currentLife = (int) Integer.parseInt(this.tv.getText().toString());
            currentLife += val;
            // Update the model
            if (playerNumber.endsWith("1")) {
                p1Life = currentLife;
            } else if (playerNumber.endsWith("2")) {
                p2Life = currentLife;
            } else if(playerNumber.endsWith("3")) {
                p3Life = currentLife;
            } else {
                p4Life = currentLife;
            }
            this.tv.setText(Integer.toString(currentLife));

            if (currentLife <= 0) {
                alert = playerNumber + " LOSES!";
                TextView tvAlert = (TextView) findViewById(R.id.alert);
                tvAlert.setText(alert);
            }
        }
    }

}
