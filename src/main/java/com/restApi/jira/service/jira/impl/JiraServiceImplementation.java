package com.restApi.jira.service.jira.impl;

import com.restApi.jira.dto.jira.CurrentUser;
import com.restApi.jira.dto.jira.SessionResponse;
import com.restApi.jira.dto.jira.SessionValue;
import com.restApi.jira.exception.EmptyFieldException;
import com.restApi.jira.service.jira.JiraService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.restApi.jira.utils.PageUri.*;

@Service
@RequiredArgsConstructor
public class JiraServiceImplementation implements JiraService {

    private final RestTemplate restTemplate;

    SessionValue sessionValue = new SessionValue();

    @Value("")
    private String username;

    @Value("")
    private String password;

    @Override
    public void getSession() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);
        if (body.isEmpty()){
            try {
                throw new EmptyFieldException("Please add your JIRA credentials to application properties");
            } catch (EmptyFieldException e) {
                e.printStackTrace();
            }
        }

        HttpEntity request = new HttpEntity<>(body, headers);
        sessionValue.setSessionValue(
            restTemplate.postForEntity(JIRA_BASE_URL + CREATE_SESSION_URL, request,
            SessionResponse.class).getBody().getSession().getValue());
    }

    @Override
    public ResponseEntity<CurrentUser> getCurrentUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cookie", "JSESSIONID=" + sessionValue.getSessionValue());
        HttpEntity request = new HttpEntity<>(headers);
        return restTemplate.exchange(JIRA_BASE_URL + GET_CURRENT_USER, HttpMethod.GET, request, CurrentUser.class);
    }
}
