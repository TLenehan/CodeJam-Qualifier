import java.io.*;
import java.math.BigInteger;

import static java.lang.StrictMath.ceil;
import static java.lang.StrictMath.floor;
import static java.lang.StrictMath.log;

/**
 * Created by Tom on 08/04/2017.
 */
public class Solution3 {
    File inputFile, outputFile;
    int numInputs;
    BigInteger[] N, K;

    public Solution3(String inputString) throws IOException {
        inputFile = new File("input/" + inputString);
        outputFile = new File("output/solution3.txt");
    }

    public void init() throws IOException{
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        line = bufferedReader.readLine();
        numInputs = Integer.parseInt(line);
        N = new BigInteger[numInputs];
        K = new BigInteger[numInputs];
        String[] cases;
        for(int x=0;x<numInputs;x++){
            line = bufferedReader.readLine();
            cases = line.split(" ");
            N[x] = new BigInteger(cases[0]);
            K[x]= new BigInteger(cases[1]);
        }
    }

    public void solve() throws IOException{
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
        for(int x=0;x<numInputs;x++){
            writer.write("Case #" + Integer.toString(x+1) + ": ");
            BigInteger pow, totalSpace;
            int power=0;
            BigInteger finding = new BigInteger("2");
            while(finding.pow(power).compareTo(K[x])<0){
                power++;
            }
            pow = new BigInteger(Integer.toString(power));
            if(K[x].doubleValue() == StrictMath.pow(2, pow.intValue())){
                totalSpace = N[x].add(K[x].multiply(new BigInteger("-1"))).divide((new BigInteger("2").pow(pow.intValue())));
            } else {
                totalSpace = N[x].add(K[x].multiply(new BigInteger("-1"))).divide((new BigInteger("2").pow(pow.intValue()-1)));
            }
            //System.out.println(totalSpace);
            double newTotal = totalSpace.doubleValue();
            long newTot = (long) newTotal;
            if(newTotal%2==0){
                //System.out.println(newTot/2 + " " + newTot/2);
                writer.write(newTot/2 + " " + newTot/2 + "\n");
            } else {
                //System.out.println(newTot/2 + 1 + " " + newTot/2);
                writer.write(newTot/2+1 + " " + newTot/2 + "\n");
            }
        }
        writer.close();
    }

    void printOutputFile() throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
        /*int quickFix;
        for(int x=0;x<numInputs;x++){
            quickFix=x+1;
            writer.write("Case #" + quickFix + ": " + solution[x] + "\n");
        }*/
        writer.close();
    }
}
