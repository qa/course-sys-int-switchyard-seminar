package switchyard_seminar;

import switchyard_seminar.model.OrderStatus;

public interface OrderStatusService {

	void save(OrderStatus order);

	OrderStatus find(Long id);

}
