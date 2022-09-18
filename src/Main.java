import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static int MaxSymbolsInWord = 30;

    public static void main(String[] args) throws IOException {
        Path pathname = Paths.get("src/Input.txt");
        String data = Files.readString(pathname);
        List<String> words = SplitByEverything(data);
        words = GetWordWithMostLength(words);

        System.out.println(words);
    }

    private static List<String> GetWordWithMostLength(List<String> strings){
        Comparator<? super String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int lengthL = o1.length();
                int lengthR = o2.length();
                return (lengthL < lengthR) ? 1 : ((lengthL == lengthR) ? 0 : -1);
            }
        };


        strings.sort(comparator);
        
        int cnt = strings.get(0).length();
        int idx = 0;
        List<String> res = new ArrayList<String>();

        while(idx < strings.size() && strings.get(idx).length() == cnt){
            res.add(strings.get(idx++));
        }
        return res;
    }

    private static List<String> SplitByEverything(String data) {
        List<String> res = new ArrayList<String>();
        int oldStart = 0 , currentIndex = -1;
        while(currentIndex < data.length()){
            currentIndex++;
            if (currentIndex != data.length() && ((data.charAt(currentIndex) >= 'a' && data.charAt(currentIndex) <= 'z') || (data.charAt(currentIndex) >= 'A' && data.charAt(currentIndex) <= 'Z'))){
                continue;
            }

            String substr = data.substring(oldStart , currentIndex);
            if (substr.length() > MaxSymbolsInWord)
                substr = substr.substring(0 , MaxSymbolsInWord);
            if (substr.length() > 0)
                res.add(substr);
            oldStart = currentIndex + 1;
        }
        return res;
    }
}