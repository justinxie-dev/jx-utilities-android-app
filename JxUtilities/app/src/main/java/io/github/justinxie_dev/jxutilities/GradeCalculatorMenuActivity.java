package io.github.justinxie_dev.jxutilities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Objects;

public class GradeCalculatorMenuActivity extends AppCompatActivity {

    private EditText assignmentDescInput1;
    private EditText assignmentDescInput2;
    private EditText assignmentDescInput3;
    private EditText assignmentDescInput4;
    private EditText assignmentDescInput5;
    private EditText assignmentDescInput6;
    private EditText assignmentDescInput7;
    private EditText assignmentDescInput8;
    private EditText assignmentDescInput9;
    private EditText assignmentDescInput10;

    // Before Double.parse
    private EditText grdInput1;
    private EditText grdInput2;
    private EditText grdInput3;
    private EditText grdInput4;
    private EditText grdInput5;
    private EditText grdInput6;
    private EditText grdInput7;
    private EditText grdInput8;
    private EditText grdInput9;
    private EditText grdInput10;

    // After Double.parse
    private Double grade1;
    private Double grade2;
    private Double grade3;
    private Double grade4;
    private Double grade5;
    private Double grade6;
    private Double grade7;
    private Double grade8;
    private Double grade9;
    private Double grade10;

    // Before Double.parse
    private EditText weiInput1;
    private EditText weiInput2;
    private EditText weiInput3;
    private EditText weiInput4;
    private EditText weiInput5;
    private EditText weiInput6;
    private EditText weiInput7;
    private EditText weiInput8;
    private EditText weiInput9;
    private EditText weiInput10;

    // After Double.parse
    private Double weight1;
    private Double weight2;
    private Double weight3;
    private Double weight4;
    private Double weight5;
    private Double weight6;
    private Double weight7;
    private Double weight8;
    private Double weight9;
    private Double weight10;

    private Button gradeCalcCheckYourAnsButton;
    private Button gradeCalcGiveMeAnsButton;

    private TextView displayGradeNoTextView;
    private EditText displayGradeLeEditText;

    private TextView weiHeading;

