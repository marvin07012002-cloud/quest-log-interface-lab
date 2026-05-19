import java.io.StreamCorruptedException;

public class Program {

    public static void main(String[] args) {

        ScoreEvent event1 = new MonsterDefeat("Toxic Roach",99,true);
        ScoreEvent event2 = new TreasureFound("legendary","Marbin");
        System.out.println(event1.getSummary());
        System.out.println(event2.getSummary());


    }
}
