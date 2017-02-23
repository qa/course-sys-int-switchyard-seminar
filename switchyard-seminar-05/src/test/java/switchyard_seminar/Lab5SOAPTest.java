package switchyard_seminar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.http.HTTPMixIn;
import org.switchyard.component.test.mixins.naming.NamingMixIn;
import org.switchyard.test.BeforeDeploy;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

import switchyard_seminar.util.JMSUtils;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(mixins = {
	CDIMixIn.class,
	HTTPMixIn.class,
	NamingMixIn.class }, config = SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = TransformSwitchYardScanner.class)
public class Lab5SOAPTest {

	HTTPMixIn httpMixIn;

	NamingMixIn namingMixIn;

	@BeforeDeploy
	public void dep() throws Exception {
		JMSUtils.bindConnectionFactory(namingMixIn);
	}

	@Test
	public void testSOAPService() throws InterruptedException {
		String result = httpMixIn.postResource("http://localhost:8080/ShopService?wsdl", "/xml/soap-order-request.xml");

		System.out.println(result);

		httpMixIn.postResourceAndTestXML("http://localhost:8080/ShopService?wsdl", "/xml/soap-order-request.xml",
				"/xml/soap-order-response.xml");
	}
}
