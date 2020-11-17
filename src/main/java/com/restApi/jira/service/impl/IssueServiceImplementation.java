package com.restApi.jira.service.impl;

import com.restApi.jira.dto.editIssue.EditIssue;
import com.restApi.jira.dto.issue.*;
import com.restApi.jira.exception.EmptyFieldException;
import com.restApi.jira.service.IssuesService;
import com.restApi.jira.utils.CheckIfObjectNullOrEmpty;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.restApi.jira.utils.PageUri.JIRA_BASE_URL;
import static com.restApi.jira.utils.PageUri.REST_ISSUE;
import static com.restApi.jira.utils.PageUri.ASSIGNEE;
import static com.restApi.jira.utils.PageUri.COMMENT;

@Service
@RequiredArgsConstructor
public class IssueServiceImplementation implements IssuesService {

    private final JiraServiceImplementation jiraServiceImplementation;
    private final RestTemplate restTemplate;

    @Override
    public String createIssue(Issue issue) {
        if (CheckIfObjectNullOrEmpty.checkIfIssueFieldsIsNullOrEmpty(issue)) {
            HttpEntity request = new HttpEntity(issue, getHeader());
            return restTemplate.exchange(JIRA_BASE_URL + REST_ISSUE, HttpMethod.POST, request,
                    CreateIssueResponse.class).getBody().getId();
        } else {
            try {
                throw new EmptyFieldException("Please fill all necessary fields");
            } catch (HttpClientErrorException httpClientErrorException) {
                return "You are not authorized, please sign in with your JIRA account";
            } catch (EmptyFieldException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
    }

    @Override
    public String assignIssueToMe(String key) {
        try {
            Assignee assignee = new Assignee(jiraServiceImplementation.getCurrentUser().getBody().getName());
            HttpEntity request = new HttpEntity(assignee, getHeader());
            restTemplate.exchange(JIRA_BASE_URL + REST_ISSUE.concat(key) + ASSIGNEE, HttpMethod.PUT, request, String.class);
            return "Issue with Key/Id " + key + " was assigned to you";
        } catch (HttpClientErrorException.Unauthorized httpClientErrorException) {
            return "You are not authorized, please sign in with your JIRA account";
        } catch (HttpClientErrorException.NotFound clientErrorException) {
            return "No issue found with Key/ID " + key;
        } catch (Exception e) {
            return "There are some internal issues";
        }
    }

    @Override
    public String editIssue(String id, EditIssue editIssue) {
        if (CheckIfObjectNullOrEmpty.checkIfAllEditIssueFieldsIsNullOrEmpty(editIssue)) {
            HttpEntity request = new HttpEntity(editIssue, getHeader());
            try {
                restTemplate.exchange(JIRA_BASE_URL + REST_ISSUE.concat(id), HttpMethod.PUT, request, String.class);
                return "Issue with Key/ID " + id + " edited successfully";
            } catch (HttpClientErrorException.NotFound httpClientErrorException) {
                return "No issue found with Key/ID " + id;
            }
        } else {
            try {
                throw new EmptyFieldException("Please fill at least one field");
            } catch (HttpClientErrorException httpClientErrorException) {
                return "You are not authorized, please sign in with your JIRA account";
            } catch (EmptyFieldException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
    }

    @Override
    public String addComment(String id, Comment comment) {
        if (!comment.getBody().isEmpty() && comment.getBody() != null) {
            HttpEntity request = new HttpEntity(comment, getHeader());
            try {
                restTemplate.exchange(JIRA_BASE_URL + REST_ISSUE.concat(id) + COMMENT, HttpMethod.POST, request, String.class);
                return "Comment added successfully to issue with ID " + id;
            } catch (HttpClientErrorException.NotFound httpClientErrorException) {
                return "No issue found with Key/ID " + id;
            }
        } else {
            try {
                throw new EmptyFieldException("Please fill the empty fields");
            } catch (HttpClientErrorException httpClientErrorException) {
                return "You are not authorized, please sign in with your JIRA account";
            } catch (EmptyFieldException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
    }

    @Override
    public String deleteCommentById(String issueId, String commentId) {
        HttpEntity request = new HttpEntity(getHeader());
        try {
            restTemplate.exchange(JIRA_BASE_URL + REST_ISSUE.concat(issueId) + COMMENT + "/" + commentId,
                    HttpMethod.DELETE, request, String.class);
            return "Comment with ID " + commentId + " from issue with Key/ID " + issueId + " was deleted successfully";
        } catch (HttpClientErrorException.NotFound httpClientErrorException) {
            return "No comment found with ID " + commentId + " or issue with Key/ID " + issueId;
        } catch (Exception e) {
            return "There are some internal issues";
        }
    }

    @Override
    public String deleteIssueById(String id) {
        HttpEntity request = new HttpEntity(getHeader());
        try {
            restTemplate.exchange(JIRA_BASE_URL + REST_ISSUE.concat(id),
                    HttpMethod.DELETE, request,
                    String.class);
            return "Issue with Key/ID " + id + " was deleted successfully";
        } catch (HttpClientErrorException.NotFound httpClientErrorException) {
            return "No issue found with Key/ID " + id;
        } catch (Exception e) {
            return "There are some internal issues";
        }
    }

    private HttpHeaders getHeader() {
        jiraServiceImplementation.getSession();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("cookie", "JSESSIONID=" + jiraServiceImplementation.sessionValue.getSessionValue());
        return headers;
    }
}
