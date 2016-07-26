package inspur.crawl.common.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;


public class MyThread implements Callable<List<Map<String,Object>>> {
	int s1 = 0,s2=0,s3=0,s4=0,s5=0;
	List<Map<String,Object>> list = null;
	List<Map<String,Object>> list22 = new ArrayList<Map<String,Object>>();
	MyThread(int ss1,int ss2,int ss3,int ss4,int ss5,List<Map<String,Object>> list1){
		s1=ss1;
		s2=ss2;
		s3=ss3;
		s4=ss4;
		s5=ss5;
		list=list1;
	}
    public void dds(int i) {
    	
    }
	@Override
	public List<Map<String, Object>> call() throws Exception {
		if(Thread.currentThread().getName().indexOf("thread-1")>-1){
			for(int i=0;i<s1;i++){
				try {
					dds(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(Thread.currentThread().getName().indexOf("thread-2")>-1){
			for(int i=s1;i<s2;i++){
				try {
					dds(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(Thread.currentThread().getName().indexOf("thread-3")>-1){
			for(int i=s2;i<s3;i++){
				try {
					dds(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(Thread.currentThread().getName().indexOf("thread-4")>-1){
			for(int i=s3;i<s4;i++){
				try {
					dds(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(Thread.currentThread().getName().indexOf("thread-5")>-1){
			for(int i=s4;i<s5;i++){
				try {
					dds(i);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list22;
	}

}