package inspur.crawl.common.cache;

import inspur.crawl.common.cache.CacheOperation.MethodInfo;

public class CacheExec {
    public static void main(String[] args) throws InterruptedException {

//        String province = request.getParameter("province");
//        String city= request.getParameter("city");
//        String county= request.getParameter("county");
//        Document doc = XMLBuilder.buildLatelyKeyword(kwm.latelyKeyword(province, city, county));
//        out.write(doc);

        String province = "Jiangsu ";
        String city= "Nanjing ";
        String county= "Jiangning";
        CacheOperation co = CacheOperation.getInstance();
//        MethodInfo mi = co.new MethodInfo(kwm, "latelyKeyword", new Object[]{province, city, county});
//        Document doc = (Document )co.getCacheData(XMLBuilder.class,"buildLatelyKeyword",new Object[]{mi}, 120000, 0);
//        out.write(doc);
        int c = 1;
       while (c<50){
            // chf@tsinghua.org.cn 
            MethodInfo mi = co.new MethodInfo(AddrDetail.class, "latelyKeyword", new Object[]{province, city, county});
            // 120000 毫秒（2分钟）更新缓存
            String aa = (String)co.getCacheData(AddrDetail.class,"buildCache",new Object[]{"23123123"}, 5000, 0);
            System.out.println(c+"CacheExec:main=" + aa);
            Thread.sleep(1000);
            c++;
        }    
       }

}