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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CardGameGUI extends Application {
  private final Label resultLabel = new Label();
  private final Label sumLabel = new Label();
  private final Label queenSpadesLabel = new Label();
  private final Label cardHeartsLabel = new Label();
  private final Label flushLabel = new Label();
  private Hand currentHand;
  private FlowPane cardDisplay;
  private final DeckOfCards deck = new DeckOfCards();
  private Button dealHandButton;


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

    dealHandButton = new Button("Deal hand");
    dealHandButton.setStyle(
        "-fx-background-color: #a2c6a2;" +
            "-fx-text-fill: black;" +
            "-fx-border-color: #053005;" +
            "-fx-border-width: 2;" +
            "-fx-font-size: 12px;"
            + "-fx-border-radius: 5;"
            + "-fx-background-radius: 5;"
    );
    dealHandButton.setOnAction(e -> handleDealHand());

    Button checkCardsButton = new Button("Check hand");
    checkCardsButton.setStyle("-fx-background-color: #a2c6a2;" +
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

    resultLabel.setStyle(
        "-fx-background-color: #a2c6a2;" +
            "-fx-text-fill: black;"+
            "-fx-border-color: #053005;" +
            "-fx-border-width: 2;" +
            "-fx-padding: 10;" +
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 5;");

    //set resultlable
    root.setTop(resultLabel);

    //Label infobox
    GridPane infobox = new GridPane();
    infobox.setAlignment(Pos.CENTER);
    infobox.setHgap(20);
    infobox.setVgap(10);



    sumLabel.setStyle("-fx-background-color: #a2c6a2;" +
        "-fx-text-fill: black;"+
        "-fx-border-color: #053005;" +
        "-fx-border-width: 2;" +
        "-fx-padding: 10;" +
        "-fx-border-radius: 5;" +
        "-fx-background-radius: 5;");

    queenSpadesLabel.setStyle("-fx-background-color: #a2c6a2;" +
        "-fx-text-fill: black;"+
        "-fx-border-color: #053005;" +
        "-fx-border-width: 2;" +
        "-fx-padding: 10;" +
        "-fx-border-radius: 5;" +
        "-fx-background-radius: 5;");

    cardHeartsLabel.setStyle("-fx-background-color: #a2c6a2;" +
        "-fx-text-fill: black;"+
        "-fx-border-color: #053005;" +
        "-fx-border-width: 2;" +
        "-fx-padding: 10;" +
        "-fx-border-radius: 5;" +
        "-fx-background-radius: 5;");

    flushLabel.setStyle("-fx-background-color: #a2c6a2;" +
        "-fx-text-fill: black;"+
        "-fx-border-color: #053005;" +
        "-fx-border-width: 2;" +
        "-fx-padding: 10;" +
        "-fx-border-radius: 5;" +
        "-fx-background-radius: 5;");

    infobox.add(sumLabel, 0, 0);
    infobox.add(queenSpadesLabel, 0, 1);
    infobox.add(cardHeartsLabel, 1, 0);
    infobox.add(flushLabel, 1, 1);

    root.setBottom(infobox);


    // Create and show scene
    Scene scene = new Scene(root, 450, 300);
    primaryStage.setTitle("Card Game ");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void handleDealHand() {
    try{
      dealHandButton.setText("Deal hand");
      cardDisplay.getChildren().clear();

      currentHand = deck.dealHand(5);

      for (int i = 0; i < currentHand.getCards().size(); i++) {
        Image cardBackImage = new Image(getClass().getResourceAsStream("/images/backOfCard.jpg"));
        ImageView cardBackView = new ImageView(cardBackImage);
        cardBackView.setFitWidth(50);
        cardBackView.setFitHeight(75);
        cardDisplay.getChildren().add(cardBackView);
      }

      sumLabel.setText("Sum of cards: " + currentHand.getSum());
      queenSpadesLabel.setText("Queen of spades: " + currentHand.checkQueenSpades());
      cardHeartsLabel.setText("Cards of hearts: " + currentHand.cardHearts());
      flushLabel.setText("Flush: " + currentHand.flush());
      resultLabel.setText("Dealt hand:\n" + currentHand.toString());

    }catch(IllegalArgumentException e){
      resultLabel.setText("Not enough cards left, reshuffle the deck!");
      dealHandButton.setText("Shuffle deck");
      deck.resetDeck();
    }

  }

  private void handleCheckCards() {
    try{
      if (currentHand == null) {
        resultLabel.setText("No hand dealt yet!");
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
      resultLabel.setText("Something went wrong trying to load the picture.");
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