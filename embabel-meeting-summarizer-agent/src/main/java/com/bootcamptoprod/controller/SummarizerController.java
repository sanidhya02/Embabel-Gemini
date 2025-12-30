package com.bootcamptoprod.controller;

import com.bootcamptoprod.dto.MeetingSummary;
import com.bootcamptoprod.dto.SummaryRequest;
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
@RequestMapping("/api/v1/meeting")
public class SummarizerController {

    private static final Logger log = LoggerFactory.getLogger(SummarizerController.class);

    private final AgentPlatform agentPlatform;

    public SummarizerController(AgentPlatform agentPlatform) {
        this.agentPlatform = agentPlatform;
    }

    @PostMapping("/summarize")
    public MeetingSummary summarizeMeeting(@RequestBody SummaryRequest request) {

        var meetingSummaryAgentInvocation = AgentInvocation
                .builder(agentPlatform)
                .options(ProcessOptions.builder().verbosity(v -> {
                    v.showPrompts(true);
                    v.showLlmResponses(true);
                    v.showPlanning(true);
                    v.debug(true);
                }).build())
                .build(MeetingSummary.class);

        MeetingSummary meetingSummary = meetingSummaryAgentInvocation.invoke(request);
        log.info("Result: {}", meetingSummary);
        return meetingSummary;
    }
}