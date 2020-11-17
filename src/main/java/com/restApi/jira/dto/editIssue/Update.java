package com.restApi.jira.dto.editIssue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Update {

    @JsonProperty("summary")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Summary> summaries;

    @JsonProperty("components")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Components> components;

    @JsonProperty("labels")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Labels> labels;

    @JsonProperty("properties")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Properties> properties;
}
