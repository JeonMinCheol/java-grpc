package org.example;

import io.grpc.stub.StreamObserver;
import kr.co.velnova.grpc.helloworld.HelloRequest;
import kr.co.velnova.grpc.helloworld.HelloResponse;
import kr.co.velnova.grpc.helloworld.HelloServiceGrpc;

public class helloService extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void helloWorld(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String firstName = request.getFirstName();
        String lastName = request.getLastName();

        System.out.println(request);

        HelloResponse helloResponse = HelloResponse.newBuilder()
                .setMessage(lastName + firstName)
                .build();

        try{
            // 응답을 클라이언트에게 전달
            responseObserver.onNext(helloResponse);
            // 응답이 종료되었음을 나타냄
            responseObserver.onCompleted();
        }
        catch (Exception e) {
            responseObserver.onError(e);
        }
    }
}
