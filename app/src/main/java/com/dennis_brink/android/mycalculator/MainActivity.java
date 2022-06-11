package com.dennis_brink.android.mycalculator;

import static android.text.TextUtils.split;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
           btnDel, btnMem, btnAc, btnMr,
           btnBracketOpen, btnBracketClose, btnDev, btnPlus, btnMin, btnMulti, btnEquals, btnInvert, btnDot;

    TextView tvHistory, tvResult, tvMemory, tvLabelMemory;

    String number = null;
    int bracketopen=0;
    int bracketclosed=0;
    boolean isProduct=false;

    //DecimalFormat myDecimalFormatter = new DecimalFormat("#########.#########");

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
        tvLabelMemory = findViewById(R.id.textViewLabelMemory);

    }

    private void initListeners() {

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                numberClick(b.getText().toString());
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                numberClick(b.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                numberClick(b.getText().toString());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                numberClick(b.getText().toString());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                numberClick(b.getText().toString());
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                numberClick(b.getText().toString());
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                numberClick(b.getText().toString());
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                numberClick(b.getText().toString());
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                numberClick(b.getText().toString());
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                numberClick(b.getText().toString());
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // only one dot allowed
                if(tvResult.getText().toString().indexOf(".") > 0){
                    return;
                }
                Button b = (Button)view;
                numberClick(b.getText().toString());
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hasError()) {return;}

               Log.d("DENNIS_B", "btnDel.onClick: delete character: " + tvResult.getText().toString().substring((tvResult.getText().toString().length() - 1)));

                if(tvResult.getText().toString().substring((tvResult.getText().toString().length() - 1)).equals("(")){
                    bracketopen--;
                }
                if(tvResult.getText().toString().substring((tvResult.getText().toString().length() - 1)).equals(")")) {
                    bracketclosed--;
                }

                tvResult.setText(tvResult.getText().toString().substring(0, tvResult.getText().toString().length() - 1));
                if(tvResult.getText().toString().length()==0){
                    tvResult.setText(R.string._zero);
                    number = null;
                }
            }
        });

        btnMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hasError()) {return;}

                if(!tvResult.getText().toString().equals(R.string._zero)) {
                    tvMemory.setText(tvResult.getText().toString());
                }
            }
        });

        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = null;
                tvHistory.setText("");
                tvResult.setText(R.string._zero);
                tvMemory.setText(R.string._empty);
                tvResult.setTextColor(Color.BLACK);
                tvMemory.setTextColor(Color.BLACK);
                tvLabelMemory.setText(R.string._m);
                bracketopen=0;
                bracketclosed=0;
            }
        });

        btnMr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hasError() || tvMemory.getText().toString().equals(getString(R.string._empty))) {return;}

                Log.d("DENNIS_B", "btnMr.onClick: isProduct = " + isProduct);
                Log.d("DENNIS_B", "btnMr.onClick: number==null = " + String.valueOf(number==null));

                if(isProduct || number==null) {
                    tvResult.setText(String.format("%s", tvMemory.getText().toString())); // start new calculation
                    number = tvMemory.getText().toString();
                    isProduct = false;
                } else {
                    if (tvResult.getText().toString().equals(getString(R.string._zero))) {
                        tvResult.setText(String.format("%s", tvMemory.getText().toString()));
                    } else {
                        tvResult.setText(String.format("%s%s", tvResult.getText().toString(), tvMemory.getText().toString()));
                    }
                }
            }
        });

        btnBracketOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                bracketClick(b.getText().toString());
            }
        });

        btnBracketClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                bracketClick(b.getText().toString());
            }
        });

        btnDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                operatorClick(b.getText().toString());
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                operatorClick(b.getText().toString());
            }
        });

        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                operatorClick(b.getText().toString());
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                operatorClick(b.getText().toString());
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hasError()) {return;}

                String result="";
                tvHistory.setText(String.format("%s%s", tvHistory.getText().toString(), tvResult.getText().toString()));
                result=evaluateExpression(tvHistory.getText().toString());

                Log.d("DENNIS_B", "btnEquals.onClick: result evaluation (before split): " + result);

                String[] div_result = result.split("#");

                tvResult.setText(div_result[0]); // the first element is always there
                if(div_result.length > 1) { // only in case of error
                    tvMemory.setText(div_result[1]);
                    tvLabelMemory.setText(R.string._dtl);
                }

                if (hasError()) {return;}
                else {
                    tvResult.setTextColor(Color.BLACK);
                    tvMemory.setTextColor(Color.BLACK);
                    tvLabelMemory.setText(R.string._m);
                }

                try {
                    double tmpNumber;
                    tmpNumber = Double.parseDouble(tvResult.getText().toString());
                    if ((tmpNumber - (int) tmpNumber) == 0) { // no decimals
                        tvResult.setText(String.valueOf(Math.round(tmpNumber)));
                    } else {
                        tvResult.setText(String.valueOf(tmpNumber));
                    }
                    tvHistory.setText("");
                    isProduct = true;
                } catch(Exception e){
                    //do nothing
                }
            }
        });

        btnInvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hasError()) {return;}

                try {
                    double tmpNumber;
                    tmpNumber = Double.parseDouble(tvResult.getText().toString()) * -1;

                    if ((tmpNumber - (int) tmpNumber) == 0) { // no decimals
                        tvResult.setText(String.valueOf(Math.round(tmpNumber)));
                    } else {
                        tvResult.setText(String.valueOf(tmpNumber));
                    }
                } catch(Exception e){
                    // do nothing
                }
            }
        });

    }

    private void numberClick(String view){

        if (hasError()) {return;}

        if(isProduct || number==null){
            tvResult.setText(view); // start new calculation
            number = view;
            isProduct=false;
            Log.d("DENNIS_B", "numberClick: isProduct = " + isProduct);
        } else {
            tvResult.setText(String.format("%s%s", tvResult.getText().toString(), view));
        }
    }

    private void operatorClick(String view){

        Log.d("DENNIS_B", "operatorClick: hasError = " + hasError());

        if (hasError()) {return;}

        if(number==null) {
            Log.d("DENNIS_B", "operatorClick: number = null");
            return;
        } else {
            Log.d("DENNIS_B", "operatorClick: bracket count (open and closed) = " + bracketopen + "-" + bracketclosed );
            if(bracketclosed == bracketopen) {
                tvHistory.setText(String.format("%s%s%s", tvHistory.getText().toString(), tvResult.getText().toString(), view));
                tvResult.setText(R.string._zero);
                Log.d("DENNIS_B", "operatorClick: setting number = null");
                number = null;
            } else {
                tvResult.setText(String.format("%s%s", tvResult.getText().toString(), view));
            }
        }
    }

    private void bracketClick(String view){

        if (hasError()) {return;}

        if(number==null && view.equals(")")) {
            return;
        } else {
            if(number==null || isProduct){
                tvResult.setText(view);
                number = view;
                isProduct = false;
            } else {
                tvResult.setText(String.format("%s%s", tvResult.getText().toString(), view));
            }

            if(view.equals("(")){
                Log.d("DENNIS_B", "bracketClick: add to open +1 " + view);
                bracketopen++;
            } else {
                Log.d("DENNIS_B", "bracketClick: add to closed +1 " + view);
                bracketclosed++;
            }
        }
    }

    private boolean hasError(){

        if (tvResult.getText().toString().equals(getString(R.string._error))) {
            tvResult.setTextColor(Color.RED);
            tvMemory.setTextColor(Color.RED);
            return true;
        }
        return false;
    }

    private String evaluateExpression(String expression){

        String result="";

        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1); // turn off optimization to work with android
        Log.d("DENNIS_B", "evaluateExpression: expression " + expression);
        try {
            Scriptable scope = rhino.initStandardObjects();
            result = rhino.evaluateString(scope, expression, "JavaScript", 1, null).toString();
            Log.d("DENNIS_B", "evaluateExpression: JS eval result: " + result);
        } catch(Exception e){
            result = String.format("%s#%s", getString(R.string._error), e.getMessage());
            Log.d("DENNIS_B", "evaluateExpression: JS eval expression failure: " + result);
        } finally {
            Context.exit();
            return result;
        }
    }
}