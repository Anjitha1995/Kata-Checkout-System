package checkoutSystem;

public class ItemPrice {

	private char item;
	
	private float price;
	
	private SpecialPrice specialPrice;

	public char getItem() {
		return item;
	}

	public void setItem(char item) {
		this.item = item;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public SpecialPrice getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(SpecialPrice specialPrice) {
		this.specialPrice = specialPrice;
	}

}
