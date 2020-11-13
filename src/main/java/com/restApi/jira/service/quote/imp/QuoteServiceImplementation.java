package com.restApi.jira.service.quote.imp;

import com.restApi.jira.dto.quote.Quote;
import com.restApi.jira.service.quote.QuoteService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.restApi.jira.utils.PageUri.QUOTE_URL;

@Service
@RequiredArgsConstructor
public class QuoteServiceImplementation implements QuoteService {

    private final RestTemplate restTemplate;

    @Override
    public Quote getRandomQuote() {
        return restTemplate.getForObject(
            QUOTE_URL, Quote.class);
    }
}
