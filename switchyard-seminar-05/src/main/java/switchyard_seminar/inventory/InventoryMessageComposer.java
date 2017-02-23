package switchyard_seminar.inventory;

import org.apache.commons.io.FilenameUtils;
import org.switchyard.Exchange;
import org.switchyard.Message;
import org.switchyard.component.camel.common.composer.CamelBindingData;
import org.switchyard.component.camel.common.composer.CamelMessageComposer;

public class InventoryMessageComposer extends CamelMessageComposer {

	@Override
	public CamelBindingData decompose(Exchange exchange, CamelBindingData target) throws Exception {
		String fileName = exchange.getContext().getPropertyValue("orderId") + ".csv";
		target.getMessage().setHeader(org.apache.camel.Exchange.FILE_NAME, fileName);
		// Here you can set any content to the message
		// CamelBindingData data = super.decompose(exchange, target);
		// data.getMessage().setHeader(org.apache.camel.Exchange.FILE_NAME, fileName);
		// data.getMessage().getExchange().getOut().setBody(fileName);
		return super.decompose(exchange, target);
	}

	@Override
	public Message compose(CamelBindingData source, Exchange exchange) throws Exception {
		Message message = super.compose(source, exchange);
		String fileName = source.getMessage().getHeader(org.apache.camel.Exchange.FILE_NAME_ONLY, String.class);
		message.getContext().setProperty("orderId", FilenameUtils.removeExtension(fileName));
		return message;
	}
}
