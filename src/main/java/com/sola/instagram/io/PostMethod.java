package com.sola.instagram.io;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;


public class PostMethod extends APIMethod {
	Map<String, Object> postParameters;
	
	public PostMethod() {
		super();
		this.type = "POST";
	}

	public PostMethod(String methodUri) {
		super(methodUri);
		this.type = "POST";
	}	
	
	@Override
	protected InputStream performRequest() {
		HttpResponse response;
		HttpPost post = new HttpPost(this.methodUri);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		for (Map.Entry<String, Object> arg : getPostParameters().entrySet()) {
			nameValuePairs.add(new BasicNameValuePair(arg.getKey(), arg.getValue().toString()));
		}
		InputStream stream = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = client.execute(post);
			stream = response.getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
	}

	
	public Map<String, Object> getPostParameters() {
		return postParameters;
	}


	public PostMethod setPostParameters(Map<String, Object> postParameters) {
		this.postParameters = postParameters;
		return this;
	}

}
