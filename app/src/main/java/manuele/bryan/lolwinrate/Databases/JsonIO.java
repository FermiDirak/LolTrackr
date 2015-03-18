package manuele.bryan.lolwinrate.Databases;

import android.app.Activity;
import android.content.res.AssetManager;

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

public class JsonIO {
    public static String KEY_RIOTAPI = "riotapi";
    
    public static String FAIL = "fail";

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

}
