import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class PokerShuffler extends JFrame {

  private ArrayList<String> deck;
  private JPanel cardPanel;
  private JButton shuffleButton;

  public PokerShuffler() {

    setTitle("Poker Card Shuffler");
    setSize(900, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Create the deck
    deck = new ArrayList<String>();
    createDeck();

    // Create card panel with 4 rows and 13 columns
    cardPanel = new JPanel();
    cardPanel.setLayout(new GridLayout(4, 13));

    // Create shuffle button
    shuffleButton = new JButton("Shuffle Cards");
    shuffleButton.addActionListener(new ShuffleButtonListener());

    // Set layout of frame
    setLayout(new BorderLayout());
    add(cardPanel, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel();
    bottomPanel.add(shuffleButton);
    add(bottomPanel, BorderLayout.SOUTH);

    drawCards();
  }

  // Creates the 52-card deck
  private void createDeck() {

    String[] suits = { "spades", "hearts", "diamonds", "clubs" };
    String[] values = { "ace", "2", "3", "4", "5", "6", "7",
        "8", "9", "10", "jack", "queen", "king" };

    for (int i = 0; i < suits.length; i++) {
      for (int j = 0; j < values.length; j++) {
        deck.add(values[j] + "_of_" + suits[i] + ".png");
      }
    }
  }

  // Draw cards on screen
  private void drawCards() {

    cardPanel.removeAll();

    for (int i = 0; i < deck.size(); i++) {

      String path = "assets/" + deck.get(i);

      ImageIcon originalIcon = new ImageIcon(path);

      // Resize image
      Image scaledImage = originalIcon.getImage().getScaledInstance(70, 105, Image.SCALE_SMOOTH);
      ImageIcon scaledIcon = new ImageIcon(scaledImage);

      JLabel cardLabel = new JLabel(scaledIcon);

      cardPanel.add(cardLabel);
    }

    cardPanel.revalidate();
    cardPanel.repaint();
  }

  // Shuffle the deck
  private void shuffleDeck() {
    Collections.shuffle(deck);
  }

  // Button listener
  private class ShuffleButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      shuffleDeck();
      drawCards();
    }
  }

  public static void main(String[] args) {

    PokerShuffler gui = new PokerShuffler();
    gui.setVisible(true);
  }
}
