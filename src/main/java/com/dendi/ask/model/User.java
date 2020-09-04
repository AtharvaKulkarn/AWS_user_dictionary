package com.dendi.ask.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {

	@JsonProperty("Name")
	String name;

	@JsonProperty("Age")
	String age;

	@JsonProperty("Salary")
	String salary;

	public User() {
		super();
	}

	public User(String name, String age, String salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "User [Name=" + name + ", Age=" + age + ", Salary=" + salary + "]";
	}

}
