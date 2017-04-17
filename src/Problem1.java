import java.io.*;

/**
 * Created by Tom on 07/04/2017.
 */
public class Problem1 {
    public static void main(String[] args) throws IOException{
        Solution1 sol = new Solution1("A-large.in");
        //Solution1 sol = new Problem1().new Solution1("A-small-attempt0.in");
        sol.init();
        sol.solve();
        sol.printOutputFile();
    }
}
