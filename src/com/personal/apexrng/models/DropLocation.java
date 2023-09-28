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
	private String imgUrl;
	
	/**
	 * Constructor for cloning
	 * @param dl
	 */
	public DropLocation(DropLocation dl) {
		this.id = dl.getId();
		this.map = dl.getMap();
		this.name = dl.getName();
		this.imgUrl = dl.getImgUrl();
	}
}
