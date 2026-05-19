public class MonsterDefeat implements ScoreEvent {
    private String monsterName;
    private int difficultyLevel;
    private boolean boss;

    public MonsterDefeat(String monsterName, int difficultyLevel, boolean boss) {
        this.monsterName = monsterName;
        this.difficultyLevel = difficultyLevel;
        this.boss = boss;
    }

    @Override
    public String getName() {
        return monsterName;
    }

    @Override
    public int getPoints() {
        int points = difficultyLevel * 50;
        if(boss == true){
            points += 100;
        }
        return points;
    }

    @Override
    public String getSummary() {
        return "Defeated " + monsterName + " for " + getPoints() + " points";
    }

}
