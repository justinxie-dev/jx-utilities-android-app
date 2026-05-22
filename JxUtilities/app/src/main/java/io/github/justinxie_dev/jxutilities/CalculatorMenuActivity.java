package io.github.justinxie_dev.jxutilities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    // Database declarations
    private AppDatabase db;
    private CalculatorDao calculatorDao;

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

        // Create an instance of the database
        // Reference: https://developer.android.com/training/data-storage/room (Usage section)
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "jxutilities-database").fallbackToDestructiveMigration(true).build();
        calculatorDao = db.calculatorDao();
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
        String correctOrIncorrect;

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
            correctOrIncorrect = "Correct";
            calcOutputMenu.setText(correctOrIncorrect);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Please try again!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            correctOrIncorrect = "Incorrect";
            calcOutputMenu.setText(correctOrIncorrect);
        }

        // Store entry/row into database on a separate thread away from UI thread to prevent crashing
        java.util.concurrent.Executors.newSingleThreadExecutor().execute(() -> {
            CalculatorDataEntity calculatorDbRow = new CalculatorDataEntity();
            calculatorDbRow.result = correctOrIncorrect;
            calculatorDbRow.expression = sbString;
            calculatorDao.insertRow(calculatorDbRow);
        });
    }

    public void calcGiveMeAnswerButtonClick(View view) {
        String sbString = sb.toString();
        String[] sbStringDelimited;
        Double result;
        String finalSbString = sbString;

        if(sbString.contains("=")) {
            sbStringDelimited = sbString.split("=");
            sbStringDelimited[0] = sbStringDelimited[0].replace("÷", "/");
            DoubleEvaluator doubleEvalDelimited = new DoubleEvaluator();
            result = doubleEvalDelimited.evaluate(sbStringDelimited[0]);
            calcOutputMenu.setText(result.toString());

        } else {
            sbString = sbString.replace("÷", "/");
            DoubleEvaluator doubleEval = new DoubleEvaluator();
            result = doubleEval.evaluate(sbString);
            calcOutputMenu.setText(result.toString());
        }

        // Store entry/row into database on a separate thread away from UI thread to prevent crashing
        java.util.concurrent.Executors.newSingleThreadExecutor().execute(() -> {
            CalculatorDataEntity calculatorDbRow = new CalculatorDataEntity();
            calculatorDbRow.result = result.toString();
            calculatorDbRow.expression = finalSbString;
            calculatorDao.insertRow(calculatorDbRow);
        });
    }

    // Establish History button and link the Activity to the Activty History screen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This line links top_menu.xml file to this activity's top/action bar
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check if the item clicked matches the ID from top_menu.xml
        if (item.getItemId() == R.id.action_history) {
            Intent intent = new Intent(this, CalculatorHistory.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}