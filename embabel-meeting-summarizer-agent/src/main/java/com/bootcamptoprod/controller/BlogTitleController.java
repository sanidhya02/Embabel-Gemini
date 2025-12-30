package com.bootcamptoprod.controller;

import com.bootcamptoprod.dto.BlogRequest;
import com.bootcamptoprod.dto.BlogTitles;
import com.embabel.agent.api.common.autonomy.AgentInvocation;
import com.embabel.agent.core.AgentPlatform;
import com.embabel.agent.core.ProcessOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogTitleController {

    private static final Logger log = LoggerFactory.getLogger(BlogTitleController.class);

    private final AgentPlatform agentPlatform;

    public BlogTitleController(AgentPlatform agentPlatform) {
        this.agentPlatform = agentPlatform;
    }

    @PostMapping("/suggest-titles")
    public BlogTitles suggestBlogTitles(@RequestBody BlogRequest request) {

        var blogTitleAgentInvocation = AgentInvocation
                .builder(agentPlatform)
                .options(ProcessOptions.builder().verbosity(v -> {
                    v.showPrompts(true);
                    v.showLlmResponses(true);
                    v.showPlanning(true);
                    v.debug(true);
                }).build())
                .build(BlogTitles.class);

        BlogTitles blogTitles = blogTitleAgentInvocation.invoke(request);
        log.info("Result: {}", blogTitles);
        return blogTitles;
    }
}

