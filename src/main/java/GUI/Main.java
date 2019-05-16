package GUI;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    private JTextField textFieldInput;
    private JTextArea textAreaZeroes;
    private JTextArea textAreaStraight;
    private JButton calculateButton;
    private JPanel mainPane;

    public Main(final Consumer<Integer> consumer, Function<Integer, List<String>> calc) {
        JFrame jFrame = new JFrame("Number Parser");
        jFrame.setSize(300, 300);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.getContentPane().add(mainPane);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Power of digits");
        JSpinner jSlider = new JSpinner(new SpinnerNumberModel(2, 2, Integer.MAX_VALUE, 1));
        jSlider.addChangeListener(e -> consumer.accept((Integer) jSlider.getValue()));
        menu.add(jSlider);
        menuBar.add(menu);
        jFrame.setJMenuBar(menuBar);

        Runnable action = () -> {
            int fromInput = getFromInput();
            if (fromInput == 0) {
                JOptionPane.showMessageDialog(mainPane, "Error, input can't be equal 0 or more than (2^32)/2 - 1 or negative", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            List<String> strings = calc.apply(fromInput);
            textAreaZeroes.setText(strings.get(0));
            textAreaStraight.setText(strings.get(1));
        };

        calculateButton.addActionListener(e -> action.run());


        jFrame.setVisible(true);
        textFieldInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    action.run();
            }
        });
    }

    private int getFromInput() {
        String text = textFieldInput.getText();
        if (text == null || text.equals("")) {
            return 0;
        }
        try {
            int i = Integer.parseInt(text);
            if (i <= 0) return 0;
            return i;
        }catch (NumberFormatException e){
            return 0;
        }
    }

}
