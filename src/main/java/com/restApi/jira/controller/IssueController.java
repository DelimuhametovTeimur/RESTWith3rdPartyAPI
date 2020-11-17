package com.restApi.jira.controller;

import com.restApi.jira.dto.editIssue.EditIssue;
import com.restApi.jira.dto.issue.Comment;
import com.restApi.jira.dto.issue.Issue;
import com.restApi.jira.service.IssuesService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.restApi.jira.utils.PageUri.CREATE;
import static com.restApi.jira.utils.PageUri.ISSUE;
import static com.restApi.jira.utils.PageUri.ASSIGNEE_ISSUE;
import static com.restApi.jira.utils.PageUri.EDIT_ISSUE;
import static com.restApi.jira.utils.PageUri.ADD_COMMENT;
import static com.restApi.jira.utils.PageUri.DELETE_COMMENT;
import static com.restApi.jira.utils.PageUri.DELETE;

@RestController
@RequiredArgsConstructor
@RequestMapping(ISSUE)
public class IssueController {

    private final IssuesService issuesService;

    @PostMapping(CREATE)
    public ResponseEntity<String> createIssue(@RequestBody Issue issue) {
        return ResponseEntity.ok("Issue with ID " + issuesService.createIssue(issue) + " was created");
    }

    @PutMapping(ASSIGNEE_ISSUE)
    public String assigneeIssue(@PathVariable String key) {
        return issuesService.assignIssueToMe(key);
    }

    @PutMapping(EDIT_ISSUE)
    public String editIssue(@PathVariable String id, @RequestBody EditIssue editIssue) {
        return issuesService.editIssue(id, editIssue);
    }

    @PostMapping(ADD_COMMENT)
    public String addComment(@PathVariable String id, @RequestBody Comment comment) {
        return issuesService.addComment(id, comment);
    }

    @DeleteMapping(DELETE_COMMENT)
    public String deleteCommentById(@PathVariable String issueId, @PathVariable String commentId) {
        return issuesService.deleteCommentById(issueId, commentId);
    }

    @DeleteMapping(DELETE)
    public String deleteIssueById(@PathVariable String id) {
        return issuesService.deleteIssueById(id);
    }
}
