package tytusnawara.morsefromtext.flashLightAndSounds;

import android.util.Log;

import tytusnawara.morsefromtext.morseFromString.MorseCodeGenerator;

public class ExternalOutputJustLogForTest extends ExternalOutput {

    public ExternalOutputJustLogForTest(MorseCodeGenerator morseCodeGenerator) {
        super(morseCodeGenerator);
    }



    @Override
    protected void outputON() {
        Log.i("output status: ", "Testing log ON");
    }

    @Override
    protected void outputOFF() {
        Log.i("output status: ", "Testing log OFF");
    }
}
