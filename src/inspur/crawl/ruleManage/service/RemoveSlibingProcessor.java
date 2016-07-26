package inspur.crawl.ruleManage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * 
 * @author maolihua
 *
 */
public class RemoveSlibingProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(RemoveSlibingProcessor.class);

	public static Document process(String url, String charset, Document htmlDoc, Node node) {
		try {
			if (htmlDoc != null && node != null) {
				while (node.getParentNode() != null && node.getNodeName().equalsIgnoreCase("body") == false) {
					Node parNode = node.getParentNode();
					Node slibing = parNode.getFirstChild();
					while (slibing != null) {
						Node nextSlibing = slibing.getNextSibling();
						if (slibing != node && slibing.getNodeName().equalsIgnoreCase("script") == false
								&& slibing.getNodeName().equalsIgnoreCase("style") == false) {
							parNode.removeChild(slibing);
						}
						slibing = nextSlibing;
					}
					node = node.getParentNode();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return htmlDoc;
	}

}
