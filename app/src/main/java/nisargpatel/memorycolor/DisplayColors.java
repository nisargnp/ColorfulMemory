package nisargpatel.memorycolor;

public class DisplayColors implements Runnable{

    String[] color;

    DisplayColors(String[] inputColor) {
        color = inputColor.clone();
    }

    @Override
    public void run() {

        GamePlayActivity.buttonLayout.post(new Runnable() {
            @Override
            public void run() {
                GamePlayActivity.disableButtons();
            }
        });

        int tempColor = 0;

        for (int i = 0; i < color.length; i++) {

            if (i == 0) {
                GamePlayActivity.info.post(new Runnable() {
                    @Override
                    public void run() {
                        GamePlayActivity.info.setText("Starting new round...");
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            GamePlayActivity.info.post(new Runnable() {
                @Override
                public void run() {
                    GamePlayActivity.info.setText("");
                }
            });

            switch (color[i]) {
                case "green":
                    tempColor = R.drawable.corner_green;
                    break;
                case "blue":
                    tempColor = R.drawable.corner_blue;
                    break;
                case "red":
                    tempColor = R.drawable.corner_red;
                    break;
                case "orange":
                    tempColor = R.drawable.corner_orange;
                    break;
                case "purple":
                    tempColor = R.drawable.corner_purple;
                    break;
                case "cyan":
                    tempColor = R.drawable.corner_cyan;
                    break;
                case "yellow":
                    tempColor = R.drawable.corner_yellow;
                    break;
            }

            final int finalTempColor = tempColor;
            //final String finalColorName = color[i];

            GamePlayActivity.imgView.post(new Runnable() {
                @Override
                public void run() {
                    GamePlayActivity.imgView.setBackgroundResource(finalTempColor);
                }
            });


            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}

        }

        GamePlayActivity.info.post(new Runnable() {
            @Override
            public void run() {
                GamePlayActivity.info.setText("Enter colors: " + GamePlayActivity.round);
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
