package com.example.vismutsf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

import controlPackage.ControlMath;
import executeClass.fromDecimalNumber;

public class MathActivity extends AppCompatActivity {
    String numb1, numb2, fromSys, toSys;
    public String[] AllowedSys = {"2   ","3   ","4   ","5   ","6   ","7   ","8   ","9   ","10  ","11  ","12  ","13  ","14  ","15  ","16  ","17  "
            ,"18  ","19  ","20  ","21  ","22  ","23  "
            ,"24  ","25  ","26  ","27  ","28  ","29  ","30  ","31  ","32  ","33  ","34  ","35  ","36  "};
    public int SelectedNumber = 1;
    TextView result;
    Spinner fromsys, tosys;
    TextView numMain1, numMain2;
    Button add, subtract, multiply, divide, info, selecter1, selecter2;
    Button pow; //, sqrt, log;
    // Keyboard Block
    Button num0, num1,num2, num3, num4, num5, num6, num7, num8, num9, numQ,numW, numE,numR, numT,numY, numU,numI, numO,numP;
    Button numA, numS, numD, numF, numG, numH, numJ, numK, numL, numZ, numX, numC, numV, numB, numN, numM, numPoint, numleftscob, numrightscob, numback;
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
        result = findViewById(R.id.resultmath);
        numMain1 = findViewById(R.id.math_firstnum);
        numMain2 = findViewById(R.id.math_secondnum);
        fromsys = findViewById(R.id.math_fromsys);
        tosys = findViewById(R.id.math_tosys);
        selecter1 = findViewById(R.id.selecternum1);
        selecter2 = findViewById(R.id.selecternum2);
        add = findViewById(R.id.button_add);
        subtract = findViewById(R.id.button_subtract);
        multiply = findViewById(R.id.button_multiply);
        divide = findViewById(R.id.button_divide);
        pow = findViewById(R.id.button_pow);
//      sqrt = findViewById(R.id.button_sqrt);
//      log = findViewById(R.id.button_log);
        info = findViewById(R.id.button_info_special);
        // Keyboard Block Start
        num0 = findViewById(R.id.num0); num1 = findViewById(R.id.num1); num2 = findViewById(R.id.num2); num3 = findViewById(R.id.num3); num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5); num6 = findViewById(R.id.num6); num7 = findViewById(R.id.num7); num8 = findViewById(R.id.num8); num9 = findViewById(R.id.num9);
        numQ = findViewById(R.id.numQ); numW = findViewById(R.id.numW); numE = findViewById(R.id.numE); numR = findViewById(R.id.numR); numT = findViewById(R.id.numT);
        numY = findViewById(R.id.numY); numU = findViewById(R.id.numU); numI = findViewById(R.id.numI); numO = findViewById(R.id.numO); numP = findViewById(R.id.numP);
        numA = findViewById(R.id.numA); numS = findViewById(R.id.numS); numD = findViewById(R.id.numD); numF = findViewById(R.id.numF); numG = findViewById(R.id.numG);
        numH = findViewById(R.id.numH); numJ = findViewById(R.id.numJ); numK = findViewById(R.id.numK); numL = findViewById(R.id.numL); numPoint = findViewById(R.id.numPoint);
        numZ = findViewById(R.id.numZ); numX = findViewById(R.id.numX); numC = findViewById(R.id.numC); numV = findViewById(R.id.numV); numB = findViewById(R.id.numB);
        numN = findViewById(R.id.numN); numM = findViewById(R.id.numM); numleftscob = findViewById(R.id.numleftscob);
        numrightscob = findViewById(R.id.numrightscob); numback = findViewById(R.id.numback);
        // Keyboard Block End
        ArrayAdapter<String> AllowedSysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, AllowedSys);
        AllowedSysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromsys.setAdapter(AllowedSysAdapter);
        tosys.setAdapter(AllowedSysAdapter);

        info.setOnClickListener(new View.OnClickListener() {
            // ПЕРЕПИСАТЬ ИНСТРУКЦИЮ ПОДРОБНЕЕ В НОВОЙ АКТИВНОСТИ!
            @Override
            public void onClick(View v) {
//                String info = "Особенности работы:\n1) Возведение в степень - второе число должно быть целым,\n" +
//                        "Дано в 10-чной системе и меньше 50\n" +
//                        "2) Извлечение корня - Первое число должно быть целым, не превышающее 2^63 - 1\nВторое число не используется" +
//                        "3) Вычисление логарифма - Первое число должно быть целым\n" +
//                        "Второе должно быть целым, больше 1 и меньше 2^50";
//                Toast.makeText(MathActivity.this, info, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MathActivity.this, InstructionActivity.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numb1 = numMain1.getText().toString();
                numb2 = numMain2.getText().toString();
                fromSys = fromsys.getSelectedItem().toString().trim();
                toSys = tosys.getSelectedItem().toString().trim();
                if(numb1.isEmpty() || numb2.isEmpty() || fromSys.isEmpty() || toSys.isEmpty()){
                    Toast.makeText(MathActivity.this, "Вы не ввели все данные", Toast.LENGTH_SHORT).show();
                    result.setText("");
                }
                else{
                    fromDecimalNumber content = ControlMath.calculate(new ControlMath.DataPath(numb1, numb2, fromSys, toSys), "add");
                    if(content == null){
                        Toast.makeText(MathActivity.this, "Данные введены неправильно.", Toast.LENGTH_SHORT).show();
                        result.setText("");
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
                        result.setText(Result);
                    }
                }
            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numb1 = numMain1.getText().toString();
                numb2 = numMain2.getText().toString();
                fromSys = fromsys.getSelectedItem().toString().trim();
                toSys = tosys.getSelectedItem().toString().trim();
                if(numb1.isEmpty() || numb2.isEmpty() || fromSys.isEmpty() || toSys.isEmpty()){
                    Toast.makeText(MathActivity.this, "Вы не ввели все данные", Toast.LENGTH_SHORT).show();
                    result.setText("");
                }
                else{
                    fromDecimalNumber content = ControlMath.calculate(new ControlMath.DataPath(numb1, numb2, fromSys, toSys), "subtract");
                    if(content == null){
                        Toast.makeText(MathActivity.this, "Данные введены неправильно.", Toast.LENGTH_SHORT).show();
                        result.setText("");
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
                        result.setText(Result);
                    }
                }
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numb1 = numMain1.getText().toString();
                numb2 = numMain2.getText().toString();
                fromSys = fromsys.getSelectedItem().toString().trim();
                toSys = tosys.getSelectedItem().toString().trim();
                if(numb1.isEmpty() || numb2.isEmpty() || fromSys.isEmpty() || toSys.isEmpty()){
                    Toast.makeText(MathActivity.this, "Вы не ввели все данные", Toast.LENGTH_SHORT).show();
                    result.setText("");
                }
                else{
                    fromDecimalNumber content = ControlMath.calculate(new ControlMath.DataPath(numb1, numb2, fromSys, toSys), "multiply");
                    if(content == null){
                        Toast.makeText(MathActivity.this, "Данные введены неправильно.", Toast.LENGTH_SHORT).show();
                        result.setText("");
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
                        result.setText(Result);
                    }
                }
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numb1 = numMain1.getText().toString();
                numb2 = numMain2.getText().toString();
                fromSys = fromsys.getSelectedItem().toString().trim();
                toSys = tosys.getSelectedItem().toString().trim();
                if(numb1.isEmpty() || numb2.isEmpty() || fromSys.isEmpty() || toSys.isEmpty()){
                    Toast.makeText(MathActivity.this, "Вы не ввели все данные", Toast.LENGTH_SHORT).show();
                    result.setText("");
                }
                else{
                    fromDecimalNumber content = ControlMath.calculate(new ControlMath.DataPath(numb1, numb2, fromSys, toSys), "divide");
                    if(content == null){
                        Toast.makeText(MathActivity.this, "Данные введены неправильно.", Toast.LENGTH_SHORT).show();
                        result.setText("");
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
                        result.setText(Result);
                    }
                }
            }
        });
        pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numb1 = numMain1.getText().toString();
                numb2 = numMain2.getText().toString();
                fromSys = fromsys.getSelectedItem().toString().trim();
                toSys = tosys.getSelectedItem().toString().trim();
                if(numb1.isEmpty() || numb2.isEmpty() || fromSys.isEmpty() || toSys.isEmpty()){
                    Toast.makeText(MathActivity.this, "Вы не ввели все данные", Toast.LENGTH_SHORT).show();
                    result.setText("");
                }
                else{
                    fromDecimalNumber content = ControlMath.calculate(new ControlMath.DataPath(numb1, numb2, fromSys, toSys), "pow");
                    if(content == null){
                        Toast.makeText(MathActivity.this, "Данные введены неправильно.", Toast.LENGTH_SHORT).show();
                        result.setText("");
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
                        result.setText(Result);
                    }
                }
            }
        });
        selecter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectedNumber = 1;
            }
        });
        selecter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectedNumber = 2;
            }
        });
