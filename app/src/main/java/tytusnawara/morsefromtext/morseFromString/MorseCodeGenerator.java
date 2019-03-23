package tytusnawara.morsefromtext.morseFromString;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import java.util.Queue;

import static tytusnawara.morsefromtext.morseFromString.OutputType.DASH;
import static tytusnawara.morsefromtext.morseFromString.OutputType.DOT;
import static tytusnawara.morsefromtext.morseFromString.OutputType.END_OF_SIGNAL;
import static tytusnawara.morsefromtext.morseFromString.OutputType.SPACE;

public class MorseCodeGenerator implements MorseTranslator {

    private static final Map<Character, OutputType[]> legend = Collections.unmodifiableMap(
            new HashMap<Character, OutputType[]>() {{
                put('a', new OutputType[] {DOT, OutputType.DASH});
                put('A', new OutputType[] {DOT, OutputType.DASH});
                put('b', new OutputType[] {OutputType.DASH, DOT, DOT, DOT});
                put('B', new OutputType[] {OutputType.DASH, DOT, DOT, DOT});
                put('c', new OutputType[] {OutputType.DASH, DOT, DASH, DOT});
                put('C', new OutputType[] {OutputType.DASH, DOT, DASH, DOT});
                put('d', new OutputType[] {DASH, DOT, DOT});
                put('D', new OutputType[] {DASH, DOT, DOT});
                put('e', new OutputType[] {DOT});
                put('E', new OutputType[] {DOT});
                put('f', new OutputType[] {DOT, DOT, DASH, DOT});
                put('F', new OutputType[] {DOT, DOT, DASH, DOT});
                put('g', new OutputType[] {DASH, DASH, DOT});
                put('G', new OutputType[] {DASH, DASH, DOT});
                put('h', new OutputType[] {DOT, DOT, DOT, DOT});
                put('H', new OutputType[] {DOT, DOT, DOT, DOT});
                put('i', new OutputType[] {DOT, DOT});
                put('I', new OutputType[] {DOT, DOT});
                put('j', new OutputType[] {DOT, DASH, DASH, DASH});
                put('J', new OutputType[] {DOT, DASH, DASH, DASH});
                put('k', new OutputType[] {DASH, DOT, DASH});
                put('K', new OutputType[] {DASH, DOT, DASH});
                put('l', new OutputType[] {DOT, DASH, DOT, DOT});
                put('L', new OutputType[] {DOT, DASH, DOT, DOT});
                put('m', new OutputType[] {DASH, DASH});
                put('M', new OutputType[] {DASH, DASH});
                put('n', new OutputType[] {DASH, DOT});
                put('N', new OutputType[] {DASH, DOT});
                put('o', new OutputType[] {DASH, DASH, DASH});
                put('O', new OutputType[] {DASH, DASH, DASH});
                put('p', new OutputType[] {DOT, DASH, DASH, DOT});
                put('P', new OutputType[] {DOT, DASH, DASH, DOT});
                put('r', new OutputType[] {DOT, DASH, DOT});
                put('R', new OutputType[] {DOT, DASH, DOT});
                put('s', new OutputType[] {DOT, DOT, DOT});
                put('S', new OutputType[] {DOT, DOT, DOT});
                put('t', new OutputType[] {DASH});
                put('T', new OutputType[] {DASH});
                put('u', new OutputType[] {DOT, DOT, DASH});
                put('U', new OutputType[] {DOT, DOT, DASH});
                put('w', new OutputType[] {DOT, DASH, DASH});
                put('W', new OutputType[] {DOT, DASH, DASH});
                put('x', new OutputType[] {DASH, DOT, DOT, DASH});
                put('X', new OutputType[] {DASH, DOT, DOT, DASH});
                put('y', new OutputType[] {DASH, DOT, DASH, DASH});
                put('Y', new OutputType[] {DASH, DOT, DASH, DASH});
                put('z', new OutputType[] {DASH, DASH, DOT, DOT});
                put('Z', new OutputType[] {DASH, DASH, DOT, DOT});
                put('q', new OutputType[] {DASH, DASH, DOT, DASH});
                put('Q', new OutputType[] {DASH, DASH, DOT, DASH});
                put('v', new OutputType[] {DOT, DOT, DOT, DASH});
                put('V', new OutputType[] {DOT, DOT, DOT, DASH});
                put('0', new OutputType[] {DASH, DASH, DASH, DASH, DASH});
                put('1', new OutputType[] {DOT, DASH, DASH, DASH, DASH});
                put('2', new OutputType[] {DOT, DOT, DASH, DASH, DASH});
                put('3', new OutputType[] {DOT, DOT, DOT, DASH, DASH});
                put('4', new OutputType[] {DOT, DOT, DOT, DOT, DASH});
                put('5', new OutputType[] {DOT, DOT, DOT ,DOT, DOT});
                put('6', new OutputType[] {DASH, DOT, DOT ,DOT, DOT});
                put('7', new OutputType[] {DASH, DASH, DOT ,DOT, DOT});
                put('8', new OutputType[] {DASH, DASH, DASH ,DOT, DOT});
                put('9', new OutputType[] {DASH, DASH, DASH ,DASH, DOT});
            }});

    private Queue<OutputType> queueOfMorse;

    private String morseSentence;

    //input only letters space and digits
    public MorseCodeGenerator(String morseSentence){
        morseSentence = morseSentence.replaceAll("\\s", "");

        this.queueOfMorse = new LinkedList<>();
        this.morseSentence = morseSentence;

        translateString();
    }


    @Override
    public OutputType reciveNextMorseSign() {
        OutputType toReturn = queueOfMorse.poll();
            if(toReturn == null)
                toReturn = OutputType.END_OF_SIGNAL;
            //Log.d("mojeLogi", toReturn.toString());
            return toReturn;
    }


    private void translateString() {
        queueOfMorse.clear();

        for (int i = 0; i < morseSentence.length(); i++){
            char normalChar = morseSentence.charAt(i);

            if(normalChar != ' ')
            {
                OutputType morseChar[] = legend.get(normalChar);

                for(int j = 0; j < morseChar.length; j++)
                    queueOfMorse.add(morseChar[j]);



                if(i != morseSentence.length() - 1)
                    queueOfMorse.add(SPACE);
            }
        }

        queueOfMorse.add(END_OF_SIGNAL);
    }

    public OutputType[] getFromLegend(char character) throws Exception
    {
        OutputType[] toReturn = legend.get(character);
        if(toReturn == null)
            throw new Exception("Invalid input. Character is not in the legend");

        return toReturn;
    }
}
