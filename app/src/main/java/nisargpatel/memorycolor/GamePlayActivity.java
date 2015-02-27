package nisargpatel.memorycolor;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GamePlayActivity extends ActionBarActivity {

    public static SharedPreferences gameSettings;
    private static final String PREFS_NAME = "Memory Color Shared Preferences";

    public static RelativeLayout buttonLayout;
    public static ImageView imgView;
    public static TextView textInfo;

    private TextView textLives;
    private TextView textScore;

    private Vibrator vibes;
    public static ArrayList<MediaPlayer> mediaPlayerList;

    private MemoryColor memColor;

    private static int pressCount;
    private static int round;
    private static int lives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        getSupportActionBar().hide();

        gameSettings = getSharedPreferences(PREFS_NAME, 0);

        buttonLayout = (RelativeLayout) findViewById(R.id.buttonLayout);
        imgView = (ImageView) findViewById(R.id.imageView);
        textInfo = (TextView) findViewById(R.id.textView);

        textLives = (TextView) findViewById(R.id.textLives);
        textScore = (TextView) findViewById(R.id.textScore);

        vibes = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mediaPlayerList = new ArrayList<>();

        mediaPlayerList.add(MediaPlayer.create(getApplicationContext(), R.raw.tone_do));
        mediaPlayerList.add(MediaPlayer.create(getApplicationContext(), R.raw.tone_re));
        mediaPlayerList.add(MediaPlayer.create(getApplicationContext(), R.raw.tone_mi));
        mediaPlayerList.add(MediaPlayer.create(getApplicationContext(), R.raw.tone_fa));
        mediaPlayerList.add(MediaPlayer.create(getApplicationContext(), R.raw.tone_so));
        mediaPlayerList.add(MediaPlayer.create(getApplicationContext(), R.raw.tone_la));
        mediaPlayerList.add(MediaPlayer.create(getApplicationContext(), R.raw.tone_ti));

        memColor = new MemoryColor(PlayerActivity.getDifficulty());

        pressCount = 0;
        round = 0;
        lives = 0;

        startGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_game_play, menu);
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

    private void displayColors(String[] colors) {
        new Thread(new DisplayColors(colors)).start();
    }

    private void startGame() {
        textInfo.setText("Press any button to start!");
    }

    private void startNextRound() {
        round++;

        addLives();

        String[] genColors = memColor.randomColorGenerator(round);
        memColor.setGenColors(genColors);
        displayColors(genColors);

    }

    private void midRound() {
        if (pressCount >= round) {
            if (!memColor.checkColors()) {
                checkLives(false);
            } else {
                endRound();
            }
        }
    }

    private void endRound() {
        memColor.setScore(10 * round + memColor.getScore());

        pressCount = 0;
        memColor.clearColors();

        textLives.setText(String.valueOf(lives));
        textScore.setText(String.valueOf(memColor.getScore()));

        if (round < memColor.getDifficulty())
            startNextRound();
        else
            checkLives(true);
    }

    private boolean gotFirstLife = false;
    private boolean gotSecondLife = false;

    private void addLives() {
        if (round == (memColor.getDifficulty() - 1) / 2 && !gotFirstLife) {
            gotFirstLife = true;

            lives++;
            textLives.setText(String.valueOf(lives));
            Toast.makeText(getApplicationContext(), "You're halfway there!" + "\n" + "Extra life added!", Toast.LENGTH_SHORT).show();
        } else if (round == memColor.getDifficulty() - 1 && !gotSecondLife) {
            gotSecondLife = true;

            lives++;
            textLives.setText(String.valueOf(lives));
            Toast.makeText(getApplicationContext(), "You're almost done!" + "\n" + "Extra life added!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkLives(boolean playerWon) {

        if (playerWon){
            playerWon(true);
        } else {
            if (lives-- > 0) {
                round--;
                endRound();
            } else {
                playerWon(false);
            }
        }

    }

    private void playerWon(boolean playerWon) {

        textInfo.setText("Game Over!");

        DialogFragment dialogWinLoss = new WinLossFragment();
        Bundle args = new Bundle();

        args.putInt("score", memColor.getScore());
        args.putBoolean("playerWon", playerWon);

        dialogWinLoss.setArguments(args);
        dialogWinLoss.show(getSupportFragmentManager(), "win/loss");

    }


    public static int getRound() {
        return round;
    }


    public static void disableButtons() {

        for (int i = 0; i < buttonLayout.getChildCount(); i++) {
            View view = buttonLayout.getChildAt(i);
            view.setEnabled(false);
        }

    }

    public static void enableButtons() {

        for (int i = 0; i < buttonLayout.getChildCount(); i++) {
            View view = buttonLayout.getChildAt(i);
            view.setEnabled(true);
        }

    }

    boolean firstButtonPress = true;

    private void buttonPressEvent(String color, int index) {

        if (gameSettings.getBoolean("sound", true)) {
            if (firstButtonPress)
                firstButtonPress = false;
            else
                mediaPlayerList.get(index).start();
        }

        if (gameSettings.getBoolean("vibration", true))
            vibes.vibrate(30);

        if (round == 0) {
            startNextRound();
        } else {
            pressCount++;
            textInfo.setText("Enter colors: " + (round - pressCount));
            memColor.addInputColor(color);
            midRound();
        }
    }

    public void buttonRed(View view) {
        buttonPressEvent("red", 0);
    }

    public void buttonOrange(View view) {
        buttonPressEvent("orange", 1);
    }

    public void buttonYellow(View view) {
        buttonPressEvent("yellow", 2);
    }

    public void buttonGreen(View view) {
        buttonPressEvent("green", 3);
    }

    public void buttonBlue(View view) {
        buttonPressEvent("blue", 4);
    }

    public void buttonCyan(View view) {
        buttonPressEvent("cyan", 5);
    }

    public void buttonPurple(View view) {
        buttonPressEvent("purple", 6);
    }


}