//        sqrt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                numb1 = num1.getText().toString();
//                numb2 = num2.getText().toString();
//                fromSys = fromsys.getText().toString();
//                toSys = tosys.getText().toString();
//                if(numb1.isEmpty() || numb2.isEmpty() || fromSys.isEmpty() || toSys.isEmpty()){
//                    Toast.makeText(MathActivity.this, "Вы не ввели все данные", Toast.LENGTH_SHORT).show();
//                    result.setText("");
//                }
//                else{
//                    fromDecimalNumber content = ControlMath.calculate(new ControlMath.DataPath(numb1, numb2, fromSys, toSys), "sqrt");
//                    if(content == null){
//                        Toast.makeText(MathActivity.this, "Произошла ошибка.", Toast.LENGTH_SHORT).show();
//                        result.setText("");
//                    }
//                    else{
//                        String Result = "";
//                        if(!content.getSign()){
//                            Result += "- ";
//                        }
//                        if(content.getOriginalNumerator().equals(BigInteger.ZERO)){
//                            Result += "0";
//                        }
//                        else{
//                            Result += content.getInteger();
//                        }
//                        if(!(content.getOriginalDenominator().equals(BigInteger.ONE))){
//                            Result += (" " + content.getFloat() + " / " + content.getDenominator());
//                        }
//                        result.setText(Result);
//                    }
//                }
//            }
//        });
//        log.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                numb1 = num1.getText().toString();
//                numb2 = num2.getText().toString();
//                fromSys = fromsys.getText().toString();
//                toSys = tosys.getText().toString();
//                if(numb1.isEmpty() || numb2.isEmpty() || fromSys.isEmpty() || toSys.isEmpty()){
//                    Toast.makeText(MathActivity.this, "Вы не ввели все данные", Toast.LENGTH_SHORT).show();
//                    result.setText("");
//                }
//                else{
//                    fromDecimalNumber content = ControlMath.calculate(new ControlMath.DataPath(numb1, numb2, fromSys, toSys), "log");
//                    if(content == null){
//                        Toast.makeText(MathActivity.this, "Произошла ошибка.", Toast.LENGTH_SHORT).show();
//                        result.setText("");
//                    }
//                    else{
//                        String Result = "";
//                        if(!content.getSign()){
//                            Result += "- ";
//                        }
//                        if(content.getOriginalNumerator().equals(BigInteger.ZERO)){
//                            Result += "0";
//                        }
//                        else{
//                            Result += content.getInteger();
//                        }
//                        if(!(content.getOriginalDenominator().equals(BigInteger.ONE))){
//                            Result += (" " + content.getFloat() + " / " + content.getDenominator());
//                        }
//                        result.setText(Result);
//                    }
//                }
//            }
//        });
        //Keyboard Block Start
        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "0"); }
                else { numMain2.setText( numMain2.getText().toString() + "0"); }
            }
        });
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "1"); }
                else { numMain2.setText( numMain2.getText().toString() + "1"); }
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "2"); }
                else { numMain2.setText( numMain2.getText().toString() + "2"); }
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "3"); }
                else { numMain2.setText( numMain2.getText().toString() + "3"); }
            }
        });
        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "4"); }
                else { numMain2.setText( numMain2.getText().toString() + "4"); }
            }
        });
        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "5"); }
                else { numMain2.setText( numMain2.getText().toString() + "5"); }
            }
        });
        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "6"); }
                else { numMain2.setText( numMain2.getText().toString() + "6"); }
            }
        });
        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "7"); }
                else { numMain2.setText( numMain2.getText().toString() + "7"); }
            }
        });
        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "8"); }
                else { numMain2.setText( numMain2.getText().toString() + "8"); }
            }
        });
        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "9"); }
                else { numMain2.setText( numMain2.getText().toString() + "9"); }
            }
        });
        numQ.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "Q");} else{ numMain2.setText(numMain2.getText().toString() + "Q");}}});
        numW.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "W");} else{ numMain2.setText(numMain2.getText().toString() + "W");}}});
        numE.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "E");} else{ numMain2.setText(numMain2.getText().toString() + "E");}}});
        numR.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "R");} else{ numMain2.setText(numMain2.getText().toString() + "R");}}});
        numT.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "T");} else{ numMain2.setText(numMain2.getText().toString() + "T");}}});
        numY.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "Y");} else{ numMain2.setText(numMain2.getText().toString() + "Y");}}});
        numU.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "U");} else{ numMain2.setText(numMain2.getText().toString() + "U");}}});
        numI.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "I");} else{ numMain2.setText(numMain2.getText().toString() + "I");}}});
        numO.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "O");} else{ numMain2.setText(numMain2.getText().toString() + "O");}}});
        numP.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "P");} else{ numMain2.setText(numMain2.getText().toString() + "P");}}});
        numA.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "A");} else{ numMain2.setText(numMain2.getText().toString() + "A");}}});
        numS.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "S");} else{ numMain2.setText(numMain2.getText().toString() + "S");}}});
        numD.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "D");} else{ numMain2.setText(numMain2.getText().toString() + "D");}}});
        numF.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "F");} else{ numMain2.setText(numMain2.getText().toString() + "F");}}});
        numG.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "G");} else{ numMain2.setText(numMain2.getText().toString() + "G");}}});
        numH.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "H");} else{ numMain2.setText(numMain2.getText().toString() + "H");}}});
        numJ.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "J");} else{ numMain2.setText(numMain2.getText().toString() + "J");}}});
        numK.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "K");} else{ numMain2.setText(numMain2.getText().toString() + "K");}}});
        numL.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "L");} else{ numMain2.setText(numMain2.getText().toString() + "L");}}});
        numZ.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "Z");} else{ numMain2.setText(numMain2.getText().toString() + "Z");}}});
        numX.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "X");} else{ numMain2.setText(numMain2.getText().toString() + "X");}}});
        numC.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "C");} else{ numMain2.setText(numMain2.getText().toString() + "C");}}});
        numV.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "V");} else{ numMain2.setText(numMain2.getText().toString() + "V");}}});
        numB.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "B");} else{ numMain2.setText(numMain2.getText().toString() + "B");}}});
        numN.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "N");} else{ numMain2.setText(numMain2.getText().toString() + "N");}}});
        numPoint.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + ".");} else{ numMain2.setText(numMain2.getText().toString() + ".");}}});
        numM.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "M");} else{ numMain2.setText(numMain2.getText().toString() + "M");}}});
        numleftscob.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + "(");} else{ numMain2.setText(numMain2.getText().toString() + "(");}}});
        numrightscob.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { if(SelectedNumber == 1) { numMain1.setText(numMain1.getText().toString() + ")");} else{ numMain2.setText(numMain2.getText().toString() + ")");}}});
        numback.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {
            if(SelectedNumber == 1){
                if(numMain1.getText().toString().length() != 0){
                    numMain1.setText(numMain1.getText().toString().substring(0,numMain1.getText().toString().length() - 1));
                }
            }
            else{
                if(numMain2.getText().toString().length() != 0){
                    numMain2.setText(numMain2.getText().toString().substring(0,numMain2.getText().toString().length() - 1));
                }
            }
        }});
        //Keyboard Block End
    }
}