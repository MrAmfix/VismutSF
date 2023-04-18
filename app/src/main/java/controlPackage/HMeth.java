package controlPackage;

import android.widget.CheckBox;

import com.example.vismutsf.MathActivity;
import com.example.vismutsf.SettingsActivity;
import com.example.vismutsf.StartActivity;

import java.math.BigInteger;

import executeClass.fromDecimalNumber;
import executeClass.toDecimalNumber;

//HMeth - Helper Method
public class HMeth {
    public static int count(String arr, char find){
        int count = 0;
        for(char x : arr.toCharArray()){
            if(x == find){
                count++;
            }
        }
        return count;
    }
    public static boolean correctChar(char x, int numSys){
        if(numSys < 10){
            if(x >= '0' && x < ('0' + numSys)){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(x >= '0' && x <= '9' || x >= 'A' && x < ('A' - 10 + numSys)){
                return true;
            }
            else{
                return false;
            }
        }
    }
    public static int indexChar(String string, char find){
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == find){
                return i;
            }
        }
        return -1;
    }
    public static BigInteger algorithmEuclid(BigInteger num1, BigInteger num2){
        while(!num1.equals(BigInteger.ZERO) && !num2.equals(BigInteger.ZERO)){
            if(num1.compareTo(num2) == -1){
                num2 = num2.mod(num1);
            }
            else{
                num1 = num1.mod(num2);
            }
        }
        return num1.add(num2);
    }
    public static toDecimalNumber fromTo(fromDecimalNumber content){
        return toDecimalNumber.masterMaker(10, content.getSign(), content.getOriginalNumerator(), content.getOriginalDenominator());
    }
    public static boolean isInt(BigInteger num){
        if(num.equals(new BigInteger(Integer.toString(num.intValue())))){
            return true;
        }
        return false;
    }
    public static boolean isCanPow(String num, BigInteger degree){
        if(StartActivity.valueSettings.getBoolean("EX_MODE", false)){
            return true;
        }
        if(num.length() >= 40 && degree.compareTo(new BigInteger("9")) == 1){
            return false;
        }
        if(num.length() >= 25 && degree.compareTo(new BigInteger("49")) == 1){
            return false;
        }
        if(num.length() >= 6 && degree.compareTo(new BigInteger("199")) == 1){
            return false;
        }
        if(num.length() >= 5 && degree.compareTo(new BigInteger("499")) == 1){
            return false;
        }
        if(num.length() >= 4 && degree.compareTo(new BigInteger("1499")) == 1){
            return false;
        }
        else if(num.length() >= 3 && degree.compareTo(new BigInteger("1999")) == 1){
            return false;
        }
        else if(num.length() >= 2 && degree.compareTo(new BigInteger("2999")) == 1){
            return false;
        }
        else if(num.length() >= 1 && degree.compareTo(new BigInteger("4999")) == 1){
            return false;
        }
        else{
            return true;
        }
    }
}
