package com.personal.apexrng.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ApexMap {
	@Id
	@Column(columnDefinition = "serial")
	private int id;
	private String name;
}
