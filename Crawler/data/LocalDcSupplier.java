23
https://raw.githubusercontent.com/datastax/metric-collector-for-apache-cassandra/master/src/main/java/com/datastax/mcac/utils/LocalDcSupplier.java
package com.datastax.mcac.utils;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import org.apache.cassandra.config.DatabaseDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalDcSupplier
{
    private static final Supplier<String> LOCAL_DC_SUPPLIER = Suppliers.memoize(DatabaseDescriptor::getLocalDataCenter);

    public static String getLocalDc()
    {
        return LOCAL_DC_SUPPLIER.get();
    }
}
