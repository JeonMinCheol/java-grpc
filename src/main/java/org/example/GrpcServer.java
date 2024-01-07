package org.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {
    Server server = ServerBuilder
            .forPort(5454)
            .addService(new HelloServiceImpl())
            .build();
}
