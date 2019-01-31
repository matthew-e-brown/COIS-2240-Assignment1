class Fibonacci {
    static int iterative(int n) {
        int a = 0, b = 1, c = 1;
        for (int i = 0; i < n; i++) {
            a = b; // n becomes what n + 1 was;
            b = c; // n + 1 becomes what n + 2 was;
            c = a + b; // n + 3 becomes (n + 1) + (n + 2).
        }
        return a;
    }

    static int recursive (int n) {
        if (n == 0 || n == 1) return n; // The 0th and 1st terms ARE 0 and 1
        else return recursive(n - 1) + recursive(n - 2);
    }
}
