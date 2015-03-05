package manuele.bryan.lolwinrate.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaticChampion {

    public String name = "";
    public String title = "";
    public String lore = "";
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

    public StaticChampion(String name, String title, String lore,
                          List<String> allytips, List<String> enemytips,
                          List<String> tags, String resoucetype, StaticSpell[] spells,
                          int attackRank, int defenseRank, int magicRank, int difficultyRank) {
        this.name = name;
        this.title = title;
        this.lore = lore;
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
}
