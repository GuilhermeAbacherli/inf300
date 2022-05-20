package com.gpabacherli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.conversions.Bson;

public class MyJDBC {

  private final String mongoDatabaseName = "inf335_trabalho6";

  public Connection connectMysql(String user, String password, String url) {
    Connection connMysql = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connMysql = DriverManager.getConnection(url, user, password);
    } catch (Exception e) {
      System.err.println("Erro ao conectar no MySQL: " + e);
      e.printStackTrace();
    }
    return connMysql;
  }

  public void disconnect(Connection connMysql) {
    try {
      connMysql.close();
    } catch (SQLException e) {
      System.err.println("Erro ao fechar conexão com MySQL: " + e);
      e.printStackTrace();
    }
  }

  public MongoClient connectMongo(String uri) {
    // https://www.mongodb.com/docs/drivers/java/sync/current/quick-start

    // public MongoDatabase connectMongo(String user, String password, String host,
    // String port, String databaseName) {

    // String uri = "mongodb://localhost";
    // String uri = "mongodb://" + user + ":" + password + "@" + host + ":" + port +
    // "/?maxPoolSize=20&w=majority";

    MongoClient mongoClient = null;

    // try (MongoClient mongoClient = MongoClients.create(uri)) {
    try {
      mongoClient = MongoClients.create(uri);
    } catch (MongoException e) {
      System.err.println("Erro ao conectar no MongoDB: " + e);
      e.printStackTrace();
    }
    return mongoClient;
  }

  public void disconnect(MongoClient connMongo) {
    try {
      connMongo.close();
    } catch (MongoException e) {
      System.err.println("Erro ao fechar conexão com MongoDB: " + e);
      e.printStackTrace();
    }
  }

  public void listProducts(Connection connMysql) {
    try {
      PreparedStatement stmt = (PreparedStatement) connMysql.prepareStatement("SELECT * FROM products");
      ResultSet rs = stmt.executeQuery();

      System.out.println("id -- name -- description -- value -- condition");
      while (rs.next()) {
        String id = rs.getString("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String value = rs.getString("value");
        String condition = rs.getString("condition");

        System.out.println(id + " -- " + name + " -- " + description + " -- " + value + " -- " + condition);
      }

    } catch (SQLException e) {
      System.err.println("Erro ao executar select products no MySQL: " + e);
      e.printStackTrace();
    }
  }

  public void listProducts(MongoClient connMongo) {
    try {
      // https://www.mongodb.com/docs/drivers/java/sync/current/usage-examples/find
      MongoDatabase db = connMongo.getDatabase(mongoDatabaseName);
      MongoCollection<Document> collection = db.getCollection("products");
      Iterable<Document> products = collection.find();

      System.out.println("id -- name -- description -- value -- condition");
      for (Document product : products) {
        String id = product.getString("id");
        String name = product.getString("name");
        String description = product.getString("description");
        String value = product.getString("value");
        String condition = product.getString("condition");
        System.out.println(id + " -- " + name + " -- " + description + " -- " + value + " -- " + condition);
      }

    } catch (MongoException e) {
      System.err.println("Erro ao executar select products no MongoDB: " + e);
      e.printStackTrace();
    }
  }

  public void insertProduct(Connection connMysql, String id,
      String name, String description,
      String value, String condition) {
    try {
      Statement stmt = (Statement) connMysql.createStatement();
      String insert = "INSERT INTO products VALUES ('"
          + id + "', '"
          + name + "', '"
          + description + "', '"
          + value + "', '"
          + condition + "');";

      stmt.executeUpdate(insert);

    } catch (SQLException e) {
      System.err.println("Erro ao executar insert product no MySQL: " + e);
      e.printStackTrace();
    }
  }

  public void insertProduct(MongoClient connMongo, String id,
      String name, String description,
      String value, String condition) {
    try {
      // https://www.mongodb.com/docs/drivers/java/sync/current/usage-examples/insertOne
      MongoDatabase db = connMongo.getDatabase(mongoDatabaseName);
      MongoCollection<Document> collection = db.getCollection("products");
      Document product = new Document()
          .append("id", id)
          .append("name", name)
          .append("description", description)
          .append("value", value)
          .append("condition", condition);

      InsertOneResult result = collection.insertOne(product);
      System.out.println("\nInserted document id: " + result.getInsertedId());

    } catch (MongoException e) {
      System.err.println("Erro ao executar insert product no MongoDB: " + e);
      e.printStackTrace();
    }
  }

  public void updateProductValue(Connection connMysql, String id, String value) {
    try {
      Statement stmt = (Statement) connMysql.createStatement();
      String update = "UPDATE products SET value='" + value + "' WHERE id='" + id + "';";
      stmt.executeUpdate(update);

    } catch (SQLException e) {
      System.err.println("Erro ao executar update product no MySQL: " + e);
      e.printStackTrace();
    }
  }

  public void updateProductValue(MongoClient connMongo, String id, String value) {
    // https://www.mongodb.com/docs/drivers/java/sync/current/usage-examples/updateOne
    MongoDatabase db = connMongo.getDatabase(mongoDatabaseName);
    MongoCollection<Document> collection = db.getCollection("products");
    Document query = new Document().append("id", id);
    Bson updates = Updates.combine(Updates.set("value", value));
    UpdateOptions options = new UpdateOptions().upsert(true);
    try {
      UpdateResult result = collection.updateOne(query, updates, options);
      System.out.println("\nModified document count: " + result.getModifiedCount());
      // only contains a value when an upsert is performed
      if (result.getUpsertedId() != null) {
        System.out.println("\nUpserted id: " + result.getUpsertedId());
      }
    } catch (MongoException e) {
      System.err.println("Erro ao executar update product no MongoDB: " + e);
      e.printStackTrace();
    }
  }

  public void deleteProduct(Connection connMysql, String id) {
    try {
      Statement stmt = (Statement) connMysql.createStatement();
      String delete = "DELETE FROM products WHERE id='" + id + "';";
      stmt.executeUpdate(delete);

    } catch (SQLException e) {
      System.err.println("Erro ao executar delete product no MySQL: " + e);
      e.printStackTrace();
    }
  }

  public void deleteProduct(MongoClient connMongo, String id) {
    // https://www.mongodb.com/docs/drivers/java/sync/current/usage-examples/deleteOne
    MongoDatabase db = connMongo.getDatabase(mongoDatabaseName);
    MongoCollection<Document> collection = db.getCollection("products");
    Bson query = Filters.eq("id", id);
    try {
      DeleteResult result = collection.deleteOne(query);
      System.out.println("\nDeleted document count: " + result.getDeletedCount());
    } catch (MongoException e) {
      System.err.println("Erro ao executar delete product no MongoDB: " + e);
      e.printStackTrace();
    }
  }
}
