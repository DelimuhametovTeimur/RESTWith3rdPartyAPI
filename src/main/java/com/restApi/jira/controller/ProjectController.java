package com.restApi.jira.controller;

import com.restApi.jira.dto.project.Project;
import com.restApi.jira.service.impl.ProjectServiceImplementation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.restApi.jira.utils.PageUri.KEY;
import static com.restApi.jira.utils.PageUri.PROJECT;

@RestController
@RequiredArgsConstructor
@RequestMapping(PROJECT)
public class ProjectController {

    private final ProjectServiceImplementation projectServiceImplementation;

    @GetMapping(KEY)
    public ResponseEntity<Project> getProjectByKey(@PathVariable String key) {
        return ResponseEntity.ok(projectServiceImplementation.getProjectByKey(key));
    }
}
