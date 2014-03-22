package com.umapus.infrastructure.dao;

import java.util.HashMap;

public interface SessionRepositoryDao {
	public void AddToRedis(String key, HashMap<String,String> hashvalue);
	public HashMap<String,String> ReadFromRedis(String key);
	public void UpdateToRedis(String key, HashMap<String,String> hashValue);
	public void DeleteFromRedis(String key);
}
