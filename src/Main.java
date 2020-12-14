import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //initialize clock
        Clock clock = new Clock();
        //run clock for 24 hours = 24 * 3600 seconds
        int n = 24 * 3600;

        //run Clock
        int i = 0;
        while (i<n){
            clock.RunClock();
            i++;
        }

        HashMap<String, List<String>> armPositions = clock.ArmPositions();
        List<String> Coincident = armPositions.get("Coincident Arms");
        List<String> Opposite = armPositions.get("Directly Opposite Arms");

        System.out.println("1. Coincident Arms:\n");
        for (String time :
                Coincident) {
            System.out.println(time);
        }
        System.out.println();
        System.out.println();


        System.out.println("2. Directly Opposite Arms:\n");
        for (String time :
                Opposite) {
            System.out.println(time);
        }
        System.out.println();
        System.out.println();
    }
}
