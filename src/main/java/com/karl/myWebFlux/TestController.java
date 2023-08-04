package com.karl.myWebFlux;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description: DESC
 * @Author: zhouzhi96
 * @Date: 2023年08月04日: 10:02
 */
@Slf4j
@RestController
public class TestController {
    @GetMapping("/v1")
    public Publisher<String> home() {
        log.info("get v1 start");
        String str = createStr(1);
        log.info("get v1 end");
        return Mono.just(str);
    }

    @GetMapping("/v2")
    public String home2() {
        log.info("get v2 start");
        String str = createStr(2);
        log.info("get v2 end");
        return str;
    }

    @GetMapping("/v3")
    public Mono<String> home3() {
        log.info("get v3 start");
//        String str = createStr(3);
        Mono<String> result = Mono.fromSupplier(() -> createStr(3));
        log.info("get v3 end");
        return result;
    }

    /**
     * @Description: Flux 返回0-N个元素
     * produces = "text/event-stream"
     * @return * @return null
     * @author zhouzhi96
     * @date 2023/8/4 11:19
     */
    @GetMapping(value = "/v4", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> home4() {
        Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux data--- " + i;
        }));
        return result;
    }

    private String createStr(int i){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "home page " + i;
    }
}
