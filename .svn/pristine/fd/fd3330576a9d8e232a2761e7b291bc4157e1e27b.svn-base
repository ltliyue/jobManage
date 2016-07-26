package com.inspur.bigdata.hbaseio.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 每一条监控程序所生成的监控结果数据 每一条数据都包括： 监控指标Metric, 时间戳,监控目标（包括需求、任务、整体等），监控目标的ID,监控的阶段。
 * 
 * 
 * @author maolh
 *
 */
public class DataPoint {
	private Metric metric;
	private Map<String, String> tagsMap = new HashMap<String, String>();
	private TimeSeriesData data = new TimeSeriesData();

	public static final String METRIC_TARGET_TAG = "metricTarget";
	public static final String TARGET_ID_TAG = "targetId";
	public static final String STAGE_TAG = "stage";

	public void setNumber(Number value) {
		data.setData(value);
	}

	public void setMetric(Metric metric) {
		this.metric = metric;
	}

	public void setTime(Date date) {
		if (date != null) {
			data.setTime(date.getTime());
		}
	}

	public void setTime(long time) {
		data.setTime(time);
	}

	/**
	 * 使用任意的tag，适用于key和value是动态变化的情况。如果key是已经指定的， 应使用具体的set方法，例如addInstanceIdTag
	 * 
	 * @param key
	 * @param value
	 */
	/*
	 * public void addTag(String key,String value){ tagsMap.put(key, value); }
	 */

	public void setTargetId(String targetId) {
		tagsMap.put(TARGET_ID_TAG, targetId);
	}

	public String getTargetId() {
		return tagsMap.get(TARGET_ID_TAG);
	}

	public void setStage(Stage s) {
		tagsMap.put(STAGE_TAG, s.toString().toLowerCase());
	}

	public Stage getStage() {
		if (tagsMap.get(STAGE_TAG) != null) {
			return Stage.valueOf(tagsMap.get(STAGE_TAG).toUpperCase());
		}
		return null;
	}
	
	public void setMetricTarget(MetricTarget mt){
		tagsMap.put(METRIC_TARGET_TAG, mt.toString().toLowerCase());
	}
	
	public MetricTarget getMetricTarget(){
		if (tagsMap.get(METRIC_TARGET_TAG) != null) {
			return MetricTarget.valueOf(tagsMap.get(METRIC_TARGET_TAG).toUpperCase());
		}
		return null;
	}

	public void setValue(Number value) {
		data.setData(value);
	}

	public Metric getMetric() {
		return metric;
	}

	public long getTime() {
		return data.getTime();
	}

	public Number getValue() {
		return data.getData();
	}

	public TimeSeriesData getData() {
		return data;
	}

	@Override
	public String toString() {
		StringBuffer buffer=new StringBuffer("");
		if(metric!=null){
			buffer.append(metric.toString());
		}else{
			buffer.append("null");
		}
		buffer.append("_"+getTime()+"_");
		if(getMetricTarget()!=null){
			buffer.append(getMetricTarget().toString());
		}else{
			buffer.append("null");
		}
		buffer.append("_"+getTargetId()+"_");
		if(getStage()!=null){
			buffer.append(getStage().toString());
		}else{
			buffer.append("null");
		}
		return buffer.toString();
	}
}
