import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameUI extends JFrame {
    private int numberToGuess;
    private int numberOfAttempts;
    private final JTextField guessInputField;
    private final JLabel resultLabel;
    private final JLabel attemptsLabel;

    public NumberGuessingGameUI() {
        // Initialize the game
        initializeGame();

        // Set up the UI components
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Label to instruct the player
        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        instructionLabel.setBounds(50, 20, 300, 25);
        add(instructionLabel);

        // Text field for player's input
        guessInputField = new JTextField();
        guessInputField.setBounds(50, 50, 100, 25);
        add(guessInputField);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(160, 50, 80, 25);
        submitButton.addActionListener(new SubmitButtonListener());
        add(submitButton);

        // Restart button
        JButton restartButton = new JButton("Restart");
        restartButton.setBounds(250, 50, 80, 25);
        restartButton.addActionListener(new RestartButtonListener());
        add(restartButton);

        // Label to show the result
        resultLabel = new JLabel("");
        resultLabel.setBounds(50, 80, 300, 25);
        add(resultLabel);

        // Label to show remaining attempts
        attemptsLabel = new JLabel("Attempts left: " + numberOfAttempts);
        attemptsLabel.setBounds(50, 110, 300, 25);
        add(attemptsLabel);
    }

    // Method to initialize the game state
    private void initializeGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;  // Random number between 1 and 100
        numberOfAttempts = 5;  // Set the number of attempts
    }

    // ActionListener for the submit button
    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int userGuess = Integer.parseInt(guessInputField.getText());
                numberOfAttempts--;

                // Game logic
                if (userGuess == numberToGuess) {
                    resultLabel.setText("Congratulations! You guessed the correct number!");
                    guessInputField.setEditable(false);
                } else if (userGuess < numberToGuess) {
                    resultLabel.setText("Too low! Try again.");
                } else {
                    resultLabel.setText("Too high! Try again.");
                }

                // Update attempts label
                attemptsLabel.setText("Attempts left: " + numberOfAttempts);

                // Check if out of attempts
                if (numberOfAttempts == 0 && userGuess != numberToGuess) {
                    resultLabel.setText("Sorry, you're out of attempts! The number was: " + numberToGuess);
                    guessInputField.setEditable(false);
                }

            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter a valid number.");
            }
        }
    }

    // ActionListener for the restart button
    private class RestartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            initializeGame();  // Reset the game
            guessInputField.setEditable(true);  // Allow input again
            guessInputField.setText("");  // Clear input field
            resultLabel.setText("");  // Clear result message
            attemptsLabel.setText("Attempts left: " + numberOfAttempts);  // Reset attempts label
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        NumberGuessingGameUI gameUI = new NumberGuessingGameUI();
        gameUI.setVisible(true);
    }
}
