package switchyard_seminar;

import switchyard_seminar.model.Order;
import switchyard_seminar.model.OrderAck;

public interface ShopService {

	OrderAck submitOrder(Order order);
}
