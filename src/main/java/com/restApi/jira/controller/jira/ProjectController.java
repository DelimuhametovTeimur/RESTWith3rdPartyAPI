package com.restApi.jira.controller.jira;

import com.restApi.jira.service.jira.impl.ProjectServiceImplementation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.restApi.jira.utils.PageUri.ALL;
import static com.restApi.jira.utils.PageUri.PROJECT;

@RestController
@RequiredArgsConstructor
@RequestMapping(PROJECT)
public class ProjectController {

    private final ProjectServiceImplementation projectServiceImplementation;

    @GetMapping(ALL)
    public ResponseEntity<String> getAllProjects() {
        return ResponseEntity.ok("You currently assigned to the " +
                                 projectServiceImplementation.getAllProjects().size() +
                                 " projects");
    }
}