    // Database declarations
    private AppDatabase db;
    private GradeCalculatorDao gradeCalculatorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_calculator_menu);

        // Scroll view in XML file source: https://stackoverflow.com/questions/6674341/how-to-use-scrollview-in-android

        assignmentDescInput1 = (EditText) findViewById(R.id.assignmentDescriptionInput1);
        assignmentDescInput2 = (EditText) findViewById(R.id.assignmentDescriptionInput2);
        assignmentDescInput3 = (EditText) findViewById(R.id.assignmentDescriptionInput3);
        assignmentDescInput4 = (EditText) findViewById(R.id.assignmentDescriptionInput4);
        assignmentDescInput5 = (EditText) findViewById(R.id.assignmentDescriptionInput5);
        assignmentDescInput6 = (EditText) findViewById(R.id.assignmentDescriptionInput6);
        assignmentDescInput7 = (EditText) findViewById(R.id.assignmentDescriptionInput7);
        assignmentDescInput8 = (EditText) findViewById(R.id.assignmentDescriptionInput8);
        assignmentDescInput9 = (EditText) findViewById(R.id.assignmentDescriptionInput9);
        assignmentDescInput10 = (EditText) findViewById(R.id.assignmentDescriptionInput10);

        grdInput1 = (EditText) findViewById(R.id.gradeInput1);
        grdInput2 = (EditText) findViewById(R.id.gradeInput2);
        grdInput3 = (EditText) findViewById(R.id.gradeInput3);
        grdInput4 = (EditText) findViewById(R.id.gradeInput4);
        grdInput5 = (EditText) findViewById(R.id.gradeInput5);
        grdInput6 = (EditText) findViewById(R.id.gradeInput6);
        grdInput7 = (EditText) findViewById(R.id.gradeInput7);
        grdInput8 = (EditText) findViewById(R.id.gradeInput8);
        grdInput9 = (EditText) findViewById(R.id.gradeInput9);
        grdInput10 = (EditText) findViewById(R.id.gradeInput10);

        weiInput1 = (EditText) findViewById(R.id.weightInput1);
        weiInput2 = (EditText) findViewById(R.id.weightInput2);
        weiInput3 = (EditText) findViewById(R.id.weightInput3);
        weiInput4 = (EditText) findViewById(R.id.weightInput4);
        weiInput5 = (EditText) findViewById(R.id.weightInput5);
        weiInput6 = (EditText) findViewById(R.id.weightInput6);
        weiInput7 = (EditText) findViewById(R.id.weightInput7);
        weiInput8 = (EditText) findViewById(R.id.weightInput8);
        weiInput9 = (EditText) findViewById(R.id.weightInput9);
        weiInput10 = (EditText) findViewById(R.id.weightInput10);

        gradeCalcCheckYourAnsButton = (Button) findViewById(R.id.gradeCalcCheckYourAnswerButton);
        gradeCalcGiveMeAnsButton = (Button) findViewById(R.id.gradeCalcGiveMeAnswerButton);

        displayGradeNoTextView = (TextView) findViewById(R.id.displayGradeNumberTextView);
        displayGradeLeEditText = (EditText) findViewById(R.id.displayGradeLetterEditText);

        weiHeading = (TextView) findViewById(R.id.weightHeading);

        gradeCalcCheckYourAnsButton.setBackgroundColor(Color.GREEN);
        gradeCalcGiveMeAnsButton.setBackgroundColor(Color.GREEN);

        // Set Action Bar color
        // Source: https://www.geeksforgeeks.org/how-to-change-the-color-of-action-bar-in-an-android-app/
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#006400"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // Create an instance of the database
        // Reference: https://developer.android.com/training/data-storage/room (Usage section)
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "jxutilities-database").fallbackToDestructiveMigration(true).build();
        gradeCalculatorDao = db.gradeCalculatorDao();
    }

    public void gradeCalcCheckYourAnswerButtonClick(View view) {
        Double grade = 0.0;
        String trueGrade = null;
        String inputGrade = null;
        String letterGrade = null;
        Double sumOfWeights = 0.0;

        if (!grdInput1.getText().toString().isEmpty() && !weiInput1.getText().toString().isEmpty()) {
            grade1 = Double.parseDouble(grdInput1.getText().toString());
            weight1 = Double.parseDouble(weiInput1.getText().toString());
            grade += grade1 * (weight1 / 100.0);
            sumOfWeights += weight1;
        }

        if (!grdInput2.getText().toString().isEmpty() && !weiInput2.getText().toString().isEmpty()) {
            grade2 = Double.parseDouble(grdInput2.getText().toString());
            weight2 = Double.parseDouble(weiInput2.getText().toString());
            grade += grade2 * (weight2 / 100.0);
            sumOfWeights += weight2;
        }

        if (!grdInput3.getText().toString().isEmpty() && !weiInput3.getText().toString().isEmpty()) {
            grade3 = Double.parseDouble(grdInput3.getText().toString());
            weight3 = Double.parseDouble(weiInput3.getText().toString());
            grade += grade3 * (weight3 / 100.0);
            sumOfWeights += weight3;
        }

        if (!grdInput4.getText().toString().isEmpty() && !weiInput4.getText().toString().isEmpty()) {
            grade4 = Double.parseDouble(grdInput4.getText().toString());
            weight4 = Double.parseDouble(weiInput4.getText().toString());
            grade += grade4 * (weight4 / 100.0);
            sumOfWeights += weight4;
        }

        if (!grdInput5.getText().toString().isEmpty() && !weiInput5.getText().toString().isEmpty()) {
            grade5 = Double.parseDouble(grdInput5.getText().toString());
            weight5 = Double.parseDouble(weiInput5.getText().toString());
            grade += grade5 * (weight5 / 100.0);
            sumOfWeights += weight5;
        }

        if (!grdInput6.getText().toString().isEmpty() && !weiInput6.getText().toString().isEmpty()) {
            grade6 = Double.parseDouble(grdInput6.getText().toString());
            weight6 = Double.parseDouble(weiInput6.getText().toString());
            grade += grade6 * (weight6 / 100.0);
            sumOfWeights += weight6;
        }

        if (!grdInput7.getText().toString().isEmpty() && !weiInput7.getText().toString().isEmpty()) {
            grade7 = Double.parseDouble(grdInput7.getText().toString());
            weight7 = Double.parseDouble(weiInput7.getText().toString());
            grade += grade7 * (weight7 / 100.0);
            sumOfWeights += weight7;
        }

        if (!grdInput8.getText().toString().isEmpty() && !weiInput8.getText().toString().isEmpty()) {
            grade8 = Double.parseDouble(grdInput8.getText().toString());
            weight8 = Double.parseDouble(weiInput8.getText().toString());
            grade += grade8 * (weight8 / 100.0);
            sumOfWeights += weight8;
        }

        if (!grdInput9.getText().toString().isEmpty() && !weiInput9.getText().toString().isEmpty()) {
            grade9 = Double.parseDouble(grdInput9.getText().toString());
            weight9 = Double.parseDouble(weiInput9.getText().toString());
            grade += grade9 * (weight9 / 100.0);
            sumOfWeights += weight9;
        }

        if (!grdInput10.getText().toString().isEmpty() && !weiInput10.getText().toString().isEmpty()) {
            grade10 = Double.parseDouble(grdInput10.getText().toString());
            weight10 = Double.parseDouble(weiInput10.getText().toString());
            grade += grade10 * (weight10 / 100.0);
            sumOfWeights += weight10;
        }

        if (grade >= 90.0) {
            trueGrade = "A";
        } else if (grade >= 80.0) {
            trueGrade = "B";
        } else if (grade >= 70.0) {
            trueGrade = "C";
        } else if (grade >= 60.0) {
            trueGrade = "D";
        } else if (grade < 60.0) {
            trueGrade = "F";
        }

        if (!displayGradeLeEditText.getText().toString().equals("A") && !displayGradeLeEditText.getText().toString().equals("B")
            && !displayGradeLeEditText.getText().toString().equals("C") && !displayGradeLeEditText.getText().toString().equals("D")
            && !displayGradeLeEditText.getText().toString().equals("F")) {

            displayGradeLeEditText.setError("Please enter A, B, C, D or F only");
        } else if (displayGradeLeEditText.getText().toString().equals("A") || displayGradeLeEditText.getText().toString().equals("B")
                || displayGradeLeEditText.getText().toString().equals("C") || displayGradeLeEditText.getText().toString().equals("D")
                || displayGradeLeEditText.getText().toString().equals("F")) {
            inputGrade = displayGradeLeEditText.getText().toString();
            if (inputGrade.equals(trueGrade)) {
                // Decimal to two places source: https://stackoverflow.com/questions/7747469/how-can-i-truncate-a-double-to-only-two-decimal-places-in-java
                String gradeDecimalFormat = new DecimalFormat("#.##").format(grade);
                displayGradeNoTextView.setText(gradeDecimalFormat.toString());

                // Setting text colors programmatically source: https://stackoverflow.com/questions/8472349/how-to-set-text-color-of-a-textview-programmatically
                if (grade >= 90.0) {
                    displayGradeLeEditText.setTextColor(Color.parseColor("#228B22"));
                    displayGradeNoTextView.setTextColor(Color.parseColor("#228B22"));
                } else if (grade >= 80.0) {
                    displayGradeLeEditText.setTextColor(Color.parseColor("#1870d5"));
                    displayGradeNoTextView.setTextColor(Color.parseColor("#1870d5"));
                } else if (grade >= 70.0) {
                    displayGradeLeEditText.setTextColor(Color.parseColor("#e6b400"));
                    displayGradeNoTextView.setTextColor(Color.parseColor("#e6b400"));
                } else if (grade >= 60.0) {
                    displayGradeLeEditText.setTextColor(Color.parseColor("#e47200"));
                    displayGradeNoTextView.setTextColor(Color.parseColor("#e47200"));
                } else if (grade < 60.0) {
                    displayGradeLeEditText.setTextColor(Color.parseColor("#FF0000"));
                    displayGradeNoTextView.setTextColor(Color.parseColor("#FF0000"));
                }

                Context context = getApplicationContext();
                // Smiley Emoji reference link: https://stackoverflow.com/questions/34540498/how-to-display-emoticons-emoji-in-snackbar-or-toast-textview
                // 100 Emoji reference link: https://emojiterra.com/hundred-points/
                CharSequence text = "GOOD JOB! You get an A for your correct answer!" + " " + ("\ud83d\ude01") + " " + ("\uD83D\uDCAF");
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                displayGradeNoTextView.setText("");
                Context context = getApplicationContext();
                CharSequence text = "Not correct. Please try again.";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }

        // Final check to see if weights add up to 100. If not, give warning to user
        if (sumOfWeights != 100.0) {
            // Warning emoji source link: http://www.iemoji.com/view/emoji/163/symbols/warning
            Context context = getApplicationContext();
            CharSequence text = ("\u26A0\uFE0F") + "WARNING: Weights not add to 100" + ("\u26A0\uFE0F");
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        // Variables inside lambda expression needs to be effectively final
        Double finalGrade = grade;

        if(Objects.equals(inputGrade, trueGrade)) {
            letterGrade = inputGrade + " - Correct! You guessed right!";
        } else {
            letterGrade = inputGrade + " - Incorrect! You guessed wrong...";
        }
        String finalLetterGrade = letterGrade;

        // Store entry/row into database on a separate thread away from UI thread to prevent crashing
        java.util.concurrent.Executors.newSingleThreadExecutor().execute(() -> {
            GradeCalculatorDataEntity gradeCalculatorDbRow = new GradeCalculatorDataEntity();

            gradeCalculatorDbRow.assignmentDesc1 = assignmentDescInput1.getText().toString();
            gradeCalculatorDbRow.grade1 = grade1;
            gradeCalculatorDbRow.weight1 = weight1;

            gradeCalculatorDbRow.assignmentDesc2 = assignmentDescInput2.getText().toString();
            gradeCalculatorDbRow.grade2 = grade2;
            gradeCalculatorDbRow.weight2 = weight2;

            gradeCalculatorDbRow.assignmentDesc3 = assignmentDescInput3.getText().toString();
            gradeCalculatorDbRow.grade3 = grade3;
            gradeCalculatorDbRow.weight3 = weight3;

            gradeCalculatorDbRow.assignmentDesc4 = assignmentDescInput4.getText().toString();
            gradeCalculatorDbRow.grade4 = grade4;
            gradeCalculatorDbRow.weight4 = weight4;

            gradeCalculatorDbRow.assignmentDesc5 = assignmentDescInput5.getText().toString();
            gradeCalculatorDbRow.grade5 = grade5;
            gradeCalculatorDbRow.weight5 = weight5;

            gradeCalculatorDbRow.assignmentDesc6 = assignmentDescInput6.getText().toString();
            gradeCalculatorDbRow.grade6 = grade6;
            gradeCalculatorDbRow.weight6 = weight6;

            gradeCalculatorDbRow.assignmentDesc7 = assignmentDescInput7.getText().toString();
            gradeCalculatorDbRow.grade7 = grade7;
            gradeCalculatorDbRow.weight7 = weight7;

            gradeCalculatorDbRow.assignmentDesc8 = assignmentDescInput8.getText().toString();
            gradeCalculatorDbRow.grade8 = grade8;
            gradeCalculatorDbRow.weight8 = weight8;

            gradeCalculatorDbRow.assignmentDesc9 = assignmentDescInput9.getText().toString();
            gradeCalculatorDbRow.grade9 = grade9;
            gradeCalculatorDbRow.weight9 = weight9;

            gradeCalculatorDbRow.assignmentDesc10 = assignmentDescInput10.getText().toString();
            gradeCalculatorDbRow.grade10 = grade10;
            gradeCalculatorDbRow.weight10 = weight10;

            gradeCalculatorDbRow.finalGrade = finalGrade;
            gradeCalculatorDbRow.finalLetterGrade = finalLetterGrade;

            gradeCalculatorDao.insertRow(gradeCalculatorDbRow);
        });
    }

    public void gradeCalcGiveMeAnswerButtonClick(View view) {
        Double grade = 0.0;
        String letterGrade = null;
        Double sumOfWeights = 0.0;

        if (!grdInput1.getText().toString().isEmpty() && !weiInput1.getText().toString().isEmpty()) {
            grade1 = Double.parseDouble(grdInput1.getText().toString());
            weight1 = Double.parseDouble(weiInput1.getText().toString());
            grade += grade1 * (weight1 / 100.0);
            sumOfWeights += weight1;
        }

        if (!grdInput2.getText().toString().isEmpty() && !weiInput2.getText().toString().isEmpty()) {
            grade2 = Double.parseDouble(grdInput2.getText().toString());
            weight2 = Double.parseDouble(weiInput2.getText().toString());
            grade += grade2 * (weight2 / 100.0);
            sumOfWeights += weight2;
        }

        if (!grdInput3.getText().toString().isEmpty() && !weiInput3.getText().toString().isEmpty()) {
            grade3 = Double.parseDouble(grdInput3.getText().toString());
            weight3 = Double.parseDouble(weiInput3.getText().toString());
            grade += grade3 * (weight3 / 100.0);
            sumOfWeights += weight3;
        }

        if (!grdInput4.getText().toString().isEmpty() && !weiInput4.getText().toString().isEmpty()) {
            grade4 = Double.parseDouble(grdInput4.getText().toString());
            weight4 = Double.parseDouble(weiInput4.getText().toString());
            grade += grade4 * (weight4 / 100.0);
            sumOfWeights += weight4;
        }

        if (!grdInput5.getText().toString().isEmpty() && !weiInput5.getText().toString().isEmpty()) {
            grade5 = Double.parseDouble(grdInput5.getText().toString());
            weight5 = Double.parseDouble(weiInput5.getText().toString());
            grade += grade5 * (weight5 / 100.0);
            sumOfWeights += weight5;
        }

        if (!grdInput6.getText().toString().isEmpty() && !weiInput6.getText().toString().isEmpty()) {
            grade6 = Double.parseDouble(grdInput6.getText().toString());
            weight6 = Double.parseDouble(weiInput6.getText().toString());
            grade += grade6 * (weight6 / 100.0);
            sumOfWeights += weight6;
        }

        if (!grdInput7.getText().toString().isEmpty() && !weiInput7.getText().toString().isEmpty()) {
            grade7 = Double.parseDouble(grdInput7.getText().toString());
            weight7 = Double.parseDouble(weiInput7.getText().toString());
            grade += grade7 * (weight7 / 100.0);
            sumOfWeights += weight7;
        }

        if (!grdInput8.getText().toString().isEmpty() && !weiInput8.getText().toString().isEmpty()) {
            grade8 = Double.parseDouble(grdInput8.getText().toString());
            weight8 = Double.parseDouble(weiInput8.getText().toString());
            grade += grade8 * (weight8 / 100.0);
            sumOfWeights += weight8;
        }

        if (!grdInput9.getText().toString().isEmpty() && !weiInput9.getText().toString().isEmpty()) {
            grade9 = Double.parseDouble(grdInput9.getText().toString());
            weight9 = Double.parseDouble(weiInput9.getText().toString());
            grade += grade9 * (weight9 / 100.0);
            sumOfWeights += weight9;
        }

        if (!grdInput10.getText().toString().isEmpty() && !weiInput10.getText().toString().isEmpty()) {
            grade10 = Double.parseDouble(grdInput10.getText().toString());
            weight10 = Double.parseDouble(weiInput10.getText().toString());
            grade += grade10 * (weight10 / 100.0);
            sumOfWeights += weight10;
        }

        // Decimal to two places source: https://stackoverflow.com/questions/7747469/how-can-i-truncate-a-double-to-only-two-decimal-places-in-java
        String gradeDecimalFormat = new DecimalFormat("#.##").format(grade);
        displayGradeNoTextView.setText(gradeDecimalFormat.toString());

        // Setting text colors programmatically source: https://stackoverflow.com/questions/8472349/how-to-set-text-color-of-a-textview-programmatically
        if (grade >= 90.0) {
            letterGrade = "A";
            displayGradeLeEditText.setText(letterGrade);
            displayGradeLeEditText.setTextColor(Color.parseColor("#228B22"));
            displayGradeNoTextView.setTextColor(Color.parseColor("#228B22"));
        } else if (grade >= 80.0) {
            letterGrade = "B";
            displayGradeLeEditText.setText(letterGrade);
            displayGradeLeEditText.setTextColor(Color.parseColor("#1870d5"));
            displayGradeNoTextView.setTextColor(Color.parseColor("#1870d5"));
        } else if (grade >= 70.0) {
            letterGrade = "C";
            displayGradeLeEditText.setText(letterGrade);
            displayGradeLeEditText.setTextColor(Color.parseColor("#e6b400"));
            displayGradeNoTextView.setTextColor(Color.parseColor("#e6b400"));
        } else if (grade >= 60.0) {
            letterGrade = "D";
            displayGradeLeEditText.setText(letterGrade);
            displayGradeLeEditText.setTextColor(Color.parseColor("#e47200"));
            displayGradeNoTextView.setTextColor(Color.parseColor("#e47200"));
        } else if (grade < 60.0) {
            letterGrade = "F";
            displayGradeLeEditText.setText(letterGrade);
            displayGradeLeEditText.setTextColor(Color.parseColor("#FF0000"));
            displayGradeNoTextView.setTextColor(Color.parseColor("#FF0000"));
        }

        // Final check to see if weights add up to 100. If not, give warning to user
        if (sumOfWeights != 100.0) {
            // Warning emoji source link: http://www.iemoji.com/view/emoji/163/symbols/warning
            Context context = getApplicationContext();
            CharSequence text = ("\u26A0\uFE0F") + "WARNING: Weights not add to 100" + ("\u26A0\uFE0F");
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        // Variables inside lambda expression needs to be effectively final
        Double finalGrade = grade;
        String finalLetterGrade = letterGrade;

        // Store entry/row into database on a separate thread away from UI thread to prevent crashing
        java.util.concurrent.Executors.newSingleThreadExecutor().execute(() -> {
            GradeCalculatorDataEntity gradeCalculatorDbRow = new GradeCalculatorDataEntity();

            gradeCalculatorDbRow.assignmentDesc1 = assignmentDescInput1.getText().toString();
            gradeCalculatorDbRow.grade1 = grade1;
            gradeCalculatorDbRow.weight1 = weight1;

            gradeCalculatorDbRow.assignmentDesc2 = assignmentDescInput2.getText().toString();
            gradeCalculatorDbRow.grade2 = grade2;
            gradeCalculatorDbRow.weight2 = weight2;

            gradeCalculatorDbRow.assignmentDesc3 = assignmentDescInput3.getText().toString();
            gradeCalculatorDbRow.grade3 = grade3;
            gradeCalculatorDbRow.weight3 = weight3;

            gradeCalculatorDbRow.assignmentDesc4 = assignmentDescInput4.getText().toString();
            gradeCalculatorDbRow.grade4 = grade4;
            gradeCalculatorDbRow.weight4 = weight4;

            gradeCalculatorDbRow.assignmentDesc5 = assignmentDescInput5.getText().toString();
            gradeCalculatorDbRow.grade5 = grade5;
            gradeCalculatorDbRow.weight5 = weight5;

            gradeCalculatorDbRow.assignmentDesc6 = assignmentDescInput6.getText().toString();
            gradeCalculatorDbRow.grade6 = grade6;
            gradeCalculatorDbRow.weight6 = weight6;

            gradeCalculatorDbRow.assignmentDesc7 = assignmentDescInput7.getText().toString();
            gradeCalculatorDbRow.grade7 = grade7;
            gradeCalculatorDbRow.weight7 = weight7;

            gradeCalculatorDbRow.assignmentDesc8 = assignmentDescInput8.getText().toString();
            gradeCalculatorDbRow.grade8 = grade8;
            gradeCalculatorDbRow.weight8 = weight8;

            gradeCalculatorDbRow.assignmentDesc9 = assignmentDescInput9.getText().toString();
            gradeCalculatorDbRow.grade9 = grade9;
            gradeCalculatorDbRow.weight9 = weight9;

            gradeCalculatorDbRow.assignmentDesc10 = assignmentDescInput10.getText().toString();
            gradeCalculatorDbRow.grade10 = grade10;
            gradeCalculatorDbRow.weight10 = weight10;

            gradeCalculatorDbRow.finalGrade = finalGrade;
            gradeCalculatorDbRow.finalLetterGrade = finalLetterGrade;

            gradeCalculatorDao.insertRow(gradeCalculatorDbRow);
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
            Intent intent = new Intent(this, GradeCalculatorHistory.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
