import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GuessTheNumberGameGUI {
    private int numberToGuess;
    private int numberOfTries;
    private int bestScore = Integer.MAX_VALUE;
    private final ArrayList<Integer> guessHistory = new ArrayList<>();

    private final JFrame frame;
    private final JTextField guessField;
    private final JLabel resultLabel;
    private final JLabel historyLabel;
    private final JLabel bestScoreLabel;
    private final JButton guessButton;
    private final JButton resetButton;

    public GuessTheNumberGameGUI() {
        initializeGame();

        // Create the frame
        frame = new JFrame("Guess the Number Game");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Create the label
        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        instructionLabel.setBounds(50, 20, 400, 30);
        frame.add(instructionLabel);

        // Create the text field
        guessField = new JTextField();
        guessField.setBounds(50, 60, 300, 30);
        frame.add(guessField);

        // Create the guess button
        guessButton = new JButton("Guess");
        guessButton.setBounds(360, 60, 80, 30);
        frame.add(guessButton);

        // Create the reset button
        resetButton = new JButton("Reset");
        resetButton.setBounds(360, 100, 80, 30);
        frame.add(resetButton);

        // Create the result label
        resultLabel = new JLabel("");
        resultLabel.setBounds(50, 140, 400, 30);
        frame.add(resultLabel);

        // Create the history label
        historyLabel = new JLabel("Your guesses: ");
        historyLabel.setBounds(50, 180, 400, 30);
        frame.add(historyLabel);

        // Create the best score label
        bestScoreLabel = new JLabel("Best score: -");
        bestScoreLabel.setBounds(50, 220, 400, 30);
        frame.add(bestScoreLabel);

        // Add action listener to the guess button
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        // Add action listener to the reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        // Add action listener to the text field for Enter key
        guessField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GuessTheNumberGameGUI();
    }

    private void initializeGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        numberOfTries = 0;
        guessHistory.clear();
    }

    private void handleGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            numberOfTries++;
            guessHistory.add(guess);

            if (guess < 1 || guess > 100) {
                resultLabel.setText("Please enter a number between 1 and 100.");
            } else if (guess < numberToGuess) {
                resultLabel.setText("It's higher than " + guess + ".");
            } else if (guess > numberToGuess) {
                resultLabel.setText("It's lower than " + guess + ".");
            } else {
                resultLabel.setText("Congratulations! You guessed the number in " + numberOfTries + " tries.");
                if (numberOfTries < bestScore) {
                    bestScore = numberOfTries;
                    bestScoreLabel.setText("Best score: " + bestScore);
                }
            }

            historyLabel.setText("Your guesses: " + guessHistory.toString());
            guessField.setText("");

        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
        }
    }

    private void resetGame() {
        initializeGame();
        resultLabel.setText("");
        historyLabel.setText("Your guesses: ");
        guessField.setText("");
    }
}
