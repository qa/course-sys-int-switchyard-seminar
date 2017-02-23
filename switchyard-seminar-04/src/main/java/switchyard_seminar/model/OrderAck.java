package switchyard_seminar.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrderAck {

	private long orderId;

	public OrderAck() {
		
	}
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	
}
