package com.example.server

import io.rsocket.RSocket
import io.rsocket.RSocketFactory
import io.rsocket.transport.netty.server.CloseableChannel
import io.rsocket.transport.netty.server.TcpServerTransport
import reactor.core.publisher.Mono
import java.util.*
import java.util.logging.Logger

fun main(args: Array<String> = arrayOf()) {

    val log = Logger.getGlobal()

    log.info("Starting RSocket server at 8080")
    RSocketFactory.receive()
        .acceptor { setupPayload, reactiveSocket -> Mono.just<RSocket>(ServerSocket()) }
        .transport<CloseableChannel>(TcpServerTransport.create("localhost", 8080))
        .start()
        .subscribe()
    log.info("Started RSocket server at 8080")

    log.info("Press <ENTER> in the prompt to stop the server")
    val scanner = Scanner(System.`in`)
    scanner.nextLine()

}