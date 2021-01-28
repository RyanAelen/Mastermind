import java.util.concurrent.ThreadLocalRandom;

public class MastermindGame {
    private boolean gameWon = false;
    private char[] lastGuest;
    private String code;
    private char[] kleuren = { 'B', 'G', 'R', 'Y' };
    private int turnsleft = 8;

    public String generateSecretCode() {
        if (code == null) {
            code = "";
            for (int i = 0; i < kleuren.length; i++) {
                code += kleuren[ThreadLocalRandom.current().nextInt(0, 4)];
            }
            System.out.println(code);
        }
        return code;
    }

    public void checkCombination(String guessString) {
        if (generateSecretCode().equals(guessString)) {
            gameWon = true;
        }
        lastGuest = guessString.toCharArray();
        turnsleft--;
    }

    public boolean isWon() {
        return gameWon;
    }

    public String getHint() {
        int zelfdeplek = 0;
        int verkeerderflek = 0;
        char[] guest = lastGuest;
        for (int i = 0; i < guest.length; i++) {
            if (guest[i] == generateSecretCode().charAt(i)) {
                zelfdeplek++;
                guest[i] = 'x';
            }
        }
        for (int i = 0; i < guest.length; i++) {
            if (guest[i] != 'x') {
                for (int j = 0; j < generateSecretCode().length(); j++) {
                    if (guest[j] != 'x') {
                        if (guest[i] == generateSecretCode().charAt(j)) {
                            verkeerderflek++;
                        }
                    }
                }
            }
        }

        return "right colour right position = " + zelfdeplek + "\n" + "right colour wrong position = " + verkeerderflek;
    }

    public boolean maxTurnsUsed() {
        if (turnsleft <= 0) {
            return true;
        }
        return false;
    }

}
