23
https://raw.githubusercontent.com/datastax/metric-collector-for-apache-cassandra/master/src/main/java/com/datastax/mcac/interceptors/AbstractInterceptor.java
package com.datastax.mcac.interceptors;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

import com.datastax.mcac.UnixSocketClient;

public abstract class AbstractInterceptor
{
    /*
     * Needs to be visible for Advice that cannot add fields
     */
    public static final Supplier<UnixSocketClient> client = Suppliers.memoize(() -> new UnixSocketClient());
}
