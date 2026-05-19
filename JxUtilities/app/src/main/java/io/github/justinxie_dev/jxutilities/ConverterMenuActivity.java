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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ConverterMenuActivity extends AppCompatActivity {

    int COUNTER = 0;

    private Button tempButton;
    private Button lenButton;
    private Button masButton;

    private RadioButton fromOp1;
    private RadioButton fromOp2;
    private RadioButton fromOp3;

    private RadioButton toOp1;
    private RadioButton toOp2;
    private RadioButton toOp3;

    private RadioGroup fromRadioGrp;
    private RadioGroup toRadioGrp;

    private EditText fromSecEditText;
    private EditText toSecEditText;
    private TextView fromSecTextView;
    private TextView toSecTextView;

    private Button convCheckYourAnsButton;
    private Button convGiveMeAnsButton;

    // Database declarations
    private AppDatabase db;
    private ConverterDao converterDao;
    private ConverterDataEntity converterDataEntity = new ConverterDataEntity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter_menu);

        tempButton = (Button) findViewById(R.id.temperatureButton);
        lenButton = (Button) findViewById(R.id.lengthButton);
        masButton = (Button) findViewById(R.id.massButton);

        fromOp1 = (RadioButton) findViewById(R.id.fromOption1);
        fromOp2 = (RadioButton) findViewById(R.id.fromOption2);
        fromOp3 = (RadioButton) findViewById(R.id.fromOption3);

        toOp1 = (RadioButton) findViewById(R.id.toOption1);
        toOp2 = (RadioButton) findViewById(R.id.toOption2);
        toOp3 = (RadioButton) findViewById(R.id.toOption3);

        fromRadioGrp = (RadioGroup) findViewById(R.id.fromRadioGroup);
        toRadioGrp = (RadioGroup) findViewById(R.id.toRadioGroup);

        fromSecEditText = (EditText) findViewById(R.id.fromSectionEditText);
        toSecEditText = (EditText) findViewById(R.id.toSectionEditText);
        fromSecTextView = (TextView) findViewById(R.id.fromSectionTextView);
        toSecTextView = (TextView) findViewById(R.id.toSectionTextView);

        convCheckYourAnsButton = (Button) findViewById(R.id.convCheckYourAnswerButton);
        convGiveMeAnsButton = (Button) findViewById(R.id.convGiveMeAnswerButton);

        tempButton.setBackgroundColor(Color.YELLOW);
        lenButton.setBackgroundColor(Color.YELLOW);
        masButton.setBackgroundColor(Color.YELLOW);

        convCheckYourAnsButton.setBackgroundColor(Color.GREEN);
        convGiveMeAnsButton.setBackgroundColor(Color.GREEN);

        // Set Action Bar color
        // Source: https://www.geeksforgeeks.org/how-to-change-the-color-of-action-bar-in-an-android-app/
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#E6CC00"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // Create an instance of the database
        // Reference: https://developer.android.com/training/data-storage/room (Usage section)
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "jxutilities-database").fallbackToDestructiveMigration(true).build();
        converterDao = db.converterDao();
    }

    public void temperatureButtonClick(View view) {
        COUNTER = 1;

        fromOp1.setText("Fahrenheit (°F)");
        fromOp2.setText("Celsius (°C)");
        fromOp3.setText("Kelvin (K)");

        toOp1.setText("Fahrenheit (°F)");
        toOp2.setText("Celsius (°C)");
        toOp3.setText("Kelvin (K)");

        fromRadioGrp.clearCheck();
        toRadioGrp.clearCheck();
        fromSecEditText.setText("");
        toSecEditText.setText("");
        fromSecTextView.setText("");
        toSecTextView.setText("");
    }

    public void lengthButtonClick(View view) {
        COUNTER = 2;

        fromOp1.setText("inch (in)");
        fromOp2.setText("foot (ft)");
        fromOp3.setText("yard (yd)");

        toOp1.setText("inch (in)");
        toOp2.setText("foot (ft)");
        toOp3.setText("yard (yd)");

        fromRadioGrp.clearCheck();
        toRadioGrp.clearCheck();
        fromSecEditText.setText("");
        toSecEditText.setText("");
        fromSecTextView.setText("");
        toSecTextView.setText("");
    }

    public void massButtonClick(View view) {
        COUNTER = 3;

        fromOp1.setText("pound (lb)");
        fromOp2.setText("ounce (oz)");
        fromOp3.setText("ton (t)");

        toOp1.setText("pound (lb)");
        toOp2.setText("ounce (oz)");
        toOp3.setText("ton (t)");

        fromRadioGrp.clearCheck();
        toRadioGrp.clearCheck();
        fromSecEditText.setText("");
        toSecEditText.setText("");
        fromSecTextView.setText("");
        toSecTextView.setText("");
    }

    public void fromOption1RadioButtonClick(View view) {
        String s = fromOp1.getText().toString();
        fromSecTextView.setText(s);
    }

    public void fromOption2RadioButtonClick(View view) {
        String s = fromOp2.getText().toString();
        fromSecTextView.setText(s);
    }

    public void fromOption3RadioButtonClick(View view) {
        String s = fromOp3.getText().toString();
        fromSecTextView.setText(s);
    }

    public void toOption1RadioButtonClick(View view) {
        String s = toOp1.getText().toString();
        toSecTextView.setText(s);
    }

    public void toOption2RadioButtonClick(View view) {
        String s = toOp2.getText().toString();
        toSecTextView.setText(s);
    }

    public void toOption3RadioButtonClick(View view) {
        String s = toOp3.getText().toString();
        toSecTextView.setText(s);
    }

    public void convGiveMeAnswerButtonClick(View view) {
        if (COUNTER == 1) {
            if (fromSecEditText.getText().toString().isEmpty()) {
                fromSecEditText.setError("Please provide a number.");
            } else {
                String stringInput = fromSecEditText.getText().toString();
                Double doubleInput = Double.parseDouble(stringInput);
                Double doubleOutput = 0.0;

                if(fromOp1.isChecked() && toOp1.isChecked()) {
                    doubleOutput = doubleInput;
                }

                if(fromOp1.isChecked() && toOp2.isChecked()) {
                    doubleOutput = (doubleInput - 32) * 5/9;
                }

                if(fromOp1.isChecked() && toOp3.isChecked()) {
                    doubleOutput = (doubleInput - 32) * 5/9 + 273.15;
                }

                if(fromOp2.isChecked() && toOp1.isChecked()) {
                    doubleOutput = (doubleInput * 9/5) + 32;
                }

                if(fromOp2.isChecked() && toOp2.isChecked()) {
                    doubleOutput = doubleInput;
                }

                if(fromOp2.isChecked() && toOp3.isChecked()) {
                    doubleOutput = doubleInput + 273.15;
                }

                if(fromOp3.isChecked() && toOp1.isChecked()) {
                    doubleOutput = (doubleInput - 273.15) * 9/5 + 32;
                }

                if(fromOp3.isChecked() && toOp2.isChecked()) {
                    doubleOutput = doubleInput - 273.15;
                }

                if(fromOp3.isChecked() && toOp3.isChecked()) {
                    doubleOutput = doubleInput;
                }

                toSecEditText.setText(doubleOutput.toString());
            }
        }

        if(COUNTER == 2) {
            if (fromSecEditText.getText().toString().isEmpty()) {
                fromSecEditText.setError("Please provide a number.");
            } else {
                String stringInput = fromSecEditText.getText().toString();
                Double doubleInput = Double.parseDouble(stringInput);
                Double doubleOutput = 0.0;

                if(fromOp1.isChecked() && toOp1.isChecked()) {
                    doubleOutput = doubleInput;
                }

                if(fromOp1.isChecked() && toOp2.isChecked()) {
                    doubleOutput = doubleInput/12;
                }

                if(fromOp1.isChecked() && toOp3.isChecked()) {
                    doubleOutput = doubleInput/36;
                }

                if(fromOp2.isChecked() && toOp1.isChecked()) {
                    doubleOutput = doubleInput * 12;
                }

                if(fromOp2.isChecked() && toOp2.isChecked()) {
                    doubleOutput = doubleInput;
                }

                if(fromOp2.isChecked() && toOp3.isChecked()) {
                    doubleOutput = doubleInput / 3;
                }

                if(fromOp3.isChecked() && toOp1.isChecked()) {
                    doubleOutput = doubleInput * 36;
                }

                if(fromOp3.isChecked() && toOp2.isChecked()) {
                    doubleOutput = doubleInput * 3;
                }

                if(fromOp3.isChecked() && toOp3.isChecked()) {
                    doubleOutput = doubleInput;
                }

                toSecEditText.setText(doubleOutput.toString());
            }
        }

        if (COUNTER == 3) {
            if (fromSecEditText.getText().toString().isEmpty()) {
                fromSecEditText.setError("Please provide a number.");
            } else {
                String stringInput = fromSecEditText.getText().toString();
                Double doubleInput = Double.parseDouble(stringInput);
                Double doubleOutput = 0.0;

                if(fromOp1.isChecked() && toOp1.isChecked()) {
                    doubleOutput = doubleInput;
                }

                if(fromOp1.isChecked() && toOp2.isChecked()) {
                    doubleOutput = doubleInput * 16;
                }

                if(fromOp1.isChecked() && toOp3.isChecked()) {
                    doubleOutput = doubleInput / 2000;
                }

                if(fromOp2.isChecked() && toOp1.isChecked()) {
                    doubleOutput = doubleInput / 16;
                }

                if(fromOp2.isChecked() && toOp2.isChecked()) {
                    doubleOutput = doubleInput;
                }

                if(fromOp2.isChecked() && toOp3.isChecked()) {
                    doubleOutput = doubleInput / 32000;
                }

                if(fromOp3.isChecked() && toOp1.isChecked()) {
                    doubleOutput = doubleInput * 2000;
                }

                if(fromOp3.isChecked() && toOp2.isChecked()) {
                    doubleOutput = doubleInput * 32000;
                }

                if(fromOp3.isChecked() && toOp3.isChecked()) {
                    doubleOutput = doubleInput;
                }

                toSecEditText.setText(doubleOutput.toString());
            }
        }
    }

    public void convCheckYourAnswerButtonClick(View view) {
        if (COUNTER == 1) {
            if(fromOp1.isChecked() && toOp1.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp1.isChecked() && toOp2.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = (doubleInput - 32) * 5/9;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp1.isChecked() && toOp3.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = (doubleInput - 32) * 5/9 + 273.15;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp2.isChecked() && toOp1.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = (doubleInput * 9/5) + 32;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp2.isChecked() && toOp2.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp2.isChecked() && toOp3.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput + 273.15;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp3.isChecked() && toOp1.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = (doubleInput - 273.15) * 9/5 + 32;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp3.isChecked() && toOp2.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput - 273.15;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp3.isChecked() && toOp3.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        }

        if (COUNTER == 2) {
            if(fromOp1.isChecked() && toOp1.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp1.isChecked() && toOp2.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput/12;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp1.isChecked() && toOp3.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput/36;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp2.isChecked() && toOp1.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput * 12;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp2.isChecked() && toOp2.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp2.isChecked() && toOp3.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput / 3;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp3.isChecked() && toOp1.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput * 36;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp3.isChecked() && toOp2.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput * 3;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp3.isChecked() && toOp3.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        }

        if (COUNTER == 3) {
            if(fromOp1.isChecked() && toOp1.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp1.isChecked() && toOp2.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput * 16;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp1.isChecked() && toOp3.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput / 2000;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp2.isChecked() && toOp1.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput / 16;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp2.isChecked() && toOp2.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp2.isChecked() && toOp3.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput / 32000;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp3.isChecked() && toOp1.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput * 2000;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp3.isChecked() && toOp2.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput * 32000;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }

            if(fromOp3.isChecked() && toOp3.isChecked()) {
                if(fromSecEditText.getText().toString().isEmpty()) {
                    fromSecEditText.setError("Please provide a number.");
                } else if (toSecEditText.getText().toString().isEmpty()) {
                    toSecEditText.setError("Please provide your answer.");
                } else {
                    String stringInput = fromSecEditText.getText().toString();
                    Double doubleInput = Double.parseDouble(stringInput);
                    Double doubleOutput = 0.0;

                    String stringAnswer = toSecEditText.getText().toString();
                    Double doubleAnswer = Double.parseDouble(stringAnswer);

                    doubleOutput = doubleInput;

                    if ((Math.abs(doubleAnswer) >= (Math.abs(doubleOutput) * (1 - 0.15))) && (Math.abs(doubleAnswer) <= (Math.abs(doubleOutput) * (1 + 0.15)))) {
                        Context context = getApplicationContext();
                        CharSequence text = "GOOD JOB! Your answer is close to " + doubleOutput.toString() + "!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect. Please Try Again!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
            }
        }
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
            Intent intent = new Intent(this, ConverterHistory.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}