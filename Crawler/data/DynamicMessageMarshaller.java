3
https://raw.githubusercontent.com/zalopay-oss/jmeter-grpc-request/master/src/main/java/vn/zalopay/benchmark/core/grpc/DynamicMessageMarshaller.java
package vn.zalopay.benchmark.core.grpc;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.ExtensionRegistryLite;
import io.grpc.MethodDescriptor.Marshaller;

import java.io.IOException;
import java.io.InputStream;

/**
 * A {@link Marshaller} for dynamic messages.
 */
public class DynamicMessageMarshaller implements Marshaller<DynamicMessage> {
    private final Descriptor messageDescriptor;

    public DynamicMessageMarshaller(Descriptor messageDescriptor) {
        this.messageDescriptor = messageDescriptor;
    }

    @Override
    public DynamicMessage parse(InputStream inputStream) {
        try {
            return DynamicMessage.newBuilder(messageDescriptor)
                    .mergeFrom(inputStream, ExtensionRegistryLite.getEmptyRegistry())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Unable to merge from the supplied input stream", e);
        }
    }

    @Override
    public InputStream stream(DynamicMessage abstractMessage) {
        return abstractMessage.toByteString().newInput();
    }
}