package nisargpatel.memorycolor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class WinLossFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        boolean playerWon = getArguments().getBoolean("playerWon");
        int score = getArguments().getInt("score");

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        if (playerWon) {
            dialogBuilder.setMessage("Wow! You Won!" + "\n" + "Score: " + score);
        } else {
            dialogBuilder.setMessage("Aww, you lost." + "\n" + "Score: " + score);
        }

        dialogBuilder.setNegativeButton("Title Screen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myIntent = new Intent(getActivity(), PlayerActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        dialogBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myIntent = new Intent(getActivity(), GamePlayActivity.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);
            }
        });

        return dialogBuilder.create();

    }
}
