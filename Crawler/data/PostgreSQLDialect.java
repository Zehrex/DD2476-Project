13
https://raw.githubusercontent.com/javamxd/ssssssss/master/src/main/java/org/ssssssss/dialect/PostgreSQLDialect.java
package org.ssssssss.dialect;

import org.ssssssss.context.RequestContext;

public class PostgreSQLDialect implements Dialect {
    @Override
    public String getPageSql(String sql, RequestContext context, long offset, long limit) {
        context.addParameter(limit);
        context.addParameter(offset);
        return sql + " limit ? offset ?";
    }
}
