package manuele.bryan.lolwinrate.Helpers;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

public interface RiotAPIConstants {

    //QUEUE_TYPES

    //RANKED_QUEUE_TYPES
    String QUEUE_RANKED_SOLO_FIVES = "RANKED_SOLO_5x5";
    String QUEUE_RANKED_TEAM_FIVES = "RANKED_TEAM_5x5";
    String QUEUE_RANKED_TEAM_THREE = "RANKED_TEAM_3X3";
    //UNRANKED_QUEUE_TYPES
    String QUEUE_CUSTOM = "CUSTOM";
    String QUEUE_NORMAL_FIVES_BLIND = "NORMAL_5x5_BLIND";

    //MAPS
    int MAP_SUMMONERSRIFTOLDSUMMER = 1;
    int MAP_SUMMONERSRIFTOLDAUTUMN = 2;
    int MAP_PROVINGGROUNDS = 3; //tutorial map
    int MAP_TWISTEDTREELINEOLD = 4;
    int MAP_CRYSTALSCAR = 8;
    int MAP_TWISTEDTREELINE = 10;
    int MAP_SUMMONERSRIFT = 11;
    int MAP_HOWLINGABYSS = 12;

    //GAME_MODES
    String MODE_CLASSIC = "CLASSIC";
    String MODE_ODIN = "ODIN";
    String MODE_ARAM = "ARAM";
    String MODE_TUTORIAL = "TUTORIAL";
    String MODE_ONE_FOR_ALL = "ONEFORALL";
    String MODE_ASCENSION = "ASCENSION";
    String MODE_FIRSTBLOOD = "FIRSTBLOOD";
    String MODE_KING_PORO = "KINGPORO";

    //GAME_TYPES
    String TYPE_CUSTOM = "CUSTOM_GAME";
    String TYPE_TUTORIAL = "TUTORIAL_GAME";
    String TYPE_MATCHED_GAME = "MATCHED_GAME";

    //DIVISIONS
    String DIVISION_BRONZE = "BRONZE";
    String DIVISION_SILVER = "SILVER";
    String DIVISION_GOLD = "GOLD";
    String DIVISION_PLAT = "PLATINUM";
    String DIVISION_DIAMOND = "DIAMOND";
    String DIVISION_MASTERS = "MASTER";
    String DIVISION_CHALLENGER = "CHALLENGER";

    SparseArray<String> championDictionary = new SparseArray<String>() {{
        put(266, "aatrox");
        put(103, "ahri");
        put(84, "akali");
        put(12, "alistar");
        put(32, "amumu");
        put(34, "anivia");
        put(1, "annie");
        put(22, "ashe");
        put(268, "azir");
        put(432, "bard");
        put(53, "blitzcrank");
        put(63, "brand");
        put(201, "braum");
        put(51, "caitlyn");
        put(69, "cassiopeia");
        put(31, "chogath");
        put(42, "corki");
        put(122, "darius");
        put(131, "diana");
        put(36, "drmundo");
        put(119, "draven");
        put(60, "elise");
        put(28, "evelynn");
        put(81, "ezreal");
        put(9, "fiddlesticks");
        put(114, "fiora");
        put(105, "fizz");
        put(3, "galio");
        put(41, "gangplank");
        put(86, "garen");
        put(150, "gnar");
        put(79, "gragas");
        put(104, "graves");
        put(120, "hecarim");
        put(74, "heimerdinger");
        put(39, "irelia");
        put(40, "janna");
        put(59, "jarvaniv");
        put(24, "jax");
        put(126, "jayce");
        put(222, "jinx");
        put(429, "kalista");
        put(43, "karma");
        put(30, "karthus");
        put(38, "kassadin");
        put(55, "katarina");
        put(10, "kayle");
        put(85, "kennen");
        put(121, "khazix");
        put(96, "kogmaw");
        put(7, "leblanc");
        put(64, "leesin");
        put(89, "leona");
        put(127, "lissandra");
        put(236, "lucian");
        put(117, "lulu");
        put(99, "lux");
        put(54, "malphite");
        put(90, "malzahar");
        put(57, "maokai");
        put(11, "masteryi");
        put(21, "missfortune");
        put(82, "mordekaiser");
        put(25, "morgana");
        put(267, "nami");
        put(75, "nasus");
        put(111, "nautilus");
        put(76, "nidalee");
        put(56, "nocturne");
        put(20, "nunu");
        put(2, "olaf");
        put(61, "orianna");
        put(80, "pantheon");
        put(78, "poppy");
        put(133, "quinn");
        put(33, "rammus");
        put(421, "reksai");
        put(58, "renekton");
        put(107, "rengar");
        put(92, "riven");
        put(68, "rumble");
        put(13, "ryze");
        put(113, "sejuani");
        put(35, "shaco");
        put(98, "shen");
        put(102, "shyvana");
        put(27, "singed");
        put(14, "sion");
        put(15, "sivir");
        put(72, "skarner");
        put(37, "sona");
        put(16, "soraka");
        put(50, "swain");
        put(134, "syndra");
        put(91, "talon");
        put(44, "taric");
        put(17, "teemo");
        put(412, "thresh");
        put(18, "tristana");
        put(48, "trundle");
        put(23, "tryndamere");
        put(4, "twistedfate");
        put(29, "twitch");
        put(77, "udyr");
        put(6, "urgot");
        put(110, "varus");
        put(67, "vayne");
        put(45, "veigar");
        put(161, "velkoz");
        put(254, "vi");
        put(112, "viktor");
        put(8, "vladimir");
        put(106, "volibear");
        put(19, "warwick");
        put(62, "wukong");
        put(101, "xerath");
        put(5, "xinzhao");
        put(157, "yasuo");
        put(83, "yorick");
        put(154, "zac");
        put(238, "zed");
        put(115, "ziggs");
        put(26, "zilean");
        put(143, "zyra");

    }};



}
