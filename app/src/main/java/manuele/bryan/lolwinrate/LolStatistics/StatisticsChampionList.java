package manuele.bryan.lolwinrate.LolStatistics;

import java.util.ArrayList;
import java.util.List;

public class StatisticsChampionList {
    public List<StatisticsChampion> statisticsChampions = new ArrayList<>();
    public static int totalMatches = 0;

    public StatisticsChampionList(List<StatisticsChampion> statisticsChampions) {
        this.statisticsChampions = statisticsChampions;

        calculateTotalMatches();
        calculateChampionListPopularity();

        //TODO: get SortPreferences and sort champions

    }

    private void calculateTotalMatches() {
        totalMatches = 0;
        for (StatisticsChampion champ : statisticsChampions) {
            totalMatches += champ.matches;
        }
    }

    private void calculateChampionListPopularity() {
        for (int i = 0; i < statisticsChampions.size(); i++) {
            statisticsChampions.get(i).popularity = 1.0 * statisticsChampions.get(i).matches / totalMatches;
        }
    }

    public List<StatisticsChampion> sortByPopularity() {
        List<StatisticsChampion> destructableChampsList = statisticsChampions;
        List<StatisticsChampion> sortedChampList = new ArrayList<>();

        while (destructableChampsList.size() > 0) {

            int mostPopularsPosition = 0;
            StatisticsChampion mostPopular = destructableChampsList.get(mostPopularsPosition);
            for (int i = 0; i < destructableChampsList.size(); i++) {
                if (destructableChampsList.get(i).matches > mostPopular.matches) {
                    mostPopular = destructableChampsList.get(i);
                    mostPopularsPosition = i;
                }
            }

            sortedChampList.add(mostPopular);
            destructableChampsList.remove(mostPopularsPosition);
        }

        statisticsChampions = sortedChampList;
        return sortedChampList;
    }

    public List<StatisticsChampion> sortByWinrate() {
        List<StatisticsChampion> destructableChampsList = statisticsChampions;
        List<StatisticsChampion> sortedChampList = new ArrayList<>();

        while (destructableChampsList.size() > 0) {

            int bestsPosition = 0;
            StatisticsChampion best = destructableChampsList.get(bestsPosition);
            for (int i = 0; i < destructableChampsList.size(); i++) {
                if (destructableChampsList.get(i).winrate > best.winrate) {
                    best = destructableChampsList.get(i);
                    bestsPosition = i;
                }
            }

            sortedChampList.add(best);
            destructableChampsList.remove(bestsPosition);
        }

        statisticsChampions = sortedChampList;
        return sortedChampList;
    }

    public List<StatisticsChampion> sortByInversePopularity() {
        List<StatisticsChampion> destructableChampList = sortByPopularity();
        List<StatisticsChampion> sortedChampList = new ArrayList<>();

        while (destructableChampList.size() > 0) {
            sortedChampList.add(destructableChampList.remove(destructableChampList.size() - 1));
        }

        statisticsChampions = sortedChampList;
        return sortedChampList;
    }

    public List<StatisticsChampion> sortByInverseWinrate() {
        List<StatisticsChampion> destructableChampList = sortByWinrate();
        List<StatisticsChampion> sortedChampList = new ArrayList<>();

        while (destructableChampList.size() > 0) {
            sortedChampList.add(destructableChampList.remove(destructableChampList.size() - 1));
        }

        statisticsChampions = sortedChampList;
        return sortedChampList;
    }

}
