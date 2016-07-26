package inspur.crawl.common.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperty {
	
	 public Properties loadConfiguration(String resource) {
	        Properties prop = new Properties();
	        try {
	            //load a properties file from class path
	            InputStream s = getClass().getResourceAsStream(resource);
	            if(s == null){
	                throw new RuntimeException("Cannot find "+resource);
	            }
	            prop.load(s);
	        }
	        catch (IOException ex) {
	            throw new RuntimeException(ex);
	        }
	        return prop;
	    }
}
