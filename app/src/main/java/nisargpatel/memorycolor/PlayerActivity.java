package nisargpatel.memorycolor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class PlayerActivity extends ActionBarActivity {

    private String[] difficultyModes = {"Select difficulty: ", "Easy", "Medium", "Hard", "Impossible"};

    private static int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        getSupportActionBar().hide();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.simple_spinner_layout, difficultyModes);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setAdapter(spinnerAdapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //When the adapter is first created, the first option is "Select difficulty:", which is now changed to "Tutorial"
                difficultyModes[0] = "Super Easy";
                difficulty = (position + 1) * 2 + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                difficulty = 3;
            }
        });

        Button buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PlayerActivity.this, GamePlayActivity.class);
                startActivity(myIntent);
            }
        });

        Button buttonSettings = (Button) findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PlayerActivity.this, SettingsActivity.class);
                startActivity(myIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public static int getDifficulty() {
        return difficulty;
    }

}
