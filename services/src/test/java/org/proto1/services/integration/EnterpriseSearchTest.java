package org.proto1.services.integration;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.junit.Test;
import static org.elasticsearch.node.NodeBuilder.*;

public class EnterpriseSearchTest {

	@Test
	public void test() {
		// on startup

		Node node = nodeBuilder()
		        .settings(Settings.settingsBuilder().put("http.enabled", false).put("path.home", "e:/"))
		        .client(true)
		        .node();
		Client client = node.client();

		// on shutdown

		node.close();	}

}
