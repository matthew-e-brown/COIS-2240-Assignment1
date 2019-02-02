class Fibonacci {
    /* Method:      iterative
     * Arguments:   int n - the term of the Fibonacci Sequence to be calculated
     * This method runs through the basic building blocks of the Fibonacci sequence.
     * If we imagine a window of 3 terms, with n as the first one...
     * It works by shifting term 3 into 2, 2 into 1, and the generating a new 3 using 1 and 2.
     * This repeats until 1 is equal to the desired term, at which point it is returned.
     */
    static long iterative(int n) {
        long a = 0, b = 1, c = 1;
        for (int i = 0; i < n; i++) {
            a = b; // n becomes what n + 1 was;
            b = c; // n + 1 becomes what n + 2 was;
            c = a + b; // n + 3 becomes (n + 1) + (n + 2).
        }
        return a;
    }

    /* Method:      recursive
     * Arguments:   int n - the term of the Fibonacci Sequence to be calculated
     * Each time the recursive function is called, it will call itself again, and again and....
     * Until it has called itself about 'n' times, at which point the value being passed to the
     * function will have been knocked down to 1 or 0, which will mean that the returns will stop
     * being recursive, resulting in, rather than a few additions of large numbers, n additions of 1.
     */
    static long recursive (int n) {
        if (n == 0 || n == 1) return n; // The 0th and 1st terms ARE 0 and 1
        else return recursive(n - 1) + recursive(n - 2);
    }

    /* Method:      iterativeTest
     * Arguments:   int n - the term of the Fibonacci Sequence to be calculated & timed
     * Runs the above method, except it tests how long it takes in nanoseconds, and returns
     * the elapsed time.
     */
    static long iterativeTest(int n) {
        long[] times = new long[2];
        times[0] = System.nanoTime();
        iterative(n); // Calculate it, but ignore the result
        times[1] = System.nanoTime();
        return times[1] - times[0];
    }

    /* Method:      recursiveTest
     * Arguments:   int n - the term of the Fibonacci Sequence to be calculated & timed
     * Runs the Fibonacci.recursive method, and tests how long it takes in nanoseconds,
     * returning the elapsed time.
     */
    static long recursiveTest(int n) {
        long[] times = new long[2];
        times[0] = System.nanoTime();
        recursive(n); // Calculate it, but ignore the result
        times[1] = System.nanoTime();
        return times[1] - times[0];
    }
}
