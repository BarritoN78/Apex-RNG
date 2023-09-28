package com.personal.apexrng.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Legend {
	@Id
	@Column(columnDefinition = "serial")
	private int id;
	private String name;
	private String imgUrl;
}
