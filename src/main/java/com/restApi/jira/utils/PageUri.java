package com.restApi.jira.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageUri {
    public static final String QUOTE_URL = "https://gturnquist-quoters.cfapps.io/api/random";

    //Jira Base URL
    public static final String JIRA_BASE_URL = "https://jira-uat.endava.com/";

    //Sessions, User URLS
    public static final String CREATE_SESSION_URL = "rest/auth/latest/session";
    public static final String GET_CURRENT_USER = "rest/auth/latest/session";

    //Project
    public static final String GET_PROJECT = "rest/api/2/project/";
    
    //Issue
    public static final String REST_ISSUE = "rest/api/2/issue/";

    public static final String CREATE_SESSION = "/createNewSession";
    public static final String AUTH = "/auth";
    public static final String CURRENT_USER = "/currentUser";
    public static final String PROJECT = "/project";
    public static final String ISSUE = "/issue";
    public static final String CREATE = "/create";
    public static final String DELETE = "/delete/{id}";
    public static final String ASSIGNEE_ISSUE = "/assignee/{key}";
    public static final String ASSIGNEE = "/assignee";
    public static final String KEY = "/{key}";

    public static final String EDIT_ISSUE = "/edit/{id}";

    public static final String COMMENT = "/comment";
    public static final String ADD_COMMENT = "/{id}/comment/add";
    public static final String DELETE_COMMENT = "/{issueId}/comment/delete/{commentId}";

}
