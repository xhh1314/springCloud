package com.example.test.hbase.hbasetest;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseDemo {

  private Configuration configuration = HBaseConfiguration.create();
  private Connection connection = null;

  private void initial() throws IOException {
    configuration.set("hbase.zookeeper.quorum", "baiduyun:2181");
    configuration.set("hbase.master", "baiduyun:45890");
    connection = ConnectionFactory
        .createConnection(configuration);

  }

  public void add(String row, String cf, String q, String value) throws IOException {
    Table table = connection.getTable(TableName.valueOf("test"));
    try {
      Put p = new Put(Bytes.toBytes(row));
      p.addColumn(Bytes.toBytes(cf), Bytes.toBytes(q), Bytes.toBytes(value));
      table.put(p);
    } finally {
      if (table != null) {
        table.close();
      }
    }
  }

  private String get(String row, String cf, String q) throws IOException {
    Table table = connection.getTable(TableName.valueOf("test"));
    //retrieve the data
    String s;
    try {
      Get get = new Get(Bytes.toBytes(row));
      Result result = table.get(get);
      byte[] value = result.getValue(Bytes.toBytes(cf), Bytes.toBytes(q));
      s = Bytes.toString(value);
      System.out.println("the value is: " + s);
    } finally {
      if (table != null) {
        table.close();
      }
    }
    return s;
  }


  public static void main(String[] args) throws IOException {
    HbaseDemo hbaseDemo = new HbaseDemo();
    hbaseDemo.initial();
    hbaseDemo.add("row1", "cf", "c", "father");
    hbaseDemo.add("row1", "cf", "d", "mom");
    hbaseDemo.add("row1", "cf", "b", "x");
    hbaseDemo.get("row1", "cf", "c");

  }

}
