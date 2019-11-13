package ch.amk.exercise1.utils;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.ResponseDelivery;
import com.android.volley.toolbox.NoCache;

import java.util.Objects;

import ch.amk.exercise1.ImmediateResponseDelivery;

public class RequestQueueMockBuilder {

    private ResponseDelivery responseDelivery = new ImmediateResponseDelivery();
    private Network network = null;
    private Cache cache = new NoCache();
    private int threadPoolSize = 1;

    public RequestQueueMockBuilder() { }

    public RequestQueueMockBuilder network(Network network) {
        this.network = network;
        return this;
    }

    public RequestQueueMockBuilder cache(Cache cache) {
        this.cache = cache;
        return this;
    }

    public RequestQueueMockBuilder threadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
        return this;
    }

    public RequestQueueMockBuilder responseDeliver(ResponseDelivery responseDelivery) {
        this.responseDelivery = responseDelivery;
        return this;
    }

    public RequestQueue create() {
        if(Objects.isNull(this.network)) {
            throw new RuntimeException("Failed to create Request queue, network not set");
        }
        RequestQueue requestQueue = new RequestQueue(this.cache, this.network, this.threadPoolSize, this.responseDelivery);
        requestQueue.start();
        return requestQueue;
    }
}
