package com.inspur.bigdata.hbaseio.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Comparable;

/**
 * 时间-数据
 * 
 * @author maolh
 *
 */
public class TimeSeriesData implements Comparable<TimeSeriesData> {
	private long time;
	private Number data;

	public TimeSeriesData() {
	}

	public TimeSeriesData(long time, Number data) {
		this.time = time;
		this.data = data;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Number getData() {
		return data;
	}

	public void setData(Number data) {
		this.data = data;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "time:" + df.format(new Date(this.time)) + ",value:" + data;
	}

	/**
	 * -1表示o1<o2, 1表示o1>o2,0表示二者相等，null小于所有的值
	 */
	@Override
	public int compareTo(TimeSeriesData o) {
		if (o == null)
			return 1;
		return this.time == o.getTime() ? 0 : (this.time > o.getTime() ? 1 : -1);
	}
}
