package manuele.bryan.lolwinrate.LolStatistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import manuele.bryan.lolwinrate.Helpers.StringHelper;

public class StaticChampion {

    public int key = 0;

    public String name = "";
    public String title = "";
    public String lore = "";
    public String blurb = "";
    public List<String> allytips = new ArrayList<>();
    public List<String> enemytips = new ArrayList<>();
    public List<String> tags = new ArrayList<>();
    public String resoucetype = "";
    public StaticSpell[] spells = new StaticSpell[4];

    //rankings
    public int attackRank = 0;
    public int defenseRank = 0;
    public int magicRank = 0;
    public int difficultyRank = 0;

    public StaticChampion(String name, int key, String title, String lore, String blurb,
                          List<String> allytips, List<String> enemytips,
                          List<String> tags, String resoucetype, StaticSpell[] spells,
                          int attackRank, int defenseRank, int magicRank, int difficultyRank) {
        this.name = name;
        this.key = key;
        this.title = title;
        this.lore = StringHelper.formatChampionBio(lore);
        this.blurb = blurb;
        this.allytips = allytips;
        this.enemytips = enemytips;
        this.tags = tags;
        this.resoucetype = resoucetype;
        this.spells = spells;
        this.attackRank = attackRank;
        this.defenseRank = defenseRank;
        this.magicRank = magicRank;
        this.difficultyRank = difficultyRank;
    }

    @Override
    public String toString() {
        return "StaticChampion{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", lore='" + lore + '\'' +
                ", allytips=" + allytips +
                ", enemytips=" + enemytips +
                ", tags=" + tags +
                ", resoucetype='" + resoucetype + '\'' +
                ", spells=" + Arrays.toString(spells) +
                ", attackRank=" + attackRank +
                ", defenseRank=" + defenseRank +
                ", magicRank=" + magicRank +
                ", difficultyRank=" + difficultyRank +
                '}';
    }

    public static class StaticSpell {
        public String spellImageName = "";;
        public String name = "";
        public String description = "";

        public StaticSpell(String spellImageName, String name, String description) {
            this.spellImageName = spellImageName;
            this.name = name;
            this.description = description;
        }

    }
}
