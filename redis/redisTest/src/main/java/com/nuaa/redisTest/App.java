package com.nuaa.redisTest;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	testJedisPool();
    }
    
    public static void testJedis() {
    	
    	Jedis jedis = new Jedis("localhost",6379);
        int i=0;
        try {
        	long start =System.currentTimeMillis();
        	while(true){
        		long end =System.currentTimeMillis();
        		if(end-start>=1000) {
        			break;
        		}
        		i++;
        		jedis.set("test"+i,i+"" );
        	}
        	
        }finally {
			jedis.close();
		}
        System.out.println(i);
    	
    } 
    public static void testJedisPool() {
    	
    	JedisPoolConfig poolConfig = new JedisPoolConfig();
    	//最大空闲数
    	poolConfig.setMaxIdle(50);
    	//最大连接数
    	poolConfig.setMaxTotal(100);
    	//最大等待毫秒数
    	poolConfig.setMaxWaitMillis(20000);
    	JedisPool pool = new JedisPool(poolConfig,"localhost");
    	
    	
    	Jedis jedis = pool.getResource();
    	int i=0;
    	try {
    		long start =System.currentTimeMillis();
    		while(true){
    			long end =System.currentTimeMillis();
    			if(end-start>=1000) {
    				break;
    			}
    			i++;
    			jedis.set("test"+i,i+"" );
    		}
    		
    	}finally {
    		jedis.close();
    	}
    	System.out.println(i);
    	
    } 
}
