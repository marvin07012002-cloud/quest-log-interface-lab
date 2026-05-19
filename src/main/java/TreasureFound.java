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
        if (rarity.equalsIgnoreCase("common")) {
            points += 25;
        }
        if (rarity.equalsIgnoreCase("rare")) {
            points += 75;
        }
        if (rarity.equalsIgnoreCase("legendary")) {
            points += 150;
        }

        return points;
    }

    @Override
    public String getSummary() {
        return "Found " + rarity + " " + treasureName + " for " + getPoints() + " points";
    }
}
