import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        if (L.size() == 0) {
            return 0;
        }
        int sum = 0;
        for (int num : L) {
            sum += num;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> lst = new ArrayList<>();
        for (int num : L) {
            if (num % 2 == 0) {
                lst.add(num);
            }
        }
        return lst;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> lst = new ArrayList<>();
        for (int num : L1) {
            if (L2.contains(num)) {
                lst.add(num);
            }
        }
        return lst;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int sum = 0;
        for (String word : words) {
            for (int i = 0; i < word.length(); i ++) {
                if (word.charAt(i) == c) {
                    sum ++;
                }
            }
        }
        return sum;
    }
}
