package com.sola.instagram.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UriConstructor {
	String accessToken;

	public UriConstructor() {
		super();
	} 
	
	public UriConstructor(String accessToken) {
		super();
		setAccessToken(accessToken);
	} 

	private String getAccessToken() {
		return accessToken;
	}

	private void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String constructUri(String uri, HashMap<String, Object> map, boolean addAccessToken) {
		try{
			for (Map.Entry<String, Object> arg : map.entrySet()) {
				String key = URLEncoder.encode(arg.getKey(), "UTF-8");
				String value = URLEncoder.encode(arg.getValue().toString(), "UTF-8");
				uri = uri.replaceAll("\\{"+ key + "\\}", value);
			}
		}
		catch(Exception e) {}
		if(addAccessToken) {
			uri += "?access_token=" + getAccessToken();
		}
		return uri;
	}
}
