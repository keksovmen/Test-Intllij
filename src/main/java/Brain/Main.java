package Brain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static int POWER = 2;

    public static void main(String[] args) {

        new GUI.Main(integer -> POWER = integer, integer -> {
            int degree = POWER;
            List<String> result = new ArrayList<>();
            try {
                result.add(produceOutput(0, degree, integer));
                result.add(produceOutput(1, degree, integer));
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
                result.add("Error");
                result.add("Error");
            }
            return result;
        });
    }

    private static String produceOutput(final int initial, final int degree, final int value) throws CloneNotSupportedException {
        NumberHolder numberHolder = new NumberHolder(initial, degree);
        List<NumberHolder> list = new ArrayList<>();
        while (numberHolder.powerFirst() <= value) {
            NumberHolder increase = numberHolder.increase(value);
            if (increase != null) {
                list.add(increase);
            }
        }
        List<NumberHolder> objects = list.stream().filter(numberHolder1 -> numberHolder1.getCashSum() <= value).collect(Collectors.toList());
        NumberHolder maxHolder = Collections.max(objects, Comparator.comparingInt(NumberHolder::getCashSum));
        StringBuilder stringBuilder = new StringBuilder();
        objects.stream().filter(numberHolder1 -> numberHolder1.equals(maxHolder)).forEach(numberHolder1 -> stringBuilder.append(numberHolder1.toString()));
        stringBuilder.append("Sum = ").append(maxHolder.getCashSum());
        return stringBuilder.toString();
    }


    private static class NumberHolder implements Cloneable {
        private int first, second, third;
//        private NumberHolderBack backup;
        private final int degree;
        private int cashSum;

        private final int INITIAL_VALUE;

        private NumberHolder(final int initial, final int degree) {
            first = second = third = INITIAL_VALUE = initial;
            this.degree = degree;
            cashSum = sum();
//            backup = new NumberHolderBack(initial, cashSum);
        }

        private NumberHolder increase(final int max) throws CloneNotSupportedException {
//            backup.update(first, second, third, cashSum);

            third++;
            if (sum() > max) {
                second++;
                third = INITIAL_VALUE;
                if (sum() > max) {
                    first++;
                    second = INITIAL_VALUE;
                }
            }
            if (cashSum + 7 < max) return null;
            return this.clone();
        }

        private int powerFirst() {
            return (int) Math.pow(first, degree);
        }

//        private void undo() {
//            backup.undo(this);
//        }

        private int sum() {
            return cashSum = (int) (Math.pow(first, degree) + Math.pow(second, degree) + Math.pow(third, degree));
        }

        @Override
        public String toString() {
            return "Result = " + first + " " + second + " " + third + "\n";
        }

        @Override
        public NumberHolder clone() throws CloneNotSupportedException {
//            NumberHolder numberHolder = (NumberHolder) super.clone();
//            numberHolder.backup = backup.clone();
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

        private int getCashSum() {
            return cashSum;
        }

//        private static class NumberHolderBack implements Cloneable {
//            private int first, second, third;
//            private int cashSum;
//
//
//            private NumberHolderBack(final int initial, final int cashSum) {
//                first = second = third = initial;
//                this.cashSum = cashSum;
//            }
//
//            private void undo(NumberHolder actual) {
//                actual.third = third;
//                actual.second = second;
//                actual.first = first;
//                actual.cashSum = cashSum;
//            }
//
//            private void update(final int... ints) {
//                first = ints[0];
//                second = ints[1];
//                third = ints[2];
//                cashSum = ints[3];
//            }
//
//            @Override
//            public NumberHolderBack clone() throws CloneNotSupportedException {
//                return (NumberHolderBack) super.clone();
//            }
//        }
    }
}
