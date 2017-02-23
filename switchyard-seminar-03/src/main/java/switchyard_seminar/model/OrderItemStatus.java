package switchyard_seminar.model;

public class OrderItemStatus {

	private long articleId;

	private int amountReserved;

	private int amountLeft;

	public OrderItemStatus() {

	}

	public OrderItemStatus(long articleId, int amountReserved, int amountLeft) {
		this.articleId = articleId;
		this.amountReserved = amountReserved;
		this.amountLeft = amountLeft;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public int getAmountReserved() {
		return amountReserved;
	}

	public void setAmountReserved(int amountReserved) {
		this.amountReserved = amountReserved;
	}

	public int getAmountLeft() {
		return amountLeft;
	}

	public void setAmountLeft(int amountLeft) {
		this.amountLeft = amountLeft;
	}

}
