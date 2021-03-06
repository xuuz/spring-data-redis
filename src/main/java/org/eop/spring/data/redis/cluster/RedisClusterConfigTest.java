package org.eop.spring.data.redis.cluster;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author lixinjie
 * @since 2018-03-25
 */
@Profile("test")
@Configuration
@PropertySource("classpath:redis/cluster/test/redis.properties")
public class RedisClusterConfigTest {

	// redis.clients.jedis.JedisCluster
	@Value("${cluster.clusterNodes}")
	private String clusterNodes;
	
	@Value("${cluster.connectionTimeout}")
	private Integer connectionTimeout;
	
	@Value("${cluster.soTimeout}")
	private Integer soTimeout;
	
	@Value("${cluster.maxAttempts}")
	private Integer maxAttempts;
	
	@Value("${cluster.password}")
	private String password;
	
	// org.apache.commons.pool2.impl.BaseObjectPoolConfig
	@Value("${pool.lifo}")
	private Boolean lifo;
	
	@Value("${pool.fairness}")
	private Boolean fairness;
	
	@Value("${pool.maxWaitMillis}")
	private Long maxWaitMillis;
	
	@Value("${pool.minEvictableIdleTimeMillis}")
	private Long minEvictableIdleTimeMillis;
	
	@Value("${pool.softMinEvictableIdleTimeMillis}")
	private Long softMinEvictableIdleTimeMillis;
	
	@Value("${pool.numTestsPerEvictionRun}")
	private Integer numTestsPerEvictionRun;
	
	@Value("${pool.evictionPolicyClassName}")
	private String evictionPolicyClassName;
	
	@Value("${pool.testOnCreate}")
	private Boolean testOnCreate;
	
	@Value("${pool.testOnBorrow}")
	private Boolean testOnBorrow;
	
	@Value("${pool.testOnReturn}")
	private Boolean testOnReturn;
	
	@Value("${pool.testWhileIdle}")
	private Boolean testWhileIdle;
	
	@Value("${pool.timeBetweenEvictionRunsMillis}")
	private Long timeBetweenEvictionRunsMillis;
	
	@Value("${pool.blockWhenExhausted}")
	private Boolean blockWhenExhausted;
	
	// org.apache.commons.pool2.impl.GenericObjectPoolConfig
	@Value("${pool.maxTotal}")
	private Integer maxTotal;
	
	@Value("${pool.maxIdle}")
	private Integer maxIdle;
	
	@Value("${pool.minIdle}")
	private Integer minIdle;
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(clusterConfig(), poolConfig());
		jedisConnectionFactory.setPassword(password);
		jedisConnectionFactory.setTimeout(connectionTimeout);
		return jedisConnectionFactory;
	}
	
	private RedisClusterConfiguration clusterConfig() {
		RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration(Arrays.asList(clusterNodes.split(",")));
		clusterConfig.setMaxRedirects(maxAttempts);
		return clusterConfig;
	}
	
	private JedisPoolConfig poolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setLifo(lifo);
		poolConfig.setFairness(fairness);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		poolConfig.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
		poolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		poolConfig.setEvictionPolicyClassName(evictionPolicyClassName);
		poolConfig.setTestOnCreate(testOnCreate);
		poolConfig.setTestOnBorrow(testOnBorrow);
		poolConfig.setTestOnReturn(testOnReturn);
		poolConfig.setTestWhileIdle(testWhileIdle);
		poolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		poolConfig.setBlockWhenExhausted(blockWhenExhausted);
		
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		
		return poolConfig;
	}
}
