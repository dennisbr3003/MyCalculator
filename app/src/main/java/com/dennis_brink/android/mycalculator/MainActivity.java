package com.dennis_brink.android.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
           btnDel, btnMem, btnAc, btnMr,
           btnBracketOpen, btnBracketClose, btnDev, btnPlus, btnMin, btnMulti, btnEquals, btnInvert, btnDot;

    TextView tvHistory, tvResult, tvMemory;

    String number = null;
    int bracketopen=0;
    int bracketclosed=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initObjects();
        initListeners();
        setupLogo();
    }

    private void setupLogo(){
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.mc_logo_padding_main);
        getSupportActionBar().setTitle(getString(R.string._appname));
        //getSupportActionBar().setSubtitle(getString(null));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }

    private void initObjects(){

        btn0 = findViewById(R.id.btnZero);
        btn1 = findViewById(R.id.btnOne);
        btn2 = findViewById(R.id.btnTwo);
        btn3 = findViewById(R.id.btnThree);
        btn4 = findViewById(R.id.btnFour);
        btn5 = findViewById(R.id.btnFive);
        btn6 = findViewById(R.id.btnSix);
        btn7 = findViewById(R.id.btnSeven);
        btn8 = findViewById(R.id.btnEight);
        btn9 = findViewById(R.id.btnNine);

        btnDel = findViewById(R.id.btnDel);
        btnMem = findViewById(R.id.btnMemoryAdd);
        btnAc = findViewById(R.id.btnAC);
        btnMr = findViewById(R.id.btnMemoryUse);

        btnBracketOpen = findViewById(R.id.btnBracketOpen);
        btnBracketClose = findViewById(R.id.btnBracketClose);
        btnDev = findViewById(R.id.btnDevide);
        btnPlus = findViewById(R.id.btnPlus);
        btnMin = findViewById(R.id.btnMinus);
        btnMulti = findViewById(R.id.btnMultiply);
        btnEquals = findViewById(R.id.btnEquals);
        btnInvert = findViewById(R.id.btnInvert);
        btnDot = findViewById(R.id.btnDot);

        tvHistory = findViewById(R.id.textViewHistory);
        tvResult = findViewById(R.id.textViewResult);
        tvMemory = findViewById(R.id.textViewMemory);

    }

    private void initListeners() {

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("9");
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnMr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnBracketOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bracketClick("(");
            }
        });

        btnBracketClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bracketClick(")");
            }
        });

        btnDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorClick("/");
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorClick("+");
            }
        });

        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorClick("-");
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operatorClick("*");
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvHistory.setText(String.format("%s%s", tvHistory.getText().toString(), tvResult.getText().toString()));
                tvResult.setText(evaluateExpression(tvHistory.getText().toString()));
                try {
                    double tmpNumber;
                    tmpNumber = Double.parseDouble(tvResult.getText().toString());
                    if ((tmpNumber - (int) tmpNumber) == 0) { // no decimals
                        tvResult.setText(String.valueOf(Math.round(tmpNumber)));
                    } else {
                        tvResult.setText(String.valueOf(tmpNumber));
                    }
                    tvHistory.setText("");
                } catch(Exception e){
                    //do nothing
                }
                //number = null;
            }
        });

        btnInvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Double tmpNumber;
                    tmpNumber = Double.parseDouble(tvResult.getText().toString()) * -1;
                    tvResult.setText(String.valueOf(tmpNumber));
                } catch(Exception e){
                    // do nothing
                }
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick(".");
            }
        });


    }

    private void numberClick(String view){
        if(number==null) {
            tvResult.setText(view);
            number = view;
        } else {
            tvResult.setText(String.format("%s%s", tvResult.getText().toString(), view));
        }
    }

    private void operatorClick(String view){
        if(number==null) {
            return;
        } else {
            if(bracketclosed == bracketopen) {
                tvHistory.setText(String.format("%s%s%s", tvHistory.getText().toString(), tvResult.getText().toString(), view));
                tvResult.setText("0");
                number = null;
            } else {
                tvResult.setText(String.format("%s%s", tvResult.getText().toString(), view));
            }
        }
    }

    private void bracketClick(String view){
        if(number==null && view == ")") {
            return;
        } else {
            if(number==null){
                tvResult.setText(view);
                number = view;
            } else {
                tvResult.setText(String.format("%s%s", tvResult.getText().toString(), view));
            }
            if(view == "("){
                bracketopen++;
            } else {
                bracketclosed++;
            }
        }
    }


    private void functionClick(String view){
        if(number==null) {
            tvResult.setText(view);
            number = view;
        } else {
            tvResult.setText(String.format("%s%s", tvResult.getText().toString(), view));
        }
    }


    private String evaluateExpression(String expression){

        String result="";

        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1); // turn off optimization to work with android

        try {
            Scriptable scope = rhino.initStandardObjects();
            result = rhino.evaluateString(scope, expression, "JavaScript", 1, null).toString();
            Log.d("DENNIS_B", "JS eval result: " + result);
        } catch(Exception e){
            Log.d("DENNIS_B", "JS eval expression failure: " + e.getMessage());
            result = String.format("Error in expression");
        } finally {
            Context.exit();
            return result;
        }
    }
}