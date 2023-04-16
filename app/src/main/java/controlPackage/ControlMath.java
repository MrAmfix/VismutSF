package controlPackage;

import executeClass.fromDecimalNumber;
import executeClass.toDecimalNumber;

public class ControlMath {
    public static class DataPath{
        public String num1;
        public String num2;
        public String toSys;
        public String fromSys;

        public DataPath(String num1, String num2, String fromSys, String toSys){
            this.num1 = num1;
            this.num2 = num2;
            this.toSys = toSys;
            this.fromSys = fromSys;
        }
    }
    public static fromDecimalNumber calculate(DataPath content, String typeOperation){
        if(checkAllCorrectly(content.num1, content.num2, content.fromSys, content.toSys)){
            try{
                toDecimalNumber in1 = HMeth.fromTo(ControlTransfer.doTransfer(content.fromSys, content.toSys, content.num1));
                toDecimalNumber in2 = HMeth.fromTo(ControlTransfer.doTransfer(content.fromSys, content.toSys, content.num2));
                if(in1 == null || in2 == null){
                    return null;
                }
                switch (typeOperation){
                    case "add":
                        return new fromDecimalNumber(Integer.parseInt(content.toSys), JMath.add(in1, in2));
                    case "subtract":
                        return new fromDecimalNumber(Integer.parseInt(content.toSys), JMath.subtract(in1, in2));
                    case "multiply":
                        return new fromDecimalNumber(Integer.parseInt(content.toSys), JMath.multiply(in1, in2));
                    case "divide":
                        return new fromDecimalNumber(Integer.parseInt(content.toSys), JMath.divide(in1, in2));
                    case "pow":
                        toDecimalNumber powC = JMath.pow(in1, in2);
                        if(powC == null){
                            return null;
                        }
                        else{
                            return new fromDecimalNumber(Integer.parseInt(content.toSys), powC);
                        }
//                    case "sqrt":
//                        toDecimalNumber sqrtC = JMath.sqrt(in1, in2);
//                        if(sqrtC == null){
//                            return null;
//                        }
//                        else{
//                            return new fromDecimalNumber(Integer.parseInt(content.toSys), sqrtC);
//                        }
//                    case "log":
//                        toDecimalNumber logC = JMath.log(in1, in2);
//                        if(logC == null){
//                            return null;
//                        }
//                        else{
//                            return new fromDecimalNumber(Integer.parseInt(content.toSys), logC);
//                        }
                    default:
                        return null;
                }
            } catch (Exception e){
                return null;
            }
        }
        else{
            return null;
        }
    }

    public static boolean checkAllCorrectly(String num1, String num2, String fromSys, String toSys){
        if(ControlTransfer.checkCorrectNumSys(fromSys) && ControlTransfer.checkCorrectNumSys(toSys) && ControlTransfer
                .checkCorrectNumber(fromSys, num1) && ControlTransfer.checkCorrectNumber(fromSys, num2)){
            return true;
        }
        return false;
    }
}
