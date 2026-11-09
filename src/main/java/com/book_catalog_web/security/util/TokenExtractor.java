package com.book_catalog_web.security.util;

public interface TokenExtractor {

    public String extract(String payload);
}
