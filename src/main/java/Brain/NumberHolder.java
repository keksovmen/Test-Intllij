package Brain;

public class NumberHolder implements Cloneable {

    private int first, second, third;
    private final int degree;
    private int cashSum;

    private final int INITIAL_VALUE;
    private static final int ERROR_RANGE = 7;

    public NumberHolder(final int initial, final int degree) {
        first = second = third = INITIAL_VALUE = initial;
        this.degree = degree;
        cashSum = sum();
    }

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

    public int powerFirst() {
        return (int) Math.pow(first, degree);
    }

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

    public int getCashSum() {
        return cashSum;
    }
}
