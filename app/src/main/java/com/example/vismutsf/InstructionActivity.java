package com.example.vismutsf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class InstructionActivity extends AppCompatActivity {
    TextView scroller;
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        scroller = findViewById(R.id.textscroller);
        String text = "Перевод чисел\n\nС помощью клавиатуры в приложении введите число, далее выберите исходную систему счисления в поле \"Из\" и искомую систему счисления" +
                " в поле \"В\".\nНажмите кнопку \"Выполнить перевод\".\n\n\nМатематические операции\n\n1) Операции сложения, вычитания, умножения и деления:\nС помощью" +
                " клавиатуры в приложения введите первое и второе число (чтобы сменить вводимое число, воспользуйтесь кнопками \"Первое число\" и \"Второе число\"." +
                " Затем выберите исходную систему счисления и искомую, нажмите кнопку необходимо действия.\n\n2) Операция возведения в степень:\nПервое число укажите в искомой системе" +
                " счисления, а второе (степень) укажите в десятичной системе счисления.\nПРИМЕЧАНИЕ: для предотвращения зависания телефона на возведение в степень установлены" +
                " ограничения в зависимости от длины числа. Выберите \"Экспериментальный режим\", чтобы отключить ограничения.\n\n\n\n";
        scroller.setText(text);
    }
}