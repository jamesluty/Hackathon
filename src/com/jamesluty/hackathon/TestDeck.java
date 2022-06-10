package com.jamesluty.hackathon;
import java.util.ArrayList;
import java.util.Scanner;


public class TestDeck {

	public static void main(String[] args) {
		boolean status = true;
		boolean playerStatus = true;
		int playerSum = 0;
		int dealerSum = 0;
		
		Deck myDeck = new Deck();
//		for (Card card: myDeck.getCards()) {
//			card.showCard();
//		}
		
//		Introduction to game
		System.out.println("Welcome to BlackJack");
		System.out.println("Do you want to start a game?");
		System.out.println("Enter yes or no:");
		
//		Player input
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		System.out.println(" ");
		if(input.equals("yes")) {
			
//			Players hand
			System.out.print("Players hand\n");
			System.out.println("---------------------");
			ArrayList<Card> playerHand = myDeck.getHand();
			for(Card card: playerHand) {
				card.showCard();
				myDeck.remove(card);
				if(card.getRank() == 1) {
					playerSum += 11;
				} else if(card.getRank() == 11 || card.getRank() == 12 || card.getRank() == 13) {
					playerSum += 10;
				} else {
					playerSum += card.getRank();
				}
			}
			System.out.println(" ");
			
//			Dealer First Hand
			System.out.println("Dealer showing");
			System.out.println("---------------------");
			ArrayList<Card> dealerHand = myDeck.getHand();
			for(Card card: dealerHand) {
				myDeck.remove(card);
				if(card.getRank() == 1) {
					dealerSum += 11;
				} else if(card.getRank() == 11 || card.getRank() == 12 || card.getRank() == 13) {
					dealerSum += 10;
				} else {
					dealerSum += card.getRank();
				}
			}
			dealerHand.get(0).showCard();
			if(playerSum == 22) {
				playerSum = 12;
			}
			if(dealerSum == 22) {
				dealerSum = 12;
			}
			if(playerSum == 21 && dealerSum != 21) {
				System.out.println("Blackjack, you win?");
			} else if (dealerSum == 21 && playerSum != 21) {
				System.out.println(" ");
				for(Card card: dealerHand) {
					card.showCard();
					myDeck.remove(card);
//					if(card.getRank() == 1) {
//						dealerSum += 11;
//					} else if(card.getRank() == 11 || card.getRank() == 12 || card.getRank() == 13) {
//						dealerSum += 10;
//					} else {
//						dealerSum += card.getRank();
//					}
				}
				System.out.println("Dealer has Blackjack, you lose");
			}

			System.out.println(" ");
			System.out.println("You have " + playerSum);
			System.out.println("Dealer is showing " + dealerHand.get(0).getName());
			System.out.println(" ");
			System.out.println("What would you like to do next?");
			System.out.println("hit or stay");
			
//			Next input option
			if(playerSum < 21 && dealerSum < 21) {
				while(status) {
					String newInput = "";
					if(playerStatus) {
						newInput = s.nextLine();						
					} else {
						newInput = "stay";
					}
					if(newInput.equals("hit")) {
						playerHand.add(myDeck.getCard());
						System.out.println(" ");
						System.out.println("Your new hand");
						System.out.println("---------------------");
						playerSum = 0;
						for(Card card: playerHand) {
							card.showCard();
							myDeck.remove(card);
							if(card.getRank() == 11 || card.getRank() == 12 || card.getRank() == 13) {
								playerSum += 10;
							} else {
								playerSum += card.getRank();
							}
						}
						if(playerSum > 21) {
							System.out.println("Total: " + playerSum + " - Bust, your lose!");
							return;
						} else {
							System.out.println(" ");
							System.out.println("Your total is " + playerSum);
							System.out.println("Dealer is showing " + dealerHand.get(0).getName());
						}
						System.out.println(" ");
						System.out.println("What would you like to do next?");
						System.out.println("hit or stay");
					} else if (newInput.equals("stay")) {
						playerStatus = false;
						if(dealerSum < 16) {
							dealerHand.add(myDeck.getCard());
							System.out.println(" ");
							System.out.println("Dealers takes a card");
							System.out.println("---------------------");
							dealerSum = 0;
							for(Card card: dealerHand) {
								card.showCard();
								myDeck.remove(card);
								if(card.getRank() == 11 || card.getRank() == 12 || card.getRank() == 13) {
									dealerSum += 10;
								} else {
									dealerSum += card.getRank();
								}
							}
							if(dealerSum > 21) {
								System.out.println(dealerSum + " - Bust, your win!");
								status = false;
							} else {
								System.out.println("Dealer has " + dealerSum);	
							}
						} else {
							System.out.println(" ");
							System.out.println("Dealer stays with");
							System.out.println("---------------------");
							dealerSum = 0;
							for(Card card: dealerHand) {
								card.showCard();
								myDeck.remove(card);
								if(card.getRank() == 11 || card.getRank() == 12 || card.getRank() == 13) {
									dealerSum += 10;
								} else {
									dealerSum += card.getRank();
								}
							}
							status = false;
						}
					} else {
						System.out.println("***Invalid input***");
						System.out.println("What would you like to do next?");
						System.out.println("hit or stay");
					}
				}
			}
		} else if (input.equals("no")) {
			System.out.println("Sorry to see you go");
		} else {
			System.out.println("That is not a valid input");
		}
		
		if(input.equals("yes")) {
			System.out.println(" ");
			System.out.println("Your final total is " + playerSum);
			System.out.println("Dealer final total is " + dealerSum);
			if(playerSum == dealerSum) {
				System.out.println("You push!");
			} else if (playerSum > dealerSum || dealerSum > 21){
				System.out.println("You win!");
			} else if (playerSum > 21 || dealerSum > playerSum){
				System.out.println("You lose!");
			}
			
			System.out.println(" ");
			System.out.println("---------------------");
			System.out.println("GAME OVER");
			System.out.println("Player again?");
		}
		s.close();
		
		
		
		
//		for (Card card: myDeck.getCards()) {
//			card.showCard();
//		}
	}
}