package switchyard_seminar.inventory;

import java.util.List;

import switchyard_seminar.model.OrderItem;

public class InventoryRequest {

	private List<OrderItem> orderItems;

	public InventoryRequest() {

	}

	public InventoryRequest(List<OrderItem> orderItems) {
		setOrderItems(orderItems);
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
