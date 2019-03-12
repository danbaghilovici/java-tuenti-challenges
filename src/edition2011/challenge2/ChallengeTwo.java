package edition2011.challenge2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ChallengeTwo {
    private static final int EDITION_YEAR=2011;
    private static final int CHALLENGE_NUMBER=2;
    private static final String SAMPLE_INPUT="src/edition"+EDITION_YEAR+"/challenge"+CHALLENGE_NUMBER+"/raw/inputs/sample_input.txt";
    private static final String SAMPLE_OUTPUT="src/edition"+EDITION_YEAR+"/challenge"+CHALLENGE_NUMBER+"/raw/outputs/sample_output.txt";
    private static final String TEST_INPUT="src/edition"+EDITION_YEAR+"/challenge"+CHALLENGE_NUMBER+"/raw/inputs/test_input.txt";
    private static final String TEST_OUTPUT="src/edition"+EDITION_YEAR+"/challenge"+CHALLENGE_NUMBER+"/raw/outputs/test_output.txt";

    private static final String INPUT=SAMPLE_INPUT;
    private static final String OUTPUT="src/edition"+EDITION_YEAR+"/challenge"+CHALLENGE_NUMBER+"/raw/outputs/output.txt";

    public static void main(String[] args) {
        Future<Integer> auxFutureObj;
        int number_cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService= Executors.newFixedThreadPool(number_cores);
        ArrayList<Future<Integer>> futureObjArray=new ArrayList<>();

        BufferedReader bufferedReader=null;
        FileReader fileReader=null;

        try{
            fileReader=new FileReader(INPUT);
            bufferedReader=new BufferedReader(fileReader);
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                // TO DO REGEX
                String[] separatedItems=currentLine.split(" ");
                char sign=separatedItems[0].charAt(1);
                int i=Integer.parseInt(separatedItems[1]);
                int j=Integer.parseInt(separatedItems[2].replaceAll("$",""));
                System.out.println(""+sign+i+j);
                auxFutureObj=executorService.submit(new TaskChallengeTwo(sign,i,j));
                futureObjArray.add(auxFutureObj);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {

                if (bufferedReader != null)
                    bufferedReader.close();

                if (fileReader != null)
                    fileReader.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
        executorService.shutdown();
        for(int i=0;i<futureObjArray.size();i++){
            try {
                auxFutureObj=futureObjArray.get(i);
                Integer result=auxFutureObj.get();
                System.out.println(result);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
