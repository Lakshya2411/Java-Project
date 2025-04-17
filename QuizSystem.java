
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizSystem extends JFrame implements ActionListener {
    String currentUser;

    public QuizSystem(String username) {
        this.currentUser = username;
        setupUI();
    }

    String[] questions = {
            "What is the size of an int variable in Java?",
            "Which of these is not a Java keyword?",
            "Who invented Java?",
            "Which keyword is used to create an object?",
            "Which language is used for Android dev?"
    };

    String[][] options = {
            { "2 bytes", "4 bytes", "8 bytes", "Depends on the system" },
            { "static", "Boolean", "final", "void" },
            { "James Gosling", "Elon Musk", "Guido van Rossum", "Mark Zuckerberg" },
            { "class", "new", "this", "void" },
            { "Python", "Swift", "Java", "C#" }
    };

    String[] answers = { "4 bytes", "Boolean", "James Gosling", "new", "Java" };

    int index = 0, score = 0, timeLeft = 15;
    JLabel questionLabel, timerLabel;
    JRadioButton[] optionButtons = new JRadioButton[4];
    ButtonGroup bg;
    JButton nextButton;
    Timer timer;

    void setupUI() {

        setTitle("Java Quiz - Welcome, " + currentUser);

        setTitle("Java Quiz");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            bg.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        add(optionsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.addActionListener(this);
        bottomPanel.add(nextButton, BorderLayout.EAST);

        timerLabel = new JLabel("Time left: 15s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        timerLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        bottomPanel.add(timerLabel, BorderLayout.WEST);

        add(bottomPanel, BorderLayout.SOUTH);

        loadQuestion();
        startTimer();

        setVisible(true);
    }

    void loadQuestion() {
        if (index >= questions.length) {
            showResult();
            return;
        }

        questionLabel.setText("Q" + (index + 1) + ": " + questions[index]);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[index][i]);
        }
        bg.clearSelection();
        resetTimer();
    }

    void showResult() {
        if (timer != null)
            timer.stop();
        JOptionPane.showMessageDialog(this,
                "Done!\nYour score: " + score + " out of " + questions.length,
                "Quiz Finished",
                JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    void startTimer() {
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                checkAnswer(false);
                index++;
                loadQuestion();
            }
        });
        timer.start();
    }

    void resetTimer() {
        timeLeft = 15;
        timerLabel.setText("Time left: 15s");
    }

    void checkAnswer(boolean clickedNext) {
        if (clickedNext) {
            for (JRadioButton btn : optionButtons) {
                if (btn.isSelected()) {
                    if (btn.getText().equals(answers[index]))
                        score++;
                    break;
                }
            }
        } else {
            for (JRadioButton btn : optionButtons) {
                if (btn.isSelected() && btn.getText().equals(answers[index])) {
                    score++;
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        timer.stop();
        checkAnswer(true);
        index++;
        loadQuestion();
        timer.start();
    }
}
