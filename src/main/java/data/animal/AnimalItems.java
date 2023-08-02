package data.animal;

import java.util.Arrays;

public class AnimalItems {
	AnimalItem[] item;

	@Override
	public String toString() {
		return "Items [aitem=" + Arrays.toString(item) + "]";
	}

	public AnimalItem[] getItem() {
		return item;
	}

	public void setItem(AnimalItem[] aitem) {
		this.item = aitem;
	}

	
	
}
