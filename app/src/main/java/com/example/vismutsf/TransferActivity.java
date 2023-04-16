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

import controlPackage.ControlTransfer;
import executeClass.fromDecimalNumber;

public class TransferActivity extends AppCompatActivity {
    TextView Result, Number;
    Spinner NumSysFrom, NumSysTo;
    Button Press, Info;
    // Keyboard Block
    Button num0, num1,num2, num3, num4, num5, num6, num7, num8, num9, numQ,numW, numE,numR, numT,numY, numU,numI, numO,numP;
    Button numA, numS, numD, numF, numG, numH, numJ, numK, numL, numZ, numX, numC, numV, numB, numN, numM, numPoint, numleftscob, numrightscob, numback;
    public String[] AllowedSys = {"2   ","3   ","4   ","5   ","6   ","7   ","8   ","9   ","10  ","11  ","12  ","13  ","14  ","15  ","16  ","17  "
            ,"18  ","19  ","20  ","21  ","22  ","23  "
            ,"24  ","25  ","26  ","27  ","28  ","29  ","30  ","31  ","32  ","33  ","34  ","35  ","36  "};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        Result = findViewById(R.id.textontop);
        Number = findViewById(R.id.number);
        NumSysFrom = findViewById(R.id.numsysfrom);
        NumSysTo = findViewById(R.id.numsysto);
        Press = findViewById(R.id.buttongo);
        Info = findViewById(R.id.button_info_special_t);
        ArrayAdapter<String> AllowedSysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, AllowedSys);
        AllowedSysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NumSysFrom.setAdapter(AllowedSysAdapter);
        NumSysTo.setAdapter(AllowedSysAdapter);
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
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransferActivity.this, InstructionActivity.class);
                startActivity(intent);
            }
        });

        Press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numFrom = NumSysFrom.getSelectedItem().toString().trim();
                String numTo = NumSysTo.getSelectedItem().toString().trim();
                String num = Number.getText().toString();
                if(numFrom.isEmpty() || numTo.isEmpty() || num.isEmpty()){
                    Toast.makeText(TransferActivity.this, "Вы не ввели все данные.", Toast.LENGTH_SHORT).show();
                    Result.setText("");
                }
                else{
                    fromDecimalNumber in = ControlTransfer.doTransfer(numFrom, numTo, num);
                    if(in == null){
                        Toast.makeText(TransferActivity.this, "Данные введены неправильно.", Toast.LENGTH_SHORT).show();
                        Result.setText("");
                    }
                    else{
                        String result = "";
                        if(!in.getSign()){
                            result += "- ";
                        }
                        if(in.getOriginalNumerator().equals(BigInteger.ZERO)){
                            result += "0";
                        }
                        else{
                            result += in.getInteger();
                        }
                        if(!(in.getOriginalDenominator().equals(BigInteger.ONE))){
                            result += (" " + in.getFloat() + " / " + in.getDenominator());
                        }
                        Result.setText(result);
                    }
                }
            }
        });

        //Keyboard Block Start
        num0.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "0");}});
        num1.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "1");}});
        num2.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "2");}});
        num3.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "3");}});
        num4.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "4");}});
        num5.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "5");}});
        num6.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "6");}});
        num7.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "7");}});
        num8.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "8");}});
        num9.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "9");}});
        numQ.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "Q");}});
        numW.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "W");}});
        numE.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "E");}});
        numR.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "R");}});
        numT.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "T");}});
        numY.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "Y");}});
        numU.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "U");}});
        numI.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "I");}});
        numO.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "O");}});
        numP.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "P");}});
        numA.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "A");}});
        numS.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "S");}});
        numD.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "D");}});
        numF.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "F");}});
        numG.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "G");}});
        numH.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "H");}});
        numJ.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "J");}});
        numK.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "K");}});
        numL.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "L");}});
        numPoint.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + ".");}});
        numZ.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "Z");}});
        numX.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "X");}});
        numC.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "C");}});
        numV.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "V");}});
        numB.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "B");}});
        numN.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "N");}});
        numM.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "M");}});
        numleftscob.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + "(");}});
        numrightscob.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {Number.setText(Number.getText().toString() + ")");}});
        numback.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) {
            if(Number.getText().toString().length() != 0){
                Number.setText(Number.getText().toString().substring(0, Number.getText().toString().length() - 1));
            }
        }});
        //Keyboard Block End
    }
}