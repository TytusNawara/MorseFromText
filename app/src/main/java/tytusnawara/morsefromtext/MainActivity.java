package tytusnawara.morsefromtext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import tytusnawara.morsefromtext.flashLightAndSounds.ExternalOutput;
import tytusnawara.morsefromtext.flashLightAndSounds.ExternalOutputJustLogForTest;
import tytusnawara.morsefromtext.morseFromString.MorseCodeGenerator;
import tytusnawara.morsefromtext.morseFromString.MorseTranslator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MorseCodeGenerator morseCodeGenerator = new MorseCodeGenerator("asdfgasd");

        ExternalOutputJustLogForTest externalOutputJustLogForTest =
                new ExternalOutputJustLogForTest(morseCodeGenerator);

        Log.i("output status: ", "Testing log");

        externalOutputJustLogForTest.startOutputingInNetThread();



//
//        Thread myThread = new Thread(){
//            public void run() {
//                for(int i=0; i<3; i++) {
//                    try{ sleep(100); }catch(InterruptedException ie){}
//                    System.out.print(i+", ");
//                }
//                System.out.println("done.");
//            }
//        };
//
//        myThread.start();

    }
}
