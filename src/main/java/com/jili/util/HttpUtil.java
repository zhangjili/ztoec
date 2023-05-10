/**   
 * Copyright © 2016 QianBao. All rights reserved.
 * 
 * @Title: HttpUtil.java 
 * @Prject: TestDemo
 * @Package: com.zrj.pay.util 
 * @Description: TODO
 * @author: ningbin  
 * @date: 2016年11月17日 上午10:29:49 
 * @version: V1.0   
 */
package com.jili.util;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/** 
 * @ClassName: HttpUtil 
 * @Description: http请求工具类
 * @author: ningbin 
 * @date: 2016年11月17日 上午10:29:49  
 */
public class HttpUtil {
	
	/**
	 * @Title: post 
	 * @Description: POST请求
	 * @param url
	 * @param paramMap
	 * @return: String
	 */
	public static String post(String url, Map<String, String> paramMap){
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		String strResult = "";
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			for(String key : paramMap.keySet()){
				nameValuePairs.add(new BasicNameValuePair(key, paramMap.get(key)));
			}
			httppost.addHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() == 200) {
				/* 读返回数据 */
				strResult = EntityUtils.toString(response.getEntity());
			} else {
				String err = response.getStatusLine().getStatusCode() + "";
				strResult += "发送失败:" + err;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strResult;
	}
	
	/**
	 * @Title: get 
	 * @Description: GET请求
	 * @param url
	 * @param paramMap
	 * @throws Exception
	 * @return: String
	 */
	private static String get(String url, Map<String, String> paramMap) throws Exception{
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet hGet = new HttpGet();
		URI uri = new URI(url);
		hGet.setURI(uri);
		HttpResponse response = httpclient.execute(hGet);
		if (response.getStatusLine().getStatusCode() == 200) {
			return EntityUtils.toString(response.getEntity());
		}else {
			String err = response.getStatusLine().getStatusCode() + "";
			return "发送失败:" + err;
		}
	}
	
	public static void main(String[] args) throws Exception{
		HttpUtil.get("", null);
	}
}
