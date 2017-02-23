package switchyard_seminar.util;

import java.util.ArrayList;
import java.util.List;

import org.switchyard.Message;
import org.switchyard.internal.DefaultMessage;

import switchyard_seminar.model.Address;
import switchyard_seminar.model.Order;
import switchyard_seminar.model.OrderItem;

public class ModelFactory {

	private ModelFactory() {
	}

	public Message createOrderMessage(long id) {
		return new DefaultMessage().setContent(createOrder(id));
	}

	public static Order createOrder(long id) {
		Order order = new Order();
		order.setId(id);
		order.setItems(createItems());
		order.setAddress(createAddress());
		return order;
	}

	public static List<OrderItem> createItems() {
		ArrayList<OrderItem> list = new ArrayList<>();

		OrderItem item1 = new OrderItem();
		item1.setArticleId(1);
		item1.setCount(1);
		item1.setUnitPrice(3);
		list.add(item1);

		OrderItem item2 = new OrderItem();
		item2.setArticleId(1);
		item2.setCount(1);
		item2.setUnitPrice(3);
		list.add(item2);
		return list;
	}

	public static Address createAddress() {
		Address address = new Address();
		address.setFirstName("Jiri");
		address.setLastName("Novak");
		address.setStreet("Purkynova");
		address.setCity("Brno");
		address.setZipCode("602 00");
		return address;
	}

}
