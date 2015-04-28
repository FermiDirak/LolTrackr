package manuele.bryan.lolwinrate.Helpers;

public class RiotAPIConstantsHelper implements RiotAPIConstants {

    public static String getQueueTypePlainText(String queue) {
        switch (queue) {
            case QUEUE_RANKED_SOLO_FIVES:
                return "Solo Queue";
            case QUEUE_RANKED_TEAM_FIVES:
                return "Team 5v5";
            case QUEUE_RANKED_TEAM_THREE:
                return "Team 3v3";
            default:
                return "Ranked";
        }
    }

}
