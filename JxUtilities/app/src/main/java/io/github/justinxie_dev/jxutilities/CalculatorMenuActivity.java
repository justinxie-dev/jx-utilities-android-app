package io.github.justinxie_dev.jxutilities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class CalculatorMenuActivity extends AppCompatActivity {

    private TextView calcMenuInputText;
    private Button addOpButton;
    private Button subOpButton;
    private Button mulOpButton;
    private Button divOpButton;
    private Button eqOpButton;
    private Button cal0Button;
    private Button cal1Button;
    private Button cal2Button;
    private Button cal3Button;
    private Button cal4Button;
    private Button cal5Button;
    private Button cal6Button;
    private Button cal7Button;
    private Button cal8Button;
    private Button cal9Button;
    private Button calcCheckYourAnsButton;
    private Button calcGiveMeAnsButton;
    private ImageButton delEqButton;
    private TextView calcOutputMenu;

    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_menu);

        calcMenuInputText = (TextView) findViewById(R.id.calculatorMenuInputText);
        addOpButton = (Button) findViewById(R.id.addOperatorButton);
        subOpButton = (Button) findViewById(R.id.subtractOperatorButton);
        mulOpButton = (Button) findViewById(R.id.multiplyOperatorButton);
        divOpButton = (Button) findViewById(R.id.divideOperatorButton);
        eqOpButton = (Button) findViewById(R.id.equalOperatorButton);
        cal0Button = (Button) findViewById(R.id.calc0Button);
        cal1Button = (Button) findViewById(R.id.calc1Button);
        cal2Button = (Button) findViewById(R.id.calc2Button);
        cal3Button = (Button) findViewById(R.id.calc3Button);
        cal4Button = (Button) findViewById(R.id.calc4Button);
        cal5Button = (Button) findViewById(R.id.calc5Button);
        cal6Button = (Button) findViewById(R.id.calc6Button);
        cal7Button = (Button) findViewById(R.id.calc7Button);
        cal8Button = (Button) findViewById(R.id.calc8Button);
        cal9Button = (Button) findViewById(R.id.calc9Button);
        calcCheckYourAnsButton = (Button) findViewById(R.id.calcCheckYourAnswerButton);
        calcGiveMeAnsButton = (Button) findViewById(R.id.calcGiveMeAnswerButton);
        delEqButton = (ImageButton) findViewById(R.id.deleteEquationButton);
        calcOutputMenu = (TextView) findViewById(R.id.calculatorOutputMenu);

        addOpButton.setBackgroundColor(Color.BLACK);
        subOpButton.setBackgroundColor(Color.BLACK);
        mulOpButton.setBackgroundColor(Color.BLACK);
        divOpButton.setBackgroundColor(Color.BLACK);
        eqOpButton.setBackgroundColor(Color.BLACK);
        cal0Button.setBackgroundColor(Color.RED);
        cal1Button.setBackgroundColor(Color.RED);
        cal2Button.setBackgroundColor(Color.RED);
        cal3Button.setBackgroundColor(Color.RED);
        cal4Button.setBackgroundColor(Color.RED);
        cal5Button.setBackgroundColor(Color.RED);
        cal6Button.setBackgroundColor(Color.RED);
        cal7Button.setBackgroundColor(Color.RED);
        cal8Button.setBackgroundColor(Color.RED);
        cal9Button.setBackgroundColor(Color.RED);
        calcCheckYourAnsButton.setBackgroundColor(Color.GREEN);
        calcGiveMeAnsButton.setBackgroundColor(Color.GREEN);

        // Set Action Bar color
        // Source: https://www.geeksforgeeks.org/how-to-change-the-color-of-action-bar-in-an-android-app/
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#800000"));
        actionBar.setBackgroundDrawable(colorDrawable);
    }

    public void addOperatorButtonClick(View view) {
        sb.append("+");
        calcMenuInputText.setText(sb.toString());
    }

    public void subtractOperatorButtonClick(View view) {
        sb.append("-");
        calcMenuInputText.setText(sb.toString());
    }

    public void multiplyOperatorButtonClick(View view) {
        sb.append("*");
        calcMenuInputText.setText(sb.toString());
    }

    public void divideOperatorButtonClick(View view) {
        sb.append("÷");
        calcMenuInputText.setText(sb.toString());
    }

    public void equalOperatorButtonClick(View view) {
        sb.append("=");
        calcMenuInputText.setText(sb.toString());
    }

    public void calc0ButtonClick(View view) {
        sb.append("0");
        calcMenuInputText.setText(sb.toString());
    }

    public void calc1ButtonClick(View view) {
        sb.append("1");
        calcMenuInputText.setText(sb.toString());
    }

    public void calc2ButtonClick(View view) {
        sb.append("2");
        calcMenuInputText.setText(sb.toString());
    }

    public void calc3ButtonClick(View view) {
        sb.append("3");
        calcMenuInputText.setText(sb.toString());
    }

    public void calc4ButtonClick(View view) {
        sb.append("4");
        calcMenuInputText.setText(sb.toString());
    }

    public void calc5ButtonClick(View view) {
        sb.append("5");
        calcMenuInputText.setText(sb.toString());
    }

    public void calc6ButtonClick(View view) {
        sb.append("6");
        calcMenuInputText.setText(sb.toString());
    }

    public void calc7ButtonClick(View view) {
        sb.append("7");
        calcMenuInputText.setText(sb.toString());
    }

    public void calc8ButtonClick(View view) {
        sb.append("8");
        calcMenuInputText.setText(sb.toString());
    }

    public void calc9ButtonClick(View view) {
        sb.append("9");
        calcMenuInputText.setText(sb.toString());
    }

    public void deleteEquationButtonClick(View view) {
        sb.setLength(0);
        calcMenuInputText.setText("");
        calcOutputMenu.setText("");
    }

    public void calcCheckYourAnswerButtonClick(View view) {
        String sbString = sb.toString();
        String[] sbStringDelimited;

        sbStringDelimited = sbString.split("=");
        sbStringDelimited[0] = sbStringDelimited[0].replace("÷", "/");
        DoubleEvaluator doubleEvalDelimited = new DoubleEvaluator();
        Double resultDelimited = doubleEvalDelimited.evaluate(sbStringDelimited[0]);
        double resultDelimited2 = resultDelimited;
        Integer resultDelimitedInt = (int) resultDelimited2;

        if (sbStringDelimited[1].equals(resultDelimitedInt.toString())) {
            Context context = getApplicationContext();
            CharSequence text = "GOOD JOB!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            calcOutputMenu.setText("Correct");
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Please try again!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            calcOutputMenu.setText("Incorrect");
        }
    }

    public void calcGiveMeAnswerButtonClick(View view) {
        String sbString = sb.toString();
        String[] sbStringDelimited;

        if(sbString.contains("=")) {
            sbStringDelimited = sbString.split("=");
            sbStringDelimited[0] = sbStringDelimited[0].replace("÷", "/");
            DoubleEvaluator doubleEvalDelimited = new DoubleEvaluator();
            Double resultDelimited = doubleEvalDelimited.evaluate(sbStringDelimited[0]);
            calcOutputMenu.setText(resultDelimited.toString());
        } else {
            sbString = sbString.replace("÷", "/");
            DoubleEvaluator doubleEval = new DoubleEvaluator();
            Double result = doubleEval.evaluate(sbString);
            calcOutputMenu.setText(result.toString());
        }
    }
}