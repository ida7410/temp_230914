package com.temp.medicine.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Medicine {
	private int id;
	private String name;
	private int companyId;
	private int amount;
	private int price;
	private int searchedCount;
	private Date searchedAt;
	private Date createdAt;
	private Date updatedAt;
}
