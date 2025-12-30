package com.bootcamptoprod.agent;

import com.bootcamptoprod.dto.BlogRequest;
import com.bootcamptoprod.dto.BlogTitles;
import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.common.OperationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Agent(name = "blog-title-suggestion-agent",
        description = "Suggests creative and engaging blog titles based on blog content.",
        version = "1.0.0",
        beanName = "blogTitleSuggestionAgent")
public class BlogTitleSuggestionAgent {

    private static final Logger log = LoggerFactory.getLogger(BlogTitleSuggestionAgent.class);

    @Action
    @AchievesGoal(description = "Generate creative and engaging blog title suggestions based on the provided blog content")
    public BlogTitles suggestBlogTitles(BlogRequest blogRequest, OperationContext context) {
        log.info("[BlogTitleSuggestionAgent] Starting blog title suggestion generation.");

        BlogTitles result = context.ai()
                .withDefaultLlm()
                .createObjectIfPossible(
                        """
                                Read the following blog content carefully and generate 5-7 creative, engaging, and SEO-friendly blog title suggestions. 
                                The titles should:
                                - Be attention-grabbing and compelling
                                - Accurately reflect the blog content
                                - Be concise (ideally 6-12 words)
                                - Include relevant keywords when appropriate
                                - Appeal to the target audience
                                
                                Blog Content:
                                %s
                                """.formatted(blogRequest.blogContent()),
                        BlogTitles.class
                );

        log.info("[BlogTitleSuggestionAgent] Generated {} blog title suggestions.", result.titles().size());
        return result;
    }
}
