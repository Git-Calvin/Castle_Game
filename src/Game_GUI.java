import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Catherine Huang
 *
 * on 12/8/14.
 */
public class Game_GUI {

    private JFrame frame;
    private JPanel inputPanel;
    private JPanel roomPanel;
    private JTextArea prison;
    private JTextArea academy;
    private JTextArea embassy;
    private JTextArea treasury;
    private JTextArea altar;
    private JTextArea market;

    private JPanel infoPanel;
    private JTextArea infoText;

    private Game_Controller control;

    /**
     * Constructor
     */
    public Game_GUI(){
        makeFrame();
        control = new Game_Controller();
    }

    /**
     * Make the window frame
     */
    private void makeFrame(){
        frame = new JFrame("Rebellion");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800,800);

        makeContent(frame);

        frame.setBackground(Color.getHSBColor(10,99,35));
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Put all contents into a container
     * @param frame
     */
    private void makeContent(JFrame frame){
        Container content = frame.getContentPane();
        content.setSize(700,700);

        makeButtons();
        makeRooms();
        makeLabels();

        content.add(inputPanel,BorderLayout.SOUTH);
        content.add(roomPanel, BorderLayout.CENTER);
        content.add(infoPanel, BorderLayout.EAST);

    }

    /**
     * Create all the buttons and TextField for user input
     */
    private void makeButtons() {
        inputPanel = new JPanel();

        final JLabel hintLabel = new JLabel("Please enter a number then press Add Players to Start the Game");
        final JTextField maxPlayerNumber = new JTextField(10);

        //Add Player Button
        JButton addButton = new JButton("Add Players");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        clearTextArea();
                        control.setCheck(false);
                        int input = Integer.parseInt(maxPlayerNumber.getText());
                        control.newPlayers(input);
                        treasury.setText(control.showRoom("Treasury"));
                    } catch (NumberFormatException n) {
                        hintLabel.setText("You need to enter an number before pressing Add Players");
                        hintLabel.setForeground(Color.RED);
                    }

            }
        });

        //Next move
        JButton startButton = new JButton("Next Move");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTextArea();
                maxPlayerNumber.setText(" ");
                if(control.wasGameWon() == true) {
                    infoText.setText("GameOver");
                }
                else {
                    hintLabel.setText("Please enter a number then press Add Players to Start the Game");
                    hintLabel.setForeground(Color.BLACK);
                    infoText.setText(control.movePlayer());
                    prison.setText(control.showRoom("Prison"));
                    market.setText(control.showRoom("Market"));
                    altar.setText(control.showRoom("Altar"));
                    embassy.setText(control.showRoom("Embassy"));
                    treasury.setText(control.showRoom("Treasury"));
                    academy.setText(control.showRoom("Academy"));
                }
            }
        });

        //Restart button
        JButton restartButton = new JButton("New Game");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control = new Game_Controller();
                clearTextArea();
                hintLabel.setText("Please enter a number then press Add Players to Start the Game");
                hintLabel.setForeground(Color.BLACK);
            }
        });

        //Quit Game button
        JButton endButton = new JButton("Quit Game");
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        inputPanel.add(hintLabel);
        inputPanel.add(maxPlayerNumber);
        inputPanel.add(addButton);
        inputPanel.add(startButton);
        inputPanel.add(restartButton);
        inputPanel.add(endButton);
    }

    /**
     * Create all the rooms as TextArea
     */
    private void makeRooms(){
        roomPanel = new JPanel(new GridLayout(2,3,2,2));

        //rooms
        prison = new JTextArea("Prison",10,20);
        embassy = new JTextArea("Embassy",10,20);
        academy = new JTextArea("Academy",10,20);
        treasury = new JTextArea("Treasury",10,20);
        altar = new JTextArea("Altar",10,20);
        market = new JTextArea("Market",10,20);

        roomPanel.add(prison);
        roomPanel.add(embassy);
        roomPanel.add(academy);
        roomPanel.add(treasury);
        roomPanel.add(altar);
        roomPanel.add(market);
    }

    /**
     * Create the information text display of the moves
     */
    private void makeLabels() {
        infoPanel = new JPanel();

        infoText = new JTextArea(30,25);

        infoPanel.add(infoText);
    }

    public void clearTextArea() {
        prison.setText("Prison");
        treasury.setText("Treasury");
        academy.setText("Academy");
        market.setText("Market");
        altar.setText("Altar");
        embassy.setText("Embassy");
        infoText.setText(" ");
    }
} // end of class
