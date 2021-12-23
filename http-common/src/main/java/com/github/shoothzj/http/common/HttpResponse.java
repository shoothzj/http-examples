package com.github.shoothzj.http.common;

import lombok.Data;

@Data
public class HttpResponse {

    private int statusCode;

    private String resp;

    public HttpResponse() {
    }
}
