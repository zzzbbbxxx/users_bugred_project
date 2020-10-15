package restAssured;

import java.util.HashSet;
import java.util.Set;

public class Utils {

    public static String randomIdentifier() {

        java.util.Random rand = new java.util.Random();
        String lexicon = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();
        Set<String> identifiers = new HashSet<String>();

        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}
