package executeClass;

import java.math.BigInteger;

public class fromDecimalNumber {
    protected boolean sign; // true + / false -
    protected int numSys;
    protected BigInteger originalNumerator;
    protected BigInteger originalDenominator;
    protected String resultNumerator;
    protected String resultDenominator;

    public fromDecimalNumber(){
        this.sign = true;
        this.numSys = 10;
        this.originalNumerator = BigInteger.ZERO;
        this.originalDenominator = BigInteger.ONE;
        this.resultNumerator = "0";
        this.resultDenominator = "1";
    }
    public fromDecimalNumber(int numSys, toDecimalNumber content){
        this.sign = content.getSign();
        this.numSys = numSys;
        this.originalNumerator = content.getNumerator();
        this.originalDenominator = content.getDenominator();
        this.resultNumerator = "0";
        this.resultDenominator = "0";
        this.transfer();
    }
    public fromDecimalNumber(int numSys, BigInteger numerator, BigInteger denominator){
        this.numSys = numSys;
        if(numerator.compareTo(BigInteger.ZERO) == -1){
            this.sign = false;
            numerator = numerator.abs();
        }
        else{
            this.sign = true;
        }
        this.originalNumerator = numerator;
        this.originalDenominator = denominator;
        this.resultNumerator = "0";
        this.resultDenominator = "1";
        this.transfer();
    }

    protected void transfer(){
        if(this.originalNumerator.mod(this.originalDenominator).equals(BigInteger.ZERO) || this.originalDenominator == BigInteger.ONE){ //Integer
            String result = "";
            BigInteger workCopy = this.originalNumerator;
            while(!workCopy.equals(BigInteger.ZERO)){
                if(workCopy.mod(new BigInteger(Integer.toString(this.numSys))).compareTo(BigInteger.TEN) == -1){
                    result += workCopy.mod(new BigInteger(Integer.toString(this.numSys))).toString();
                    workCopy = workCopy.divide(new BigInteger(Integer.toString(this.numSys)));
                }
                else{
                    result += (char)('A' - 10 + workCopy.mod(new BigInteger(Integer.toString(this.numSys))).intValue());
                    workCopy = workCopy.divide(new BigInteger(Integer.toString(this.numSys)));
                }
            }
            this.resultNumerator = new StringBuilder(result).reverse().toString();
            this.resultDenominator = "1";
        }
        else{ //Float + Period
            this.resultNumerator = new fromDecimalNumber(this.numSys, this.originalNumerator, BigInteger.ONE).getNumerator();
            this.resultDenominator = new fromDecimalNumber(this.numSys, this.originalDenominator, BigInteger.ONE).getNumerator();
        }
    }

    public boolean getSign(){ return this.sign; }
    public int getNumSys(){ return this.numSys; }
    public BigInteger getOriginalDenominator(){ return this.originalDenominator; }
    public BigInteger getOriginalNumerator(){ return this.originalNumerator; }
    public String getNumerator(){ return this.resultNumerator; }
    public String getDenominator(){ return this.resultDenominator; }
    public String getInteger(){
        fromDecimalNumber fdn = new fromDecimalNumber(this.numSys, this.originalNumerator.divide(originalDenominator), BigInteger.ONE);
        return fdn.getNumerator();
    }
    public String getFloat(){
        fromDecimalNumber fdn = new fromDecimalNumber(this.numSys, this.originalNumerator.mod(this.originalDenominator), BigInteger.ONE);
        return fdn.getNumerator();
    }
    //getInteger, getFloat
}
