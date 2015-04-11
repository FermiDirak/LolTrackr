package manuele.bryan.lolwinrate.LolItems;

import java.util.ArrayList;
import java.util.HashMap;

public class StaticLolItemsList {

    HashMap<String, LolItem> itemList = new HashMap<>();

    public StaticLolItemsList(HashMap<String, LolItem> itemList) {
        this.itemList = itemList;
    }

    public static class LolItem {

        public String itemId = "";
        public String name = "";
        public String description = "";
        public String quickDescription = "";
        public ArrayList<String> buildInto = new ArrayList<>();
        public int goldPrice = 0;
        public int goldSell = 0;
        public boolean purchasable;
        public ArrayList<String> tags = new ArrayList<>();

        public LolItem(String itemId, String name,
                       String description, String quickDescription, ArrayList<String> buildInto,
                       int goldPrice, int goldSell, boolean purchasable, ArrayList<String> tags) {
            this.itemId = itemId;
            this.name = name;
            this.description = description;
            this.quickDescription = quickDescription;
            this.buildInto = buildInto;
            this.goldPrice = goldPrice;
            this.goldSell = goldSell;
            this.purchasable = purchasable;
            this.tags = tags;
        }
    }

}
