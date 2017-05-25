package com.xp.mymvc.Tool.Net;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.xp.mymvc.Tool.Util.DES3Util;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by CheYR-T-XiangP on 2017/5/18.
 */

public class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;
    private final Gson mGson;
    DecodeResponseBodyConverter(Gson gson,TypeAdapter<T> adapter) {
        this.mGson = gson;
        this.adapter = adapter;
    }
    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        String response = responseBody.string();
        String result = "";
        try {
            result = DES3Util.decode(response);
            JsonReader jsonReader = mGson.newJsonReader(new StringReader(result));
            try {
                return adapter.read(jsonReader);
            } finally {
                responseBody.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

