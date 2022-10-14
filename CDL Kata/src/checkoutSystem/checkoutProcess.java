package checkoutSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class checkoutProcess {
	
	ArrayList<ItemPrice> pricingRuleList = new ArrayList<>();
	ArrayList<Character> itemsBought = new ArrayList<Character>();
	Scanner scannerObject = new Scanner(System.in);
	float total = 0;
	
	public static void main(String[] args) {
		checkoutProcess checkoutProcess = new checkoutProcess();
		checkoutProcess.getPricingRuleList();
		if (!checkoutProcess.pricingRuleList.isEmpty()) {
			checkoutProcess.displayPricingRuleList();
			checkoutProcess.getItemsBought();
			checkoutProcess.calculateTotal();
			checkoutProcess.displayTotal();
		}
	}
	
	public void getPricingRuleList() {
		char hasMoreItems = ' ';
		do {
			try {
				ItemPrice newItem = new ItemPrice();
				System.out.println("Enter the Item Label ");
				newItem.setItem(scannerObject.next().toCharArray()[0]);
				System.out.println("Enter the Item Price ");
				newItem.setPrice(scannerObject.nextFloat());
				System.out.println("Does this item has any Special Price (Y/N)? ");
				char hasSpecialPrice = scannerObject.next().toCharArray()[0];
				if (hasSpecialPrice == 'Y' || hasSpecialPrice == 'y') {
					SpecialPrice specialPrice = new SpecialPrice();
					System.out.println("Enter the Number of Items that has Special Price ");
					specialPrice.setItemNo(scannerObject.nextInt());
					System.out.println("Enter the Special price for the item " );
					specialPrice.setItemPrice(scannerObject.nextFloat());
					newItem.setSpecialPrice(specialPrice);
				}
			pricingRuleList.add(newItem);
			System.out.println("Do you have more items to add (Y/N)? ");
			hasMoreItems = scannerObject.next().toCharArray()[0];
			} catch (Exception e) {
				System.out.println("Type error!!! Please enter valid Type");
			}
		} while(hasMoreItems == 'Y' || hasMoreItems == 'y');
	}
	
	public void displayPricingRuleList() {
		System.out.println("Item\t Unit Price(Pence)\t Special Price(Pence)");
		System.out.println("-------------------------------------------------------");
		for (ItemPrice itemPrice : pricingRuleList) {
			System.out.print(itemPrice.getItem()+"\t\t"+itemPrice.getPrice()+"\t\t\t");
			if (null != itemPrice.getSpecialPrice()) {
				System.out.println(itemPrice.getSpecialPrice().getItemNo()
						+" for "+ itemPrice.getSpecialPrice().getItemPrice());
			} else {
				System.out.println();
			}
		}
	}
	
	public void getItemsBought() {
		char item = ' ';
		System.out.println("Enter the Items bought, Please enter 0 to total the purchase");
		while (item != '0') {
			item = scannerObject.next().toCharArray()[0];
			if (item == '0') {
				break;
			} else {
				itemsBought.add(item);
			}
		}
	}
	
	public void calculateTotal() {
		int count, remainingItems;
		ArrayList<Character> ItemsBoughtNoDuplicates = new ArrayList<>(
			      new LinkedHashSet<>(itemsBought));
		for (Character item : ItemsBoughtNoDuplicates) {
			for (ItemPrice rule : pricingRuleList) {
				if (item.equals(rule.getItem())) {
					count = Collections.frequency(itemsBought, item);
					if (null != rule.getSpecialPrice()) {
						remainingItems = count %  rule.getSpecialPrice().getItemNo();
						if (remainingItems == 0) {
							total += (count/rule.getSpecialPrice().getItemNo())*rule.getSpecialPrice().getItemPrice();
						} else {
							total = total + (remainingItems*rule.getPrice());
						}
					} else  {
						total = total + (count*rule.getPrice());
					}
				}
				
			}
			
		}
	}
	
	public void displayTotal() {

		if (total > 100) {
			System.out.println("Total Price of the purchase £"+(total/100));
		} else {
			System.out.println("Total Price of the purchase "+total+" pence");
		}
	}


}
