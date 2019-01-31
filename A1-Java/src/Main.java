/* # Matthew Brown, #0648289
 * Trent University, COIS-2240H: Assignment #1
 * Fibonacci Sequence through iteration and recursion
 *
 * ## Resources used:
 * Not all of these were used in the final project; these are just the things I looked up during development.
 * ---
 * https://stackoverflow.com/questions/2506077/how-to-read-integer-value-from-the-standard-input-in-java
 * https://stackoverflow.com/a/351571/10549827
 * https://stackoverflow.com/questions/30073980/java-writing-strings-to-a-csv-file
 * https://stackoverflow.com/questions/7586266/is-chain-of-stringbuilder-append-more-efficient-than-string-concatenation/7586780
 * https://stackoverflow.com/questions/3715967/when-should-we-call-system-exit-in-java
 * ---
 */

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int terms = 0;
        boolean validInput = false;
        do {
            try {
                System.out.print("Enter the term number you'd like: ");
                terms = scan.nextInt();
                validInput = (terms >= 0);
            } catch (InputMismatchException ex) {
                scan.nextLine();
                System.out.println("Please enter a positive integer value.");
            }
        } while(!validInput);

        System.out.println(Fibonacci.iterative(terms));
        System.out.println(Fibonacci.recursive(terms));
    }
}