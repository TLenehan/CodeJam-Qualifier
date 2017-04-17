import java.io.*;
import java.math.BigInteger;
import java.util.Vector;

/**
 * Created by Tom on 08/04/2017.
 */
public class Solution4 {
    File inputFile, outputFile;
    int numInputs;
    int stageDim, style;
    char[][] stage;
    Vector<String> changes;
    int bestStyle;


    Writer writer;

    public Solution4(String inputString) throws IOException {
        inputFile = new File("input/" + inputString);
        outputFile = new File("output/solution4.txt");
    }

    public void init() throws IOException {
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        line = bufferedReader.readLine();
        numInputs = Integer.parseInt(line);
        changes = new Vector<>();
        String[] cases;
        for (int x = 0; x < numInputs; x++) {
            line = bufferedReader.readLine();
            cases = line.split(" ");
            stageDim = Integer.parseInt(cases[0]);
            int numSet = Integer.parseInt(cases[1]);
            style = 0;
            stage = new char[stageDim][stageDim];
            for (int y = 0; y < numSet; y++) {
                line = bufferedReader.readLine();
                cases = line.split(" ");
                stage[Integer.parseInt(cases[1]) - 1][Integer.parseInt(cases[2]) - 1] = cases[0].charAt(0);
                if (cases[0].charAt(0) == 'o') {
                    style += 2;
                } else {
                    style += 1;
                }
            }
            //System.out.println("Case #" + x);
            bestStyle=0;
            //solve(stage, new Vector<>(), 0, 0);
            //System.out.println(style + "\n\n");
            printOutputFile(x);
        }
        writer.close();
    }

    public void solve(char[][] tempStage, Vector<String> tempChanges, int currRow, int currCol, int currStyle) throws IOException {
        if(currCol>0){
            for(;currCol<stageDim;currCol++){
                if(canAddO(currRow, currCol)){

                }
            }
        }
        for (int x = 0; x < stageDim; x++) {
            for (int y = 0; y < stageDim; y++) {
                if (stage[x][y] == 0) {
                    if(canAddP(x, y)) {
                        //style += replace(x, y, '+');
                    }else if(canAddX(x, y)) {
                        //style+= replace(x, y, 'x');
                    }else {
                        //replace(x, y, '.');
                    }
                }
            }
        }
        for (int x = 0; x < stageDim; x++) {
            for (int y = 0; y < stageDim; y++) {
                if (canAddO(x, y)) {
                    //style += replace(x, y, 'o');
                }
                //System.out.print(stage[x][y]);
            }
            //System.out.println();
        }
        //System.out.println("Style total = " + style);
    }

    private int replace(int row, int column, char replaceWith, char[][] currStage){
        int styleIncrease;
        if(replaceWith=='o'){
            styleIncrease=2;
            if(currStage[row][column]=='+'||currStage[row][column]=='x'){
                styleIncrease--;
            }
        } else if(replaceWith == '.'){
            styleIncrease=0;
        } else {
            styleIncrease=1;
        }
        if(replaceWith!='.'){
            if(currStage[row][column]=='x'||stage[row][column]=='+'){
                for(int x=0;x<changes.size();x++){
                    if(changes.get(x).compareTo(stage[row][column] + " " + (row+1) + " " + (column+1))==0){
                        changes.remove(x);
                        break;
                    }
                }
            }
            changes.add(replaceWith + " " + (row+1) + " " + (column+1));
        }
        stage[row][column]=replaceWith;
        return styleIncrease;
    }

    private boolean canAddP(int row, int column){
        boolean can=true;
        int tempR=row, tempC=column;
        while(tempR<stageDim-1&&tempC<stageDim-1){
            tempR++;
            tempC++;
        }
        while(tempR>=0&&tempC>=0){
            if(stage[tempR][tempC]!=0&&stage[tempR][tempC]!='.'&&stage[tempR][tempC]!='x'&&tempR!=row){
                can=false;
                break;
            }
            tempC--;
            tempR--;
        }
        tempR=row;
        tempC=column;
        while(tempR<stageDim-1&&tempC>0){
            tempR++;
            tempC--;
        }
        while (tempR>=0&&tempC<stageDim){
            if(stage[tempR][tempC]!=0&&stage[tempR][tempC]!='.'&&stage[tempR][tempC]!='x'&&tempR!=row){
                can=false;
                break;
            }
            tempR--;
            tempC++;
        }
        return can;
    }

    private boolean canAddX(int row, int column){
        boolean can=true;
        for(int tempR=0;tempR<stageDim;tempR++){
            if(stage[tempR][column]!=0&&stage[tempR][column]!='.'&&stage[tempR][column]!='+'&&tempR!=row){
                can=false;
            }
        }
        for(int tempC=0;tempC<stageDim;tempC++){
            if(stage[row][tempC]!=0&&stage[row][tempC]!='.'&&stage[row][tempC]!='+'&&tempC!=column){
                can=false;
            }
        }
        return can;
    }

    private boolean canAddO(int row, int column){
        return canAddP(row, column)&&canAddX(row, column)&&stage[row][column]!='o';
    }

    void printOutputFile(int caseNum) throws IOException {
        int quickFix = caseNum + 1;
        writer.write("Case #" + quickFix + ": " + style + " " + changes.size() + "\n");
        for(int x=0;x<changes.size();x++){
            writer.write(changes.get(x) + "\n");
        }
    }
}
