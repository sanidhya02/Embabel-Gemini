package com.bootcamptoprod.agent;

import com.bootcamptoprod.dto.ActionItems;
import com.bootcamptoprod.dto.KeyPoints;
import com.bootcamptoprod.dto.MeetingSummary;
import com.bootcamptoprod.dto.SummaryRequest;
import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.common.OperationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Agent(name = "meeting-summarizer-agent",
        description = "Summarizes a meeting transcript with actionable and key insights.",
        version = "1.0.0",
        beanName = "meetingSummarizerAgent")
public class MeetingSummarizerAgent {

    private static final Logger log = LoggerFactory.getLogger(MeetingSummarizerAgent.class);

    @Action
    public KeyPoints findKeyPoints(SummaryRequest summaryRequest, OperationContext context) {
        log.info("[MeetingSummarizerAgent] Starting extraction of key points from transcript.");

        KeyPoints result = context.ai()
                .withDefaultLlm()
                .createObjectIfPossible(
                        """
                                Read the following meeting transcript carefully and identify the main discussion points. Summarize them into clear, concise bullet points, highlighting key decisions and topics discussed. For each point, include the name of the responsible person or team if mentioned in the transcript:
                                %s
                                """.formatted(summaryRequest.transcript()),
                        KeyPoints.class
                );

        log.info("[MeetingSummarizerAgent] Extracted key points: {}", result.points());
        return result;
    }

    @Action
    public ActionItems findActionItems(SummaryRequest summaryRequest, OperationContext context) {
        log.info("[MeetingSummarizerAgent] Starting extraction of action items from transcript.");
        ActionItems result = context.ai()
                .withDefaultLlm()
                .createObjectIfPossible(
                        """
                                Read the following meeting transcript and extract all action items assigned, mentioning responsible persons and deadlines if available:
                                %s
                                """.formatted(summaryRequest.transcript()),
                        ActionItems.class
                );
        log.info("[MeetingSummarizerAgent] Extracted action items: {}", result.actionItems());
        return result;
    }

    @Action
    @AchievesGoal(description = "Summarize a meeting transcript into clear key points and assigned action items with responsible person and deadlines if available")
    public MeetingSummary createSummary(KeyPoints keyPoints, ActionItems actionItems, OperationContext context) {
        log.info("[MeetingSummarizerAgent] Composing final meeting summary.");
        MeetingSummary summary = new MeetingSummary(keyPoints.points(), actionItems.actionItems());
        log.info("[MeetingSummarizerAgent] Meeting summary created: {}", summary);
        return summary;
    }
}