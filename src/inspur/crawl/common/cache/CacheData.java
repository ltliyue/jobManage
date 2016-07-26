package inspur.crawl.common.cache;

public class CacheData {
    private Object data;
    private long time;
    private int count;

    public CacheData() {

    }

    public CacheData(Object data, long time, int count) {
        this.data = data;
        this.time = time;
        this.count = count;
    }

    public CacheData(Object data) {
        this.data = data;
        this.time = System.currentTimeMillis();
        this.count = 1;
    }

    public void addCount() {
        count++;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }
}