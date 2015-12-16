package com.brothers.sharegoods.database;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class QueryGoods {

	protected Properties properties;

	public QueryGoods() {
		properties = new Properties();
		InputStream queryFile = this.getClass().getResourceAsStream("queryGoods.properties");
		try {
			properties.load(queryFile);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	protected Properties getProperties() {
			return properties;
	}

	protected String getQuery(String queryId) {
		return properties.getProperty(queryId);
	}
}