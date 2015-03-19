package manuele.bryan.lolwinrate.Databases;

import android.app.Activity;
import android.content.res.AssetManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import manuele.bryan.lolwinrate.Helpers.StringHelper;
import manuele.bryan.lolwinrate.Items.StaticChampion;
import manuele.bryan.lolwinrate.Items.StaticSpell;
import manuele.bryan.lolwinrate.UserStatistics.RankedStatsInfo;
import manuele.bryan.lolwinrate.UserStatistics.UserInfo;
import manuele.bryan.lolwinrate.UserStatistics.UserSummaryInfo;

public class JsonIO {
    public static String KEY_RIOTAPI = "riotapi";
    
    public static String FAIL = "fail";

    //_________________________WEB_____________________________

    public static String getJSONFromWeb(String urlString) {
        //note: only execute method within async task

        String json = "";

        try {

            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(urlString);
            HttpResponse responseGet = null;

            responseGet = client.execute(get);
            HttpEntity resEntityGet = responseGet.getEntity();
            json = EntityUtils.toString(resEntityGet);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    //____________________RIOT_API_KEY_________________________

    public static String getRiotApiKey(AssetManager assets) {
        return loadApiKeyJSON(assets, KEY_RIOTAPI);
    }

    public static String loadApiKeyJSON(AssetManager assets, String key) {
        String apiKey = "";

        try {
            InputStream inputStream = assets.open("json/apikeys.json");
            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, "UTF-8");
            JSONObject JSONApiKeysObject = new JSONObject(json);

            apiKey = JSONApiKeysObject.getString(key);


        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }

        return apiKey;
    }

    //___________________________STATIC_CHAMPION_INFO________________________

    public static StaticChampion loadChampionInfo(Activity activity, String champName) {
        String json = loadChampionJSONFromAsset(activity, champName);

        champName = StringHelper.capitalizeFirstLetter(champName);
        return parseChampionJSON(json, champName);
    }

    public static String loadChampionJSONFromAsset(Activity activity, String champName) {
        String json = null;
        try {
            InputStream inputStream = activity.getAssets().open("json/champions/" + champName + ".json");
            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return FAIL;
        }

        return json;
    }

    public static StaticChampion parseChampionJSON(String jsonString, String champName) {

        StaticChampion staticChampion = null;

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONObject data = json.getJSONObject("data");
            JSONObject champion = data.getJSONObject(champName);

            String name = champion.getString("name");
            String title = champion.getString("title");
            String lore = champion.getString("lore");
            JSONArray JSONallytips = champion.getJSONArray("allytips");
            JSONArray JSONenemytips = champion.getJSONArray("enemytips");
            JSONArray JSONtags = champion.getJSONArray("tags");

            List<String> allytips = JSONArrayToArrayList(JSONallytips);
            List<String> enemytips = JSONArrayToArrayList(JSONenemytips);
            List<String> tags = JSONArrayToArrayList(JSONtags);

            String resourcetype = champion.getString("partype");

            JSONObject rankInfo = champion.getJSONObject("info");
            int attackRank = rankInfo.getInt("attack");
            int defenseRank = rankInfo.getInt("defense");
            int magicRank = rankInfo.getInt("magic");
            int difficultyRank = rankInfo.getInt("difficulty");

            JSONArray JSONspells = champion.getJSONArray("spells");

            StaticSpell[] spells = new StaticSpell[4];

            if (JSONspells != null) {
                for (int i = 0; i < JSONspells.length(); i++) {
                    JSONObject spell = JSONspells.getJSONObject(i);

                    String spellImageName = spell.getString("id");
                    String spellName = spell.getString("name");
                    String spellDescription = spell.getString("description");

                    StaticSpell staticSpell = new StaticSpell(spellImageName, spellName, spellDescription);

                    spells[i] = staticSpell;

                }
            }

            staticChampion = new StaticChampion(name, title, lore,
                    allytips, enemytips,
                    tags, resourcetype, spells,
                    attackRank, defenseRank, magicRank, difficultyRank);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return staticChampion;
    }

    public static ArrayList<String> JSONArrayToArrayList(JSONArray jsonArray) throws JSONException {
        ArrayList<String> arrayList = new ArrayList<>();

        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                arrayList.add(jsonArray.get(i).toString());
            }
        }

