package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class WordGame implements ActionListener {
    private String array[] = { "mince", "burnt", "royal", "squad", "black", "scare", "stare", "foray", "poach",
            "sound", "value", "rough", "heart", "bread", "model", "irony", "plead", "drink",
            "frown", "hatch", "comet", "empty", "stern", "newly", "spike", "float", "often",
            "react", "lousy", "rebel", "hound", "fiber", "entry", "scary", "query", "lives",
            "epoch", "taxer", "cited", "wring", "mould", "ruled", "molar", "point", "trash"};
    private double random = Math.random() * array.length - 1;
    private String word = array[(int) random];
    private int count = 0;
    private JLabel label, labelright_pos,labelnot_right_pos, labelnot_in_word ;
    private String guess1;
    private JFrame frame;
    private JPanel panel;
    private JTextArea text;
    private int counter = 1;
    private String right_pos = "";
    private String not_right_pos = "";
    private String not_in_word=" ";
    private Border border = BorderFactory.createLineBorder(Color.BLACK);
    private JButton button;
    private GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
   private GraphicsDevice device = graphics.getDefaultScreenDevice();
   private JButton link, end;
   private Desktop D;

   private JLabel title;
    public WordGame() {
        end=new JButton("Close");
        end.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    selectionButtonPressed2(e);
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } );
        end.setFont(new Font("Verdana", Font.PLAIN,25));
        end.setBackground(Color.RED);
        end.setForeground(Color.WHITE);
        D=Desktop.getDesktop();
        title = new JLabel();
        title.setText("Welcome to WordGame!");
        title.setFont(new Font("Verdana", Font.ROMAN_BASELINE,70));
        title.setForeground(Color.green);
        frame = new JFrame();
        link=new JButton("Link to real Wordle.");
        link.addActionListener(this);
        button = new JButton("Go!");
        button.addActionListener(this);
        button.setBackground(Color.GREEN);
        button.setFont(new Font("Verdana", Font.PLAIN,25));
        link.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    selectionButtonPressed(e);
                } catch (URISyntaxException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } );
        link.setFont(new Font("Verdana", Font.PLAIN,25));
        link.setBackground(Color.blue);
        link.setForeground(Color.white);
        label = new JLabel();
        labelright_pos = new JLabel();
        labelnot_right_pos = new JLabel();
        labelnot_in_word = new JLabel();

        label.setText("Enter a 5-letter word with no capital letters. Remember, do not add any spaces or characters other than letters.");

        label.setFont(new Font("Verdana", Font.ROMAN_BASELINE,25));

        labelnot_in_word.setFont(new Font("Calibri", Font.BOLD,20));
        labelnot_in_word.setForeground(Color.RED);

        labelnot_right_pos.setFont(new Font("Calibri", Font.ROMAN_BASELINE,30));
        labelnot_right_pos.setForeground(Color.BLACK);

        labelright_pos.setFont(new Font("Calibri", Font.ROMAN_BASELINE,30));
        labelright_pos.setForeground(Color.GREEN);

        label.setForeground(Color.BLUE);
        text = new JTextArea();
        text.setFont(new Font("Verdana",Font.PLAIN,30));
        text.setBorder(border);
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 50));
        panel.setLayout(new GridLayout(10,6,30,30));
        panel.setBackground(Color.WHITE);
        panel.add(title);
        panel.add(button);
        panel.add(label);
        panel.add(text);
        panel.add(labelright_pos);
        panel.add(labelnot_right_pos);
        panel.add(labelnot_in_word);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wordle!");
        frame.pack();
        frame.setVisible(true);
        device.setFullScreenWindow(frame);
    }


    public static void main(String[] args) {
        new WordGame();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        counter++;
         String right_pos = "";
         String not_right_pos = "";
        String not_in_word= " ";
        if (text.getText().length() == 5) {
            if (text.getText().equals(word)) {
                label.setText("Correct! It took you " + (counter-1) + " tries!");
                labelright_pos.setText("Congratulations! :)))))))))))))");
                labelnot_right_pos.setText("");
                labelnot_in_word.setText("");
                panel.add(link);
                panel.add(end);
            } else if (!text.getText().contentEquals(word)) {
                label.setText("Incorrect, try again");
                for (int j = 0; j < 6; j++) {
                    labelright_pos.setText(right_pos);
                    labelnot_right_pos.setText(not_right_pos);
                    labelnot_in_word.setText(not_in_word);

                    if (text.getText().charAt(j) == word.charAt(j)) {
                        right_pos += text.getText().charAt(j) + ": right position.    ";
                    } else if (word.contains(Character.toString(text.getText().charAt(j)))) {
                        not_right_pos += text.getText().charAt(j) + ": not in right position.   ";
                    } else if (!word.contains(Character.toString(text.getText().charAt(j)))) {
                        not_in_word += text.getText().charAt(j) + ": not in word.    ";
                    }
                }
            }
        }
        else if(text.getText().length() != 5){
            label.setText("Enter a word that contains five letters");
            labelright_pos.setText("Once the chosen word is entered, select ' Go!' ");
            labelnot_right_pos.setText("");
            labelnot_in_word.setText("");
        }
    }
    public void selectionButtonPressed(ActionEvent E) throws URISyntaxException, IOException {
       D.browse(new URI("https://www.nytimes.com/games/wordle/index.html"));
    }
    public void selectionButtonPressed2(ActionEvent E) throws URISyntaxException, IOException {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

}