package vn.silverbot99.farm_traders.app.network;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.*;
import java.util.zip.DataFormatException;

final class AppGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

private static final MediaType MEDIA_TYPE = MediaType.get("application/json");

private final Gson gson;
private final TypeAdapter<T> adapter;
AppGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
    this.gson = gson;
    this.adapter = adapter;
}

@Override
public T convert(ResponseBody value) throws IOException {

    MediaType contentType = value.contentType();
    if (contentType.equals(MEDIA_TYPE)) {

        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            T result = adapter.read(jsonReader);
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }
            return result;
        } finally {
            value.close();
        }
    }

    T result;
    JsonReader jsonReader;

    try {
        byte[] buffer = value.bytes();
        byte[] out;
        try {
            out = Compression.decompress(buffer);
        } catch (DataFormatException e) {
            throw new JsonIOException("JSON document was not fully consumed.");
        }
        Reader reader = new InputStreamReader(new ByteArrayInputStream(out));
        String temp = new String(out, "utf-8");
        Log.d("OkHttp", "response: "+ temp);

        jsonReader = gson.newJsonReader(reader);
        result = adapter.read(jsonReader);
        if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
            throw new JsonIOException("JSON document was not fully consumed.");
        }
        return result;
    } finally {
        value.close();
    }

}

public byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
    try {
        byte[] buffer = new byte[1024];
        int bytesRead;


        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    } catch (OutOfMemoryError error) {
        return null;
    }
}
}