        return arrayList;
    }

    //____________________________USER_INFO__________________________________

    public static UserInfo parseUserJson(String userName, String jsonString) {

        UserInfo userInfo = null;

        try {
            JSONObject json = new JSONObject(jsonString);
            JSONObject user = json.getJSONObject(userName);

            int id = user.getInt("id");
            String name = user.getString("name");
            int profileIconId = user.getInt("profileIconId");
            int summonerLevel = user.getInt("summonerLevel");

            userInfo = new UserInfo(id, name, profileIconId, summonerLevel);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    //_______________________RANKED_STATS_INFO_______________________________

    public static RankedStatsInfo parseRankedStatsJson(String jsonString) {

        RankedStatsInfo rankedStatsInfo = null;

        try {
            JSONObject json = new JSONObject(jsonString);
            int summonerId = json.getInt("summonerId");

            JSONArray championJsonArray = json.getJSONArray("champions");

            List<RankedStatsInfo.ChampionInfo> championInfos = new ArrayList<>();
            for (int i = 0; i < championJsonArray.length(); i++) {
                JSONObject championObject = championJsonArray.getJSONObject(i);

                int championId = championObject.getInt("id");
                JSONObject statsObject = championObject.getJSONObject("stats");

                int totalSessionsPlayed = statsObject.getInt("totalSessionsPlayed");
                int totalSessionsLost = statsObject.getInt("totalSessionsLost");
                int totalSessionsWon = statsObject.getInt("totalSessionsWon");
                int totalChampionKills = statsObject.getInt("totalChampionKills");
                int totalDamageDealt = statsObject.getInt("totalDamageDealt");
                int totalDamageTaken = statsObject.getInt("totalDamageTaken");
                int mostChampionKillsPerSession = statsObject.getInt("mostChampionKillsPerSession");
                int totalMinionKills = statsObject.getInt("totalMinionKills");
                int totalDoubleKills = statsObject.getInt("totalDoubleKills");
                int totalTripleKills = statsObject.getInt("totalTripleKills");
                int totalQuadraKills = statsObject.getInt("totalQuadraKills");
                int totalPentaKills  = statsObject.getInt("totalPentaKills");
                int totalUnrealKills = statsObject.getInt("totalUnrealKills");
                int totalDeathsPerSession = statsObject.getInt("totalDeathsPerSession");
                int totalGoldEarned = statsObject.getInt("totalGoldEarned");
                int totalTurretsKilled = statsObject.getInt("totalTurretsKilled");
                int totalPhysicalDamageDealt = statsObject.getInt("totalPhysicalDamageDealt");
                int totalMagicDamageDealt = statsObject.getInt("totalMagicDamageDealt");
                int totalFirstBlood = statsObject.getInt("totalFirstBlood");
                int totalAssists = statsObject.getInt("totalAssists");
                int maxChampionsKilled = statsObject.getInt("maxChampionsKilled");
                int maxNumDeaths = statsObject.getInt("maxNumDeaths");

                RankedStatsInfo.ChampionInfo championInfo = new RankedStatsInfo.ChampionInfo(championId,
                        new RankedStatsInfo.ChampionInfo.Stats(totalSessionsPlayed, totalSessionsLost, totalSessionsWon,
                        totalChampionKills, totalDamageDealt, totalDamageTaken, mostChampionKillsPerSession, totalMinionKills, totalDoubleKills, totalTripleKills,
                        totalQuadraKills, totalPentaKills, totalUnrealKills, totalDeathsPerSession, totalGoldEarned, totalTurretsKilled, totalPhysicalDamageDealt,
                        totalMagicDamageDealt, totalFirstBlood, totalAssists, maxChampionsKilled, maxNumDeaths));

                championInfos.add(championInfo);
            }

            rankedStatsInfo = new RankedStatsInfo(summonerId, championInfos);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rankedStatsInfo;
    }

    //_______________________USER_SUMMARY_INFO_______________________________

    public static UserSummaryInfo parseUserSummaryJson(String jsonString) {
        UserSummaryInfo userSummaryInfo = null;

        try {
            JSONObject jsonObject = new JSONObject(jsonString);

            int summonerId = jsonObject.getInt("summonerId");

            JSONArray playerStatSummaries = jsonObject.getJSONArray("playerStatSummaries");

            List<UserSummaryInfo.PlayerSummaries> playerSummaries = new ArrayList<>();
            for (int i = 0; i < playerStatSummaries.length(); i++) {
                JSONObject pso = playerStatSummaries.getJSONObject(i);

                String playerStatsSummaryType = pso.getString("playerStatSummaryType");
                int wins = pso.getInt("wins");

                JSONObject aggregatedStats = pso.getJSONObject("aggregatedStats");

                int totalChampionKills, totalMinionKills, totalTurretsKilled, totalNeutralMinionsKilled, totalAssists;
                totalChampionKills = totalMinionKills = totalTurretsKilled = totalNeutralMinionsKilled = totalAssists = -1;

                if (aggregatedStats.has("totalChampionKills")) {
                    totalChampionKills = aggregatedStats.getInt("totalChampionKills");
                }
                if (aggregatedStats.has("totalMinionKills")) {
                    totalMinionKills = aggregatedStats.getInt("totalMinionKills");
                }
                if (aggregatedStats.has("totalTurretsKilled")) {
                    totalTurretsKilled = aggregatedStats.getInt("totalTurretsKilled");
                }
                if (aggregatedStats.has("totalNeutralMinionsKilled")) {
                    totalNeutralMinionsKilled = aggregatedStats.getInt("totalNeutralMinionsKilled");
                }
                if (aggregatedStats.has("totalAssists")) {
                    totalAssists = aggregatedStats.getInt("totalAssists");
                }

                UserSummaryInfo.PlayerSummaries playerSummary = new UserSummaryInfo.PlayerSummaries(playerStatsSummaryType, wins, totalChampionKills, totalMinionKills,
                        totalTurretsKilled, totalNeutralMinionsKilled, totalAssists);
                playerSummaries.add(playerSummary);
            }

            userSummaryInfo = new UserSummaryInfo(playerSummaries, summonerId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userSummaryInfo;
    }
}
