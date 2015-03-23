package manuele.bryan.lolwinrate.UserStatistics;

import java.util.HashMap;

public class RankedStatsInfo {

    public int summonerId;
    public HashMap<Integer, ChampionStats> championList;

    public RankedStatsInfo(int summonerId, HashMap<Integer, ChampionStats> championList) {
        this.summonerId = summonerId;
        this.championList = championList;
    }

    public static class ChampionStats {
        public int totalSessionsPlayed;
        public int totalSessionsLost;
        public int totalSessionsWon;
        public int totalChampionKills;
        public int totalDamageDealt;
        public int totalDamageTaken;
        public int mostChampionKillsPerSession;
        public int totalMinionKills;
        public int totalDoubleKills;
        public int totalTripleKills;
        public int totalQuadraKills;
        public int totalPentaKills;
        public int totalUnrealKills;
        public int totalDeathsPerSession;
        public int totalGoldEarned;
        public int totalTurretsKilled;
        public int totalPhysicalDamageDealt;
        public int totalMagicDamageDealt;
        public int totalFirstBlood;
        public int totalAssists;
        public int maxChampionsKilled;
        public int maxNumDeaths;

        public ChampionStats(int totalSessionsPlayed, int totalSessionsLost, int totalSessionsWon, int totalChampionKills,
                             int totalDamageDealt, int totalDamageTaken,
                             int mostChampionKillsPerSession, int totalMinionKills,
                             int totalDoubleKills, int totalTripleKills, int totalQuadraKills, int totalPentaKills, int totalUnrealKills,
                             int totalDeathsPerSession, int totalGoldEarned, int totalTurretsKilled,
                             int totalPhysicalDamageDealt, int totalMagicDamageDealt, int totalFirstBlood, int totalAssists,
                             int maxChampionsKilled, int maxNumDeaths) {
            this.totalSessionsPlayed = totalSessionsPlayed;
            this.totalSessionsLost = totalSessionsLost;
            this.totalSessionsWon = totalSessionsWon;
            this.totalChampionKills = totalChampionKills;
            this.totalDamageDealt = totalDamageDealt;
            this.totalDamageTaken = totalDamageTaken;
            this.mostChampionKillsPerSession = mostChampionKillsPerSession;
            this.totalMinionKills = totalMinionKills;
            this.totalDoubleKills = totalDoubleKills;
            this.totalTripleKills = totalTripleKills;
            this.totalQuadraKills = totalQuadraKills;
            this.totalPentaKills = totalPentaKills;
            this.totalUnrealKills = totalUnrealKills;
            this.totalDeathsPerSession = totalDeathsPerSession;
            this.totalGoldEarned = totalGoldEarned;
            this.totalTurretsKilled = totalTurretsKilled;
            this.totalPhysicalDamageDealt = totalPhysicalDamageDealt;
            this.totalMagicDamageDealt = totalMagicDamageDealt;
            this.totalFirstBlood = totalFirstBlood;
            this.totalAssists = totalAssists;
            this.maxChampionsKilled = maxChampionsKilled;
            this.maxNumDeaths = maxNumDeaths;
        }
    }


}
