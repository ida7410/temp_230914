package com.temp.medicine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/medicine")
@RestController
public class MedicineRestController {
	
	private static final String SERVICE_KEY = "8mEc7Ximrw9QNaqh5m9dXjW6mIIk1po%2BfAcx6cLrw2jFpco745g81uUTR%2FslmaNdGkNdHwZoUo3XlBem7P6%2BTQ%3D%3D";
	private static final String SERVICE_URL = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
	
	private URL buildUrl(String itemName, String entpName) {
		String urlPath = SERVICE_URL 
						+ "?ServiceKey=" + SERVICE_KEY 
						+ "&itemName=" + itemName
						+ "&entpName=" + entpName
						+ "&type=json";
		
		URL url = null;
		try {
			url = new URL(urlPath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return url;	
	}
	
	private InputStream getNetworkConnection(HttpURLConnection conn) throws IOException {

		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setDoInput(true);
		
		if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP error code : " + conn.getResponseCode());
        }
		return conn.getInputStream();
	}
	
	private String readStreamToString(InputStream stream) throws IOException {
		StringBuilder result = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		
		String readLine;
		while((readLine = br.readLine()) != null) {
			result.append(readLine + "\n\r");
		}
		
		br.close();
		
		return result.toString();
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> httpGet(String itemName, String entpName){
		
		Map<String, Object> map = new HashMap<>();
		
		URL url = buildUrl(itemName, entpName);
		
		StringBuilder sb = new StringBuilder();
		String result = null;
		HttpURLConnection conn = null;
		InputStream stream = null;
		try {
			conn = (HttpURLConnection)url.openConnection();
			stream = getNetworkConnection(conn);
			result = readStreamToString(stream);
			System.out.println("Response code: " + conn.getResponseCode());
			
			if (stream != null) {
				stream.close();
			}
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<String> search(
			@RequestParam("itemName") String itemName,
			@RequestParam("entpName") String entpName) {
		
		MedicineRestController api = new MedicineRestController();
		ResponseEntity<String> response = api.httpGet(itemName, entpName);
		return response;
	}
	
}
