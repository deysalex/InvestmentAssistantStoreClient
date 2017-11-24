package ru.deysa.investment.assistant.store.client;

import org.junit.Assert;
import org.junit.Test;
import ru.deysa.investment.assistant.store.client.api.v1.share.ShareResponse;

import java.util.List;

public class StoreClientTest {

    @Test
    public void getShares() throws Exception {
        StoreClient storeClient = new StoreClient("http://investmentassistantstore.deysa.ru/api/v1");
        List<ShareResponse> list = storeClient.getShares();

        Assert.assertTrue(list != null);
        Assert.assertTrue(!list.isEmpty());
    }
}