package com.gpabacherli;

import java.sql.Connection;

import com.mongodb.client.MongoClient;

public class App {
  public static void main(String[] args) {

    System.out.println("\n=== MYSQL ===");

    MyJDBC myJdbc = new MyJDBC();
    Connection connMysql = myJdbc.connectMysql("root", "123456", "jdbc:mysql://localhost/inf335_trabalho6");

    if (connMysql != null) {
      System.out.println("- Lista original de produtos:");
      myJdbc.listProducts(connMysql);

      myJdbc.insertProduct(connMysql, "7", "Prod7", "Bla bla", "500.0", "novo");
      System.out.println("\n- Lista com novo produto:");
      myJdbc.listProducts(connMysql);

      myJdbc.updateProductValue(connMysql, "7", "400.0");
      System.out.println("\n- Lista com valor do produto alterado:");
      myJdbc.listProducts(connMysql);

      myJdbc.deleteProduct(connMysql, "7");
      System.out.println("\n- Volta a lista original de produtos:");
      myJdbc.listProducts(connMysql);

      myJdbc.disconnect(connMysql);
      connMysql = null;
    }

    System.out.println("\n=== MONGODB ===");
    MongoClient connMongo = myJdbc.connectMongo("mongodb://localhost");

    if (connMongo != null) {
      System.out.println("- Lista original de produtos:");
      myJdbc.listProducts(connMongo);

      myJdbc.insertProduct(connMongo, "7", "Prod7", "Bla bla", "500.0", "novo");
      System.out.println("\n- Lista com novo produto:");
      myJdbc.listProducts(connMongo);

      myJdbc.updateProductValue(connMongo, "7", "400.0");
      System.out.println("\n- Lista com valor do produto alterado:");
      myJdbc.listProducts(connMongo);

      myJdbc.deleteProduct(connMongo, "7");
      System.out.println("\n- Volta a lista original de produtos:");
      myJdbc.listProducts(connMongo);

      myJdbc.disconnect(connMongo);
      connMongo = null;
    }
  }
}
