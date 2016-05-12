package com.sola.instagram.model;

import org.json.JSONException;
import org.json.JSONObject;


public class Tag extends InstagramModel {

	int mediaCount;
	String name;

	public Tag(JSONObject obj, String accessToken) throws JSONException {
		super(obj, accessToken);
		setName(obj.getString("name"));
		setMediaCount(obj.getInt("media_count"));
	}

	protected void setMediaCount(int mediaCount) {
		this.mediaCount = mediaCount;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public int getMediaCount() {
		return mediaCount;
	}

	public String getName() {
		return name;
	}

    /**
     * Checks if two tags objects are equal
     * @param o The object to be compared 
     * @return True of the two objects are equal, false otherwise
     */
	public boolean equals(Object o) {
		if(o == null) return false;
		if(o == this) return true;
		if(o.getClass() != this.getClass()) return false;
		return ((Tag)o).getName().equals(getName());
	}
}
