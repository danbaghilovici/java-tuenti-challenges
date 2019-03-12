package edition2011.challenge2;

import java.util.concurrent.Callable;

public class TaskChallengeTwo implements Callable<Integer> {
    private char sign;
    private int firstValue;
    private int secondValue;

    public TaskChallengeTwo(char sign, int firstValue, int secondValue) {
        this.sign = sign;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public Integer call() throws Exception {
        int sum=0;
        switch (this.sign){
            case '=':
               sum=firstValue+secondValue;
               break;
            case '#':
                sum=(int)Math.pow(firstValue,secondValue);
                break;
            case '@':
                sum=firstValue-secondValue;
        }
        return new Integer(sum);
    }
}
