package controlPackage;

import java.util.Arrays;

import executeClass.fromDecimalNumber;
import executeClass.toDecimalNumber;

//Класс, контролирующий перевод чисел из одной системы в другую:
// * Проверяет числа на корректность
// * Проверяет системы счислений на корректность
// * Осуществляет перевод
public class ControlTransfer {
    public static fromDecimalNumber doTransfer(String numSysFrom, String numSysTo, String number){
        if(ControlTransfer.checkCorrectNumSys(numSysFrom) && ControlTransfer.checkCorrectNumSys(numSysTo)){
            if(ControlTransfer.checkCorrectNumber(numSysFrom, number)){
                if(ControlTransfer.typeNumber(number) == "None"){
                    return null;
                }
                else{
                    toDecimalNumber in = new toDecimalNumber(Integer.parseInt(numSysFrom), number);
                    return new fromDecimalNumber(Integer.parseInt(numSysTo), in);
                }
            }
            else{
                return null;
            }
        }
        else{
            return  null;
        }
    }
    public static boolean checkCorrectNumSys(String numSys){
        try{
            int numSYS = Integer.parseInt(numSys);
            if(numSYS >= 2 && numSYS <= 36){
                return true;
            }
            else{
                return false;
            }
        } catch (NumberFormatException e){
            return false;
        }
    }
    public static String typeNumber(String string){
        int[] arrI = new int[3];
        arrI[0] = HMeth.count(string, '.');
        arrI[1] = HMeth.count(string, '(');
        arrI[2] = HMeth.count(string, ')');
        if(arrI[0] == 0 && arrI[1] == 0 && arrI[2] == 0){
            return "Integer";
        }
        else if(arrI[0] == 1 && arrI[1] == 0 && arrI[2] == 0){
            return "Float";
        }
        else if(arrI[0] == 1 && arrI[1] == 1 && arrI[2] == 1){
            return "Period";
        }
        else{
            return "None";
        }
    }
    public static boolean checkCorrectNumber(String numSys, String number){
        if(number.charAt(0) == '-' || number.charAt(0) == '+'){
            if(number.length() == 1){
                return false;
            }
            number = number.substring(1);
        }
        int numSYS;
        try{
            numSYS = Integer.parseInt(numSys);
        } catch (NumberFormatException e){
            return false;
        }
        char[] nC = number.toCharArray();
        Arrays.sort(nC);
        String type = ControlTransfer.typeNumber(number);
        switch(type){
            case "Integer":
                if(HMeth.correctChar(nC[0], numSYS) && HMeth.correctChar(nC[nC.length - 1], numSYS)){
                    return true;
                }
                return false;
            case "Float":
                // '.' < '0' < 'A'
                if(number.charAt(0) != '.' && number.charAt(number.length() - 1) != '.'
                        && HMeth.correctChar(nC[1], numSYS) && HMeth.correctChar(nC[nC.length - 1], numSYS)){
                    return true;
                }
                return false;
            case "Period":
                // '(' < ')' < '.' < '0' < 'A'
                int ind0 = HMeth.indexChar(number, '.');
                int ind1 = HMeth.indexChar(number, '(');
                int ind2 = HMeth.indexChar(number, ')');
                if(ind0 < ind1 && ind1 + 1 < ind2 && ind0 != 0 && (ind2 + 1) == number.length()
                        && HMeth.correctChar(nC[3], numSYS) && HMeth.correctChar(nC[nC.length - 1], numSYS)){
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
}
