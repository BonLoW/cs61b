package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        // TODO: Fill in this method.
        Map<Character, Integer> freq = new TreeMap<Character, Integer>();
        for (String word: words) {
            List<Character> uniqu = new ArrayList<>();
            for (int i = 0 ; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!uniqu.contains(c)) {
                    uniqu.add(c);
                    if (freq.containsKey(c)) {
                        freq.replace(c, freq.get(c) + 1);
                    } else {
                        freq.put(c, 1);
                    }
                }
            }
        }
        return freq;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> freq = getFrequencyMap();
        Character maxKey = '?';
        int maxValue = Integer.MIN_VALUE;
        for (Character c: freq.keySet()) {
            if (guesses.contains(c)) continue;
            if (freq.get(c) > maxValue) {
                maxValue = freq.get(c);
                maxKey = c;
            }
        }
        return maxKey;
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
