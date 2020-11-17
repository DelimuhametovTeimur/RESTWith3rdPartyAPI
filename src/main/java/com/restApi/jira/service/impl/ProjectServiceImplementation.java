package com.restApi.jira.service.impl;

import com.restApi.jira.dto.project.Project;
import com.restApi.jira.service.ProjectService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.restApi.jira.utils.PageUri.JIRA_BASE_URL;
import static com.restApi.jira.utils.PageUri.GET_PROJECT;

@Service
@RequiredArgsConstructor
public class ProjectServiceImplementation implements ProjectService {

    private final JiraServiceImplementation jiraServiceImplementation;
    private final RestTemplate restTemplate;

    @Override
    public Project getProjectByKey(String key) {
        jiraServiceImplementation.getSession();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cookie", "JSESSIONID=" + jiraServiceImplementation.sessionValue.getSessionValue());
        HttpEntity request = new HttpEntity<>(headers);
        return restTemplate.exchange(JIRA_BASE_URL + GET_PROJECT + key, HttpMethod.GET, request, Project.class)
                .getBody();
    }
}
