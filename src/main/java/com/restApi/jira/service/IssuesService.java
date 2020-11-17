package com.restApi.jira.service;

import com.restApi.jira.dto.editIssue.EditIssue;
import com.restApi.jira.dto.issue.Comment;
import com.restApi.jira.dto.issue.Issue;

public interface IssuesService {
    String createIssue(Issue issue);
    String deleteIssueById(String id);
    String assignIssueToMe(String key);
    String editIssue(String id, EditIssue editIssue);
    String addComment(String id, Comment comment);
    String deleteCommentById(String issueId, String commentId);
}
