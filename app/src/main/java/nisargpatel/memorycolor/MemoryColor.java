package nisargpatel.memorycolor;

public class MemoryColor {

    private final String COLOR_SET[] = {"red", "orange", "yellow", "green", "blue", "purple", "cyan"};

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

    public String[] randomColorGenerator(int amount) {
        String[] outputColors = new String[amount];

        for (int i = 0; i < amount; i++) {
            String tempColor;
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
        for (String inputGenColor : inputGenColors) {
            genColors += inputGenColor + " ";
        }
    }

    public void clearColors () {
        inputColors = "";
        genColors = "";
    }

}
