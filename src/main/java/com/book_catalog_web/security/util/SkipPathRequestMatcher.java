package com.book_catalog_web.security.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.List;
import java.util.stream.Collectors;

public class SkipPathRequestMatcher implements RequestMatcher{

    private OrRequestMatcher skipMatcher;
    private OrRequestMatcher processingMatcher;

    public SkipPathRequestMatcher(List<String> pathToSkip, List<String> processingPath){
        List<RequestMatcher> skip = pathToSkip.stream().map(s -> PathPatternRequestMatcher.withDefaults().matcher(s)).collect(Collectors.toList());

        skipMatcher = new OrRequestMatcher(skip);

        List<RequestMatcher> processing = processingPath.stream().map(p -> PathPatternRequestMatcher.withDefaults().matcher(p)).collect(Collectors.toList());

        processingMatcher = new OrRequestMatcher(processing);
    }


    @Override
    public boolean matches(HttpServletRequest request) {

        if (skipMatcher.matches(request)) {
            return false;
        }

        return processingMatcher.matches(request);
    }
}
