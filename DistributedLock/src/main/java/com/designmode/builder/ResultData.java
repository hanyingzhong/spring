package com.designmode.builder;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

class ResultVo {
	private String status;
	private String msg;
	public static ResultVo OK = new ResultVo("OK", "result is OK");

	private ResultVo(String status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatu(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}

class Ad {
	String id;
	String title;
	String path;

	public Ad(String iD, String title, String path) {
		super();
		this.id = iD;
		this.title = title;
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

public class ResultData {

	private String statu;
	private String msg;
	private JSONArray data;

	private ResultData(Builder builder) {
		this.statu = builder.statu;
		this.msg = builder.msg;
		this.data = builder.data;
	}

	/**
	 * 构建器
	 */
	public static class Builder {
		private String statu;
		private String msg;
		private JSONArray data;

		public Builder(String statu, String msg) {
			this.statu = statu;
			this.msg = msg;
		}

		public Builder(ResultVo resultVo) {
			this.statu = resultVo.getStatus();
			this.msg = resultVo.getMsg();
		}

		public Builder setData(JSONArray data) {
			this.data = data;
			return this;
		}

		/**
		 * 定制json串,只转换properties中指定name
		 * 
		 * @param clazz
		 *            要过滤的类
		 * @param filters
		 *            要转换的字段
		 * @param object
		 *            返回jsonArray或者jsonObject
		 * @return
		 */
		public Builder setData(Class<?> clazz, String[] filters, Object object) {
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(clazz, filters);
			String result = JSON.toJSONString(object, filter);
			this.data = JSON.parseArray(result);
			return this;
		}

		public ResultData builder() {
			return new ResultData(this);
		}

	}

	//use getXXX to get the properties name.
	public static void main(String[] args) {
		ResultData result = null;
		String[] filters = // { "type", "title", "path", "order", "url" };
				{ "title", "path", "id" };

		Set<Ad> ads = new HashSet<Ad>();

		ads.add(new Ad("abc", "study english", "english"));
		ads.add(new Ad("a123", "study chinese", "chinese"));
		ads.add(new Ad("a1113", "study chinese", "chinese"));
		
		result = new ResultData.Builder(ResultVo.OK).setData(Ad.class, filters, ads).builder();

		System.err.println(result.toString());
	}
	// 省略get和set

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public JSONArray getData() {
		return data;
	}

	public void setData(JSONArray data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultData [statu=" + statu + ", msg=" + msg + ", data=" + data + "]";
	}

}
