package com.example.client

import io.rsocket.RSocketFactory
import io.rsocket.transport.netty.client.TcpClientTransport
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import java.util.logging.Logger

fun main(args: Array<String> = arrayOf()) {

    val log = Logger.getGlobal()

    log.info("Starting connection with RSocket Server")
    val rsocketClient = RSocketFactory.connect()
        .transport(TcpClientTransport.create("localhost", 8080))
        .start()
        .block();

    log.info("Calling RSocket Server using request-channel")
    rsocketClient.requestChannel(Flux.from(Publisher { subscriber -> subscriber.onComplete() })).blockLast()

    log.info("Stopped RSocket Server connection")
    rsocketClient.dispose()

}