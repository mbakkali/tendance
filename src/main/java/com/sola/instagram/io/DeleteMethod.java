package com.sola.instagram.io;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.HttpResponse;

import java.io.InputStream;

public class DeleteMethod extends APIMethod {
	
	public DeleteMethod() {
		super();
	}
	
	public DeleteMethod(String methodUri) {
		super(methodUri);
		this.type = "DELETE";
	}		
	
	@Override
	protected InputStream performRequest() throws Exception {
		HttpDelete   delete   = new HttpDelete(this.methodUri);
		HttpResponse response = client.execute(delete);
		InputStream  stream   = response.getEntity().getContent();
		return stream;
	}

}
