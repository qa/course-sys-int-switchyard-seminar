package switchyard_seminar;

import javax.inject.Inject;

import org.switchyard.Context;
import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import switchyard_seminar.model.OrderStatus;
import switchyard_seminar.shipment.ShipmentResponse;
import switchyard_seminar.shipment.ShipmentResponseService;

@Service(ShipmentResponseService.class)
public class ShipmentResponseServiceBean implements ShipmentResponseService {

	@Inject
	Context context;

	@Inject
	@Reference
	OrderStatusService orderStatusService;

	@Override
	public void consume(ShipmentResponse shipmentResponse) {
		String orderId = context.getPropertyValue("orderId");

		OrderStatus orderStatus = orderStatusService.find(Long.valueOf(orderId));
		orderStatus.setShipmentStatus(shipmentResponse.getStatus());
		orderStatusService.save(orderStatus);
	}

}
