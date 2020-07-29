import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.List;

public class Syllable {

    public List<String> getSyllableFromWord(String word)
    {
        word = word.toLowerCase();
        List<String> syllables  = new ArrayList<>();
        int marker = 0;

        while (true) {
            int i=0;
            List<String> group = getGroupFromWord(word.substring(marker));
            String currentSyllable = "";

            if (group.size() == 1 && syllables.size() == 0) {
                syllables.add(group.get(0));
                break;
            }else if (group.size() == 1) {
                String lastSyllable = syllables.remove(syllables.size() - 1);
                lastSyllable = lastSyllable + group.get(0);
                syllables.add(lastSyllable);
                break;
            }else if (group.size() == 2 && group.get(1).equals("e")) {
                String lastSyllable = syllables.remove(syllables.size() - 1);
                if (group.get(0) != null) {
                    lastSyllable = lastSyllable + group.get(0);
                }
                lastSyllable = lastSyllable + group.get(1);
                syllables.add(lastSyllable);
                break;
            }

            if (group.get(0) != null) {
                currentSyllable = currentSyllable + group.get(0);
            }
            //System.out.println(group.get(1));
            if (isMultiSyllables(group.get(1))) {
                currentSyllable = currentSyllable + group.get(1).substring(0, 1);
            }else {
                currentSyllable = currentSyllable + group.get(1);
                if (group.size() > 2 && group.get(2) != null && group.get(2).length() > 1) {
                    currentSyllable = currentSyllable + group.get(2).substring(0, 1);
                }
            }
            syllables.add(currentSyllable);
            marker += currentSyllable.length();
            i++;
        }
        return syllables;
    }

    public List<String> getGroupFromWord(String word)
    {
        List<String> group = new ArrayList<String>();
        StringBuffer currentGroup = new StringBuffer();
        boolean buildingConsonantGroup = true;

        for(int i = 0; i < word.length(); i++)
        {
            //System.out.println(i + " " + "Przejscie pętli");
            char c = word.charAt(i);

            if(i == 0 && isVowel(c))
            {
                group.add(null);
                currentGroup.append(c);
                //System.out.println("Pierwszy if");
            }else if (buildingConsonantGroup && isVowel(c))
            {
                group.add(currentGroup.toString());
                currentGroup.delete(0, currentGroup.length());
                currentGroup.append(c);
                buildingConsonantGroup = false;
                //System.out.println("Drugi if ");

            }else if (!buildingConsonantGroup && isConsonant(c)) {
                group.add(currentGroup.toString());
                currentGroup.delete(0, currentGroup.length());
                currentGroup.append(c);
                buildingConsonantGroup = true;
                //System.out.println("Trzeci if ");

            } else if (buildingConsonantGroup && isConsonant(c)) {
                currentGroup.append(c);
                //System.out.println("Czwarty if ");

            } else if (!buildingConsonantGroup && isVowel(c)) {
                currentGroup.append(c);
                //System.out.println("Piaty if ");
            } else {
                //System.out.println("Bład");
            }

        }
        group.add(currentGroup.toString());
        return group;
    }

    public boolean isMultiSyllables(String group) {
        if (group.length() > 2)
        {
            return true;
        } else if (group.equals("eo") || group.equals("ia")
                || group.equals("io") || group.equals("ua")
                || group.equals("uo") || group.equals("uo")
                || group.equals("eu") || group.equals("au"))
        {
            return true;
        }
        return false;
    }

    public boolean isVowel(char c) {
        boolean value;

        switch (c)
        {
            case 'a':
                value = true;
                break;
            case 'ą':
                value = true;
                break;
            case 'e':
                value = true;
                break;
            case 'ę':
                value = true;
                break;
            case 'i':
                value = true;
                break;
            case 'o':
                value = true;
                break;
            case 'u':
                value = true;
                break;
            case 'y':
                value = true;
                break;
            case 'ó':
                value = true;
                break;
            default:
                value = false;
        }
        return value;
    }

    public boolean isConsonant(char c) {
        return !isVowel(c);
    }

}
