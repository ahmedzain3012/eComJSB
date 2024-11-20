package com.ecom.proj.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "category_id")
private Integer id;

private String name;

}
