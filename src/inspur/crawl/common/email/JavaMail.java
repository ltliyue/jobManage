package inspur.crawl.common.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


public class JavaMail {  
	  
    // 设置服务器  
    private static String KEY_SMTP = "mail.smtp.host";  
    private static String VALUE_SMTP = "smtp.163.com";  
    // 服务器验证  
    private static String KEY_PROPS = "mail.smtp.auth";  
    private static String VALUE_PROPS = "true";  
    // 发件人用户名、密码  
    private String SEND_USER = "a359033721@163.com";  
    private String SEND_UNAME = "监控助手";  
    private String SEND_PWD = "shf4251520";   
    // 建立会话  
    private MimeMessage message;  
    private Session s;  
    
//    private String filename="";  
//    private Vector file = new Vector(); //用于保存发送附件的文件名的集合  
    /** 
     * 该方法用于收集附件名 
     */  
//    public void addAttachfile(String fname){  
//        file.addElement(fname);  
//    }  
    /* 
     * 初始化方法 
     */  
    public JavaMail() {  
        Properties props = System.getProperties();  
        props.setProperty(KEY_SMTP, VALUE_SMTP);  
        props.put(KEY_PROPS, VALUE_PROPS);
        props.put("mail.smtp.port","25");
        //设置认证器
        PopupAuthenticator pop = new PopupAuthenticator();
        pop.performCheck("a359033721@163.com", "shf4251520");//你的帐户和密码
        
        //得到session
        s = Session.getDefaultInstance(props,pop);
   // 要注意的是,为了避免垃圾邮件,大多数的smtp服务器需要认证,SMTP认证(SMTP AUTH)需要用户名和密码来发送邮件;因此,必须在session的初始化参数中设置一个认证者（Authenticator）来返回所需的认证证书,具体代码必须由自己来实现:
       
//        s.setDebug(true); /*开启后有调试信息 */  
        message = new MimeMessage(s);  
    }  
    class PopupAuthenticator extends Authenticator {
        String username = null;
        String password = null;
        public PopupAuthenticator() {}
        public PasswordAuthentication performCheck(String user, String pass) {
          username = user;
          password = pass;
          return getPasswordAuthentication();
         }
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
         }
       }
    /** 
     * 发送邮件 
     *  
     * @param headName 
     *            邮件头文件名 
     * @param sendHtml 
     *            邮件内容 
     * @param receiveUser 
     *            收件人地址 
     */  
    public void doSendHtmlEmail(String headName, String sendHtml,  
            String receiveUser,String csUser,String filename1) {  
        try {  
            // 发件人  
            InternetAddress from = new InternetAddress(SEND_USER);  
            MimeMultipart multipart = new MimeMultipart();
            BodyPart msgBodyPart = new MimeBodyPart();
            //设置格式为"text/html"
            msgBodyPart.setContent(sendHtml, "text/html;charset=gb2312");
            multipart.addBodyPart(msgBodyPart);
            message.setFrom(from);  
            // 收件人  
            List list = new ArrayList();//不能使用string类型的类型，这样只能发送一个收件人
            String []median=receiveUser.split(",");//对输入的多个邮件进行逗号分割
            for(int i=0;i<median.length;i++){
            	if(!"".equals(median[i])){
            		list.add(new InternetAddress(median[i]));
            	}
                
            }
            InternetAddress[] address =(InternetAddress[])list.toArray(new InternetAddress[list.size()]);
            message.setRecipients(Message.RecipientType.TO,address);//当邮件有多个收件人时，用逗号隔开
            
            List list1 = new ArrayList();//不能使用string类型的类型，这样只能发送一个收件人
            if(!"".equals(csUser)){
            	String []median1=csUser.split(",");//对输入的多个邮件进行逗号分割
                for(int i=0;i<median1.length;i++){
                    list1.add(new InternetAddress(median1[i]));
                }
                InternetAddress[] address1 =(InternetAddress[])list1.toArray(new InternetAddress[list1.size()]);
                message.setRecipients(Message.RecipientType.CC,address1);//当邮件有多个收件人时，用逗号隔开
            }
            if(!filename1.isEmpty()){//有附件  
            	BodyPart mbp=new MimeBodyPart();  
//                    filename=efile.nextElement().toString(); //选择出每一个附件名  
                FileDataSource fds=new FileDataSource(filename1); //得到数据源  
                System.out.println("filename1::"+filename1+"     "+fds.getContentType());
                mbp.setDataHandler(new DataHandler(fds)); //得到附件本身并至入BodyPart  
                mbp.setFileName(MimeUtility.encodeText(fds.getName()));  //得到文件名同样至入BodyPart  
                multipart.addBodyPart(mbp);  
            }   
//            InternetAddress to = new InternetAddress(receiveUser);  
//            message.setRecipient(Message.RecipientType.TO, to);  
            // 邮件标题  
            message.setSubject(headName);  
            // 邮件内容,也可以使纯文本"text/plain"  
            message.setContent(multipart);  
            message.saveChanges();  
            Transport transport = s.getTransport("smtp");  
//            Store store=s.getSorte("pop3");//Store 是用来收信的
            // smtp验证，就是你用来发邮件的邮箱用户名密码  
            transport.connect(VALUE_SMTP, SEND_UNAME, SEND_PWD);  
            // 发送  
            transport.sendMessage(message, message.getAllRecipients());  
            transport.close();  
            System.out.println("send success!");  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
    public static void main(String[] args) {  
        JavaMail se = new JavaMail();  
        se.doSendHtmlEmail("需求增加信息", "<table width=\"100%\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\"><tr  width=\"90%\"><th>序号</th><th>需求名称</th><th>登记时间</th><th>联系人</th><th>查看地址</th></tr>" +
        		"<tr width=\"90%\"><td align=\"center\">1</td><td align=\"center\">xx采集需求</td><td align=\"center\">2015-04-17</td><td align=\"center\">全智贤</td><td align=\"center\">http://120.26.75.94:8011/droDataCoop/#</td></tr></table>", "zhang_yuchao@inspur.com","","");  
    }  
}  
