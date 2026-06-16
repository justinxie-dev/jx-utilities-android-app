package io.github.justinxie_dev.jxutilities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private Button calcButton;
    private Button convButton;
    private Button mButton;
    private Button gradesButton;

    private Switch musSwitch;
    private MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set background color for the Main Menu buttons
        calcButton = (Button) findViewById(R.id.calculatorButton);
        convButton = (Button) findViewById(R.id.convertersButton);
        mButton = (Button) findViewById(R.id.mapButton);
        gradesButton = (Button) findViewById(R.id.gradeCalculatorButton);

        // Preparing the music switch
        musSwitch = (Switch) findViewById(R.id.musicSwitch);
        musSwitch.setChecked(false);

        // Set Action Bar color
        // Source: https://www.geeksforgeeks.org/how-to-change-the-color-of-action-bar-in-an-android-app/
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F9D58"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // Main Menu Background Image Source: https://depositphotos.com/stock-photos/kids-learning-cartoon.html
    }

    public void calculatorButtonClick (View view) {
        Intent intent = new Intent(this, CalculatorMenuActivity.class);
        startActivity(intent);
    }

    public void converterButtonClick(View view) {
        Intent intent = new Intent(this, ConverterMenuActivity.class);
        startActivity(intent);
    }

    public void gradeCalculatorButtonClick(View view) {
        Intent intent = new Intent(this, GradeCalculatorMenuActivity.class);
        startActivity(intent);
    }

    public void mapsButtonClick(View view) {
        Intent intent = new Intent(this, MapsMenuActivity.class);
        startActivity(intent);
    }

    // Music icons source link: https://www.i2symbol.com/symbols/music
    public void musicSwitchToggle(View view) {
        if (!musSwitch.isChecked()) {
            music.stop();
        }

        if (musSwitch.isChecked()) {
            // Play fun music
            // Source Link: https://www.geeksforgeeks.org/how-to-add-audio-files-to-android-app-in-android-studio/
            // Music persisting source link: https://stackoverflow.com/questions/6241687/mediaplayer-stop-playing-after-about-5-seconds
            // Music download source link: https://downloads.khinsider.com/game-soundtracks/album/pokemon-diamond-and-pearl-super-music-collection
            music = MediaPlayer.create(MainActivity.this, R.raw.jubilife_city_night);
            music.setLooping(true);
            music.start();

            // Soundtrack is from 2007 Pokémon Diamond and Pearl game. The soundtrack is the Jubilife City (night) theme.
            // © 2007 Pokémon. ©1995-2007 Nintendo/Creatures Inc./GAME FREAK Inc. TM,® AND THE NINTENDO DS LOGO ARE TRADEMARKS OF NINTENDO.
            // © 2007 NINTENDO
        }
    }
}