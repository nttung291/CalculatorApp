package com.example.nttungpc.calculatorapp;

import android.content.DialogInterface;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    private static final String TAG = MainActivity.class.toString();
    private TextView tvUp;
    private TextView tvDown;
    private double num1;
    private double num2;
    private static String s = "0";
    private boolean isNumber = true;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##########");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUp = (TextView) findViewById(R.id.tv_result);
        tvDown = (TextView) findViewById(R.id.tv_bfresult);
        tvUp.setText("0");
    }


    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        if (button!=null){
            switch (view.getId()){
                case R.id.bt_num:
                    isNumber = true;
                    tvDown.setText(tvDown.getText() + (String) button.getText());
                    break;
                case R.id.bt_bh:
                    computeCalculation();
                    break;
                case R.id.bt_math:
                    isNumber = false;
                    if (button.getText().equals("+")){
                        computeCalculation();
                        s = "+";
                    }
                    else if (button.getText().equals("-")){
                        computeCalculation();
                        s = "-";
                    }
                    else if (button.getText().equals("x")){
                        computeCalculation();
                        s = "x";
                    }
                    else if (button.getText().equals(":")) {
                        computeCalculation();
                        s = ":";
                    }
                    else if (button.getText().equals("√")) {
                        computeCalculation();
                        s = "√";
                    }
                    tvUp.setText(decimalFormat.format(num1));
                    tvDown.setText("");
                    break;
                case R.id.bt_clean:
                    isNumber = true;
                    num1 = 0;
                    num2 = 0;
                    s = "0";
                    tvUp.setText("0");
                    tvDown.setText("");
                    break;
                case R.id.bt_c:
                    String newString = tvDown.getText().toString();
                    if (!newString.equals("")){
                        newString = newString.substring(0,tvDown.getText().toString().length()-1);
                    }
                    tvDown.setText(newString);
                    Log.d(TAG, "onClick: " + newString);
                    break;
                case R.id.bt_dot:
                    tvDown.setText(tvDown.getText()+".");
                    break;
            }
        }
    }
    private void computeCalculation(){
        if (!tvDown.getText().equals("")){
            num1 = Double.parseDouble(tvUp.getText().toString());
            num2 = Double.parseDouble(tvDown.getText().toString());
            if (num1 == 0 && !isNumber && !s.equals("√")){
                num1 = num2;
                tvUp.setText(decimalFormat.format(num1));
                tvDown.setText("");
            } else{
                if (s.equals("+")){
                    num1+=num2;
                }
                else if (s.equals("-")){
                    num1-=num2;
                }
                else if (s.equals("x")){
                    num1 = num1*num2;
                }
                else if (s.equals(":") && num2 !=0) {
                    num1 = num1/num2;
                }
                else if (s.equals("√")) {
                    num1 = Math.sqrt(num2);
                }else if (s.equals("0")){
                    num1 = num2;
                }
                tvUp.setText(decimalFormat.format(num1));
                tvDown.setText("");
            }
        }
        s = "0";
    }
}
