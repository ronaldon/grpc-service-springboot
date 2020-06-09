package com.example.grpc.client.grpcclient;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.grpc.server.grpcserver.HelloRequest;
import com.example.grpc.server.grpcserver.HelloResponse;
import com.example.grpc.server.grpcserver.HelloServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
public class GrpcClientApplication {

	public static void main(String[] args) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
				.usePlaintext()
				.build();

		HelloServiceGrpc.HelloServiceBlockingStub stub
				= HelloServiceGrpc.newBlockingStub(channel);

		HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
				.setFirstName("Baeldung")
				.setLastName("gRPC")
				.build());

		System.out.println(helloResponse);

		channel.shutdown();

		/*ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
				.usePlaintext()
				.build();

		HelloServiceGrpc.HelloServiceBlockingStub stub
				= HelloServiceGrpc.newBlockingStub(channel);

		HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
				.setFirstName("Baeldung")
				.setLastName("gRPC")
				.build());

		channel.shutdown();*/
//		SpringApplication.run(GrpcClientApplication.class, args);
	}

}
