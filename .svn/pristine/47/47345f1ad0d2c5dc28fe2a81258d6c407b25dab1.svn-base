package inspur.crawl.common.webclient;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

public class WebClients {
	public static WebClient webclient() {
		WebClient browser = new WebClient(BrowserVersion.CHROME);
		try {
			browser.getOptions().setCssEnabled(false);
			browser.getOptions().setJavaScriptEnabled(false);
			browser.getOptions().setRedirectEnabled(true);
			browser.getOptions().setThrowExceptionOnFailingStatusCode(false);
			browser.getOptions().setThrowExceptionOnScriptError(false);
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			browser.close();
		}
		return browser;
	}
	public static WebClient webclient_js() {
		WebClient browser = new WebClient(BrowserVersion.CHROME);
		try {
			browser.getOptions().setCssEnabled(false);
			browser.getOptions().setJavaScriptEnabled(true);
			browser.getOptions().setRedirectEnabled(true);
			browser.setAjaxController(new NicelyResynchronizingAjaxController());
			browser.getOptions().setThrowExceptionOnFailingStatusCode(false);
			browser.getOptions().setThrowExceptionOnScriptError(false);
		} catch (Exception e) {
//			e.printStackTrace();
		} finally {
			browser.close();
		}
		return browser;
	}
}
