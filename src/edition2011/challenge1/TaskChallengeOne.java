package edition2011.challenge1;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class TaskChallengeOne implements Callable<BigInteger> {
    private BigInteger[] lineInputs;

    public TaskChallengeOne(BigInteger[] lineInputs){
        this.lineInputs=lineInputs;
    }

    @Override
    public BigInteger call() throws Exception {
        BigInteger sum=new BigInteger("0");
        for (int i=0;i<lineInputs.length;i++) {
            sum=sum.add(lineInputs[i]);
        }
        return sum;
    }
}
