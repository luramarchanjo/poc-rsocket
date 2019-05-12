package com.example.client

import io.rsocket.Payload
import io.rsocket.RSocketFactory
import io.rsocket.transport.netty.client.TcpClientTransport
import io.rsocket.util.DefaultPayload
import java.util.*
import java.util.logging.Logger

fun main(args: Array<String> = arrayOf()) {

    val log = Logger.getGlobal()

    log.info("Starting connection with RSocket Server")
    val rsocketClient = RSocketFactory.connect()
        .transport(TcpClientTransport.create("localhost", 8080))
        .start()
        .block();

    log.info("Calling RSocket Server using request and response")
    val response = rsocketClient.requestResponse(DefaultPayload.create(UUID.randomUUID().toString()))
        .map { Payload::getDataUtf8 }
        .block()

    log.info("RSocket Server response $response")
    rsocketClient.dispose()
    log.info("Stopped RSocket Server connection")

}