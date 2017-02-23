package switchyard_seminar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.bean.config.model.BeanSwitchYardScanner;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.http.HTTPMixIn;
import org.switchyard.component.test.mixins.naming.NamingMixIn;
import org.switchyard.test.BeforeDeploy;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

import switchyard_seminar.model.Order;
import switchyard_seminar.model.OrderStatus;
import switchyard_seminar.util.JMSUtils;
import switchyard_seminar.util.ModelFactory;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = {
	CDIMixIn.class,
	HTTPMixIn.class,
	NamingMixIn.class }, scanners = { BeanSwitchYardScanner.class, TransformSwitchYardScanner.class })
public class Lab05FILETest {

	private NamingMixIn namingMixIn;

	@ServiceOperation("ShopService")
	private Invoker shopService;

	@ServiceOperation("OrderStatusService")
	private Invoker orderStatusService;

	@BeforeDeploy
	public void dep() throws Exception {
		JMSUtils.bindConnectionFactory(namingMixIn);
	}

	@Test
	public void testSubmitOrder() throws Exception {
		Order message = ModelFactory.createOrder(1l);
		shopService.operation("submitOrder").sendInOut(message);

		// wait for inventory response in target/outbox/inventory
		Thread.sleep(2000);

		OrderStatus orderStatus = orderStatusService.operation("find").sendInOut(1l).getContent(OrderStatus.class);
		Assert.assertEquals("OK", orderStatus.getInventoryStatus());
	}

}
