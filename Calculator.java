import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Calculator extends JFrame {
  private JPanel contentPane;
  private JTextField txtDisplay;
  private JButton btn8;
  private JButton btn9;
  private JButton btnSplit;
  private JButton btn4;
  private JButton btn5;
  private JButton btn6;
  private JButton btnMultiply;
  private JButton btn1;
  private JButton btn2;
  private JButton btn3;
  private JButton btnSubtract;
  private JButton btn0;
  private JButton btnDot;
  private JButton btnEqual;
  private JButton btnAddition;
  private JButton btnMPlus; // Button to add in memory
  private JButton btnMR; // Button to read the number in memory
  private JButton btnMC; // Button to clear memory
  private JButton btnC; // Button to clear the result screen

  private double accumulator = 0.0; // To save the result
  private double memory = 0.0; // To store the memory value
  private String operator = "=";
  private boolean firstDigit = true;

  /* Launch the calculator */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          Calculator frame = new Calculator();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public Calculator() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 403, 478);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
    contentPane.setLayout(new BorderLayout(0, 0));
    setContentPane(contentPane);
    txtDisplay = new JTextField();
    txtDisplay.setFont(new Font("Tahoma", Font.PLAIN, 32));
    txtDisplay.setHorizontalAlignment(SwingConstants.RIGHT);
    txtDisplay.setText("0");
    contentPane.add(txtDisplay, BorderLayout.NORTH);
    txtDisplay.setColumns(10);
    JPanel panelButtons = new JPanel();
    contentPane.add(panelButtons, BorderLayout.CENTER);
    panelButtons.setLayout(new GridLayout(5, 4, 5, 5));
    JButton btn7 = new JButton("7");
    panelButtons.add(btn7);
    btn8 = new JButton("8");
    panelButtons.add(btn8);
    btn9 = new JButton("9");
    panelButtons.add(btn9);
    btnSplit = new JButton("/");
    panelButtons.add(btnSplit);
    btn4 = new JButton("4");
    panelButtons.add(btn4);
    btn5 = new JButton("5");
    panelButtons.add(btn5);
    btn6 = new JButton("6");
    panelButtons.add(btn6);
    btnMultiply = new JButton("X");
    panelButtons.add(btnMultiply);
    btn1 = new JButton("1");
    panelButtons.add(btn1);
    btn2 = new JButton("2");
    panelButtons.add(btn2);
    btn3 = new JButton("3");
    panelButtons.add(btn3);
    btnSubtract = new JButton("-");
    panelButtons.add(btnSubtract);
    btn0 = new JButton("0");
    panelButtons.add(btn0);
    btnDot = new JButton(".");
    panelButtons.add(btnDot);
    btnEqual = new JButton("=");
    panelButtons.add(btnEqual);
    btnAddition = new JButton("+");
    panelButtons.add(btnAddition);

    // Botones para memoria y limpiar
    btnMPlus = new JButton("M+");
    panelButtons.add(btnMPlus);
    btnMR = new JButton("MR");
    panelButtons.add(btnMR);
    btnMC = new JButton("MC");
    panelButtons.add(btnMC);
    btnC = new JButton("C");
    panelButtons.add(btnC);

    /* Create the event handler */
    eventHandler handler = new eventHandler();
    btn7.addActionListener(handler);
    btn8.addActionListener(handler);
    btn9.addActionListener(handler);
    btnSplit.addActionListener(handler);
    btn4.addActionListener(handler);
    btn5.addActionListener(handler);
    btn6.addActionListener(handler);
    btnMultiply.addActionListener(handler);
    btn1.addActionListener(handler);
    btn2.addActionListener(handler);
    btn3.addActionListener(handler);
    btnSubtract.addActionListener(handler);
    btn0.addActionListener(handler);
    btnDot.addActionListener(handler);
    btnEqual.addActionListener(handler);
    btnAddition.addActionListener(handler);
    btnMPlus.addActionListener(handler);
    btnMR.addActionListener(handler);
    btnMC.addActionListener(handler);
    btnC.addActionListener(handler);
  }

  public class eventHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String key = e.getActionCommand();
      // JOptionPane.showMessageDialog(null, key);
      if ("0123456789.".indexOf(key) != -1) {
        if (firstDigit) {
          firstDigit = false;
          txtDisplay.setText(key);
        } else {
          txtDisplay.setText(txtDisplay.getText() + key);
        }
      } else {
        if (!firstDigit) {
          calculate(txtDisplay.getText());
          firstDigit = true; // Read the next character
        }
        if (key.equals("=")) {
          operator = key;
        } else if (key.equals("M+")) {
          addToMemory(txtDisplay.getText());
        } else if (key.equals("MR")) {
          readMemory();
        } else if (key.equals("MC")) {
          clearMemory();
        } else if (key.equals("C")) {
          clearAll();
        } else {
          operator = key;
        }
      }
    }

    private void calculate(String s) {
      double value = Double.parseDouble(s);
      char op = operator.charAt(0);

      switch (op) {
        case '=':
          accumulator = value;
          break;
        case '+':
          accumulator += value;
          if (memory != 0.0) {
            accumulator = memory + value;
          }
          break;
        case '-':
          accumulator -= value;
          if (memory != 0.0) {
            accumulator = memory - value;
          }
          break;
        case 'X':
          accumulator *= value;
          if (memory != 0.0) {
            accumulator = memory * value;
          }
          break;
        case '/':
          accumulator /= value;
          if (memory != 0.0) {
            accumulator = memory / value;
          }
          break;
      }
      txtDisplay.setText("" + accumulator);
    }

    private void addToMemory(String s) {
      double value = Double.parseDouble(s);
      memory = value;
    }

    private void readMemory() {
      txtDisplay.setText("" + memory);
    }

    private void clearMemory() {
      memory = 0.0;
    }

    private void clearAll() {
      txtDisplay.setText("0");
      accumulator = 0.0;
      operator = "=";
      firstDigit = true;
    }
  }
}