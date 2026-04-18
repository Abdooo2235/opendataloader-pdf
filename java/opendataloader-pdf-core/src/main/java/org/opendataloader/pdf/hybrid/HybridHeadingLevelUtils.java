/*
 * Copyright 2025-2026 Hancom Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opendataloader.pdf.hybrid;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Shared heading level utilities for hybrid backends.
 */
final class HybridHeadingLevelUtils {
    static final int MIN_HEADING_LEVEL = 1;
    static final int MAX_HEADING_LEVEL = 6;
    private static final Pattern HEADING_NUMBERING_PATTERN =
        Pattern.compile("^\\s*(\\d+(?:\\.\\d+)*)(?:[\\.)])?\\s+.*$");

    private HybridHeadingLevelUtils() {
    }

    static int clampWithOffset(int baseLevel, int headingLevelOffset) {
        long adjusted = (long) baseLevel + (long) headingLevelOffset;
        if (adjusted < MIN_HEADING_LEVEL) {
            return MIN_HEADING_LEVEL;
        }
        if (adjusted > MAX_HEADING_LEVEL) {
            return MAX_HEADING_LEVEL;
        }
        return (int) adjusted;
    }

    static Integer extractNumberingLevel(String text) {
        if (text == null) {
            return null;
        }
        Matcher matcher = HEADING_NUMBERING_PATTERN.matcher(text);
        if (!matcher.matches()) {
            return null;
        }

        String numbering = matcher.group(1);
        int segments = 1;
        for (int i = 0; i < numbering.length(); i++) {
            if (numbering.charAt(i) == '.') {
                segments++;
            }
        }
        return segments;
    }
}
