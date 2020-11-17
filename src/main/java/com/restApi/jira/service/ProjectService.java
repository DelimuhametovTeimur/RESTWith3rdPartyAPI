package com.restApi.jira.service;

import com.restApi.jira.dto.project.Project;

public interface ProjectService {
    Project getProjectByKey(String key);
}
