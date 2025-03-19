package edu.ntnu.idatt2003.Kortspill;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CardGameGUI extends Application {
  private Label resultLable = new Label();
  private Hand currentHand;
  private FlowPane cardDisplay;
  private DeckOfCards deck = new DeckOfCards();



  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    // Create layout
    BorderPane root = new BorderPane();
    root.setStyle("-fx-background-color: #4f7850;");


    //Card display
    cardDisplay = new FlowPane();
    cardDisplay.setHgap(10);
    cardDisplay.setVgap(10);
    cardDisplay.setPrefWrapLength(250);
    cardDisplay.setStyle("-fx-padding: 20;");
    root.setCenter(cardDisplay);

    // Create buttons
    VBox buttonBox = new VBox(10);
    buttonBox.setPadding(new Insets(40, 20, 0, 10));
    buttonBox.setAlignment(Pos.CENTER);

    Button dealHandButton = new Button("Deal hand");
    dealHandButton.setStyle(
        "-fx-background-color: #6f9c6f;" +
            "-fx-text-fill: black;" +
            "-fx-border-color: #053005;" +
            "-fx-border-width: 2;" +
            "-fx-font-size: 12px;"
            + "-fx-border-radius: 5;"
            + "-fx-background-radius: 5;"
    );
    dealHandButton.setOnAction(e -> handleDealHand());

    Button checkCardsButton = new Button("Check hand");
    checkCardsButton.setStyle("-fx-background-color: #6f9c6f;" +
        "-fx-text-fill: black;" +
        "-fx-border-color: #053005;" +
        "-fx-border-width: 2;" +
        "-fx-font-size: 12px;"
        + "-fx-border-radius: 5;"
        + "-fx-background-radius: 5;");
    checkCardsButton.setOnAction(event -> handleCheckCards());

    // Add buttons in layout
    buttonBox.getChildren().addAll(dealHandButton,checkCardsButton);
    root.setRight(buttonBox);

    //Result label style
    resultLable.setStyle(
        "-fx-background-color: #6f9c6f;" +
            "-fx-text-fill: black;"+
        "-fx-border-color: #053005;" +
        "-fx-border-width: 2;" +
        "-fx-padding: 10;" +
        "-fx-border-radius: 5;" +
        "-fx-background-radius: 5;");

    StackPane bottomBox = new StackPane(resultLable);
    bottomBox.setStyle("-fx-padding: 20");
    root.setBottom(bottomBox);


    // Create and show scene
    Scene scene = new Scene(root, 450, 300);
    primaryStage.setTitle("Card Game ");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void handleDealHand() {
    try{
      currentHand = deck.dealHand(5);

      cardDisplay.getChildren().clear();

      for (int i = 0; i < currentHand.getCards().size(); i++) {
        Image cardBackImage = new Image(getClass().getResourceAsStream("/images/backOfCard.jpg"));
        ImageView cardBackView = new ImageView(cardBackImage);
        cardBackView.setFitWidth(50);
        cardBackView.setFitHeight(75);
        cardDisplay.getChildren().add(cardBackView);
      }
      resultLable.setText("Dealt hand:\n" + currentHand.toString());
    }catch(IllegalArgumentException e){
      resultLable.setText("Not enough cards left, reshuffle the deck!");
      deck.resetDeck();
    }

  }

  private void handleCheckCards() {
    try{
      if (currentHand == null) {
        resultLable.setText("No hand dealt yet!");
        return;
      }
      cardDisplay.getChildren().clear();

      for (PlayingCard card : currentHand.getCards()) {
        String imagePath = getCardImagePath(card);
        Image cardImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView cardView = new ImageView(cardImage);
        cardView.setFitWidth(50);
        cardView.setFitHeight(75);
        cardDisplay.getChildren().add(cardView);
      }
    }catch (IllegalArgumentException e){
      resultLable.setText("Something went wrong trying to load the picture.");
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