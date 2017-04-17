import java.io.*;
import java.math.BigInteger;
import java.util.Vector;

/**
 * Created by Tom on 08/04/2017.
 */
public class Solution2 {
    File inputFile, outputFile;
    int numInputs;
    String[] N, solution;

    public Solution2(String inputString) throws IOException {
        inputFile = new File("input/" + inputString);
        outputFile = new File("output/solution2.txt");
    }

    public void init() throws IOException{
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        line = bufferedReader.readLine();
        numInputs = Integer.parseInt(line);
        N = new String[numInputs];
        solution = new String[numInputs];
        for(int x=0;x<numInputs;x++){
            N[x] = bufferedReader.readLine();
        }
    }

    public void solve(){
        for(int x=0;x<numInputs;x++){
            solution[x] = nonDecreasing(N[x]);
        }
    }

    public String nonDecreasing(String currString){
        String temp = "";
        temp = currString.substring(0, 1);
        for(int x=0;x<currString.length()-1;x++){
            if(currString.charAt(x)<=currString.charAt(x+1)){
                temp = temp + currString.charAt(x+1);
            } else {
                temp = currString.substring(0, x) + Integer.toString(Integer.parseInt(currString.substring(x, x+1))-1);
                for(int y=x+1;y<currString.length();y++){
                    temp = temp + '9';
                }
                return nonDecreasing(temp);
            }
        }
        while(temp.charAt(0)=='0'||temp.length()==0){
            temp=temp.substring(1);
        }
        if(temp.length()==0){
            temp = "0";
        }
        return temp;
    }

    void printOutputFile() throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
        int quickFix;
        for(int x=0;x<numInputs;x++){
            quickFix=x+1;
            writer.write("Case #" + quickFix + ": " + solution[x] + "\n");
        }
        writer.close();
    }
}
