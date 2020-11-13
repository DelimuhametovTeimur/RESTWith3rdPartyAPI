package com.restApi.jira.service.jira.impl;

import com.restApi.jira.dto.issue.CreateIssueResponse;
import com.restApi.jira.dto.issue.Issue;
import com.restApi.jira.exception.EmptyFieldException;
import com.restApi.jira.service.jira.IssuesService;
import com.restApi.jira.utils.CheckIfObjectNullOrEmpty;
import lombok.RequiredArgsConstructor;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.restApi.jira.utils.PageUri.*;

@Service
@RequiredArgsConstructor
public class IssueServiceImplementation implements IssuesService {

    private final JiraServiceImplementation jiraServiceImplementation;
    private final RestTemplate restTemplate;

    @Override
    public String createIssue(Issue issue) {
        if (CheckIfObjectNullOrEmpty.checkIfIssueFieldsIsNullOrEmpty(issue)) {
            HttpEntity request = new HttpEntity(issue, getHeader());
            return restTemplate.exchange(JIRA_BASE_URL + CREATE_ISSUE, HttpMethod.POST, request,
                CreateIssueResponse.class).getBody().getId();
        } else {
            try {
                throw new EmptyFieldException("Please fill all necessary fields");
            } catch (HttpClientErrorException httpClientErrorException){
                return "You are not authorized, please sign in with your JIRA account";
            }
            catch (EmptyFieldException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
    }

    @Override
    public String deleteIssueById(String id) {
        HttpEntity request = new HttpEntity(getHeader());
        try {
            restTemplate.exchange(JIRA_BASE_URL + DELETE_ISSUE.concat(id),
                HttpMethod.DELETE, request,
                String.class);
            return "Issue with id - " + id + " was deleted successfully";
        } catch (HttpClientErrorException clientErrorException){
            return "You don't have permission to delete this issue";
        }
        catch (Exception  e) {
            return "No Issue with ID " + id;
        }
    }

    @Override
    public ResponseEntity<String> getIssuesAssignedToUser(String name) {
        String url = JIRA_BASE_URL.concat(GET_ALL_ISSUES_ASSIGNED_TO_USER).concat(name);
        HttpEntity request = new HttpEntity<>(getHeader());
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        return result;
    }

    private HttpHeaders getHeader() {
        jiraServiceImplementation.getSession();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cookie", "JSESSIONID=" + jiraServiceImplementation.sessionValue.getSessionValue());
        return headers;
    }
}
