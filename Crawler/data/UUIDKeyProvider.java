13
https://raw.githubusercontent.com/javamxd/ssssssss/master/src/main/java/org/ssssssss/provider/impl/UUIDKeyProvider.java
package org.ssssssss.provider.impl;

import org.ssssssss.provider.KeyProvider;

import java.util.UUID;

public class UUIDKeyProvider implements KeyProvider {

    @Override
    public String getName() {
        return "uuid";
    }

    @Override
    public Object getKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
