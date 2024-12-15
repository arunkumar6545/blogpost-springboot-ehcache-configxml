package dev.wagnus.ehcache.config;

import dev.wagnus.ehcache.ConfigComponent;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.expiry.ExpiryPolicy;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.ehcache.xml.XmlConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class EhcacheConfig {

    @Autowired
    private ConfigComponent configComponent;


//    public CacheManager cacheManager() {
//        final URL myUrl = getClass().getResource("/configs/docs/getting-started.xml");
//        XmlConfiguration xmlConfig = new XmlConfiguration(myUrl);
//        org.ehcache.CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
//        myCacheManager.init();
//        return myCacheManager;
//    }
//
//    private static ExpiryPolicy<Object, Object> createExpiryPolicy(Duration timeToLive, Duration timeToIdle) {
//        return ExpiryPolicyBuilder
//                .expiry()
//                .create(timeToLive)
//                .access(timeToIdle)
//                .build();
//    }

    @Bean
    public JCacheCacheManager jCacheCacheManager1() {
//        final URL myUrl = getClass().getResource("/jcache.xml");
        XmlConfiguration xmlConfig = this.configComponent.xmlConfiguration();
        EhcacheCachingProvider ehcacheCachingProvider = (EhcacheCachingProvider) Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
        DefaultConfiguration defaultConfiguration = new DefaultConfiguration(xmlConfig.getCacheConfigurations(), ehcacheCachingProvider.getDefaultClassLoader());
        javax.cache.CacheManager cacheManager = ehcacheCachingProvider.getCacheManager(ehcacheCachingProvider.getDefaultURI(), defaultConfiguration);
        return new JCacheCacheManager(cacheManager);
    }

//    @Bean
//    public JCacheCacheManager jCacheCacheManager() {
//        Map<String, CacheConfiguration<?, ?>> cacheMap = new HashMap<>();
//
//        ResourcePoolsBuilder resourcePoolsBuilder = ResourcePoolsBuilder
//                .heap(3)
//                .offheap(1, MemoryUnit.MB);
//
//        ExpiryPolicy<Object, Object> expiryPolicy = createExpiryPolicy(Duration.ofMinutes(10), Duration.ofMinutes(5));
//
//        CacheConfiguration<Object, Object> cacheConfiguration = CacheConfigurationBuilder
//                .newCacheConfigurationBuilder(Object.class, Object.class, resourcePoolsBuilder)
//                .withExpiry(expiryPolicy)
//                .build();
//
//        cacheMap.put("MY_CACHE", cacheConfiguration);
//        EhcacheCachingProvider ehcacheCachingProvider = (EhcacheCachingProvider) Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
//        DefaultConfiguration defaultConfiguration = new DefaultConfiguration(cacheMap, ehcacheCachingProvider.getDefaultClassLoader());
//        javax.cache.CacheManager cacheManager = ehcacheCachingProvider.getCacheManager(ehcacheCachingProvider.getDefaultURI(), defaultConfiguration);
//        return new JCacheCacheManager(cacheManager);
//    }

}
