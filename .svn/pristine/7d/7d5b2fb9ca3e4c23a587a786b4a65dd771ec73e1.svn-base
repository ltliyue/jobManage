package inspur.crawl.common.thread;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class TestCachedThreadPool {

    public static void main(String[] args) {
    	int s1 = 0,s2=0,s3=0,s4=0,s5=0;
    	int sum = 3;
		int y = sum%5;
		if(y==0){
			s1 = sum/5;
			s2 = s1 + s1;
			s3 = s2 + s1;
			s4 = s3 +s1;
			s5 = s4 + s1;
		}else if(y>2){
			s1 = (sum+(5-y))/5;
			s2 = s1 + s1;
			s3 = s2 + s1;
			s4 = s3 +s1;
			s5 = s4 + s1-(5-y);
		}else{
			s1 = (sum-y)/5;
			s2 = s1 + s1;
			s3 = s2 + s1;
			s4 = s3 +s1;
			s5 = s4 + s1+y;
		}
		System.out.println(s1+" :"+s2+" :"+s3+" :"+s4+" :"+s5);
    }
    public static void fList(List<Map<String,Object>> list1) throws SQLException{
    	
    	int s1 = 0,s2=0,s3=0,s4=0,s5=0;
    	int sum = list1.size();
		int y = sum%5;
		if(y==0){
			s1 = sum/5;
			s2 = s1 + s1;
			s3 = s2 + s1;
			s4 = s3 +s1;
			s5 = s4 + s1;
		}else if(y>2){
			s1 = (sum+(5-y))/5;
			s2 = s1 + s1;
			s3 = s2 + s1;
			s4 = s3 +s1;
			s5 = s4 + s1-(5-y);
		}else{
			s1 = (sum-y)/5;
			s2 = s1 + s1;
			s3 = s2 + s1;
			s4 = s3 +s1;
			s5 = s4 + s1+y;
		}
		runTh(s1,s2,s3,s4,s5,list1);
    }
    public static void runTh(int s1,int s2,int s3,int s4,int s5,List<Map<String,Object>> list1){
    	 //����һ�������ù̶��߳�����̳߳�
    	
        ExecutorService pool = Executors.newCachedThreadPool();

        //����ʵ����Runnable�ӿڶ���Thread����ȻҲʵ����Runnable�ӿ�

        MyThread t1 = new MyThread(s1,s2,s3,s4,s5,list1);

        MyThread t2 = new MyThread(s1,s2,s3,s4,s5,list1);

        MyThread t3 = new MyThread(s1,s2,s3,s4,s5,list1);

        MyThread t4 = new MyThread(s1,s2,s3,s4,s5,list1);

        MyThread t5 = new MyThread(s1,s2,s3,s4,s5,list1);

        //���̷߳�����н���ִ��
        Future<List<Map<String,Object>>> f1 = pool.submit(t1);
        Future<List<Map<String,Object>>> f2 = pool.submit(t2);

        Future<List<Map<String,Object>>> f3 = pool.submit(t3);

        Future<List<Map<String,Object>>> f4 = pool.submit(t4);

        Future<List<Map<String,Object>>> f5 = pool.submit(t5);
        
        //�ر��̳߳�
       
        pool.shutdown();
   
    }
}
