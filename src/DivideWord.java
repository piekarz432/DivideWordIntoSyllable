import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DivideWord {

    protected int countSyllables(String word) {
        // getNumSyllables method in BasicDocument (module 1) and
        // EfficientDocument (module 2).
        int syllables = 0;
        word = word.toLowerCase();

        String[] split = word.split("e!$|e[?]$|e,|e |e[),]|e$");

        ArrayList<String> tokens = new ArrayList<String>();
        Pattern tokSplitter = Pattern.compile("[aąeęiouyó]+");


        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            Matcher m = tokSplitter.matcher(s);
            while (m.find()) {
                tokens.add(m.group());
            }
        }

        syllables += tokens.size();
        return syllables;
    }
}