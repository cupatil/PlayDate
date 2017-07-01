package com.playdate.Utils;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

public class GetDataFromWebService {
	public static String apiResponse;
	public static JSONObject jsonObject;

	public static String readJsonForService(String Method,
			List<NameValuePair> nameValuePairList) {

		String result = "";
		try {

			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(Method);

			try {

				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(
						nameValuePairList);
				request.setEntity(urlEncodedFormEntity);

			} catch (UnsupportedEncodingException e2) {
				e2.printStackTrace();
			}

			HttpResponse response = null;
			try {
				Log.d("http request", nameValuePairList.toString());
				response = client.execute(request);
			} catch (Exception e1) {
				e1.printStackTrace();
				Log.d("http resp error", e1.toString());
			}

			try {

				result = EntityUtils.toString(response.getEntity());
				result = JSONTokener(result);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String readJsonForService(String Method,
			MultipartEntity mpEntity) {

		String result = "";
		try {

			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(Method);

			request.setEntity(mpEntity);

			HttpResponse response = null;
			try {
				Log.d("http request", mpEntity.toString());
				response = client.execute(request);
			} catch (Exception e1) {
				e1.printStackTrace();
				Log.d("http resp error", e1.toString());
			}

			try {

				result = EntityUtils.toString(response.getEntity());
				result = JSONTokener(result);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static String JSONTokener(String in) {
		// consume an optional byte order mark (BOM) if it exists
		if (in != null && in.startsWith("\ufeff")) {
			in = in.substring(1);
		}
		return in;
	}
}
