package manuele.bryan.lolwinrate.UserStatistics;

import java.util.List;

public class RankedStatsInfo {

    public int summonerId;
    public List<ChampionInfo> championList;

    public RankedStatsInfo(int summonerId, List<ChampionInfo> championList) {
        this.summonerId = summonerId;
        this.championList = championList;
    }

    public static class ChampionInfo {
        public int id;
        public Stats stats;

        public ChampionInfo(int id, Stats stats) {
            this.id = id;
            this.stats = stats;
        }

        public static class Stats {
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

            public Stats(int totalSessionsPlayed, int totalSessionsLost, int totalSessionsWon, int totalChampionKills,
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


}
