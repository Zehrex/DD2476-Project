23
https://raw.githubusercontent.com/WeBankFinTech/Exchangis/master/modules/executor/engine/datax/datax-core/src/main/java/com/alibaba/datax/plugin/unstructuredstorage/writer/UnstructuredWriter.java
package com.alibaba.datax.plugin.unstructuredstorage.writer;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public interface UnstructuredWriter extends Closeable {

    public void writeOneRecord(List<String> splitedRows) throws IOException;

    public void flush() throws IOException;

    @Override
    public void close() throws IOException;

}
