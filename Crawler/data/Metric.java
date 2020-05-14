23
https://raw.githubusercontent.com/datastax/metric-collector-for-apache-cassandra/master/src/main/java/com/datastax/mcac/insights/metrics/Metric.java
package com.datastax.mcac.insights.metrics;

import java.util.Map;
import java.util.Optional;

import com.datastax.mcac.insights.Insight;
import com.datastax.mcac.insights.InsightMetadata;

public class Metric extends Insight
{
    public enum MetricType
    {
        COUNTER,
        GAUGE,
        HISTOGRAM,
        METER,
        TIMER
    }

    public Metric(
            String name,
            Optional<Long> timestamp,
            Optional<Map<String, String>> tags,
            InsightMetadata.InsightType insightType,
            Object metricData
    )
    {
        super(new InsightMetadata(name, timestamp, tags, Optional.of(insightType), Optional.empty()), metricData);
    }
}
