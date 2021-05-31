/* Copyright (C)2021  Vivian */
package com.codeHub.utils;

import com.codeHub.Data;

public class Response {
    private Data data;
    private Metadata metadata;

    public Response(Data data, Metadata metadata) {
        this.data = data;
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "Response{" + "data=" + data + ", metadata=" + metadata + '}';
    }
}
