package tytusnawara.morsefromtext;

import android.hardware.Camera;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tytusnawara.morsefromtext.flashLightAndSounds.ExternalOutput;
import tytusnawara.morsefromtext.flashLightAndSounds.ExternalOutputJustLogForTest;
import tytusnawara.morsefromtext.flashLightAndSounds.SoundOutput;
import tytusnawara.morsefromtext.morseFromString.MorseCodeGenerator;
import tytusnawara.morsefromtext.morseFromString.MorseTranslator;

public class MainActivity extends AppCompatActivity {

    Button toMorseBtn;
    EditText morseFromUser;

    ExternalOutput soundOutput;
    MorseCodeGenerator morseCodeGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toMorseBtn = findViewById(R.id.toMorseButton);
        morseFromUser = findViewById(R.id.morseFromUser);

        morseFromUser.setFilters(new InputFilter[]{onlyLettersAndDigitsFilter});

        toMorseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundOutput == null || !soundOutput.getIsOutputLasting()) {
                    morseCodeGenerator = new MorseCodeGenerator(
                            morseFromUser.getText().toString()
                    );

                    ExternalOutput soundOutput = new SoundOutput(getApplicationContext(), morseCodeGenerator);
                    soundOutput.start();
                }
            }
        });



    }

    InputFilter onlyLettersAndDigitsFilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start,
                                   int end, Spanned dest, int dstart, int dend) {

            for (int i = start; i < end; i++) {
                if (!Character.isLetterOrDigit(source.charAt(i)) &&
                        !Character.toString(source.charAt(i)).equals(" ")) {
                    return "";
                }
            }
            return null;
        }
    };

}
