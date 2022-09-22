package com.personal.apexrng.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class DropLocation {
	@Id
	@Column(columnDefinition = "serial")
	private int id;
	@ManyToOne
	private ApexMap map;
	private String name;
}
