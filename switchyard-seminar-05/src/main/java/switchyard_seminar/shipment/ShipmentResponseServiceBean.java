package switchyard_seminar.shipment;

import javax.inject.Inject;

import org.switchyard.Context;
import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import switchyard_seminar.OrderStatusService;
import switchyard_seminar.model.OrderStatus;

@Service(ShipmentResponseService.class)
public class ShipmentResponseServiceBean implements ShipmentResponseService {

	@Inject
	private Context context;

	@Inject
	@Reference
	private OrderStatusService orderStatusService;

	@Override
	public void consume(ShipmentResponse shipmentResponse) {
		String orderId = context.getPropertyValue("orderId");

		OrderStatus orderStatus = orderStatusService.find(Long.valueOf(orderId));
		orderStatus.setShipmentStatus(shipmentResponse.getStatus());
		orderStatusService.save(orderStatus);
	}

}
