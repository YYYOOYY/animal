package data.animal;

public class AnimalBody {
	AnimalItems items;
	int numOfRows;
	int pageNo;
	int totalCount;
	
	@Override
	public String toString() {
		return "Abody [aitems=" + items + ", numOfRows=" + numOfRows + ", pageNo=" + pageNo + ", totalCount="
				+ totalCount + "]";
	}

	public AnimalItems getItems() {
		return items;
	}

	public void setItems(AnimalItems aitems) {
		this.items = aitems;
	}

	public int getNumOfRows() {
		return numOfRows;
	}

	public void setNumOfRows(int numOfRows) {
		this.numOfRows = numOfRows;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
