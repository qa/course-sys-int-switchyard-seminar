package switchyard_seminar;

import java.util.HashMap;
import java.util.Map;

import org.switchyard.component.bean.Service;

import switchyard_seminar.model.OrderStatus;

@Service(OrderStatusService.class)
public class OrderStatusServiceBean implements OrderStatusService {

	public static Map<Long, OrderStatus> storage = new HashMap<>();

	@Override
	public void save(OrderStatus status) {
		storage.put(status.getOrder().getId(), status);
	}

	@Override
	public OrderStatus find(Long id) {
		return storage.get(id);
	}
}
