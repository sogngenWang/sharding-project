package com.wsg.sharding.algo.common;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;
import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;

import java.util.ArrayList;
import java.util.Collection;

public final class DatabaseHintShardingAlgorithm implements HintShardingAlgorithm<String>, ShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String each : availableTargetNames) {
            for (String value : shardingValue.getValues()) {
                String[] values = value.split(",");
                for (String s : values) {
                    if (each.endsWith("_" + s)) {
                        result.add(each);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "COMMON_SHARD";
    }
}
