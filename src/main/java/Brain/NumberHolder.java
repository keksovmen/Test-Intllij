package Brain;

public class NumberHolder implements Cloneable {

    private int first, second, third;
    private final int degree;
    private int cashSum;

    private final int INITIAL_VALUE;
    private static final int ERROR_RANGE = 7;

    /**
     * Wrapper for 3 digits
     * Can increase it self, like a 3 digits lock, starts from right
     * @param initial value, 0 or 1 or whatever you need
     * @param degree power of digits
     */

    public NumberHolder(final int initial, final int degree) {
        first = second = third = INITIAL_VALUE = initial;
        this.degree = degree;
        cashSum = sum();
    }

    /**
     * Increases its values and if it was in the range return copy
     * @param max the digit you want to achieve
     * @return clone of this object
     * @throws CloneNotSupportedException
     */

    public NumberHolder increase(final int max) throws CloneNotSupportedException {
        third++;
        if (sum() > max) {
            second++;
            third = INITIAL_VALUE;
            if (sum() > max) {
                first++;
                second = INITIAL_VALUE;
            }
        }
        if (cashSum + ERROR_RANGE < max) return null;
        return this.clone();
    }

    /**
     * Needed to less computing comparison
     * @return power of the first digit
     */
    public int powerFirst() {
        return (int) Math.pow(first, degree);
    }

    /**
     *
     * @return A^n + B^n + C^n
     */
    private int sum() {
        return cashSum = (int) (Math.pow(first, degree) + Math.pow(second, degree) + Math.pow(third, degree));
    }

    @Override
    public String toString() {
        return "Result = " + first + " " + second + " " + third + "\n";
    }

    @Override
    public NumberHolder clone() throws CloneNotSupportedException {
        return (NumberHolder) super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof NumberHolder) {
            NumberHolder o = (NumberHolder) obj;
            return cashSum == o.cashSum;
        }
        return false;
    }

    /**
     * To minimize cost of computing
     * @return cashSum
     */
    public int getCashSum() {
        return cashSum;
    }
}
