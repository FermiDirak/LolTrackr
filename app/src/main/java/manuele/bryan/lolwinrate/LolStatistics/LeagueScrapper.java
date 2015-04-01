package manuele.bryan.lolwinrate.LolStatistics;

import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeagueScrapper {
    public QueryPreferences queryPreferences;

    public static final int NUMBEROFCHAMPIONS = 124;

    public LeagueScrapper(Context context) {
        this.queryPreferences = new QueryPreferences(context);
    }

    public List<StatisticsChampion> createDataTable() throws IOException {

        try {
            Document doc = Jsoup.connect(queryPreferences.createLink()).get();
            Element body = doc.body();
            Elements tbodys = body.getElementsByTag("tbody");
            Element champTable = tbodys.get(12);

            Elements champOrder = champTable.getElementsByClass("ar1"); //useless
            Elements champName = champTable.getElementsByClass("ar2");
            Elements champWinRate = champTable.getElementsByClass("ar3");
            Elements champMatches = champTable.getElementsByClass("ar4");
            Elements champWins = champTable.getElementsByClass("ar5");
            Elements champLosses = champTable.getElementsByClass("ar6");

            List<StatisticsChampion> table = new ArrayList<>();

            for (int i = 0; i < NUMBEROFCHAMPIONS; i++) {
                String name = nameOfChamp(champName, i);
                String winrate = winrateOfChamp(champWinRate, i); //useless
                String matchCount = matchCountOfChamp(champMatches, i);
                String winCount = winCountOfChamp(champWins, i);
                String lossCount = lossCountOfChamp(champLosses, i);

                matchCount = matchCount.replaceAll("[^0-9]", "");
                winCount = winCount.replaceAll("[^0-9]", "");
                lossCount = lossCount.replaceAll("[^0-9]", "");

                int matchCountInt = Integer.parseInt(matchCount);
                int winCountInt = Integer.parseInt(winCount);
                int lossCountInt = Integer.parseInt(lossCount);

                StatisticsChampion champ = new StatisticsChampion(name, winCountInt, lossCountInt, matchCountInt);

                table.add(champ);
            }

            return table;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }

    private static String nameOfChamp(Elements champName, int position) {
        String name = champName.get(position).toString();
        name = name.substring(37);
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '.') {
                name = name.substring(0, i);
                name = optimizeChampName(name);
            }
        }

        if (name.equals("429")) {
            return "kalista";
        }

        return name;
    }

    private static String optimizeChampName(String name) {
        name = name.toLowerCase();
        name = name.replaceAll("[ ]?-[ ]?", "");
        return name;
    }

    public static String winrateOfChamp(Elements champWinRate, int position) {
        return champWinRate.get(position).getElementsByTag("p").get(0).text();
    }

    private static String matchCountOfChamp(Elements champMatches, int position) {
        return champMatches.get(position).getElementsByTag("p").get(0).text();
    }

    private static String winCountOfChamp(Elements champWins, int position) {
        return champWins.get(position).getElementsByTag("p").get(0).text();
    }

    private static String lossCountOfChamp(Elements champLosses, int position) {
        return champLosses.get(position).getElementsByTag("p").get(0).text();
    }

}