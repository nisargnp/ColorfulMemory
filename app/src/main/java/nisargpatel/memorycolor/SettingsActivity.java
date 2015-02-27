package nisargpatel.memorycolor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ToggleButton;


public class SettingsActivity extends ActionBarActivity {

    private static final String SHARED_PREFERENCES_NAME = "Memory Color Shared Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().hide();

        final SharedPreferences gameSettings = getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
        final SharedPreferences.Editor gameSettingsEditor = gameSettings.edit();

        ToggleButton toggleButtonVibration = (ToggleButton) findViewById(R.id.toggleButtonVibration);
        ToggleButton toggleButtonSound = (ToggleButton) findViewById(R.id.toggleButtonSound);

        //sets the toggleButtons to on/off depending SharedPreferences values
        toggleButtonVibration.setChecked(gameSettings.getBoolean("vibration", true));
        toggleButtonSound.setChecked(gameSettings.getBoolean("sound", true));

        toggleButtonVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gameSettingsEditor.putBoolean("vibration", isChecked);
                gameSettingsEditor.apply();
            }
        });

        toggleButtonSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                gameSettingsEditor.putBoolean("sound", isChecked);
                gameSettingsEditor.apply();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
