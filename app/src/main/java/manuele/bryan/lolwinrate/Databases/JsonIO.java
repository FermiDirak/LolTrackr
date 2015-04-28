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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import manuele.bryan.lolwinrate.Helpers.StringHelper;
import manuele.bryan.lolwinrate.LolItems.StaticLolItemsList;
import manuele.bryan.lolwinrate.LolStatistics.StaticChampion;
import manuele.bryan.lolwinrate.UserStatistics.MatchHistory;
import manuele.bryan.lolwinrate.UserStatistics.RankedStatsInfo;
import manuele.bryan.lolwinrate.UserStatistics.UserInfo;
import manuele.bryan.lolwinrate.UserStatistics.UserSummaryInfo;
import manuele.bryan.lolwinrate.UserStatistics.UsersLeagueInfo;

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
            JSONObject champion = data.getJSONObject(data.keys().next());

            String name = champion.getString("name");
            int key = champion.getInt("key");
            String title = champion.getString("title");
            String lore = champion.getString("lore");
            String blurb = champion.getString("blurb");
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

            StaticChampion.StaticSpell[] spells = new StaticChampion.StaticSpell[4];

            if (JSONspells != null) {
                for (int i = 0; i < 4; i++) {
                    JSONObject spell = JSONspells.getJSONObject(i);

                    String spellImageName = spell.getString("id");
                    String spellName = spell.getString("name");
                    String spellDescription = spell.getString("description");

                    StaticChampion.StaticSpell staticSpell = new StaticChampion.StaticSpell(spellImageName, spellName, spellDescription);

                    spells[i] = staticSpell;

                }
            }

            staticChampion = new StaticChampion(name, key, title, lore, blurb,
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

            HashMap<Integer, RankedStatsInfo.ChampionStats> championInfos = new HashMap<Integer, RankedStatsInfo.ChampionStats>();

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

                RankedStatsInfo.ChampionStats champStats = new RankedStatsInfo.ChampionStats(totalSessionsPlayed, totalSessionsLost, totalSessionsWon,
                        totalChampionKills, totalDamageDealt, totalDamageTaken, mostChampionKillsPerSession, totalMinionKills, totalDoubleKills, totalTripleKills,
                        totalQuadraKills, totalPentaKills, totalUnrealKills, totalDeathsPerSession, totalGoldEarned, totalTurretsKilled, totalPhysicalDamageDealt,
                        totalMagicDamageDealt, totalFirstBlood, totalAssists, maxChampionsKilled, maxNumDeaths);

                championInfos.put(championId, champStats);
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

    //_______________________________USER_LEAGUE_INFO___________________________________

    public static UsersLeagueInfo parseUsersLeagueJson(String jsonString) {
        UsersLeagueInfo usersLeagueInfo = null;

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray queueJsonArray = jsonObject.getJSONArray(jsonObject.keys().next());

            HashMap<String, UsersLeagueInfo.RankedQueue> queues = new HashMap<>();
            for (int i = 0; i < queueJsonArray.length(); i++) {

                JSONObject queue = queueJsonArray.getJSONObject(i);

                String name = queue.getString("name");
                String tier = queue.getString("tier");
                String queueType = queue.getString("queue");

                JSONArray entries = queue.getJSONArray("entries");
                JSONObject entry = entries.getJSONObject(0);
                String playerOrTeamName = entry.getString("playerOrTeamName");
                String division = entry.getString("division");
                int leaguePoints = entry.getInt("leaguePoints");
                int wins = entry.getInt("wins");
                int losses = entry.getInt("losses");
                boolean isHotStreak = entry.getBoolean("isHotStreak");
                boolean isVeteran = entry.getBoolean("isVeteran");
                boolean isFreshBlood = entry.getBoolean("isFreshBlood");
                boolean isInactive = entry.getBoolean("isInactive");

                boolean inSeries = false;
                int seriesWins = 0;
                int seriesLosses = 0;

                if (entry.has("miniSeries")) {
                    JSONObject miniSeries = entry.getJSONObject("miniSeries");

                    inSeries = true;
                    seriesWins = miniSeries.getInt("wins");
                    seriesLosses = miniSeries.getInt("losses");
                }

                queues.put(queueType, new UsersLeagueInfo.RankedQueue(name, tier, queueType, playerOrTeamName, division, leaguePoints, wins, losses,
                        isHotStreak, isVeteran, isFreshBlood, isInactive, inSeries, seriesWins, seriesLosses));

            }

            usersLeagueInfo = new UsersLeagueInfo(queues);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return usersLeagueInfo;
    }

    //______________________________________MATCH_HISTORY_________________________________________

    public static MatchHistory parseMatchHistoryJson(String jsonString) {
        MatchHistory matchHistory = null;

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonMatches = jsonObject.getJSONArray("matches");

            ArrayList<MatchHistory.Match> matches = new ArrayList<>();
            for (int i = 0; i < jsonMatches.length(); i++) {
                JSONObject jsonMatch = jsonMatches.getJSONObject(i);

                long matchId = jsonMatch.getLong("matchId");
                String region = jsonMatch.getString("region");

                long matchCreation = jsonMatch.getLong("matchCreation");
                long matchDuration = jsonMatch.getLong("matchDuration");

                String queueType = jsonMatch.getString("queueType");
                int mapId = jsonMatch.getInt("mapId");
                String season = jsonMatch.getString("season");

                JSONObject participant = jsonMatch.getJSONArray("participants").getJSONObject(0);
                if (participant == null) {
                    //this should never happen, or we're fucked
                    return null;
                }

                int teamSide = participant.getInt("teamId");
                int spell1Id = participant.getInt("spell1Id");
                int spell2Id = participant.getInt("spell2Id");

                int championId = participant.getInt("championId");

                JSONObject matchStats = participant.getJSONObject("stats");

                boolean winner = matchStats.getBoolean("winner");
                int championLevel = matchStats.getInt("champLevel");

                int item0 = matchStats.getInt("item0");
                int item1 = matchStats.getInt("item1");
                int item2 = matchStats.getInt("item2");
                int item3 = matchStats.getInt("item3");
                int item4 = matchStats.getInt("item4");
                int item5 = matchStats.getInt("item5");
                int item6 = matchStats.getInt("item6");

                int kills = matchStats.getInt("kills");
                int deaths = matchStats.getInt("deaths");
                int assists = matchStats.getInt("assists");

                int doubleKills = matchStats.getInt("doubleKills");
                int tripleKills = matchStats.getInt("tripleKills");
                int quadraKills = matchStats.getInt("quadraKills");
                int pentaKills = matchStats.getInt("pentaKills");

                int largestKillingSpree = matchStats.getInt("largestKillingSpree");

                int totalDamageDealt = matchStats.getInt("totalDamageDealt");
                int totalDamageDealtToChampions = matchStats.getInt("totalDamageDealtToChampions");
                int totalDamageTaken = matchStats.getInt("totalDamageTaken");
                int largestCriticalStrike = matchStats.getInt("largestCriticalStrike");

                int totalHeal = matchStats.getInt("totalHeal");

                int cs = matchStats.getInt("minionsKilled");

                int neutralMinionsKilled = matchStats.getInt("neutralMinionsKilled");

                int neutralMinionsKilledTeamJungle = matchStats.getInt("neutralMinionsKilledTeamJungle");
                int neutralMinionsKilledEnemyJungle = matchStats.getInt("neutralMinionsKilledEnemyJungle");

                int goldEarned = matchStats.getInt("goldEarned");
                int goldSpent = matchStats.getInt("goldSpent");

                int combatPlayerScore = matchStats.getInt("combatPlayerScore");
                int objectivePlayerScore = matchStats.getInt("objectivePlayerScore");
                int totalPlayerScore = matchStats.getInt("totalPlayerScore");
                int totalScoreRank = matchStats.getInt("totalScoreRank");

                int magicDamageDealtToChampions = matchStats.getInt("magicDamageDealtToChampions");
                int physicalDamageDealtToChampions = matchStats.getInt("physicalDamageDealtToChampions");

                int wardsBought = matchStats.getInt("visionWardsBoughtInGame");
                int sightWardsBought = matchStats.getInt("sightWardsBoughtInGame");

                MatchHistory.Match match = new MatchHistory.Match(matchId, region, matchCreation, matchDuration,
                        queueType, mapId, season, teamSide,
                        spell1Id, spell2Id, championId, winner, championLevel,
                        item0, item1, item2, item3, item4, item5, item6,
                        kills, deaths, assists, doubleKills, tripleKills, quadraKills, pentaKills,
                        largestKillingSpree, totalDamageDealt, totalDamageDealtToChampions, totalDamageTaken, largestCriticalStrike,
                        totalHeal, cs, neutralMinionsKilled, neutralMinionsKilledTeamJungle, neutralMinionsKilledEnemyJungle,
                        goldEarned, goldSpent, combatPlayerScore, objectivePlayerScore, totalPlayerScore, totalScoreRank,
                        magicDamageDealtToChampions, physicalDamageDealtToChampions, wardsBought, sightWardsBought);

                matches.add(match);
            }

            matchHistory = new MatchHistory(matches);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return matchHistory;
    }

    //_______________________________LOL_ITEMS___________________________________

    public static StaticLolItemsList parseLolItemListJson(AssetManager assets) {
        String lolItemListJsonString = loadLolItemListJson(assets);

        StaticLolItemsList lolItemList = null;

        try {
            JSONObject jsonObject = new JSONObject(lolItemListJsonString);
            JSONObject itemsDataList = jsonObject.getJSONObject("data");

            Iterator<String> keys = itemsDataList.keys();

            HashMap<String, StaticLolItemsList.LolItem> itemList = new HashMap<>();

            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject itemData = itemsDataList.getJSONObject(key);

                String name = itemData.getString("name");
                String description = itemData.getString("description");
                String shortDescription = itemData.getString("plaintext");
                ArrayList<String> into = JSONArrayToArrayList(itemData.getJSONArray("into"));

                JSONObject goldJsonObject = itemData.getJSONObject("gold");
                int cost = goldJsonObject.getInt("total");
                int sell = goldJsonObject.getInt("sell");
                boolean purchasable = goldJsonObject.getBoolean("purchasable");

                ArrayList<String> tags = JSONArrayToArrayList(itemData.getJSONArray("tags"));

                StaticLolItemsList.LolItem lolItem = new StaticLolItemsList.LolItem(key, name, description, shortDescription, into, cost, sell, purchasable, tags);

                itemList.put(key, lolItem);
            }

            lolItemList = new StaticLolItemsList(itemList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lolItemList;
    }

    public static String loadLolItemListJson(AssetManager assets) {
        String json = "";

        try {
            InputStream inputStream = assets.open("json/lolitems.json");
            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }

        return json;
    }

}
