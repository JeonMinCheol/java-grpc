package org.example;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import kr.co.velnova.grpc.helloworld.HelloRequest;
import kr.co.velnova.grpc.helloworld.HelloResponse;
import kr.co.velnova.grpc.helloworld.HelloServiceGrpc;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server grpcServer = new GrpcServer().server;

        System.out.println("Listening port 5454.");

        grpcServer.start();
//        grpcServer.awaitTermination();

        // channel 생성
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 5454)
                .usePlaintext() // 평문 연결 : 암호화 X
                .build();

        // stub 생성
        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        // 요청 전송 후 결과 반환
        HelloResponse helloResponse = stub.helloWorld(HelloRequest
                .newBuilder()
                .setFirstName("first")
                .setLastName("last")
                .build());

        System.out.println(helloResponse);

        channel.shutdown();
    }
}