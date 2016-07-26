package inspur.crawl.common.tools;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateEditor extends PropertyEditorSupport {  
    private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");  
    private static final DateFormat TIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat MINUTETIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
    private static final DateFormat HOURTIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd HH");  
  
    private DateFormat dateFormat;  
    private boolean allowEmpty = true;  
  
    public DateEditor() {  
    }  
  
    public DateEditor(DateFormat dateFormat) {  
        this.dateFormat = dateFormat;  
    }  
  
    public DateEditor(DateFormat dateFormat, boolean allowEmpty) {  
        this.dateFormat = dateFormat;  
        this.allowEmpty = allowEmpty;  
    }  
  
    /** 
     * Parse the Date from the given text, using the specified DateFormat. 
     */  
    @Override  
    public void setAsText(String text) throws IllegalArgumentException {  
        if (this.allowEmpty && !StringUtils.hasText(text)) {  
            // Treat empty String as null value.  
            setValue(null);  
        } else {  
            try {  
                if(this.dateFormat != null) {
                    setValue(this.dateFormat.parse(text));  
                }else {  
                	if(StringUtils.countOccurrencesOf(text, " ")==0) {
                		setValue(DATEFORMAT.parse(text));  
                	}else if(StringUtils.countOccurrencesOf(text, ":")==0) {
                		setValue(HOURTIMEFORMAT.parse(text));  
                	}else if(StringUtils.countOccurrencesOf(text, ":")==1) {
                		setValue(MINUTETIMEFORMAT.parse(text));  
                	}else if(StringUtils.countOccurrencesOf(text, ":")==2) {
                		setValue(TIMEFORMAT.parse(text));  
                	}
                }
            } catch (ParseException ex) {  
                throw new IllegalArgumentException("日期解析出错，请检查日期格式");  
            }  
        }  
    }  
  
    /** 
     * Format the Date as String, using the specified DateFormat. 
     */  
    @Override  
    public String getAsText() {  
        Date value = (Date) getValue();  
        DateFormat dateFormat = this.dateFormat;  
        if(dateFormat == null)  
            dateFormat = TIMEFORMAT;  
        return (value != null ? dateFormat.format(value) : "");  
    }  
}  