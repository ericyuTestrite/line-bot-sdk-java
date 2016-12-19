package com.testritegroup.bot.luis.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class CallLuisClient {

	private final String USER_AGENT = "Mozilla/5.0";
	
//	private String URL="https://api.projectoxford.ai/luis/v2.0/apps/a33ca1aa-6423-432d-825b-3e0b7bb2d303?subscription-key=73ff9afd03b9405db93da7cd4255b109&q=";
	private final String URL=ExchangeGlobals.getString("luis.url");


//	public static void main(String[] args) throws Exception {
//		CallLuisClient http = new CallLuisClient();
//		System.out.println("Testing 1 - Send Http GET request");
//		String strQuery="今天天氣好嗎";
//		http.sendGet(strQuery);
//
//		//System.out.println("\nTesting 2 - Send Http POST request");
//		//http.sendPost();
//
//	}

	// HTTP GET request

	public String sendGet(String strQuery) throws Exception {
		strQuery=URLEncoder.encode(strQuery,"utf8");
		System.out.println("sendGet  strQuery= "+strQuery);
		String url=	StringUtil.stringReplace(URL, "{0}", strQuery);
		
		@SuppressWarnings("deprecation")
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		// add request header
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " +
                       response.getStatusLine().getStatusCode());
		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
		return result.toString();
	}

	

}