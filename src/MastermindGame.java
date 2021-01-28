import java.util.concurrent.ThreadLocalRandom;

public class MastermindGame {
    private boolean gameWon = false;
    private char[] lastGuest;
    private String code;
    private char[] kleuren = { 'B', 'G', 'R', 'Y' };
    private int turnsleft = 8;
    final private int codelenght = 4;
    private String regex;

    /**
     * Maakt een nieuwe code aan als <code>code</code> ==<code>NULL</code>
     * 
     * @return sercet code, van <code>4</code> lang
     */
    public String generateSecretCode() {
        if (code == null) {
            code = "";
            for (int i = 0; i < kleuren.length; i++) {
                code += kleuren[ThreadLocalRandom.current().nextInt(0, kleuren.length)];
            }
            buildRegex();
        }
        return code;
    }

    /**
     * Als de guessString het zelfde is al de code word <code>gameWon</code> True
     * Anders word de input gecontroleerd of een gelde regex en lengte, zijn die
     * twee goed dan word <code>tunrsleft</code> een beurt afgehaald, aan gezien het
     * niet de geheimge code was. Een melding word uitgepint op besis van de twee
     * mogelijke problemen.
     * 
     * @param guessString Geef een string van max <code>4</code> lang, alle karatker
     *                    moeten voor komen in <code>kleuren</code>
     */
    public void checkCombination(String guessString) {
        lastGuest = "xxxx".toCharArray();
        if (generateSecretCode().equals(guessString)) {
            gameWon = true;
            return;
        }
        if (guessString.matches(regex)) {
            if (guessString.length() == codelenght) {
                lastGuest = guessString.toCharArray();
                turnsleft--;
            } else {
                System.out.println("Wrong input follow syntax: XXXX");
            }
        } else {
            System.out.println("Wrong color input follow " + String.valueOf(kleuren));
        }
    }

    /**
     * 
     * @return <code>True</code> als de speler heeft gewonnen
     *         <li><code>False</code> zo lang de speler niet heeft gewonnen
     */
    public boolean isWon() {
        return gameWon;
    }

    /**
     * 
     * @return geeft text met daar in de aantal correcte locatie geraden en de
     *         aantal in corecte locatie geraden
     */
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

    /**
     * Controleer of de speler doors zijn beurten heen is
     * 
     * @return <code>True</code> als speler geen turns meer heeft.
     *         <li><code>False</code> als de speler nog turns over heeft</li>
     */
    public boolean maxTurnsUsed() {
        if (turnsleft <= 0) {
            return true;
        }
        return false;
    }

    /**
     * Zet de regex naar de geweste regex voor de kleuren.
     */
    private void buildRegex() {
        regex = "^[";
        regex += String.valueOf(kleuren);
        regex += "]+$";
    }

}
