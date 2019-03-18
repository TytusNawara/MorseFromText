package tytusnawara.morsefromtext.flashLightAndSounds;

import tytusnawara.morsefromtext.morseFromString.MorseCodeGenerator;
import tytusnawara.morsefromtext.morseFromString.OutputType;

public abstract class ExternalOutput{
    int DOT_LengthMillis = 200;
    int BREAK_BETWEEN_MARKS_LengthMillis = DOT_LengthMillis;
    int DASH_LengthMillis = 3 * DOT_LengthMillis;
    int SPACE_LengthMillis = DASH_LengthMillis;

    MorseCodeGenerator morseCodeGenerator;

//    void outputSequence(){
//        long startTime = System.currentTimeMillis();
//        if(System.currentTimeMillis() - startTime >= BREAK_BETWEEN_MARKS_LengthMillis)
//        {
//            outputON();
//        }
//    };

    public ExternalOutput(MorseCodeGenerator morseCodeGenerator) {
        this.morseCodeGenerator = morseCodeGenerator;
    }


    public void startOutputingInNetThread() {
        Thread myThread = new Thread(){
            public void run() {
                OutputType currentOutput = null;
                while (currentOutput != OutputType.END_OF_SIGNAL)
                {
                    if(currentOutput == OutputType.DOT)
                    {
                        outputON();
                        try {
                            Thread.sleep(DOT_LengthMillis);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        outputOFF();
                    }else if(currentOutput == OutputType.DASH)
                    {
                        outputON();
                        try {
                            Thread.sleep(DASH_LengthMillis);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        Thread.sleep(BREAK_BETWEEN_MARKS_LengthMillis);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        myThread.start();
    }

    protected void outputON(){};
    protected void outputOFF(){};
}
