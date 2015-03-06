package manuele.bryan.lolwinrate.Helpers;

import java.util.ArrayList;
import java.util.List;

public class StringHelper {

    public static String capitalizeFirstLetter(String string) {
        if (string.length() <= 1) {
            return string.toUpperCase();
        } else {
            return Character.toUpperCase(string.charAt(0)) + string.substring(1);
        }
    }

    public static String addBulletPoint(String string) {
        return ('\u2022' + " " + string);
    }

    public static List<String> addBulletPoints(List<String> list) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            result.add(addBulletPoint(list.get(i)));
        }

        return result;
    }

    public static String ArrayListToString(List<String> list) {
        String result = "";
        for (String string : list) {
            result += string + "\n";
        }

        return result;
    }

    public static String createBulletPointText(List<String> list) {
        list = addBulletPoints(list);
        return ArrayListToString(list);
    }

    public static String formatChampionBio(String bio) {
        return bio.replaceAll("<br><br>", "\n");

    }

}
