package info.spicyclient.blackjack.cards;

public class Card {
	
	public Card(Suit suit,Type type) {
		
		this.suit = suit;
		this.type = type;
		
		if (type.equals(Type.ACE)){
			this.value = 1;
		}
		else if (type.equals(Type.TWO)){
			this.value = 2;
		}
		else if (type.equals(Type.THREE)){
			this.value = 3;
		}
		else if (type.equals(Type.FOUR)){
			this.value = 4;
		}
		else if (type.equals(Type.FIVE)){
			this.value = 5;
		}
		else if (type.equals(Type.SIX)){
			this.value = 6;
		}
		else if (type.equals(Type.SEVEN)){
			this.value = 7;
		}
		else if (type.equals(Type.EIGHT)){
			this.value = 8;
		}
		else if (type.equals(Type.NINE)){
			this.value = 9;
		}
		else if (type.equals(Type.TEN)){
			this.value = 10;
		}
		else if (type.equals(Type.JACK)){
			this.value = 10;
		}
		else if (type.equals(Type.QUEEN)){
			this.value = 10;
		}
		else if (type.equals(Type.KING)){
			this.value = 10;
		}
		
		
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	public String getName() {
		
		return (type + " OF " + suit).toLowerCase();
		
	}
	
	public int value;
	public Suit suit;
	public Type type;
	
}
