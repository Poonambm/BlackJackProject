import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {

		System.out.println("Welcome to Blackjack.");

		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffleDeck();

		Deck playerCards = new Deck();
		double playerMoney = 100.0;
		Deck dealerCards = new Deck();

		Scanner userInput = new Scanner(System.in);

		while (playerMoney > 0) {
			System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
			double playerBet;
			while (true) {
				playerBet = userInput.nextInt();
				break;

			}
			boolean endRound = false;

			playerCards.draw(playingDeck);
			playerCards.draw(playingDeck);

			dealerCards.draw(playingDeck);
			dealerCards.draw(playingDeck);

			while (true) {
				System.out.println("Your Hand looks like this:" + playerCards.toString());

				System.out.println("Your hand is currently valued at: " + playerCards.cardsValue());

				System.out
						.println("Dealer Hand looks like this: " + dealerCards.getCard(0).toString() + " and [hidden]");

				System.out.println("Would you like to (1)Hit or (2)Stand");
				int response;
				while (true) {
						response = userInput.nextInt();
						break;
					}
				if (response == 1) {
					playerCards.draw(playingDeck);
					System.out.println("You draw a:" + playerCards.getCard(playerCards.deckSize() - 1).toString());
					if (playerCards.cardsValue() > 21) {
						System.out.println("You Busts. Currently valued at: " + playerCards.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}

				if (response == 2) {
					break;
				}

			}

			System.out.println("Dealer Cards:" + dealerCards.toString());
			if ((dealerCards.cardsValue() > playerCards.cardsValue()) && endRound == false) {
				System.out.println("Dealer beats you " + dealerCards.cardsValue() + " to " + playerCards.cardsValue());
				playerMoney -= playerBet;
				endRound = true;
			}
			while ((dealerCards.cardsValue() < 17) && endRound == false) {
				dealerCards.draw(playingDeck);
				System.out.println("Dealer draws: " + dealerCards.getCard(dealerCards.deckSize() - 1).toString());
			}
			System.out.println("Dealers hand value: " + dealerCards.cardsValue());
			if ((dealerCards.cardsValue() > 21) && endRound == false) {
				System.out.println("Dealer Busts. You win!");
				playerMoney += playerBet;
				endRound = true;
			}

			if ((playerCards.cardsValue() > dealerCards.cardsValue()) && endRound == false) {
				System.out.println("You win the hand.");
				playerMoney += playerBet;
				endRound = true;
			} else if (endRound == false) {
				System.out.println("Dealer wins.");
				playerMoney -= playerBet;
			}

			playerCards.moveAllToDeck(playingDeck);
			dealerCards.moveAllToDeck(playingDeck);
			System.out.println("End of Hand.");

		}

		System.out.println("Game over! You lost all your money. :(");
		userInput.close();

	}

}