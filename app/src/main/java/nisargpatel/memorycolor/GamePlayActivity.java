package nisargpatel.memorycolor;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class GamePlayActivity extends ActionBarActivity {

    private ActionBar actionBar;

    public static ImageView imgView;
    public static TextView info;

    public static RelativeLayout buttonLayout;

    private DialogFragment dialogWinLoss;

    private MemoryColor memColor;

    public static int pressCount;
    public static int round;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        actionBar = getSupportActionBar();
        actionBar.hide();

        buttonLayout = (RelativeLayout) findViewById(R.id.buttonLayout);
        imgView = (ImageView) findViewById(R.id.imageView);
        info = (TextView) findViewById(R.id.textView);

        memColor = new MemoryColor(PlayerActivity.getDifficulty());

        pressCount = 0;
        round = 0;

        startGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_play, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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
        info.setText("Press any button to start!");
    }

    private void startNextRound() {
        round++;
        if (round <= memColor.getDifficulty()) {
            String[] genColors = memColor.randomColorGenerator(round);
            memColor.setGenColors(genColors);
            displayColors(genColors);
        } else {
            winConditionMet(true, memColor.getScore());
        }
    }

    private void midRound() {
        if (pressCount >= round) {
            if (!memColor.checkColors()) {
                winConditionMet(false, memColor.getScore());
            } else {
                endRound();
            }
        }
    }

    private void endRound() {
        memColor.setScore(10 * round + memColor.getScore());

        pressCount = 0;
        memColor.clearColors();
        startNextRound();
    }

    private void winConditionMet(boolean playerWon, int score) {

        info.setText("Game Over!");

        dialogWinLoss = new WinLossFragment();

        Bundle args = new Bundle();

        args.putInt("score", score);
        args.putBoolean("playerWon", playerWon);

        dialogWinLoss.setArguments(args);
        dialogWinLoss.show(getSupportFragmentManager(), "win/loss");

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

    private void buttonPressEvent(String color) {
        if (round == 0) {
            startNextRound();
        } else {
            pressCount++;
            memColor.addInputColor(color);
            midRound();
        }
    }

    public void buttonRed(View view) {
        buttonPressEvent("red");
    }

    public void buttonGreen(View view) {
        buttonPressEvent("green");
    }

    public void buttonYellow(View view) {
        buttonPressEvent("yellow");
    }

    public void buttonOrange(View view) {
        buttonPressEvent("orange");
    }

    public void buttonPurple(View view) {
        buttonPressEvent("purple");
    }

    public void buttonCyan(View view) {
        buttonPressEvent("cyan");
    }

    public void buttonBlue(View view) {
        buttonPressEvent("blue");
    }

}
