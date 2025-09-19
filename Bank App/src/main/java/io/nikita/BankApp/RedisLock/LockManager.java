package io.nikita.BankApp.RedisLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;

@Component
public class LockManager {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    private static final String LOCK_PREFIX = "lock:";
    private static final int LOCK_EXPIRY = 30;

    public String acquireLock(String lockKey){
        String lockId = UUID.randomUUID().toString();
        boolean lockValue = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_PREFIX + lockKey, lockId, LOCK_EXPIRY, java.util.concurrent.TimeUnit.SECONDS);
        return Boolean.TRUE.equals(lockValue)?lockId:null;
    }
    public boolean releaseLock(String lockKey, String lockId) {
        String currentLockValue = stringRedisTemplate.opsForValue().get(LOCK_PREFIX + lockKey);
        if (currentLockValue != null && currentLockValue.equals(lockId)) {
            stringRedisTemplate.delete(LOCK_PREFIX + lockKey);
            return true;
        }
        return false;
    }

}