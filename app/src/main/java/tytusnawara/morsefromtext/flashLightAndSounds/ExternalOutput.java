package tytusnawara.morsefromtext.flashLightAndSounds;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import tytusnawara.morsefromtext.morseFromString.MorseCodeGenerator;
import tytusnawara.morsefromtext.morseFromString.OutputType;

public abstract class ExternalOutput extends Thread{

    //cannot be greter than 500
    int DOT_LengthMillis = 300;
    int BREAK_BETWEEN_MARKS_LengthMillis = DOT_LengthMillis;
    int DASH_LengthMillis = 3 * DOT_LengthMillis;
    int SPACE_LengthMillis = DASH_LengthMillis;

    boolean isOutputLasting = false;

    MorseCodeGenerator morseCodeGenerator;


    public ExternalOutput(MorseCodeGenerator morseCodeGenerator) {
        this.morseCodeGenerator = morseCodeGenerator;
    }



    public boolean getIsOutputLasting(){
        return isOutputLasting;
    }

    @Override
    public void run() {

        //Log.d("mojeLogi", "funkcja run ExternalOutput");
        isOutputLasting = true;

        OutputType currentOutput = morseCodeGenerator.reciveNextMorseSign();
        while (currentOutput != OutputType.END_OF_SIGNAL)
        {


            if(currentOutput == OutputType.DOT)
            {
                //Log.d("mojeLogi", "KROPKA");

                outputON();
                try {
                    Thread.sleep(DOT_LengthMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputOFF();
            }else if(currentOutput == OutputType.DASH)
            {
                //Log.d("mojeLogi", "KRESKA");

                outputON();
                try {
                    Thread.sleep(DASH_LengthMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                outputOFF();
            }

            try {
                Thread.sleep(BREAK_BETWEEN_MARKS_LengthMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            currentOutput = morseCodeGenerator.reciveNextMorseSign();
        }

        outputOFF();
        isOutputLasting = false;
    }

    protected void outputON(){};
    protected void outputOFF(){};





}
