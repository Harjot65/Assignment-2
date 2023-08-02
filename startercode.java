import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrafficSimulationGame extends JFrame {
    private JLabel label;
    private JButton startButton;
    private Timer timer;
    private int currentLightIndex;
    private String[] lights = {"Red", "Yellow", "Green"};
    private String userGuess;

    public TrafficSimulationGame() {
        setTitle("Traffic Simulation Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        label = new JLabel("Press 'Start' when you think the light is Green");
        add(label);

        startButton = new JButton("Start");
        add(startButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        currentLightIndex = 0;
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentLightIndex = (currentLightIndex + 1) % lights.length;
                label.setText("The light is " + lights[currentLightIndex]);
            }
        });
    }

    private void startGame() {
        userGuess = JOptionPane.showInputDialog(this, "What is the color of the traffic light?");
        checkGuess();
    }

    private void checkGuess() {
        if (userGuess != null && userGuess.equalsIgnoreCase(lights[currentLightIndex])) {
            JOptionPane.showMessageDialog(this, "Congratulations! Your guess is correct.");
        } else {
            JOptionPane.showMessageDialog(this, "Sorry, your guess is incorrect. The correct answer was: " + lights[currentLightIndex]);
        }
        timer.stop();
        startButton.setEnabled(false);
    }

    public void startSimulation() {
        startButton.setEnabled(true);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TrafficSimulationGame game = new TrafficSimulationGame();
                game.setVisible(true);
                game.startSimulation();
            }
        });
    }
}
