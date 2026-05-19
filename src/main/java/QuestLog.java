import java.util.ArrayList;
import java.util.List;

public class QuestLog {
    private List<ScoreEvent> events;

    public QuestLog() {
        events = new ArrayList<>();
    }

    public void addEvent(ScoreEvent event) {
        events.add(event);
    }

    public int getTotalScore() {
        int totalPoints = 0;
        for (ScoreEvent event : events) {
            int points = event.getPoints();
            totalPoints += points;
        }
        return totalPoints;
    }

    public ScoreEvent getHighestScoringEvent() {
        int highestPoints = 0;
        ScoreEvent highestScoreEvent = null;
        for (ScoreEvent event : events) {
            int points = event.getPoints();
            if (points > highestPoints) {
                highestPoints = points;
                highestScoreEvent = event;
            }
        }
        return highestScoreEvent;
    }

    public ScoreEvent getLowestScoringEvent() {
        int lowestScore = 0;
        ScoreEvent lowestScoreEvent = null;
        for (ScoreEvent event : events) {
            int points = event.getPoints();
            if (points < lowestScore) {
                lowestScore = points;
                lowestScoreEvent = event;
            }
        }
        return lowestScoreEvent;

    }

    public void printSummary() {

    }
}
