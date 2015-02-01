package manuele.bryan.lolwinrate;

import java.util.ArrayList;
import java.util.List;

public class Champion {
    public String champName;
    public int wins;
    public int losses;
    public int matches;

    public double winrate = 0;
    public double popularity = 0;

    public Champion(String champName, int wins, int losses, int matches) {
        this.champName = champName;
        this.wins = wins;
        this.losses = losses;
        this.winrate = (1.0 * wins) / (wins + losses);
        this.matches = matches;
    }

}
