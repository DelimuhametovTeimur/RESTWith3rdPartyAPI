package com.restApi.jira.service.jira.impl;

import com.restApi.jira.service.jira.ProjectService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static com.restApi.jira.utils.PageUri.GET_ALL_PROJECTS;
import static com.restApi.jira.utils.PageUri.JIRA_BASE_URL;

@Service
@RequiredArgsConstructor
public class ProjectServiceImplementation implements ProjectService {

    private final JiraServiceImplementation jiraServiceImplementation;
    private final RestTemplate restTemplate;

    @Override
    public ArrayList getAllProjects() {
        jiraServiceImplementation.getSession();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cookie", "JSESSIONID=" + jiraServiceImplementation.sessionValue.getSessionValue());
        HttpEntity request = new HttpEntity<>(headers);
        return restTemplate.exchange(JIRA_BASE_URL + GET_ALL_PROJECTS, HttpMethod.GET, request, ArrayList.class)
            .getBody();
    }
}
