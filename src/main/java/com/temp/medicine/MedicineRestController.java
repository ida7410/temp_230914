package com.temp.medicine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RequestMapping("/medicine")
@RestController
public class MedicineRestController {
	
	private static final String SERVICE_KEY = "8mEc7Ximrw9QNaqh5m9dXjW6mIIk1po%2BfAcx6cLrw2jFpco745g81uUTR%2FslmaNdGkNdHwZoUo3XlBem7P6%2BTQ%3D%3D";
	private static final String SERVICE_URL = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
	
	private URL buildUrl(String itemName, String entpName) {
		StringBuilder sb = new StringBuilder(SERVICE_URL)
							.append("?ServiceKey=" + SERVICE_KEY)
							.append("&itemName=" + itemName)
							.append("&entpName=" + entpName)
							.append("&type=json");
		
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
	
	public String httpGet(String itemName, String entpName){
		
		Map<String, Object> map = new HashMap<>();
		
		URL url = buildUrl(itemName, entpName);
		
		StringBuilder sb = new StringBuilder();
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			
			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
			}
			catch (IOException e) {
				System.out.println("IOException for BufferedReader: " + e.getMessage());
			}
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		
		return sb.toString();
		
	}
	
	public ResponseEntity<String> tempGet(String itemName, String entpName) {
		String url = SERVICE_URL + "?ServiceKey=" + SERVICE_KEY 
				+ "&itemName=" + itemName
				+ "&entpName=" + entpName;
		
		HttpHeaders headers = new HttpHeaders();
		Map<String, Object> body = new HashMap<>();
		
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		
		return response;
	}
	
	public void temp() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode("8mEc7Ximrw9QNaqh5m9dXjW6mIIk1po+fAcx6cLrw2jFpco745g81uUTR/slmaNdGkNdHwZoUo3XlBem7P6+TQ==", "UTF-8")); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("entpName","UTF-8") + "=" + URLEncoder.encode("부광약품", "UTF-8")); /*업체명*/
        urlBuilder.append("&" + URLEncoder.encode("itemName","UTF-8") + "=" + URLEncoder.encode("뮤코졸", "UTF-8")); /*제품명*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*제품명*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
	}
	
	@GetMapping("/search")
	public String search(
			@RequestParam("itemName") String itemName,
			@RequestParam("entpName") String entpName) {
		
		MedicineRestController api = new MedicineRestController();
		String response = api.tempGet(itemName, entpName).toString();
		try {
			temp();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
