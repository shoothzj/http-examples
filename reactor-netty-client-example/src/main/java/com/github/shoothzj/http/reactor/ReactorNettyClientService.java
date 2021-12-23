package com.github.shoothzj.http.reactor;

import com.github.shoothzj.http.common.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import reactor.netty.http.client.HttpClient;

import java.util.Optional;

public class ReactorNettyClientService {

    public Optional<HttpResponse> syncGet(String host, int port, String path) {
        String content = HttpClient.create().host(host).port(port)
                .get().uri(path).responseContent().asString().blockFirst();
        if (content == null) {
            return Optional.empty();
        }
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setStatusCode(HttpResponseStatus.OK.code());
        httpResponse.setResp(content);
        return Optional.of(httpResponse);
    }

}
