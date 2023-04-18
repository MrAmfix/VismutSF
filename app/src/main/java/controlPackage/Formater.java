package controlPackage;

import android.widget.Toast;

import com.example.vismutsf.SettingsActivity;
import com.example.vismutsf.StartActivity;
import com.example.vismutsf.TransferActivity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import executeClass.fromDecimalNumber;

public class Formater {
    public static String getFormatText(fromDecimalNumber content){
        boolean a = false;
        try{
            a = SettingsActivity.getOutPut();
        } catch (Exception e){
            a = false;
        }
        if(a){
            int sign = StartActivity.valueSettings.getInt("EX_COUNT", 1);
            String Result = "";
            if(!content.getSign()){
                Result += "- ";
            }
            if(content.getOriginalNumerator().equals(BigInteger.ZERO)){
                Result += "0";
            }
            else{
                Result += content.getInteger();
            }
            if(!(content.getOriginalDenominator().equals(BigInteger.ONE))){
                Result += ("," + Formater.lP(content.getFloatEX(),content.getNumSys(), sign));

            }
            return Result;
        }
        else{
            String Result = "";
            if(!content.getSign()){
                Result += "- ";
            }
            if(content.getOriginalNumerator().equals(BigInteger.ZERO)){
                Result += "0";
            }
            else{
                Result += content.getInteger();
            }
            if(!(content.getOriginalDenominator().equals(BigInteger.ONE))){
                Result += (" " + content.getFloat() + " / " + content.getDenominator());
            }
            return Result;
        }
    }
    public static String lP(BigDecimal res, int numsys, int countnum){
        String resix = "";
        float fif = Float.parseFloat(res.toString());
        for(int i = 0; i < countnum; i++){
            fif = fif * numsys;
            if((int)fif >= 10){
                resix += (char)('A' - 10 + (int)fif);
            }
            else{
                resix += (int)fif;
            }
            fif = fif - (int)fif;
        }
        return resix;
    }
}
