public class MergeAlternately {
    public String mergeAlternately(String word1, String word2) {

        StringBuilder builder = new StringBuilder();
        int minLength = Math.min(word1.length(), word2.length());

        int i = 0;
        for (; i < minLength; i++) {
            builder.append(String.valueOf(word1.charAt(i)));
            builder.append(String.valueOf(word2.charAt(i)));
        }

        String biggestWord = "";
        if (word1.length() > word2.length()) {
            biggestWord = word1;
        } else {
            if (word2.length() > word1.length()) {
                biggestWord = word2;
            }
        }

        if (!biggestWord.isEmpty()) builder.append(biggestWord.substring(i, biggestWord.length()));
        return builder.toString();
    }

    public static void main(String[] args) {
        MergeAlternately mergeAlternatly = new MergeAlternately();
        String result = mergeAlternatly.mergeAlternately("abv", "hgtdd");
        System.out.println(result);
    }
}
