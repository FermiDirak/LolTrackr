package manuele.bryan.lolwinrate.Helpers;

import constant.Region;

/**
 * Created by Manuele on 3/17/2015.
 */
public class RiotAPIHelper {

    public static constant.Region getRegion(String region) {
        region = region.toLowerCase();

        switch (region) {
            case "br":
                return Region.BR;
            case "eune":
                return Region.EUNE;
            case "euw":
                return Region.EUW;
            case "kr":
                return Region.KR;
            case "lan":
                return Region.LAN;
            case "las":
                return Region.LAS;
            case "na":
                return Region.NA;
            case "oce":
                return Region.OCE;
            case "ru":
                return Region.RU;
            case "tr":
                return Region.TR;
            default:
                return Region.NA;
        }
    }

}
