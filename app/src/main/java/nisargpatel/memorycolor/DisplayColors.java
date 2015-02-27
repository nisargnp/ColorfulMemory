package nisargpatel.memorycolor;

import java.util.Arrays;

public class DisplayColors implements Runnable{

    String[] colors;

    DisplayColors(String[] inputColor) {
        colors = inputColor.clone();
    }

    @Override
    public void run() {

        GamePlayActivity.buttonLayout.post(new Runnable() {
            @Override
            public void run() {
                GamePlayActivity.disableButtons();
            }
        });

        for (int colorIndex = 0; colorIndex < colors.length; colorIndex++) {

            if (colorIndex == 0) {
                GamePlayActivity.textInfo.post(new Runnable() {
                    @Override
                    public void run() {
                        GamePlayActivity.textInfo.setText("Starting new round...");
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            GamePlayActivity.textInfo.post(new Runnable() {
                @Override
                public void run() {
                    GamePlayActivity.textInfo.setText("");
                }
            });

            int tempId = 0;
            switch (colors[colorIndex]) {
                case "red":
                    tempId = R.drawable.corner_red;
                    break;
                case "orange":
                    tempId = R.drawable.corner_orange;
                    break;
                case "yellow":
                    tempId = R.drawable.corner_yellow;
                    break;
                case "green":
                    tempId = R.drawable.corner_green;
                    break;
                case "blue":
                    tempId = R.drawable.corner_blue;
                    break;
                case "cyan":
                    tempId = R.drawable.corner_cyan;
                    break;
                case "purple":
                    tempId = R.drawable.corner_purple;
                    break;
            }

            final int finalId = tempId;

            GamePlayActivity.imgView.post(new Runnable() {
                @Override
                public void run() {
                    GamePlayActivity.imgView.setBackgroundResource(finalId);
                }
            });

            if (GamePlayActivity.gameSettings.getBoolean("sound", true)) {
                int toneIndex = Arrays.asList(MemoryColor.COLOR_SET).indexOf(colors[colorIndex]);
                GamePlayActivity.mediaPlayerList.get(toneIndex).start();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}

        }

        GamePlayActivity.textInfo.post(new Runnable() {
            @Override
            public void run() {
                GamePlayActivity.textInfo.setText("Enter colors: " + GamePlayActivity.getRound());
            }
        });

        GamePlayActivity.imgView.post(new Runnable() {
            @Override
            public void run() {
                GamePlayActivity.imgView.setBackgroundResource(R.drawable.corner_black);
            }
        });


        GamePlayActivity.buttonLayout.post(new Runnable() {
            @Override
            public void run() {
                GamePlayActivity.enableButtons();
            }
        });

    }
}
