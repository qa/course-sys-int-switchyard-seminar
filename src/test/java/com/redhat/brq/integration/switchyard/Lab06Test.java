package com.redhat.brq.integration.switchyard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.test.mixins.http.HTTPMixIn;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;


@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(mixins = HTTPMixIn.class)
public class Lab06Test {

    HTTPMixIn httpMixIn;

    @Test
    public void testSOAPService() throws InterruptedException {
        httpMixIn.postResourceAndTestXML("http://localhost:8181/cxf/OrderService?wsdl", "/xml/soap-order.xml", "/xml/soap-order-response.xml");
    }
}
