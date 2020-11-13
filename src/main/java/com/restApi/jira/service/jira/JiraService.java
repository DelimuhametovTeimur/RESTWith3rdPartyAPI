package com.restApi.jira.service.jira;

import com.restApi.jira.dto.jira.CurrentUser;
import org.springframework.http.ResponseEntity;

public interface JiraService {

    void getSession();

    ResponseEntity<CurrentUser> getCurrentUser();
}
