package com.sola.instagram.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ImageMedia extends Media {

	public ImageMedia(JSONObject obj, String accessToken) throws JSONException {
		super(obj, accessToken);
	}

}
