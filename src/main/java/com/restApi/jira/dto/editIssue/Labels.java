package com.restApi.jira.dto.editIssue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Labels {

    @JsonProperty("add")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String add;

    @JsonProperty("remove")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String remove;
}
