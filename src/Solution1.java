import java.io.*;

/**
 * Created by Tom on 07/04/2017.
 */
public class Solution1 {
    File inputFile, outputFile;
    int numInputs;
    String[] pancakes;
    int[] flipper, numFlips;

    public Solution1(String inputString) throws IOException {
        inputFile = new File("input/" + inputString);
        outputFile = new File("output/solution.txt");
    }

    public void init() throws IOException{
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        line = bufferedReader.readLine();
        numInputs = Integer.parseInt(line);
        pancakes = new String[numInputs];
        flipper = new int[numInputs];
        numFlips = new int[numInputs];
        // Input test: // System.out.println("line 1: " + line);

        for(int x=0;x<numInputs;x++){
            line = bufferedReader.readLine();
            String[] cases = line.split(" ");
            pancakes[x]=cases[0];
            flipper[x]=Integer.parseInt(cases[1]);
        }
    }

    public void solve(){
        int runningFlipCount=0;
        for(int x=0;x<numInputs;x++){
            runningFlipCount=0;
            for(int y=0;y<pancakes[x].length();y++){
                if(pancakes[x].charAt(y)=='-'){
                    if(y>pancakes[x].length()-flipper[x]){
                        runningFlipCount = -1;
                        break;
                    }
                    runningFlipCount++;
                    for(int z=y;z<y+flipper[x];z++){
                        if(pancakes[x].charAt(z)=='-'){
                            pancakes[x] = pancakes[x].substring(0, z) + '+' + pancakes[x].substring(z+1);
                        } else{
                            pancakes[x] = pancakes[x].substring(0, z) + '-' + pancakes[x].substring(z+1);
                        }
                    }
                }
            }
            numFlips[x] = runningFlipCount;
        }
    }

    void printOutputFile() throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));

        int quickfix=1;
        for(int x=0;x<numInputs;x++,quickfix++){
            if(numFlips[x]==-1){
                writer.write("Case #" + quickfix + ": IMPOSSIBLE");
            } else {
                writer.write("Case #" + quickfix + ": " + numFlips[x]);
            }
            writer.write("\n");
        }
        writer.close();
    }
}
