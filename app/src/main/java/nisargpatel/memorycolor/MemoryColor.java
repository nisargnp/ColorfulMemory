package nisargpatel.memorycolor;

import java.util.Scanner;

public class MemoryColor {

    private final String COLOR_SET[] = {"red", "orange", "yellow", "green", "blue", "purple", "cyan"};
    private final int NUM_COLORS = 7;

    private int score;
    private int difficulty;

    private String genColors;
    private String inputColors;

    public MemoryColor(int difficulty) {
        this.difficulty = difficulty;
        score = 0;
        genColors = "";
        inputColors = "";
    }

//    public void setDifficulty(int newDifficulty) {
//        difficulty = newDifficulty;
//    }

    public int getDifficulty() {
        return difficulty;
    }

    private String tempColor = "";

    public String[] randomColorGenerator(int amount) {
        String[] outputColors = new String[amount];

        for (int i = 0; i < amount; i++) {
            if (i > 0) {
                do {
                    tempColor = COLOR_SET[(int) (Math.random() * 7)];
                } while (tempColor.equals(outputColors[i - 1]));
                outputColors[i] = tempColor;
            } else {
                tempColor = COLOR_SET[(int) (Math.random() * 7)];
                outputColors[i] = tempColor;
            }

        }

        return outputColors;
    }

    public boolean checkColors() {
        return genColors.equals(inputColors);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int newScore) {
        score = newScore;
    }

    public void addInputColor(String color) {
        inputColors += color + " ";
    }

    public void setGenColors(String[] inputGenColors) {
        for (int i = 0; i < inputGenColors.length; i++) {
            genColors += inputGenColors[i] + " ";
        }
    }

    public void clearColors () {
        inputColors = "";
        genColors = "";
    }

}
