import java.util.*;

public class StringReplacerDemo {

    public static int numCharsChanged = 0;
    public static String newString;
    public static StringBuilder replace(StringBuilder str, int end) {


        if (end - 1 == -1) {
            return str;

        } else if (str.charAt(end) == 'a') {
            str.setCharAt(end, 'e');
            numCharsChanged++;
            replace(str, (end -1));


        } else if (str.charAt(end) == 'e') {
            str.setCharAt(end, 'i');
            numCharsChanged++;
            replace(str, (end -1));


        } else if (str.charAt(end) == 'i') {
            str.setCharAt(end, 'o');
            numCharsChanged++;
            replace(str, (end -1));

        } else if (str.charAt(end) == 'o') {
            str.setCharAt(end, 'u');
            numCharsChanged++;
            replace(str, (end -1));

        } else if (str.charAt(end) == 'u') {
            str.setCharAt(end, 'a');
            numCharsChanged++;
            replace(str, (end -1));

        } else {
            replace(str, (end - 1));
        }return str;

    }

    public static void main(String[] args) {

        System.out.print("Enter a word or sentence: ");
        Scanner input = new Scanner(System.in);
        StringBuilder str = new StringBuilder(input.nextLine());
        String str1 = str.toString();
        int end = str.length() - 1;
        StringReplacerDemo.replace(str, end);

        System.out.printf("%s would be %s\nNumber of characters replaced %d",str1, str, StringReplacerDemo.numCharsChanged);

    }
}

