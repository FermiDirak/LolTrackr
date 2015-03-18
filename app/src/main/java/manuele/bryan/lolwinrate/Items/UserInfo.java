package manuele.bryan.lolwinrate.Items;

public class UserInfo {

    public int id;
    public String name;
    public int profileIconId;
    public int summonerLevel;

    public UserInfo(int id, String name, int profileIconId, int summonerLevel) {
        this.id = id;
        this.name = name;
        this.profileIconId = profileIconId;
        this.summonerLevel = summonerLevel;
    }
}
