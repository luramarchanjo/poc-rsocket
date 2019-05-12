package com.example.server

import io.rsocket.AbstractRSocket
import io.rsocket.Payload
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger

class ServerSocket : AbstractRSocket() {

    val log = Logger.getGlobal()

    /**
     * Handle Request/Response messages
     */
    override fun requestResponse(payload: Payload?): Mono<Payload> {
        log.info("Received request-and-response")
        return Mono.empty()
    }

    /**
     * Handle Fire-and-Forget messages
     */
    override fun fireAndForget(payload: Payload?): Mono<Void> {
        log.info("Received fire-and-forget")
        return Mono.empty()
    }

    /**
     * Handle Request/Stream messages. Each request returns a new stream.
     */
    override fun requestStream(payload: Payload?): Flux<Payload> {
        log.info("Received request stream")
        return Flux.empty()
    }


    /**
     * Handle request for bidirectional channel
     */
    override fun requestChannel(payloads: Publisher<Payload>?): Flux<Payload> {
        log.info("Received request channel")
        return Flux.from(payloads)
    }

}