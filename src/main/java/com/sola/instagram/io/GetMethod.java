package com.sola.instagram.io;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;


public class GetMethod extends APIMethod {
	
	
	public GetMethod() {
		super();
		this.type = "GET";
	}
	
	public GetMethod(String methodUri) {
		super(methodUri);
	}	
	
	@Override
	protected InputStream performRequest() {
		HttpResponse response;
		InputStream stream = null;
		HttpGet post = new HttpGet(this.methodUri);
		try {
			response = client.execute(post);
			stream = response.getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return stream;
	}
}
