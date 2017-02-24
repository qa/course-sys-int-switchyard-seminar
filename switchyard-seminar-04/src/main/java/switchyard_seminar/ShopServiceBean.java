package switchyard_seminar;

import javax.inject.Inject;

import org.switchyard.Context;
import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.ReferenceInvoker;
import org.switchyard.component.bean.Service;

import switchyard_seminar.accounting.AccountResponse;
import switchyard_seminar.accounting.AccountingService;
import switchyard_seminar.inventory.InventoryRequest;
import switchyard_seminar.model.Order;
import switchyard_seminar.model.OrderAck;
import switchyard_seminar.model.OrderStatus;

@Service(ShopService.class)
public class ShopServiceBean implements ShopService {

	@Inject
	Context context;

	@Inject
	@Reference
	OrderStatusService orderStatusService;

	@Inject
	@Reference
	AccountingService accountingService;

	@Inject
	@Reference("ShipmentRequestService")
	ReferenceInvoker shipmentService;

	@Inject
	@Reference("InventoryRequestService")
	ReferenceInvoker inventoryRequestService;

	@Override
	public OrderAck submitOrder(Order order) {
		// Initialize order status
		OrderStatus orderStatus = new OrderStatus(order);
		orderStatusService.save(orderStatus);

		// Assign invoice id and invoice status
		AccountResponse accountResponse = accountingService.account(order);
		orderStatus.setInvoiceId(accountResponse.getInvoiceId());
		orderStatus.setInvoiceStatus(accountResponse.getStatus());
		orderStatusService.save(orderStatus);

		// Deliver the order
		try {
			shipmentService.newInvocation().setProperty("orderId", Long.toString(order.getId()))
					.invoke(order.getAddress());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// Send text file to /target/inbox.inventory
		try {
			inventoryRequestService.newInvocation().setProperty("orderId", Long.toString(order.getId()))
					.invoke(new InventoryRequest(order.getItems()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// Return order acknowledgement
		OrderAck orderAck = new OrderAck();
		orderAck.setOrderId(order.getId());
		return orderAck;
	}

}
