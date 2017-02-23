package switchyard_seminar.shipment;

import org.switchyard.Exchange;
import org.switchyard.Message;
import org.switchyard.Scope;
import org.switchyard.component.camel.common.composer.CamelBindingData;
import org.switchyard.component.camel.common.composer.CamelMessageComposer;

public class ShipmentMessageComposer extends CamelMessageComposer {

	@Override
	public CamelBindingData decompose(Exchange exchange, CamelBindingData target) throws Exception {
		Object orderId = exchange.getContext().getPropertyValue("orderId");
		if (orderId != null) {
			target.getMessage().setHeader("orderId", orderId);	
		}
		return super.decompose(exchange, target);
	}

	@Override
	public Message compose(CamelBindingData source, Exchange exchange) throws Exception {
		Message message =  super.compose(source, exchange);
		Object orderId = source.getMessage().getHeader("orderId");
		if (orderId != null) {
			message.getContext().setProperty("orderId", orderId.toString(), Scope.MESSAGE);
		}
		return message;
	}

}
