package com.jamesluty.hackathon;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private ArrayList<Card> cards;
	
    public Deck() {
        this.cards = new ArrayList<Card>();

        // Populate the cards list -- loop to 52
        for (String name : new String[] {"Hearts", "Clubs", "Diamonds", "Spades"}) {
            for (Integer rank = 1; rank <= 13; rank++) {
                this.cards.add(new Card(name, rank));
            }
        }
    }

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public ArrayList<Card> getHand() {
		Random randInt = new Random();
		ArrayList<Card> hand = new ArrayList<>();
		for(int i=0; i<2; i++) {
			hand.add(this.cards.get(randInt.nextInt(this.cards.size())));
		}
		return hand;
	}
	
	public Card getCard() {
		Random randInt = new Random();
		Card card = this.cards.get(randInt.nextInt(this.cards.size()));
		
		return card;
	}

	public void remove(Card showCard) {
		this.cards.remove(showCard);
	}
    
}