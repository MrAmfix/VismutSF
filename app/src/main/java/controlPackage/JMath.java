package controlPackage;

import java.math.BigDecimal;
import java.math.BigInteger;

import executeClass.toDecimalNumber;

public class JMath {
    public static toDecimalNumber add(toDecimalNumber num1, toDecimalNumber num2){
        if(num1.getSign() && num2.getSign()){ // + +
            BigInteger numerator = num1.getNumerator().multiply(num2.getDenominator()).add(num2.getNumerator().multiply(num1.getDenominator()));
            BigInteger denominator = num1.getDenominator().multiply(num2.getDenominator());
            BigInteger NOD = HMeth.algorithmEuclid(numerator, denominator);
            return toDecimalNumber.masterMaker(10, true, numerator.divide(NOD), denominator.divide(NOD));
        }
        else if(num1.getSign() && !num2.getSign()){ // + -
            BigInteger numerator = num1.getNumerator().multiply(num2.getDenominator()).subtract(num2.getNumerator().multiply(num1.getDenominator()));
            BigInteger denominator = num1.getDenominator().multiply(num2.getDenominator());
            BigInteger NOD = HMeth.algorithmEuclid(numerator.abs(), denominator);
            if(numerator.compareTo(BigInteger.ZERO) == -1){
                return toDecimalNumber.masterMaker(10, false, numerator.abs().divide(NOD), denominator.divide(NOD));
            }
            else{
                return toDecimalNumber.masterMaker(10, true, numerator.divide(NOD), denominator.divide(NOD));
            }
        }
        else if(!num1.getSign() && num2.getSign()){ // - +
            return JMath.add(num2, num1);
        }
        else{ // - -
            return JMath.add(num1.abs(), num2.abs()).changeSign();
        }
    }
    public static toDecimalNumber subtract(toDecimalNumber num1, toDecimalNumber num2){
        return JMath.add(num1, num2.changeSign());
    }
    public static toDecimalNumber multiply(toDecimalNumber num1, toDecimalNumber num2){
        BigInteger numerator = num1.getNumerator().multiply(num2.getNumerator());
        BigInteger denominator = num1.getDenominator().multiply(num2.getDenominator());
        BigInteger NOD = HMeth.algorithmEuclid(numerator, denominator);
        return toDecimalNumber.masterMaker(10, num1.getSign() == num2.getSign(), numerator.divide(NOD), denominator.divide(NOD));
    }
    public static toDecimalNumber divide(toDecimalNumber num1, toDecimalNumber num2){
        return JMath.multiply(num1, toDecimalNumber.masterMaker(10, num2.getSign(), num2.getDenominator(), num2.getNumerator()));
    }
//    public static toDecimalNumber pow(toDecimalNumber num1, toDecimalNumber degree){
//        if(degree.getInteger().abs().compareTo(new BigInteger("50")) == 1 || !(degree.getFloat().equals(BigInteger.ZERO))){ // Предохранение от зависаний приложения
//            return null;
//        }
//        else if(degree.getInteger().intValue() == 0){
//            return toDecimalNumber.masterMaker(10, true, BigInteger.ONE, BigInteger.ONE);
//        }
//        else{
//            toDecimalNumber helper = num1;
//            for(int i = 1; i < degree.getInteger().intValue(); i++){
//                helper = JMath.multiply(helper, num1);
//            }
//            if(degree.getInteger().intValue() > 0){
//                return helper;
//            }
//            else {
//                return toDecimalNumber.masterMaker(10, helper.getSign(), helper.getDenominator(), helper.getNumerator());
//            }
//        }
//    }
    public static toDecimalNumber pow(toDecimalNumber num1, toDecimalNumber degree){
        BigInteger num = num1.getNumerator(), den = num1.getDenominator();
        BigInteger numd = degree.getNumerator(), dend = degree.getDenominator();
        //for test-mode
//        if(HMeth.isInt(numd) && HMeth.isInt(dend)){
//            if(!dend.equals(BigInteger.ONE) && !num1.getSign()){
//                return null;
//            }
//            else{
//                BigDecimal n = JMath.sqrt(num.pow(numd.intValue()), dend);
//                BigDecimal d = JMath.sqrt(den.pow(numd.intValue()), dend);
//                toDecimalNumber ret = new toDecimalNumber(10, n.divide(d, BigDecimal.ROUND_CEILING).setScale(10, RoundingMode.CEILING).toString());
//                if(!num1.getSign() && dend.equals(BigInteger.ONE) && !numd.mod(new BigInteger("2")).equals(BigInteger.ZERO)){
//                    ret = ret.changeSign();
//                }
//                if(degree.getSign()){
//                    return ret;
//                }
//                else{
//                    return toDecimalNumber.masterMaker(10, ret.getSign(), ret.getDenominator(), ret.getNumerator());
//                }
//            }
//        }
//        else{
//            return null;
//        }
        if(dend.equals(BigInteger.ONE) && HMeth.isInt(numd)){
            if(!HMeth.isCanPow(num.abs().toString(), numd.abs()) || !HMeth.isCanPow(den.abs().toString(), numd.abs())){
                return null;
            }
            toDecimalNumber f = toDecimalNumber.masterMaker(10, true, num.pow(numd.intValue()), den.pow(numd.intValue()));
            if(!degree.getSign()){
                f = toDecimalNumber.masterMaker(10, true, f.getDenominator(), f.getNumerator());
            }
            if(!num1.getSign() && numd.mod(new BigInteger("2")).equals(BigInteger.ONE)){
                f = f.changeSign();
            }
            return f;
        }
        return null;
    }
    private static BigDecimal sqrt(BigInteger num1, BigInteger degree){
        if(degree.equals(BigInteger.ONE)){
            return new BigDecimal(num1);
        }
        BigDecimal l = BigDecimal.ZERO, r = new BigDecimal(num1);
        while(l.pow(degree.intValue()).subtract(new BigDecimal(num1)).abs().compareTo(new BigDecimal("0.000001")) == 1){
            BigDecimal m = l.add(r).divide(new BigDecimal("2"));
            if(m.pow(degree.intValue()).subtract(new BigDecimal(num1)).signum() <= 0){
                l = m;
            }
            else{
                r = m;
            }
        }
        return l;
    }
    public static toDecimalNumber log(toDecimalNumber num1, toDecimalNumber base){
        if(!base.getSign() || base.getNumerator() == base.getDenominator() || !num1.getSign()){
            return null;
        }
        else{
            BigDecimal val_num = new BigDecimal(num1.getNumerator()).divide(new BigDecimal(num1.getDenominator())).setScale(10);
            BigDecimal val_base = new BigDecimal(base.getNumerator()).divide(new BigDecimal(base.getDenominator())).setScale(10);
            if(val_num.doubleValue() == Double.POSITIVE_INFINITY || val_num.doubleValue() == Double.NEGATIVE_INFINITY
                    || val_base.doubleValue() == Double.POSITIVE_INFINITY || val_base.doubleValue() == Double.NEGATIVE_INFINITY){
                return null;
            }
            else{
                return new toDecimalNumber(10, Double.toString(Math.log10(val_num.doubleValue())/Math.log10(val_base.doubleValue())));
            }
        }
    }
    public static int compare(toDecimalNumber num1, toDecimalNumber num2){
        if(!num1.getSign() && num2.getSign()){
            return -1;
        }
        else if(num1.getSign() && !num2.getSign()){
            return 1;
        }
        else if(num1.getSign() && num2.getSign()){
            return num1.getNumerator().multiply(num2.getDenominator()).compareTo(num2.getNumerator().multiply(num1.getDenominator()));
        }
        else{
            return -1 * num1.getNumerator().multiply(num2.getDenominator()).compareTo(num2.getNumerator().multiply(num1.getDenominator()));
        }
    }
}
