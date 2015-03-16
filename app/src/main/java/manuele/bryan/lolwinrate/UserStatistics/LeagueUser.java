package manuele.bryan.lolwinrate.UserStatistics;

import java.util.ArrayList;
import java.util.List;

import dto.MatchHistory.MatchSummary;
import dto.MatchHistory.PlayerHistory;
import dto.Summoner.Summoner;
import main.java.riotapi.RiotApiException;
import manuele.bryan.lolwinrate.Helpers.LolStatsApplication;

public class LeagueUser {

    String name;
    long id;
    int profileIconId;
    long level;

    List<LeagueMatch> matches;

    public LeagueUser(String userName) {

        try {

            Summoner summoner = LolStatsApplication.riotApi.getSummonerByName(userName).get(userName.replaceAll("\\s+", "").toLowerCase());

            id = summoner.getId();
            name = summoner.getName();
            profileIconId = summoner.getProfileIconId();
            level = summoner.getSummonerLevel();

            PlayerHistory history = LolStatsApplication.riotApi.getMatchHistory(id);
            List<MatchSummary> matchSummaries = history.getMatches();

            matches = new ArrayList<>();

            for (MatchSummary match : matchSummaries) {
                matches.add(new LeagueMatch(match));
            }

        } catch (RiotApiException e) {
            e.printStackTrace();
        }

    }

    public void print() {
        System.out.println("name: " + name + "\n"
                + "profile icon id: " + profileIconId + "\n"
                + "level: " + level);

        for (LeagueMatch match : matches) {
            match.print();
        }

    }

}
