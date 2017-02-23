package switchyard_seminar.inventory;

import java.util.List;

import switchyard_seminar.model.OrderItemStatus;

public class InventoryResponse {

	private String orderId;

	private List<OrderItemStatus> orderItemStatuses;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String responseId) {
		this.orderId = responseId;
	}

	public List<OrderItemStatus> getOrderItemStatuses() {
		return orderItemStatuses;
	}

	public void setOrderItemStatuses(List<OrderItemStatus> orderItemStatuses) {
		this.orderItemStatuses = orderItemStatuses;
	}
}
