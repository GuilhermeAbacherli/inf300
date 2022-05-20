package com.gpabacherli.models;

public class Product {
  private String id;
  private String name;
  private String description;
  private String value;
  private String condition;

  public Product(String id, String name, String description, String value, String condition) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.value = value;
    this.condition = condition;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  @Override
  public String toString() {
    return "Product [condition=" + condition + ", description=" + description + ", id=" + id + ", name=" + name
        + ", value=" + value + "]";
  }
}
