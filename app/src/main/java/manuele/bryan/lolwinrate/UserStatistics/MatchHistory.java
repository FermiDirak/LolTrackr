package manuele.bryan.lolwinrate.UserStatistics;

import java.util.ArrayList;

public class MatchHistory {

    public ArrayList<Match> matches;

    public MatchHistory(ArrayList<Match> matches) {
        this.matches = matches;
    }


    public static class Match {

        public Match(long matchId, String region, long matchCreation, long matchDuration,
                     String queueType, int mapId, String season, int teamSide,
                     int spell1Id, int spell2Id, int championId, boolean winner, int championLevel,
                     int item0, int item1, int item2, int item3, int item4, int item5, int item6,
                     int kills, int deaths, int assists, int doubleKills, int tripleKills, int quadraKills, int pentaKills,
                     int largestKillingSpree, int totalDamageDealt, int totalDamageDealtToChampions, int totalDamageTaken, int largestCriticalStrike,
                     int totalHeal, int cs, int neutralMinionsKilled, int neutralMinionsKilledTeamJungle, int neutralMinionsKilledEnemeyJungle,
                     int goldEarned, int goldSpent, int combatPlayerScore, int objectivePlayerScore, int totalPlayerScore, int totalScoreRank,
                     int magicDamageDealtToChampions, int physicalDamageDealtToChampions, int wardsBought, int sightWardsBought) {
            this.matchId = matchId;
            this.region = region;
            this.matchCreation = matchCreation;
            this.matchDuration = matchDuration;
            this.queueType = queueType;
            this.mapId = mapId;
            this.season = season;
            this.teamSide = teamSide;
            this.spell1Id = spell1Id;
            this.spell2Id = spell2Id;
            this.championId = championId;
            this.winner = winner;
            this.championLevel = championLevel;
            this.item0 = item0;
            this.item1 = item1;
            this.item2 = item2;
            this.item3 = item3;
            this.item4 = item4;
            this.item5 = item5;
            this.item6 = item6;
            this.kills = kills;
            this.deaths = deaths;
            this.assists = assists;
            this.doubleKills = doubleKills;
            this.tripleKills = tripleKills;
            this.quadraKills = quadraKills;
            this.pentaKills = pentaKills;
            this.largestKillingSpree = largestKillingSpree;
            this.totalDamageDealt = totalDamageDealt;
            this.totalDamageDealtToChampions = totalDamageDealtToChampions;
            this.totalDamageTaken = totalDamageTaken;
            this.largestCriticalStrike = largestCriticalStrike;
            this.totalHeal = totalHeal;
            this.cs = cs;
            this.neutralMinionsKilled = neutralMinionsKilled;
            this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
            this.neutralMinionsKilledEnemeyJungle = neutralMinionsKilledEnemeyJungle;
            this.goldEarned = goldEarned;
            this.goldSpent = goldSpent;
            this.combatPlayerScore = combatPlayerScore;
            this.objectivePlayerScore = objectivePlayerScore;
            this.totalPlayerScore = totalPlayerScore;
            this.totalScoreRank = totalScoreRank;
            this.magicDamageDealtToChampions = magicDamageDealtToChampions;
            this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
            this.wardsBought = wardsBought;
            this.sightWardsBought = sightWardsBought;
        }

        public long matchId;
        public String region;

        public long matchCreation;
        public long matchDuration;

        public String queueType;
        public int mapId;
        public String season;

        public int teamSide;
        public int spell1Id;
        public int spell2Id;
        public int championId;

        public boolean winner;

        public int championLevel;

        public int item0;
        public int item1;
        public int item2;
        public int item3;
        public int item4;
        public int item5;
        public int item6;

        public int kills;
        public int deaths;
        public int assists;

        public int doubleKills;
        public int tripleKills;
        public int quadraKills;
        public int pentaKills;

        public int largestKillingSpree;

        public int totalDamageDealt;
        public int totalDamageDealtToChampions;
        public int totalDamageTaken;
        public int largestCriticalStrike;

        public int totalHeal;

        public int cs;

        public int neutralMinionsKilled;

        public int neutralMinionsKilledTeamJungle;
        public int neutralMinionsKilledEnemeyJungle;

        public int goldEarned;
        public int goldSpent;

        public int combatPlayerScore;
        public int objectivePlayerScore;
        public int totalPlayerScore;
        public int totalScoreRank;

        public int magicDamageDealtToChampions;
        public int physicalDamageDealtToChampions;

        public int wardsBought;
        public int sightWardsBought;

    }

}
