package manuele.bryan.lolwinrate.UserStatistics;

import constant.GameMode;
import constant.QueueType;
import dto.MatchHistory.MatchSummary;
import dto.MatchHistory.Participant;
import dto.MatchHistory.ParticipantStats;
import dto.MatchHistory.Player;
import main.java.riotapi.RiotApiException;
import manuele.bryan.lolwinrate.Helpers.LolStatsApplication;

public class LeagueMatch {
    MatchSummary match;

    String username;

    String gameMode;
    String queue;

    String championName;
    long championLevel;
    long kills, deaths, assists;
    long totalGold;

    String spell1, spell2;

    long trinketId, item1Id, item2Id, item3Id, item4Id, item5Id, item6Id;

    boolean victory;

    public LeagueMatch(MatchSummary match) throws RiotApiException {
        this.match = match;

        Participant participant = match.getParticipants().get(0);
        Player player = match.getParticipantIdentities().get(0).getPlayer();
        ParticipantStats stats = participant.getStats();

        username = player.getSummonerName();

        gameMode = GameMode.valueOf(match.getMatchMode()).getName();
        queue = QueueType.valueOf(match.getQueueType()).getName();

        championName = LolStatsApplication.riotApi.getDataChampion(participant.getChampionId()).getName();
        championLevel = stats.getChampLevel();
        kills = stats.getKills();
        deaths = stats.getDeaths();
        assists = stats.getAssists();
        totalGold = stats.getGoldEarned();

        spell1 = LolStatsApplication.riotApi.getDataSummonerSpell(participant.getSpell1Id()).getName();
        spell2 = LolStatsApplication.riotApi.getDataSummonerSpell(participant.getSpell2Id()).getName();

        trinketId = stats.getItem0();
        item1Id = stats.getItem1();
        item2Id = stats.getItem2();
        item3Id = stats.getItem3();
        item4Id = stats.getItem4();
        item5Id = stats.getItem5();
        item6Id = stats.getItem6();

        victory = stats.isWinner();

    }

    public void print() {
        System.out.println("username: " + username + "\n"
                + "gameMode: " + gameMode + "\n"
                + "queue: " + queue + "\n"
                + "champName: " + championName + "\n"
                + "champLevel: " + championLevel + "\n"
                + "kills: " + kills + "\n"
                + "deaths: " + deaths + "\n"
                + "assists: " + assists + "\n"
                + "totalGold: " + totalGold + "\n"
                + "spellOne: " + spell1 + "\n"
                + "spellTwo: " + spell2 + "\n"
                + "trinket: " + trinketId + "\n"
                + "item1: " + item1Id + "\n"
                + "item2: " + item2Id + "\n"
                + "item3: " + item3Id + "\n"
                + "item4: " + item4Id + "\n"
                + "item5: " + item5Id + "\n"
                + "item6: " + item6Id + "\n"
                + "victory: " + victory );

    }

}
