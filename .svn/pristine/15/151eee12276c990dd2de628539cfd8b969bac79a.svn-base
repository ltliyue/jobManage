package inspur.crawl.ruleManage.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.html.dom.HTMLDocumentImpl;
import org.apache.http.HttpStatus;
import org.apache.xpath.XPathAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.gargoylesoftware.htmlunit.SgmlPage;
import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.TextUtil;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponseData;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.gargoylesoftware.htmlunit.xml.XmlPage;

/**
 * Utils to process HTML file. Class to do some general operations on HTML file.
 * 
 * @author maolihua
 * @date
 * @history
 */
public class HtmlUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlUtils.class);
	/**
	 * Regular expression of the content of <script> <style> comment tags.
	 */
	private static String REGEX_SCRIPT = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
	private static String REGEX_NOSCRIPT = "<[\\s]*?noscript[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?noscript[\\s]*?>";
	private static String REGEX_STYLE = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";

	private static String REGEX_DOCTYPE = "<!DOCTYPE[^>]*?>";
	private static String REGEX_COMMENT = "<!--[\\s\\S]*?-->";
	private static String REGEX_XML = "<\\?xml[\\s\\S]*?\\?>";

	private static String REGEX_LINK = "<[\\s]*?link[^>]*?\\/[\\s]*?>";
	private static String REGEX_HEAD_INNER = "<[\\s]*?head[^>]*?>([\\s\\S]*?)<[\\s]*?\\/[\\s]*?head[\\s]*?>";

	private static final String INDENT = "";

	/**
	 * Clean the HTML source file.
	 * 
	 * @param page
	 * @return
	 */
	public static String clean(String page) {
		if (page == null || page.isEmpty())
			return page;

		try {

			// remove all the empty string in between two different tags.
			page = page.replaceAll("(?<=>)\\s+(?=<)", "");

			Pattern pattern = Pattern.compile(REGEX_DOCTYPE, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(page);
			page = matcher.replaceAll("");

			pattern = Pattern.compile(REGEX_COMMENT, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(page);
			page = matcher.replaceAll("");

			pattern = Pattern.compile(REGEX_XML, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(page);
			page = matcher.replaceAll("");

			pattern = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(page);
			StringBuffer script = new StringBuffer();
			while (matcher.find()) {
				// keep all the css in javascript
				script.append(matcher.group() + "\r\n");
			}
			page = matcher.replaceAll("");

			pattern = Pattern.compile(REGEX_LINK, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(script.toString());
			StringBuffer link = new StringBuffer();
			while (matcher.find()) {
				// keep all the css in javascript in head
				link.append(matcher.group() + "\r\n");
			}

			pattern = Pattern.compile(REGEX_HEAD_INNER, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(page);
			// keep all the css in head
			String headInner = null;
			if (matcher.find()) {
				headInner = matcher.group(1);
			}
			page = matcher.replaceAll("<head>\r\n" + headInner + "\r\n" + link.toString() + "\r\n</head>\r\n");

			pattern = Pattern.compile(REGEX_NOSCRIPT, Pattern.CASE_INSENSITIVE);
			matcher = pattern.matcher(page);
			page = matcher.replaceAll("");

			page = page.replaceAll("<html[^>]+>", "<html>");

			if (page.contains("<html>") && page.contains("</html>") && page.startsWith("<text")
					&& page.contains("</text>")) {
				page = page.replaceFirst("<html>", "");
				page = page.replaceFirst("<head>", "");
				page = page.replaceFirst("<text", "<meta");
				page = page.replaceFirst("</text>", "");
				page = "<html>\r\n\t<head>\r\n" + page;
			}

			if ((page.contains("<html") == false && page.contains("<HTML") == false)
					&& page.contains("<body>") == false) {
				page = "<html>\r\n<body>\r\n" + page + "\r\n</body>\r\n</html>\r\n";
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return page;
	}

	/**
	 * 
	 * @param url
	 * @param n
	 * @return
	 */
	public static String getDomainName(String url, int n) {
		if (url == null || url.isEmpty() || n < 0) {
			return null;
		}
		url = url.replaceAll("http[s]?://", "");
		int id = url.indexOf('/');
		if (id != -1) {
			url = url.substring(0, id);
		}
		String domain = "";
		String[] tokens = url.split("\\.");
		for (int i = n; i < tokens.length; i++) {
			domain = domain + "." + tokens[i];
		}
		if (domain.length() > 0) {
			domain = domain.substring(1);
		} else {
			domain = null;
		}
		return domain;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	/**
	 * htmlunit 2.14 ok , htmlunit 2.21 not ok
	public static Document getDocument(String str) {

		if (str == null) {
			return null;
		}
		str = str.replaceAll(">\\s+<", "><");
		DOMParser parser = new DOMParser();
		try {
			// parser.setProperty("http://cyberneko.org/html/properties/names/elems",
			// "lower");
			String page = str;// HtmlUtils.clean(str);
			InputSource inputSource = new InputSource(new StringReader(page));
			parser.parse(inputSource);
			return parser.getDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/

	/**
	 * 
	 * @param str
	 * @return
	 * @throws IOException
	 */
	/**
	 * htmlunit 2.14 ok , htmlunit 2.21 not ok
	 * @param file
	 * @param charset
	 * @return
	 * @throws IOException
	public static Document getDocument(File file, String charset) throws IOException {

		if (file == null) {
			return null;
		}
		if (charset == null)
			charset = "utf-8";
		String content = FileUtils.readFileToString(file, charset);
		return getDocument(content);
	}
	*/

	/**
	 * 
	 * @param str
	 * @return
	 */
	/**htmlunit 2.14 ok , htmlunit 2.21 not ok
	public static Node getDOMTree(String str) {

		if (str == null) {
			return null;
		}

		DOMParser parser = new DOMParser();
		try {
			String page = HtmlUtils.clean(str);
			InputSource inputSource = new InputSource(new StringReader(page));
			parser.parse(inputSource);
			Node node = ((Document) parser.getDocument()).getFirstChild();
			return node;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/

	/**
	 * 
	 * @param file
	 * @return
	 */
	/**htmlunit 2.14 ok , htmlunit 2.21 not ok
	public static Node getDOMTree(File file, String charset) {

		if (file == null) {
			return null;
		}

		DOMParser parser = new DOMParser();
		try {
			String page = FileUtils.readFileToString(file, charset);
			page = HtmlUtils.clean(page);
			InputSource inputSource = new InputSource(new StringReader(page));
			parser.parse(inputSource);
			return ((Document) parser.getDocument()).getFirstChild();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/

	/**
	 * 
	 * @param node
	 */
	public static void removeFromParent(Node node) {
		if (node == null)
			return;
		Node parentNode = node.getParentNode();
		if (parentNode != null) {
			parentNode.removeChild(node);
			return;
		}
	}

	/**
	 * Clean the HTML file by its DOM structure.
	 * 
	 * @param node
	 */
	public static void clean(Node node) {
		if (node == null)
			return;

		String nodeName = node.getNodeName();
		if (nodeName.equalsIgnoreCase("SCRIPT") || nodeName.equalsIgnoreCase("NOSCRIPT")
				|| nodeName.equalsIgnoreCase("LINK") || nodeName.equalsIgnoreCase("STYLE")
				|| nodeName.equalsIgnoreCase("SELECT") || nodeName.equalsIgnoreCase("TEXTAREA")) {

			removeFromParent(node);
			return;
		}

		if (nodeName.equalsIgnoreCase("#text")) {
			String content = node.getTextContent();
			if (content != null) {
				if (content.startsWith("<!--") && content.endsWith("-->")) {
					removeFromParent(node);
					return;
				}
			}
		}

		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node styleAttrNode = attrMap.getNamedItem("style");
			if (styleAttrNode != null) {
				String value = styleAttrNode.getTextContent();
				if (value.equalsIgnoreCase("display:none") || value.equalsIgnoreCase("visibility:hidden")) {
					removeFromParent(node);
					return;
				}
			}
		}

		// empty node ( contains no string)
		String nodeValue = node.getNodeValue();

		if (nodeValue != null && nodeValue.trim().length() == 0) {
			removeFromParent(node);
			return;
		}

		//

		NodeList childList = node.getChildNodes();
		List<Node> list = new ArrayList<Node>();
		for (int i = 0; i < childList.getLength(); i++) {
			list.add(childList.item(i));
		}
		for (Node tNode : list) {
			clean(tNode);
		}

	}

	/**
	 * Remove all the link in node.
	 * 
	 * @param node
	 */
	public static void removeLinks(Node node) {
		if (node == null)
			return;

		String nodeName = node.getNodeName();
		if (nodeName.equalsIgnoreCase("A")) {
			Node parentNode = node.getParentNode();
			if (parentNode != null) {
				parentNode.removeChild(node);
				return;
			}
		}

		NodeList childList = node.getChildNodes();
		List<Node> list = new ArrayList<Node>();
		for (int i = 0; i < childList.getLength(); i++) {
			list.add(childList.item(i));
		}
		for (Node tNode : list) {
			removeLinks(tNode);
		}
	}

	/**
	 * Print the DOM tree structure in format. Each sub-node is one space indent
	 * compared to its parent.
	 * 
	 * @param node
	 */
	public static void printDOMTreeView(Node node) {
		printDOMTreeView(node, 0);
	}

	public static String printDOMTree(Node node) {

		String content = "";
		if (node != null) {
			if (node.getNodeType() == Node.TEXT_NODE)
				return node.getNodeValue();

			NodeList childList = node.getChildNodes();

			if (childList != null) {
				int childSize = childList.getLength();
				for (int i = 0; i < childSize; i++) {
					Node child = childList.item(i);
					content += " " + printDOMTree(child);
				}
			}
			return content;
		}
		return null;
	}

	/**
	 * Print the DOM tree structure in format.
	 * 
	 * @param node
	 * @param space
	 *            how many spaces should be print in front of the current node.
	 */
	private static void printDOMTreeView(Node node, int space) {
		if (node != null) {
			for (int i = 0; i < space; i++)
				System.out.print("  ");
			System.out.printf("%4d", space);
			System.out.print("<" + node.getNodeName() + ">");
			if (node.getParentNode() != null) {
				System.out.print(" " + node.getParentNode().getNodeName());
			}
			LOGGER.info("");
			NodeList childList = node.getChildNodes();
			NamedNodeMap tMap = node.getAttributes();
			if (tMap != null) {
				for (int i = 0; i < tMap.getLength(); i++) {
					Node tNode = tMap.item(i);
					for (int j = 0; j < space; j++)
						System.out.print("  ");

					LOGGER.info("Attribute:" + tNode.getNodeName() + " " + tNode.getNodeValue());
				}
			}
			if (childList != null) {
				int childSize = childList.getLength();
				for (int i = 0; i < childSize; i++) {
					Node child = childList.item(i);
					printDOMTreeView(child, space + 1);
				}
			}
		}
	}

	/**
	 * 
	 * @param SgmlPage
	 * @return
	 */
	public static String getEncoding(String SgmlPage) {
		if (SgmlPage == null)
			return null;

		Pattern pattern = Pattern.compile("(charset|encoding)\\s*=\\s*\"?\\s*([0-9a-zA-Z-]+)\\s*\"?");

		int len = 300;
		if (len > SgmlPage.length())
			len = SgmlPage.length();
		String headingPart = SgmlPage.substring(0, len);
		headingPart = headingPart.toLowerCase();
		Matcher matcher = pattern.matcher(headingPart);
		if (matcher.find()) {
			return matcher.group(2);
		}
		return null;
	}

	private static void getNodesByTagName(Node node, String tagName, List<Node> nodes) {
		if (node != null) {
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				nodes.add(node);
			}
			NodeList children = node.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				getNodesByTagName(children.item(i), tagName, nodes);
			}

		}
	}

	public static List<Node> getChildrenByTagName(Node node, String tagName) {
		List<Node> rs = new ArrayList<Node>();
		if (node == null)
			return rs;
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (tagName != null && child.getNodeName().equalsIgnoreCase(tagName)) {
				rs.add(child);
			}
		}
		return rs;
	}

	public static List<Node> getSuccessorByTagName(Node node, String tagName, int level) {
		List<Node> rs = new ArrayList<Node>();
		if (node == null || tagName == null || tagName.isEmpty())
			return rs;
		List<Node> ancestors = new ArrayList<Node>();
		ancestors.add(node);

		while (level > 0 && ancestors.size() > 0) {
			boolean find = false;
			List<Node> successors = new ArrayList<Node>();
			for (Node ancestor : ancestors) {
				NodeList children = ancestor.getChildNodes();
				for (int i = 0; i < children.getLength(); i++) {
					Node child = children.item(i);
					if (child.getNodeName().equalsIgnoreCase(tagName)) {
						find = true;
					}
					successors.add(child);
				}
			}
			if (find) {
				level--;
			}
			ancestors = successors;
		}

		if (ancestors.size() > 0) {
			for (Node ancestor : ancestors) {
				if (ancestor.getNodeName().equalsIgnoreCase(tagName)) {
					rs.add(ancestor);
				}
			}
		}

		return rs;
	}

	public static List<Node> getNodesByTagName(Node node, String tagName) {
		List<Node> nodes = new ArrayList<Node>();
		if (node != null && tagName != null) {
			getNodesByTagName(node, tagName, nodes);
		}
		return nodes;
	}

	public static Node getFirstNodeByTagName(Node node, String tagName) {
		if (node != null) {
			if (node.getNodeName().equalsIgnoreCase(tagName)) {
				return node;
			}
			NodeList children = node.getChildNodes();
			for (int i = 0; i < children.getLength(); i++) {
				Node tNode = getFirstNodeByTagName(children.item(i), tagName);
				if (tNode != null)
					return tNode;
			}

		}
		return null;
	}

	public static NodeList getNodesByXpath(Node node, String xpath) throws TransformerException {
		if (node != null && xpath != null) {
			return XPathAPI.selectNodeList(node, xpath);
		}
		return null;
	}

	public static boolean hasAttribute(Node node, String attrKey) {
		if (node != null && attrKey != null) {
			NamedNodeMap attrMap = node.getAttributes();
			if (attrMap != null) {
				Node attrNode = attrMap.getNamedItem(attrKey);
				if (attrNode != null) {
					return true;
				}
			}
		}
		return false;
	}

	public static String getAttribute(Node node, String attrKey) {
		if (node == null || attrKey == null) {
			return null;
		}

		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			attrKey = attrKey.toLowerCase();
			Node attrNode = attrMap.getNamedItem(attrKey);
			if (attrNode != null) {
				return attrNode.getTextContent();
			}
		}
		return null;
	}

	public static String getAttributeIgnoreCase(Node node, String attrKey) {
		if (node == null || attrKey == null) {
			return null;
		}
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node attrNode = attrMap.getNamedItem(attrKey);
			if (attrNode != null) {
				return attrNode.getTextContent();
			}
			attrNode = attrMap.getNamedItem(attrKey.toLowerCase());
			if (attrNode != null) {
				return attrNode.getTextContent();
			}
			attrNode = attrMap.getNamedItem(attrKey.toUpperCase());
			if (attrNode != null) {
				return attrNode.getTextContent();
			}
		}
		return null;
	}

	public static void setAttribute(Node node, String attrKey, String attrValue) {
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node attrNode = attrMap.getNamedItem(attrKey);
			if (attrNode != null) {
				attrNode.setTextContent(attrValue);
			}
		}
	}

	public static Node getFirstByXPath(Node node, String xpath) throws TransformerException {
		if (node != null) {
			return XPathAPI.selectSingleNode(node, xpath);
		}
		return null;
	}

	public static void removeAllChildren(Node node) {
		if (node != null) {
			NodeList children = node.getChildNodes();
			for (int i = children.getLength() - 1; i >= 0; i--) {
				node.removeChild(children.item(i));
			}
		}
	}

	public static Node createNode(Document document, String tagName) {
		if (document != null) {
			return document.createElement(tagName);
		}
		return null;
	}

	public static Document cloneDocument(Document doc) {
		if (doc != null) {
			Node htmlNode = doc.getFirstChild();
			Document newDoc = new HTMLDocumentImpl();
			Node newHtmlNode = htmlNode.cloneNode(true);
			newDoc.adoptNode(newHtmlNode);
			newDoc.appendChild(newHtmlNode);
			return newDoc;
		}
		return null;
	}

	public static String getTextContent(Node node) {
		if (node == null)
			return null;
		StringBuffer buffer = new StringBuffer();
		getTextContent(node, buffer);
		return buffer.toString();
	}

	public static String getTextContentWithoutLinks(Node node) {
		if (node == null)
			return null;
		StringBuffer buffer = new StringBuffer();
		getTextContentWithoutLinks(node, buffer);
		return buffer.toString();
	}

	public static String getTextContent(Node node, String tagSplitChar) {
		if (node == null)
			return null;
		StringBuffer buffer = new StringBuffer();
		getTextContent(node, buffer, tagSplitChar);
		return buffer.toString();
	}

	public static void getTextContent(Node node, StringBuffer buffer, String tagSplitChar) {
		if (node.getNodeName().equalsIgnoreCase("script") || node.getNodeName().equalsIgnoreCase("style"))
			return;
		if (node.getNodeName().equalsIgnoreCase("#text")) {
			if (buffer != null) {
				String content = node.getTextContent().trim();
				if (content.length() > 0) {
					buffer.append(content);
				}
			}
		} else {
			buffer.append(tagSplitChar);
		}
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			getTextContent(nodes.item(i), buffer, tagSplitChar);
		}
	}

	public static void getTextContent(Node node, StringBuffer buffer) {
		if (node.getNodeName().equalsIgnoreCase("script") || node.getNodeName().equalsIgnoreCase("style"))
			return;
		if (node.getNodeName().equalsIgnoreCase("#text")) {
			if (buffer != null) {
				String content = node.getTextContent().replaceAll("\\s+", "");
				if (content.length() > 0) {
					buffer.append(content);
				}
			}
		}
		if (node.getNodeName().equalsIgnoreCase("br")) {
			if (buffer != null) {
				buffer.append("\r\n");
			}
		}
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			getTextContent(nodes.item(i), buffer);
		}
	}

	public static void getTextContentWithoutLinks(Node node, StringBuffer buffer) {
		if (node.getNodeName().equalsIgnoreCase("script") || node.getNodeName().equalsIgnoreCase("style")
				|| node.getNodeName().equals("A"))
			return;
		if (node.getNodeName().equalsIgnoreCase("#text")) {
			if (buffer != null) {
				String content = node.getTextContent().replaceAll("\\s+", "");
				if (content.length() > 0) {
					buffer.append(content);
				}
			}
		}
		NodeList nodes = node.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			getTextContentWithoutLinks(nodes.item(i), buffer);
		}
	}

	public static String asXml(final Document doc) throws TransformerException {
		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.transform(domSource, result);
		return writer.toString();
	}

	public static String asXml(Node node) {

		final StringWriter stringWriter = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(stringWriter);

		printXml(node, "", printWriter);
		printWriter.close();
		return stringWriter.toString();
	}

	public static boolean sameStyle(List<Node> nodes) {
		if (nodes == null || nodes.size() < 2)
			return true;
		Node node = nodes.get(0);
		for (int i = 1; i < nodes.size(); i++) {
			if (HtmlUtils.sameStyle(nodes.get(i), node) == false)
				return false;
		}
		return true;
	}

	public static boolean sameStyle(NodeList nodes) {
		if (nodes == null || nodes.getLength() < 2)
			return true;
		Node node = nodes.item(0);
		for (int i = 1; i < nodes.getLength(); i++) {
			if (HtmlUtils.sameStyle(nodes.item(i), node) == false)
				return false;
		}
		return true;
	}

	public static boolean sameStyle(Node node1, Node node2) {
		if (node1 == null || node2 == null)
			return false;
		if (node1.getNodeName().equalsIgnoreCase(node2.getNodeName()) == false)
			return false;

		NamedNodeMap slibingAttrMap = node1.getAttributes();
		NamedNodeMap nodeAttrMap = node2.getAttributes();
		if (nodeAttrMap == null && slibingAttrMap == null)
			return true;
		if (nodeAttrMap == null)
			return false;
		if (slibingAttrMap == null)
			return false;

		for (int j = 0; j < nodeAttrMap.getLength(); j++) {
			Node attrNode = nodeAttrMap.item(j);
			String nodeName = attrNode.getNodeName();
			if (nodeName.equals("id") == false) {
				Node slibingAttrNode = slibingAttrMap.getNamedItem(nodeName);
				if (slibingAttrNode == null
						|| attrNode.getTextContent().equals(slibingAttrNode.getTextContent()) == false) {
					return false;
				}
			}
		}

		for (int j = 0; j < slibingAttrMap.getLength(); j++) {
			Node slibingAttrNode = slibingAttrMap.item(j);
			String nodeName = slibingAttrNode.getNodeName();
			if (nodeName.equals("id") == false) {
				Node attrNode = slibingAttrMap.getNamedItem(nodeName);
				if (attrNode == null || attrNode.getTextContent().equals(slibingAttrNode.getTextContent()) == false) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * ��content�й���SgmlPage
	 * 
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static SgmlPage getPage(WebClient client, String content, String url) throws IOException {
		if (content == null)
			return null;

		SgmlPage page = null;
		StringWebResponse response = new StringWebResponse(content, new URL(url));
		page = HTMLParser.parseHtml(response, client.getCurrentWindow());

		return page;
	}
	
	private static WebResponseData getWebResponseData(final String contentString, final String charset) {
        final byte[] content = TextUtil.stringToByteArray(contentString, charset);
        final List<NameValuePair> compiledHeaders = new ArrayList<NameValuePair>();
        compiledHeaders.add(new NameValuePair("Content-Type", "text/html"));
        return new WebResponseData(content, HttpStatus.SC_OK, "OK", compiledHeaders);
    }
	
	public static SgmlPage getPage( String content, String url) throws IOException {
		if (content == null)
			return null;

		WebClient client = new WebClient();
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setThrowExceptionOnFailingStatusCode(false);
		client.getOptions().setThrowExceptionOnScriptError(false);
		SgmlPage page = null;
		
		StringWebResponse response = new StringWebResponse(content,"utf-8", new URL(url));
		String charset = response.getContentCharsetOrNull();
		if(charset==null){
			charset = "utf-8";
		}
		response = new StringWebResponse(content,charset, new URL(url));
		page = HTMLParser.parseHtml(response, client.getCurrentWindow());
		return page;
	}

	/**
	 * Prints the content between "&lt;" and "&gt;" (or "/&gt;") in the output
	 * of the tag name and its attributes in XML format.
	 * 
	 * @param printWriter
	 *            the writer to print in
	 */
	protected static void printOpeningTagContentAsXml(DomNode node, final PrintWriter printWriter) {
		printWriter.print(node.getNodeName());
		NamedNodeMap attrMap = node.getAttributes();
		for (int i = 0; i < attrMap.getLength(); i++) {
			Node attrNode = attrMap.item(i);
			printWriter.print(" ");
			printWriter.print(attrNode.getNodeName());
			printWriter.print("=\"");
			printWriter.print(
					com.gargoylesoftware.htmlunit.util.StringUtils.escapeXmlAttributeValue(attrNode.getNodeValue()));
			printWriter.print("\"");
		}
	}

	protected static void printOpeningTagContentAsXml(Node node, final PrintWriter printWriter) {
		printWriter.print(node.getNodeName());
		NamedNodeMap attrMap = node.getAttributes();
		for (int i = 0; i < attrMap.getLength(); i++) {
			Node attrNode = attrMap.item(i);
			printWriter.print(" ");
			printWriter.print(attrNode.getNodeName());
			printWriter.print("=\"");
			printWriter.print(
					com.gargoylesoftware.htmlunit.util.StringUtils.escapeXmlAttributeValue(attrNode.getNodeValue()));
			printWriter.print("\"");
		}
	}

	/**
	 * Recursively write the XML data for the node tree starting at
	 * <code>node</code>.
	 * 
	 * @param indent
	 *            white space to indent child nodes
	 * @param printWriter
	 *            writer where child nodes are written
	 */
	protected static void printXml(DomNode node, final String indent, final PrintWriter printWriter) {
		final boolean hasChildren = (node.getFirstChild() != null);
		if (node != null) {
			if (node.getNodeName().equals("#text")) {
				printWriter.print(indent + node.getTextContent());
				printWriter.print("\r\n");
			} else if (node.getNodeName().equals("#comment")) {
				printWriter.print(indent + "<!--\r\n");
				printChildrenAsXml(node, indent, printWriter);
				printWriter.print("\r\n-->\r\n");
			} else {
				printWriter.print(indent + "<");
				printOpeningTagContentAsXml(node, printWriter);
				printWriter.print(">");
				printWriter.print("\r\n");
				printChildrenAsXml(node, indent, printWriter);
				printWriter.print(indent + "</" + node.getNodeName() + ">");
				printWriter.print("\r\n");
			}
		}
	}

	protected static void printXml(Node node, final String indent, final PrintWriter printWriter) {
		final boolean hasChildren = (node.getFirstChild() != null);
		if (node != null) {
			if (node.getNodeName().equals("#text")) {
				printWriter.print(indent + node.getTextContent());
				printWriter.print("\r\n");
			} else if (node.getNodeName().equals("#comment")) {
				printWriter.print(indent + "<!--\r\n");
				printChildrenAsXml(node, indent, printWriter);
				printWriter.print("\r\n-->\r\n");
			} else {
				printWriter.print(indent + "<");
				printOpeningTagContentAsXml(node, printWriter);
				printWriter.print(">");
				printWriter.print("\r\n");
				printChildrenAsXml(node, indent, printWriter);
				printWriter.print(indent + "</" + node.getNodeName() + ">");
				printWriter.print("\r\n");
			}
		}
	}

	/**
	 * htmlunit 2.14 ok , htmlunit 2.21 not ok
	 * @param page
	 * @return
	public static String getXml(SgmlPage page) {
		if (page instanceof HtmlPage) {
			return asXml(page.getDocumentElement());
		} else if (page instanceof XmlPage) {
			String content = page.asText();
			content = clean(content);
			Document document = getDocument(content);
			try {
				return asXml(document);
			} catch (TransformerException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}
	*/

	/**
	 * Returns a string representation of the XML document from this element and
	 * all it's children (recursively). The charset used is the current page
	 * encoding.
	 * 
	 * @return the XML string
	 */
	public static String asXml(DomNode node) {

		final StringWriter stringWriter = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(stringWriter);

		printXml(node, "", printWriter);
		printWriter.close();
		return stringWriter.toString();
	}

	/**
	 * Recursively writes the XML data for the node tree starting at
	 * <code>node</code>.
	 * 
	 * @param indent
	 *            white space to indent child nodes
	 * @param printWriter
	 *            writer where child nodes are written
	 */
	protected static void printChildrenAsXml(DomNode node, final String indent, final PrintWriter printWriter) {
		DomNode child = node.getFirstChild();
		while (child != null) {
			printXml(child, indent + "  ", printWriter);
			child = child.getNextSibling();
		}
	}

	protected static void printChildrenAsXml(Node node, final String indent, final PrintWriter printWriter) {
		Node child = node.getFirstChild();
		while (child != null) {
			printXml(child, indent + "  ", printWriter);
			child = child.getNextSibling();
		}
	}
}
