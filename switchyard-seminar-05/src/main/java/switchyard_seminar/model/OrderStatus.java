package switchyard_seminar.model;

public class OrderStatus {

	private Order order;

	private long invoiceId;
	private String invoiceStatus;
	private String inventoryStatus;
	private String shipmentStatus;

	public OrderStatus(Order order) {
		this.order = order;

		invoiceStatus = "PROCESSED";
		inventoryStatus = "PROCESSED";
		shipmentStatus = "PROCESSED";
	}

	public Order getOrder() {
		return order;
	}

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	@Override
	public String toString() {
		return "OrderStatus{" + "invoiceId=" + invoiceId + ", submitOrder=" + order + ", invoice='" + invoiceStatus
				+ '\'' + ", inventory='" + inventoryStatus + '\'' + ", shipment='" + shipmentStatus + '\'' + '}';
	}
}
