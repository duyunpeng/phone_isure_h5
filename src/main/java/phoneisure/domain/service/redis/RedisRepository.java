package phoneisure.domain.service.redis;

import org.springframework.stereotype.Repository;
import phoneisure.domain.service.redis.generic.AbstractRedisGenericRepository;

/**
 * Created by YJH on 2016/3/21.
 */
@Repository("redisRepository")
public class RedisRepository extends AbstractRedisGenericRepository<String,String> implements IRedisRepository<String,String> {

}
