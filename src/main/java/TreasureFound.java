public class TreasureFound implements ScoreEvent {
    private String treasureName;
    private String rarity;

    public TreasureFound(String rarity, String treasureName) {
        this.rarity = rarity;
        this.treasureName = treasureName;
    }


    @Override
    public String getName() {
        return treasureName;
    }

    @Override
    public int getPoints() {
        int points = 0;
        if (rarity == "common") {
            points = 25;
        } else if (rarity == "rare") {
            points = 75;
        } else if (rarity == "legendary") {
            points = 150;
        }

        return points;
    }

    @Override
    public String getSummary() {
        return "Found " + rarity + " " + treasureName + " for " + getPoints() + " points";
    }
}
