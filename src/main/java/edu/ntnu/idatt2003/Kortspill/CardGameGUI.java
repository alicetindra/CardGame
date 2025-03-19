package edu.ntnu.idatt2003.Kortspill;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CardGameGUI extends Application {
  private Label resultLable = new Label();
  private Hand currentHand;
  private VBox root;
  private HBox cardDisplay;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // Create layout
    root = new VBox(10);
    cardDisplay = new HBox(10);
    root.getChildren().add(cardDisplay);

    // Create buttons
    Button dealHandButton = new Button("Deal hand");
    dealHandButton.setOnAction(e -> handleDealHand());

    Button checkCardsButton = new Button("Check hand");
    checkCardsButton.setOnAction(event -> handleCheckCards());

    // Add buttons in layout
    root.getChildren().addAll(dealHandButton, resultLable,checkCardsButton);


    // Create and show scene
    Scene scene = new Scene(root, 400, 300);
    primaryStage.setTitle("Card Game ");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void handleDealHand() {
    DeckOfCards deck = new DeckOfCards();
    currentHand = deck.dealHand(5);

    cardDisplay.getChildren().clear();

    for (int i = 0; i < currentHand.getCards().size(); i++) {
      Image cardBackImage = new Image(getClass().getResourceAsStream("/images/backOfCard.jpg"));
      ImageView cardBackView = new ImageView(cardBackImage);
      cardBackView.setFitWidth(50);
      cardBackView.setFitHeight(75);
      cardDisplay.getChildren().add(cardBackView);
    }
  }

  private void handleCheckCards() {
    if (currentHand == null) {
      resultLable.setText("No hand dealt yet!");
      return;
    }
    cardDisplay.getChildren().clear(); // Rensa tidigare visade kort

    for (PlayingCard card : currentHand.getCards()) {
      String imagePath = getCardImagePath(card);
      Image cardImage = new Image(getClass().getResourceAsStream(imagePath));
      ImageView cardView = new ImageView(cardImage);
      cardView.setFitWidth(50);
      cardView.setFitHeight(75);
      cardDisplay.getChildren().add(cardView);
    }
  }


  private String getCardImagePath(PlayingCard card) {
    String suit = switch (card.getSuit()) {
      case 'H' -> "hearts";
      case 'D' -> "diamonds";
      case 'C' -> "clubs";
      case 'S' -> "spades";
      default -> throw new IllegalArgumentException("Unknown suit");
    };
    String face = switch (card.getFace()) {
      case 1 -> "ace";
      case 11 -> "jack";
      case 12 -> "queen";
      case 13 -> "king";
      default -> String.valueOf(card.getFace());
    };
    return "/images/" + face + "_" + suit + ".jpg";
  }



}