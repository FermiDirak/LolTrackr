package manuele.bryan.lolwinrate.LolStatistics;

import java.text.DecimalFormat;

public class StatisticsChampion {
    public String champName;
    public int wins;
    public int losses;
    public int matches;

    public double winrate = 0;
    public double winratePercent = 0;
    public String winrateString = "";
    public double popularity = 0;

    public StatisticsChampion(String champName, int wins, int losses, int matches) {
        this.champName = champName;
        this.wins = wins;
        this.losses = losses;

        this.winrate = (1.0 * wins) / (wins + losses);
        DecimalFormat decimalFormal = new DecimalFormat(".####");
        this.winrate = Double.valueOf(decimalFormal.format(winrate));
        this.winratePercent = winrate * 100.0;

        this.winrateString = Double.toString(Math.round(winratePercent * 100) / 100.0);

        this.matches = matches;
    }

}
