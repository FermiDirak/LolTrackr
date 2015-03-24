package manuele.bryan.lolwinrate.UserStatistics;

import java.util.HashMap;

public class UsersLeagueInfo {
    HashMap<String, RankedQueue> queuesList;

    public UsersLeagueInfo(HashMap<String, RankedQueue> queuesList) {
        this.queuesList = queuesList;
    }

    public static class RankedQueue {
        public static final String QUEUE_RANKED_SOLO_FIVES = "RANKED_SOLO_5x5";
        public static final String QUEUE_RANKED_TEAM_FIVES = "RANKED_TEAM_5x5";
        public static final String QUEUE_RANKED_TEAM_THREE = "RANKED_TEAM_3X3";

        String divisionName;
        String tier;
        String queueType;

        String teamOrPlayerName;
        String division;
        int leaguePoints;
        int wins;
        int losses;
        boolean isHotStreak;
        boolean isVeteran;
        boolean isFreshBlood;
        boolean isInactive;

        boolean isInSeries;
        int seriesWins;
        int seriesLosses;

        public RankedQueue(String divisionName, String tier, String queueType, String teamOrPlayerName, String division,
                           int leaguePoints, int wins, int losses,
                           boolean isHotStreak, boolean isVeteran, boolean isFreshBlood, boolean isInactive,
                           boolean isInSeries, int seriesWins, int seriesLosses) {

            this.divisionName = divisionName;
            this.tier = tier;
            this.queueType = queueType;

            this.teamOrPlayerName = teamOrPlayerName;
            this.queueType = queueType;
            this.division = division;
            this.leaguePoints = leaguePoints;
            this.wins = wins;
            this.losses = losses;
            this.isHotStreak = isHotStreak;
            this.isVeteran = isVeteran;
            this.isFreshBlood = isFreshBlood;
            this.isInactive = isInactive;
            this.isInSeries = isInSeries;
            this.seriesWins = seriesWins;
            this.seriesLosses = seriesLosses;
        }

    }

}
