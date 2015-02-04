package manuele.bryan.lolwinrate.LolStatistics;

import java.util.ArrayList;
import java.util.List;

import manuele.bryan.lolwinrate.LolStatistics.Champion;

public class ChampionList {
    public List<Champion> champions = new ArrayList<>();
    public static int totalMatches = 0;

    public ChampionList(List<Champion> champions) {
        this.champions = champions;

        calculateTotalMatches();
        calculateChampionListPopularity();

        //TODO: get SortPreferences and sort champions

    }

    private void calculateTotalMatches() {
        totalMatches = 0;
        for (Champion champ : champions) {
            totalMatches += champ.matches;
        }
    }

    private void calculateChampionListPopularity() {
        for (int i = 0; i < champions.size(); i++) {
            champions.get(i).popularity = 1.0 * champions.get(i).matches / totalMatches;
        }
    }

    public List<Champion> sortByPopularity() {
        List<Champion> destructableChampsList = champions;
        List<Champion> sortedChampList = new ArrayList<>();

        while (destructableChampsList.size() > 0) {

            int mostPopularsPosition = 0;
            Champion mostPopular = destructableChampsList.get(mostPopularsPosition);
            for (int i = 0; i < destructableChampsList.size(); i++) {
                if (destructableChampsList.get(i).matches > mostPopular.matches) {
                    mostPopular = destructableChampsList.get(i);
                    mostPopularsPosition = i;
                }
            }

            sortedChampList.add(mostPopular);
            destructableChampsList.remove(mostPopularsPosition);
        }

        champions = sortedChampList;
        return sortedChampList;
    }

    public List<Champion> sortByWinrate() {
        List<Champion> destructableChampsList = champions;
        List<Champion> sortedChampList = new ArrayList<>();

        while (destructableChampsList.size() > 0) {

            int bestsPosition = 0;
            Champion best = destructableChampsList.get(bestsPosition);
            for (int i = 0; i < destructableChampsList.size(); i++) {
                if (destructableChampsList.get(i).winrate > best.winrate) {
                    best = destructableChampsList.get(i);
                    bestsPosition = i;
                }
            }

            sortedChampList.add(best);
            destructableChampsList.remove(bestsPosition);
        }

        champions = sortedChampList;
        return sortedChampList;
    }

    public List<Champion> sortByInversePopularity() {
        List<Champion> destructableChampList = sortByPopularity();
        List<Champion> sortedChampList = new ArrayList<>();

        while (destructableChampList.size() > 0) {
            sortedChampList.add(destructableChampList.remove(destructableChampList.size() - 1));
        }

        champions = sortedChampList;
        return sortedChampList;
    }

    public List<Champion> sortByInverseWinrate() {
        List<Champion> destructableChampList = sortByWinrate();
        List<Champion> sortedChampList = new ArrayList<>();

        while (destructableChampList.size() > 0) {
            sortedChampList.add(destructableChampList.remove(destructableChampList.size() - 1));
        }

        champions = sortedChampList;
        return sortedChampList;
    }

}
