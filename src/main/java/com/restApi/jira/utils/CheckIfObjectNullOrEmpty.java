package com.restApi.jira.utils;

import com.restApi.jira.dto.editIssue.EditIssue;
import com.restApi.jira.dto.issue.Issue;

public class CheckIfObjectNullOrEmpty {

    public static boolean checkIfIssueFieldsIsNullOrEmpty(Issue issue) {
        return issue.getFields().getDescription() != null && issue.getFields().getDescription().length() != 0 &&
                issue.getFields().getIssuetype() != null && issue.getFields().getIssuetype().getName().length() != 0 &&
                issue.getFields().getLabels() != null && !issue.getFields().getLabels().isEmpty() &&
                issue.getFields().getProject() != null && issue.getFields().getProject().getKey().length() != 0 &&
                issue.getFields().getSummary() != null && issue.getFields().getSummary().length() != 0;
    }

    public static boolean checkIfAllEditIssueFieldsIsNullOrEmpty(EditIssue editIssue) {
        return (editIssue.getUpdate().getSummaries() != null && !editIssue.getUpdate().getSummaries().isEmpty()) ||
                (editIssue.getUpdate().getComponents() != null && !editIssue.getUpdate().getComponents().isEmpty()) ||
                (editIssue.getUpdate().getLabels() != null && !editIssue.getUpdate().getLabels().isEmpty()) ||
                (editIssue.getUpdate().getProperties() != null && !editIssue.getUpdate().getProperties().isEmpty());
    }
}
