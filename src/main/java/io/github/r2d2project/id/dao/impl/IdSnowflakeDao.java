// reference: https://www.cnblogs.com/relucent/p/4955340.html

package io.github.r2d2project.id.dao.impl;

import io.github.r2d2project.id.dao.IdDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IdSnowflakeDao implements IdDao {
  @SuppressWarnings("FieldCanBeLocal")
  private final Logger logger = LoggerFactory.getLogger(IdSnowflakeDao.class);

  private final long datacenterIdBits = 2L;
  private final long workerIdBits = 2L;
  private final long sequenceBits = 8L;

  @SuppressWarnings("FieldCanBeLocal")
  private final long maxWorkerId = ~(-1L << workerIdBits);

  @SuppressWarnings("FieldCanBeLocal")
  private final long maxDatacenterId = ~(-1L << datacenterIdBits);

  @SuppressWarnings("FieldCanBeLocal")
  private final long workerIdShift = sequenceBits;

  @SuppressWarnings("FieldCanBeLocal")
  private final long datacenterIdShift = sequenceBits + workerIdBits;

  @SuppressWarnings("FieldCanBeLocal")
  private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

  @SuppressWarnings("FieldCanBeLocal")
  private final long sequenceMask = ~(-1L << sequenceBits);

  private final long twepoch;
  private final long datacenterId;
  private final long workerId;
  private long sequence;
  private long lastTimestamp;

  public IdSnowflakeDao(
      @Value("${snowflake.twepoch}") long twepoch,
      @Value("${snowflake.datacenterId}") long datacenterId,
      @Value("${snowflake.workerId}") long workerId) {
    if (workerId > maxWorkerId || workerId < 0) {
      throw new IllegalArgumentException(
          String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
    }
    if (datacenterId > maxDatacenterId || datacenterId < 0) {
      throw new IllegalArgumentException(
          String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
    }
    this.twepoch = twepoch;
    this.datacenterId = datacenterId;
    this.workerId = workerId;
    sequence = 0L;
    lastTimestamp = -1L;
    // log
    logger.info(
        String.format(
            "{\"twepoch\":%d,\"datacenterId\":%d,\"workerId\":%d}",
            this.twepoch, this.datacenterId, this.workerId));
  }

  @Override
  public synchronized Long generate() {
    long timestamp = timeGen();
    if (timestamp < lastTimestamp) {
      throw new RuntimeException(
          String.format(
              "Clock moved backwards.  Refusing to generate id for %d milliseconds",
              lastTimestamp - timestamp));
    }
    if (lastTimestamp == timestamp) {
      sequence = (sequence + 1) & sequenceMask;
      if (sequence == 0) {
        timestamp = tilNextMillis(lastTimestamp);
      }
    } else {
      sequence = 0L;
    }
    lastTimestamp = timestamp;
    long result =
        ((timestamp - twepoch) << timestampLeftShift) //
            | (datacenterId << datacenterIdShift) //
            | (workerId << workerIdShift) //
            | sequence;
    if (result > (1L << 53 - 1)) {
      throw new RuntimeException(
          String.format("result (%d) can't be greater than 2 ^ 53 - 1", result));
    }
    return result;
  }

  @Override
  public synchronized Long parse(Long id) {
    return (id >>> datacenterIdBits + workerIdBits + sequenceBits) + twepoch;
  }

  private long tilNextMillis(long lastTimestamp) {
    long timestamp = timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = timeGen();
    }
    return timestamp;
  }

  private long timeGen() {
    return System.currentTimeMillis();
  }
}
