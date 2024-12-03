/*
 * Class: CMSC204  CRN 	 22098
 * Instructor: Khandan Monshi
 * Description: contains the static methods to convert morse code into english
 * Due: 12/02/2024
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment 
* independently. I have not copied the code from a student or   * any source. I have not given my code to any student.
 * Print your Name here: Andy Gunawan
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MorseCodeConverter {

    // static MorseCodeTree instance
    private static MorseCodeTree morseCodeTree = new MorseCodeTree();

    // converts a morse code string into english
    public static String convertToEnglish(String code) {
        StringBuilder result = new StringBuilder();
        String[] words = code.split(" / "); // splits morse code into words

        for (String word : words) {
            String[] letters = word.split(" "); // split words into letters
            for (String letter : letters) {
                result.append(morseCodeTree.fetch(letter)); // fetch the letters
            }
            result.append(" "); // adds space between words
        }

        return result.toString().trim(); // removes extra space
    }

    //converts the contents of a file containing  morse code into English 
    //and throws FileNotFoundException if the file cannot be found
    public static String convertToEnglish(File codeFile) throws FileNotFoundException {
        StringBuilder morseCode = new StringBuilder();
        Scanner scanner = new Scanner(codeFile);

        // turns the file's content into a string
        while (scanner.hasNextLine()) {
            morseCode.append(scanner.nextLine()).append(" ");
        }
        scanner.close();

        // converts the morse code string to english
        return convertToEnglish(morseCode.toString().trim());
    }

    // prints the MorseCodeTree in in-order traversal
    public static String printTree() {
        return String.join(" ", morseCodeTree.toArrayList());
    }
}
