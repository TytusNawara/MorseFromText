package tytusnawara.morsefromtext.morseFromString;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class MorseCodeGeneratorTest {
    final static String JUST_LETTERS = "abcdef ghijk lmnoprstuw";
    final static String LETTERS_WITH_NUMBERS = "1ab2cd3e fg4hijk5lm6no7pr8stuw 9";

    final static String JUST_LETTERSexpected = ".- -... -.-. -.. . ..-. --. .... .. .--- -.- .-.. -- -. --- .--. .-. ... - ..- .--";
    final static String LETTERS_WITH_NUMBERSexpected = ".---- .- -... ..--- -.-. -.. ...-- . ..-. --. ....- .... .. .--- -.- ..... .-.. -- -.... -. --- --... .--. .-. ---.. ... - ..- .-- ----.";


    final static String randomInput1 = "   ";
    final static String randomInput2 = "A";
    final static String randomInput3 = "GQR7aBTMlTWW61O";
    final static String randomInput4 = " Iy mk2e6QP v2R9 oN ";
    final static String allAcceptableCharacters = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    final static String randomInput1Expected = "";
    final static String randomInput2Expected = ".-";
    final static String randomInput3Expected = "--. --.- .-. --... .- -... - -- .-.. - .-- .-- -.... .---- ---";
    final static String randomInput4Expected = ".. -.-- -- -.- ..--- . -.... --.- .--. ...- ..--- .-. ----. --- -.";
    final static String allAcceptableCharactersExpected = "--.- .-- . .-. - -.-- ..- .. --- .--. .- ... -.. ..-. --. .... .--- -.- .-.. --.. -..- -.-. ...- -... -. -- --.- .-- . .-. - -.-- ..- .. --- .--. .- ... -.. ..-. --. .... .--- -.- .-.. --.. -..- -.-. ...- -... -. -- .---- ..--- ...-- ....- ..... -.... --... ---.. ----. -----";

    MorseCodeGenerator SUT;

    @Test
    public void MorseCodeGenerator_allAcceptableCharacters_expectedReturned() {
        SUT = new MorseCodeGenerator(allAcceptableCharacters);
        String result = castMorseCodeFromSUT_ToString();
        Assert.assertThat(result, is(allAcceptableCharactersExpected));
    }

    @Test
    public void MorseCodeGenerator_justLetters_sameStringReturned() {
        SUT = new MorseCodeGenerator(JUST_LETTERS);
        String result = castMorseCodeFromSUT_ToString();
        Assert.assertThat(result, is(JUST_LETTERSexpected));
    }

    @Test
    public void MorseCodeGenerator_lettersWithNumbers_sameStringReturned() {
        SUT = new MorseCodeGenerator(LETTERS_WITH_NUMBERS);
        String result = castMorseCodeFromSUT_ToString();
        Assert.assertThat(result, is(LETTERS_WITH_NUMBERSexpected));
    }

    @Test
    public void MorseCodeGenerator_randomInput1_randomInput1ExpectedReturned() {
        SUT = new MorseCodeGenerator(randomInput1);
        String result = castMorseCodeFromSUT_ToString();
        Assert.assertThat(result, is(randomInput1Expected));
    }

    @Test
    public void MorseCodeGenerator_randomInput2_randomInput2ExpectedReturned() {
        SUT = new MorseCodeGenerator(randomInput2);
        String result = castMorseCodeFromSUT_ToString();
        Assert.assertThat(result, is(randomInput2Expected));
    }

    @Test
    public void MorseCodeGenerator_randomInput3_randomInput3ExpectedReturned() {
        SUT = new MorseCodeGenerator(randomInput3);
        String result = castMorseCodeFromSUT_ToString();
        Assert.assertThat(result, is(randomInput3Expected));
    }

    @Test
    public void MorseCodeGenerator_randomInput4_randomInput4ExpectedReturned() {
        SUT = new MorseCodeGenerator(randomInput4);
        String result = castMorseCodeFromSUT_ToString();
        Assert.assertThat(result, is(randomInput4Expected));
    }

    @Test
    public void getFromLegend_g_morseValueReturned() throws Exception{
        SUT = new MorseCodeGenerator("");

        OutputType[] result = SUT.getFromLegend('g');
        OutputType[] expected = {OutputType.DASH, OutputType.DASH, OutputType.DOT};
        Assert.assertArrayEquals(result, expected);
    }

    @Test
    public void getFromLegend_K_morseValueReturned() throws Exception{
        SUT = new MorseCodeGenerator("");

        OutputType[] result = SUT.getFromLegend('K');
        OutputType[] expected = {OutputType.DASH, OutputType.DOT, OutputType.DASH};
        Assert.assertArrayEquals(result, expected);
    }


    private String castMorseCodeFromSUT_ToString()
    {
        StringBuilder sb = new StringBuilder();
        OutputType morseChar = null;
        while(morseChar != OutputType.END_OF_SIGNAL)
        {
            morseChar = SUT.reciveNextMorseSign();
            switch (morseChar){
                case DOT:
                    sb.append("."); break;
                case DASH:
                    sb.append("-"); break;
                case SPACE:
                    sb.append(" "); break;

            }

        }
        return sb.toString();
    }
}