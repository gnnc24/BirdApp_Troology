package com.troology.birdapp.WebService;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RetrieveStream {

	public static String retrieveStreamGET(String url) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		try {
			getRequest.addHeader("content-type", "application/json");
			getRequest.addHeader("Accept","application/json");
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(RetrieveStream.class.getSimpleName(), "Error " + statusCode
						+ " for CommonURL " + url);
				return null;
			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			getResponseEntity.writeTo(out);
			String responseString = out.toString();
			out.close();
			return responseString;
		} catch (IOException e) {
			getRequest.abort();
			Log.w(RetrieveStream.class.getSimpleName(), "Error for CommonURL " + url, e);
		}
		return null;
	}

	public static String retrieveStreamGETWithCustomHeader(String url) {

		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		try {
			/*getRequest.addHeader("content-type", "application/json");
			getRequest.addHeader("Accept","application/json");*/
			getRequest.addHeader("Client-Service","frontend-client");
			getRequest.addHeader("Auth-Key","application");
			HttpResponse getResponse = client.execute(getRequest);

			final int statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(RetrieveStream.class.getSimpleName(), "Error " + statusCode
						+ " for CommonURL " + url);
				return null;
			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			getResponseEntity.writeTo(out);
			String responseString = out.toString();
			out.close();
			return responseString;
		} catch (IOException e) {
			getRequest.abort();
			Log.w(RetrieveStream.class.getSimpleName(), "Error for CommonURL " + url, e);
		}
		return null;
	}
	public static String retrieveStreamGET(String url, String userId, String userType) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		try {
			getRequest.addHeader("content-type", "application/json");
			getRequest.addHeader("Accept","application/json");
			getRequest.addHeader("Id", userId);
			getRequest.addHeader("userType", userType);
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(RetrieveStream.class.getSimpleName(), "Error " + statusCode
						+ " for CommonURL " + url);
				return null;
			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			getResponseEntity.writeTo(out);
			String responseString = out.toString();
			out.close();
			return responseString;
		} catch (IOException e) {
			getRequest.abort();
			Log.w(RetrieveStream.class.getSimpleName(), "Error for CommonURL " + url, e);
		}
		return null;
	}

	public static String retrieveStreamGET(String url, long flightId) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		try {
			getRequest.addHeader("content-type", "application/json");
			getRequest.addHeader("Accept","application/json");
			getRequest.addHeader("flightId", String.valueOf(flightId));
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(RetrieveStream.class.getSimpleName(), "Error " + statusCode
						+ " for CommonURL " + url);
				return null;
			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			getResponseEntity.writeTo(out);
			String responseString = out.toString();
			out.close();
			return responseString;
		} catch (IOException e) {
			getRequest.abort();
			Log.w(RetrieveStream.class.getSimpleName(), "Error for CommonURL " + url, e);
		}
		return null;
	}

	public static String hotelretrieveStreamGET(String url, long hotelId) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		try {
			getRequest.addHeader("content-type", "application/json");
			getRequest.addHeader("Accept","application/json");
			getRequest.addHeader("hotelId", String.valueOf(hotelId));
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(RetrieveStream.class.getSimpleName(), "Error " + statusCode
						+ " for CommonURL " + url);
				return null;
			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			getResponseEntity.writeTo(out);
			String responseString = out.toString();
			out.close();
			return responseString;
		} catch (IOException e) {
			getRequest.abort();
			Log.w(RetrieveStream.class.getSimpleName(), "Error for CommonURL " + url, e);
		}
		return null;
	}
	public static String retrieveStreamPOST(String url, String jsonData) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(url);
		try {
			//Log.d("jsonData",jsonData+"\n"+tourcode);


			StringEntity params =new StringEntity(jsonData);
			//params.setContentType("application/json");
			//postRequest.addHeader("Content-Type", "application/json");

			postRequest.setEntity(params);
			HttpResponse getResponse = client.execute(postRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.w(RetrieveStream.class.getSimpleName(), "Error " + statusCode
						+ " for CommonURL " + url);
				return null;
			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			getResponseEntity.writeTo(out);
			String responseString = out.toString();
			//JSONObject jsono = new JSONObject(responseString);
			out.close();
			return responseString;
		} catch (IOException e) {
			postRequest.abort();
			Log.w(RetrieveStream.class.getSimpleName(), "Error for CommonURL " + url, e);
		}
		return null;
	}



}
