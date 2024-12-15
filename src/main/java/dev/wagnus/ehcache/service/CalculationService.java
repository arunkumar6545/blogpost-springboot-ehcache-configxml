package dev.wagnus.ehcache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculationService {

  @Cacheable(value = "areaOfCircleCache", key = "#radius")
  public double areaOfCircle(int radius) {
//    log.info("calculate the area of a circle with a radius of {}", radius);
    return Math.PI * Math.pow(radius, 2);
  }
}
