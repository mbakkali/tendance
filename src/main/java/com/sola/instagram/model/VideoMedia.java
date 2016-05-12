package com.sola.instagram.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.sola.instagram.model.Media;

public class VideoMedia extends Media {

	/** 
	 * Low resolution version of the media's video
	 */
	protected Video lowResolutionVideo;
	
	/**
	 * Standard resolution version of the media's video
	 */
	protected Video standardResolutionVideo;
	
	public VideoMedia(JSONObject obj, String accessToken) throws JSONException {
		super(obj, accessToken);
		
		JSONObject videos = obj.getJSONObject("videos");
	 	this.setLowResolutionVideo(this.new Video(videos.getJSONObject("low_resolution")));
	 	this.setStandardResolutionVideo(this.new Video(videos.getJSONObject("standard_resolution")));
	}

	public Video getLowResolutionVideo() {
		return lowResolutionVideo;
	}

	protected void setLowResolutionVideo(Video lowResolutionVideo) {
		this.lowResolutionVideo = lowResolutionVideo;
	}

	public Video getStandardResolutionVideo() {
		return standardResolutionVideo;
	}

	protected void setStandardResolutionVideo(Video standardResolutionVideo) {
		this.standardResolutionVideo = standardResolutionVideo;
	}

	/**
	 * Object for a media video
	 * with the JSON representation
	 * <pre>
	 * 		{
	 * 			"url":"",
	 * 			"width":0,
	 * 			"height":0
	 * 		}
	 * </pre>
	 * @author Sola Ogunsakin
	 * @version 2013-08-17
	 */
	public class Video {
		/**
		 * Link to this video
		 */
		String uri;
		
		/**
		 * Width of this video
		 */
		int width;
		
		/**
		 * Height of this video
		 */
		int heigth;
	
	    /**
	     * Makes a new Video object out of a JSONObject
	     * @param obj json object used to create this video
	     * @throws JSONException
	     */
		public Video(JSONObject obj) throws JSONException {
			this.setUri(obj.getString("url"));
			this.setWidth(obj.getInt("width"));
			this.setHeigth(obj.getInt("height"));
		}
		
	    /**
	     * Returns the url link to this video
	     * @return The url link to this video 
	     */
		public String getUri() {
			return uri;
		}

	    /**
	     * Sets this video's url
	     * @param url url for this video 
	     */
		protected void setUri(String uri) {
			this.uri = uri;
		}
	
	    /**
	     * Returns the width of this video
	     * @return The width of this video 
	     */
		public int getWidth() {
			return width;
		}
		
	    /**
	     * Sets this video's width
	     * @param width width of this video 
	     */
		protected void setWidth(int width) {
			this.width = width;
		}
		
	    /**
	     * Returns the height of this video
	     * @return The height of this video 
	     */
		public int getHeigth() {
			return heigth;
		}
		
	    /**
	     * Sets this video's height
	     * @param height height of this video 
	     */
		protected void setHeigth(int heigth) {
			this.heigth = heigth;
		}

	    /**
	     * Checks if two video objects are equal
	     * @param o The object to be compared 
	     * @return True of the two objects are equal, false otherwise
	     */
		public boolean equals(Object o) {
			if(o == null) return false;
			if(o == this) return true;
			if(o.getClass() != this.getClass()) return false;
			return ((VideoMedia.Video)o).getUri().equals(getUri());
		}
	}
}
