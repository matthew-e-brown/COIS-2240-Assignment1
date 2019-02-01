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
 *
 * ## Disclaimer:
 * This program runs extremely inefficiently. This is only because of how it was asked to be done.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.PrintWriter;

public class Main {
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            char input = getInput();
            switch (input) {
                case 't':
                    Test();
                    break;
                case 'r':
                    Run();
                    break;
                case 'e':
                    try {
                        Export();
                    } catch (FileNotFoundException ex) {
                        System.out.println(ex.toString());
                        System.out.println("Please try again.");
                    }
                    break;
                case 'q':
                    System.exit(0);
            }
            System.out.print("Enter something to confirm and go again: ");
            scan.nextLine();
        }
    }

    private static char getInput() {
        char input;
        System.out.println("\nWhat would you like to do?");
        do {
            System.out.print("\nOptions are:\n" +
                    "Test - Run the program in both modes, printing out all numbers up to a given n. To verify the output of the sequence.\n" +
                    "Run - Run the program and time how long it takes to calculate a given nth term.\n" +
                    "Export - Run the program, without printing, and export the time results to a CSV file.\n" +
                    "Quit - Exit the program.\n\n" +
                    "Enter your choice: ");
            input = scan.nextLine().toLowerCase().charAt(0);
        } while (input != 't' && input != 'r' && input != 'e' && input != 'q');
        return input;
    }

    private static int getTerms() {
        int terms = -1;
        do {
            try {
                System.out.print("Enter the term number you'd like: ");
                terms = scan.nextInt();
            } catch (InputMismatchException ex) {
                scan.nextLine();
                System.out.println("Please enter a positive integer value.");
            }
        } while (terms < 0);
        return terms;
    }

    private static void Test(){
        int terms = getTerms();

        System.out.println("\n\nIterative Test:");
        for (int i = 0; i < terms; i++) {
            System.out.print(Fibonacci.iterative(i) + " ");
        }

        System.out.println("\n\nRecursive Test:");
        for (int i = 0; i < terms; i++) {
            System.out.print(Fibonacci.recursive(i) + " ");
        }
    }

    private static void Run() {
        int terms = getTerms();
        System.out.println("\nIterative Test:");
        long elapsed = Fibonacci.iterativeTest(terms);
        System.out.println("Elapsed time: " + elapsed + " nanoseconds");
        System.out.println("\nRecursive Test:");
        elapsed = Fibonacci.recursiveTest(terms);
        System.out.println("Elapsed time: " + elapsed + " nanoseconds");
    }

    private static void Export() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("data-export.csv"));
        StringBuilder sb = new StringBuilder();
        int t = 0;
        do {
            try {
                System.out.print("Enter how many runs through would you like to do: ");
                t = scan.nextInt();
            } catch (InputMismatchException ex) {
                scan.nextLine();
                System.out.println("Please input an integer.");
            }
        } while (t <= 0);

        sb.append("Call Type, Term Length, ");
        for (int i = 1; i <= t; i++) {
            sb.append(String.format("Run #%d, ", i));
        }
        sb.append("Average Time\n");

        for (int ir = 0; ir <= 1; ir++) { //Do this twice; once for Iterative, once for Recursive
            long[] el = new long[t];
            for (int n = 10; n <= 50; n += 10) {
                for (int i = 0; i < t; i++) {
                    el[i] = (ir == 0) ? Fibonacci.iterativeTest(n) : Fibonacci.recursiveTest(n);
                }
                sb.append(String.format(((ir == 0) ? "Iterative" : "Recursive") + ", %d, ", n));
                long sum = 0;
                for (long l : el) {
                    sb.append(String.format("%d, ", l));
                    sum += l;
                }
                sum /= t; //sum is now avg.
                sb.append(String.format("%d\n", sum));
            }
        }

        pw.write(sb.toString());
        pw.close();
    }
}