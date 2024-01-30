package com.temp.medicine.domain;

import java.util.Date;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "repsonse")
@Data
@AllArgsConstructor
public class Response {
	
	@XmlElement(name = "header")
	private Header header;
	
	@XmlElement(name = "body")
	private Body body;
	
	@Data
	@XmlRootElement(name = "header")
	private static class Header {
		private String resultCode;
		private String resultMsg;
	}
	
	@Data
	@XmlRootElement(name = "body")
	public static class Body {
		private Items items;
		private String numOfRows;
		private String pageNo;
		private String totalCount;
		
		@Data
		@XmlRootElement(name = "items")
		public static class Items {
			private List<Item> item;
			
			@Data
			@XmlRootElement(name = "item")
			public static class Item {
				private String entpName;
				private String itemName;
				private int itemSeq;
				private String efcyQesitm;
				private String useMethodQesitm;
				private String atpnWarnQesitm;
				private String atpnQesitm;
				private String intrcQesitm;
				private String seQesitm;
				private String depositMethodQesitm;
				private Date openDe;
				private Date updateDe;
				private String itemImage;
			}
		}
	}
	

}
