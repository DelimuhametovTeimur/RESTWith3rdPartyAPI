package com.restApi.jira.controller.quote;

import com.restApi.jira.service.quote.QuoteService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quote")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping("/random")
    public ResponseEntity<String> getRandomQuote(){
       return ResponseEntity.ok(quoteService.getRandomQuote().getValue().getQuote());
    }
}
