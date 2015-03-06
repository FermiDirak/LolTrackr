package manuele.bryan.lolwinrate.Items;

public class DrawerItem {

    public String itemName;
    public int imgId;
    public boolean currentlySelected;

    public DrawerItem(String itemName, int imgId) {
        this.itemName = itemName;
        this.imgId = imgId;
        this.currentlySelected = false;
    }

}
