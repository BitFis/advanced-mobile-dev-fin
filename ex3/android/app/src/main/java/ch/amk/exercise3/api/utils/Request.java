package ch.amk.exercise3.api.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

class Request extends StringRequest {

    private static final int DAY_IN_MILIS = 86400 * 1000;
    HashMap<String, String> headers = new HashMap<String, String>();
    HashMap<String, String> params = new HashMap<String, String>();
    private String body = "";

    public Request(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }


    /**
     * @param name
     * @param value
     * @return the value of any previous mapping with the specified key or
     *         {@code null} if there was no such mapping.
     */
    public void putParam(String name, String value) {
        params.put(name, value);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        HashMap<String, String> params = new HashMap<String, String>();
        params.putAll(super.getParams());
        params.putAll(this.params);
        return params;
    }

    /**
     * @param name
     * @param value
     * @return the value of any previous mapping with the specified key or
     *         {@code null} if there was no such mapping.
     */
    public String putHeader(String name, String value) {
        return headers.put(name, value);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.putAll(super.getHeaders());
        headers.putAll(this.headers);
        return headers;
    }

    public void setConntentType(String contentType) {
        headers.put("Content-Type", contentType);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    public byte[] getBody() {
        return ((body == null) ? "" : body).getBytes();
    }

    public void setBody(String body) {
        this.body = body;
    }
}