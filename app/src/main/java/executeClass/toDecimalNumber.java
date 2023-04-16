package executeClass;

import java.math.BigInteger;

import controlPackage.ControlTransfer;
import controlPackage.HMeth;

public class toDecimalNumber {
    protected boolean sign; // true + / false -
    protected int numSys;
    protected String originalNumber;
    protected BigInteger numerator;
    protected BigInteger denominator;

    public toDecimalNumber(){
        this.sign = true;
        this.numSys = 10;
        this.originalNumber = "0";
        this.numerator = BigInteger.ZERO;
        this.denominator = BigInteger.ONE;
    }
    public toDecimalNumber(int numSys, String originalNumber){
        this.numSys = numSys;
        if(originalNumber.charAt(0) == '-'){
            this.sign = false;
            this.originalNumber = originalNumber.substring(1);
        }
        else if(originalNumber.charAt(0) == '+'){
            this.sign = true;
            this.originalNumber = originalNumber.substring(1);
        }
        else{
            this.sign = true;
            this.originalNumber = originalNumber;
        }
        this.numerator = BigInteger.ZERO;
        this.denominator = BigInteger.ONE;
        this.transfer();
    }

    public static toDecimalNumber masterMaker(int numSys, boolean sign, BigInteger numerator, BigInteger denominator){
        toDecimalNumber tdn = new toDecimalNumber();
        tdn.numSys = numSys;
        tdn.sign = sign;
        tdn.originalNumber = "0";
        tdn.numerator = numerator;
        tdn.denominator = denominator;
        return tdn;
    }

    protected void transfer(){
        String type = ControlTransfer.typeNumber(this.originalNumber);
        if(type == "Integer"){
            BigInteger result = BigInteger.ZERO;
            for(int i = 0; i < this.originalNumber.length(); i++){
                if(this.originalNumber.charAt(i) >= '0' && this.originalNumber.charAt(i) <= '9'){
                    result = result.add(new BigInteger(Long.toString(this.numSys)).pow(this.originalNumber.length() - 1 - i)
                            .multiply(new BigInteger(Long.toString(this.originalNumber.charAt(i) - '0'))));
                }
                else{
                    result = result.add(new BigInteger(Long.toString(this.numSys)).pow(this.originalNumber.length() - 1 - i)
                            .multiply(new BigInteger(Long.toString(this.originalNumber.charAt(i) - 'A' + 10))));
                }
            }
            this.numerator = result;
            this.denominator = BigInteger.ONE;
        }
        else if(type == "Float"){
            String den = "";
            int sizer = this.originalNumber.length() - HMeth.indexChar(this.originalNumber, '.') - 1;
            int ind = 0;
            while(this.originalNumber.charAt(ind) != '.'){
                den += (char)(this.originalNumber.charAt(ind));
                ind++;
            }
            ind++;
            while(ind < this.originalNumber.length()){
                den += (char)(this.originalNumber.charAt(ind));
                ind++;
            }
            BigInteger n = new toDecimalNumber(this.numSys, den).getInteger();
            BigInteger d = new BigInteger(Integer.toString(this.numSys)).pow(sizer);
            BigInteger NOD = HMeth.algorithmEuclid(n, d);
            this.numerator = n.divide(NOD);
            this.denominator = d.divide(NOD);
        }
        else {
            String floatP = "";
            String periodP = "";
            int couFP = 0, couPP = 0, ind = 0;
            while(this.originalNumber.charAt(ind) != '.'){
                floatP += (char)(this.originalNumber.charAt(ind));
                periodP += (char)(this.originalNumber.charAt(ind));
                ind++;
            }
            ind++;
            while(this.originalNumber.charAt(ind) != '('){
                floatP += (char)(this.originalNumber.charAt(ind));
                periodP += (char)(this.originalNumber.charAt(ind));
                couFP++; couPP++;
                ind++;
            }
            ind++;
            while(this.originalNumber.charAt(ind) != ')'){
                periodP += (char)(this.originalNumber.charAt(ind));
                couPP++;
                ind++;
            }
            BigInteger del = new BigInteger(Integer.toString(this.numSys)).pow(couPP).subtract(new BigInteger(Integer.toString(this.numSys)).pow(couFP));
            BigInteger result = new toDecimalNumber(this.numSys, periodP).getInteger()
                    .subtract(new toDecimalNumber(this.numSys, floatP).getInteger());
            BigInteger NOD = HMeth.algorithmEuclid(del, result);
            del = del.divide(NOD);
            result = result.divide(NOD);
            this.numerator = result;
            this.denominator = del;
        }
    }

    public toDecimalNumber abs(){
        return toDecimalNumber.masterMaker(this.numSys, true, this.numerator, this.denominator);
    }

    public toDecimalNumber changeSign(){
        return toDecimalNumber.masterMaker(this.numSys, !this.sign, this.numerator, this.denominator);
    }

    public boolean getSign(){ return this.sign; }
    public int getNumSys(){ return this.numSys; }
    public BigInteger getNumerator(){ return this.numerator; }
    public BigInteger getDenominator(){ return this.denominator; }
    public BigInteger getInteger(){ return this.numerator.divide(this.denominator); }
    public BigInteger getFloat(){ return this.numerator.mod(this.denominator); }
}
