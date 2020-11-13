package com.restApi.jira.service.jira;


import com.restApi.jira.dto.issue.Issue;
import org.springframework.http.ResponseEntity;

public interface IssuesService {

   String createIssue(Issue issue);

   String deleteIssueById(String id);

   ResponseEntity<String> getIssuesAssignedToUser(String name);

}
