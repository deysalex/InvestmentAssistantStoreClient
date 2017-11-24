package ru.deysa.investment.assistant.store.client;

import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ru.deysa.investment.assistant.store.client.api.v1.share.ShareResponse;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StoreClient {

    private final String apiUrl;

    public StoreClient(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * Shares list. Api '/share/list'
     * @return
     */
    public List<ShareResponse> getShares() {
        try {
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
            HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

            ResponseEntity<ShareResponse[]> responseEntity = restTemplate.exchange(apiUrl + "/share/list", HttpMethod.GET, requestEntity, ShareResponse[].class);
            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                return Collections.emptyList();
            }
            return Arrays.asList(responseEntity.getBody());
        } catch (Exception e) {
            //log.error(e.getMessage());
            return Collections.emptyList();
        }
    }
}
