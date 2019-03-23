package tytusnawara.morsefromtext.flashLightAndSounds;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import tytusnawara.morsefromtext.MainActivity;
import tytusnawara.morsefromtext.R;
import tytusnawara.morsefromtext.morseFromString.MorseCodeGenerator;

public class SoundOutput extends ExternalOutput {

    Context context;

    private MediaPlayer mp;

    @Override
    protected void outputON() {
        super.outputON();
        mp.start();
       // Log.d("mojeLogi", "outputON");
    }

    @Override
    protected void outputOFF() {
        super.outputOFF();
        mp.pause();
        mp.seekTo(0);
       // Log.d("mojeLogi", "outputOFF");
    }

    public SoundOutput(Context context, MorseCodeGenerator morseCodeGenerator) {
            super(morseCodeGenerator);

            this.context = context;



//        mp = MediaPlayer.create(MainActivity.this, R.raw.morse_sound2_without_break);
//        mp.start();
//            mp.seekTo(0);
//            mp.pause();
//            mp.start();


    }

    @Override
    public void run() {
       // Log.d("mojeLogi", "funkcja run SoundOutput");
        mp = MediaPlayer.create(context, R.raw.morse_sound2);
        super.run();
    }

    @Override
    public boolean getIsOutputLasting() {
        return super.getIsOutputLasting();
    }
}
