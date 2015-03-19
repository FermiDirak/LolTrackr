package manuele.bryan.lolwinrate.UserStatistics;

import java.util.List;

public class UserSummaryInfo {
    public static final String COOP_VS_AI = "CoopVsAI";
    public static final String COUNTER_PICK = "CounterPick";
    public static final String RANKED_TEAM_3V3 = "RankedTeam3x3";
    public static final String RANKED_TEAM_5V5 = "RankedTeam5x5";
    public static final String UNRANKED_3V3 = "Unranked3x3";
    public static final String CAP_5V5 = "CAP5x5"; //teambuilder
    public static final String UNRANKED = "Unranked";
    public static final String ARAM_UNRANKED_5V5 = "AramUnranked5x5";
    public static final String RANKED_SOLO_5V5= "RankedSolo5x5";
    public static final String ODIN_UNRANKED = "OdinUnranked";

    public int summonerId;
    public List<PlayerSummaries> playerSummaries;

    public UserSummaryInfo(List<PlayerSummaries> playerSummaries, int summonerId) {
        this.playerSummaries = playerSummaries;
        this.summonerId = summonerId;
    }

    //values will be -1 if unavailable
    public static class PlayerSummaries {
        public String playerStatSummaryType;
        public int wins;

        public int totalChampionKills;
        public int totalMinionKills;
        public int totalTurretsKilled;
        public int totalNeutralMinionsKilled;
        public int totalAssists;

        public PlayerSummaries(String playerStatSummaryType, int wins, int totalChampionKills,
                               int totalMinionKills, int totalTurretsKilled, int totalNeutralMinionsKilled, int totalAssists) {
            this.playerStatSummaryType = playerStatSummaryType;
            this.wins = wins;

            this.totalChampionKills = totalChampionKills;
            this.totalMinionKills = totalMinionKills;
            this.totalTurretsKilled = totalTurretsKilled;
            this.totalNeutralMinionsKilled = totalNeutralMinionsKilled;
            this.totalAssists = totalAssists;
        }
    }

}
