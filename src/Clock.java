import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Clock {

    private final List<String> DirectlyOppositeArms;
    private final List<String> CoincidentArms;
    private int H,M,S;

    /**
     * Class Constructor
     */
    public Clock(){
        DirectlyOppositeArms = new ArrayList<>();
        CoincidentArms = new ArrayList<>();
        H=0;
        M=0;
        S=0;
    }

    public void RunClock(){
        S++;
        if(S==60){
            S=0;
            M++;
        }
        if(M==60){
            M=0;
            H++;
        }
        if(H==24){
            H=0;
        }

        CheckArmPosition(H,M,S);

    }

    /**
     * Array of angles of size 2 -> 0 = hourArm, 1 = minuteArm
     * @param hours double
     * @param minutes double
     * @param seconds integer
     * @return double angle of the hour arm from 12 o'clock
     */
    public double[] ArmAngles(int hours, int minutes, int seconds) {
        if (hours >= 12) {
            hours = hours - 12;
        }

        double totalMinutes = minutes + (double) (seconds/60);
        double totalHours = hours + (double) (minutes / 60) + (double) (seconds / 3600);

        double minAngle = totalMinutes * 6;
        double hAngle = totalHours * 30;


        return new double[]{hAngle,minAngle};
    }

    /**
     * Computes the clock Arm positions
     * @param hours integer
     * @param minutes integer
     * @param seconds integer
     */
    public void CheckArmPosition(int hours, int minutes, int seconds) {
        String currentTime = GetCurrentTime(hours,minutes,seconds);

        double[] hourAngle = ArmAngles(hours,minutes,seconds);

        double diff = hourAngle[0] - hourAngle[1];

        if (diff < 0){
            diff = diff * -1;
        }

        if (diff == 0) {
            CoincidentArms.add(currentTime);
        }

        if(diff == 180) {
            DirectlyOppositeArms.add(currentTime);
        }

    }

    /**
     * Gets the current time
     * @param hours integer
     * @param minutes integer
     * @param seconds integer
     * @return Current Time
     */
    private String GetCurrentTime(int hours, int minutes, int seconds){
        String h,m,s;
        if(hours<10){
            h = "0"+hours;
        }else {
            h = ""+hours;
        }

        if(minutes<10){
            m = "0"+minutes;
        }else {
            m = ""+minutes;
        }

        if(seconds<10){
            s = "0"+seconds;
        }else {
            s = ""+seconds;
        }

        return h+":"+m+":"+s;
    }

    /**
     * Gets the result of Arm positions opposite and coincident
     * @return HashMap of Arm positions
     */
    public HashMap<String,List<String>> ArmPositions(){
        HashMap<String,List<String>> result = new HashMap<>();
        result.put("Directly Opposite Arms",DirectlyOppositeArms);
        result.put("Coincident Arms",CoincidentArms);

        return result;
    }
}
