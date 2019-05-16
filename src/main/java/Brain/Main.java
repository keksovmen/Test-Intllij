package Brain;

import GUI.MainFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    /**
     * Purpose of this program is to find 3 digits that satisfied
     * equation the NUMBER = A^n + B^n + C^n
     * result is most closest combination to the NUMBER
     */

    private static int POWER = 2;   //Initial power of digits, same as in GUI

    public static void main(String[] args) {

        new MainFrame(integer -> POWER = integer, integer -> {
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

    /**
     * Make String readable for humans and to display on GUI
     * @param initial value that could be used
     * @param degree power of digits
     * @param value desired output
     * @return string with all possible combinations
     * @throws CloneNotSupportedException
     */
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

}
