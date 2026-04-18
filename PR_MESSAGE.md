# Hybrid Heading Level Offset: validation, inference, and Python typing fixes

## Summary
- Added strict `Config#setHybridHeadingOffset(int)` validation to enforce the supported range `-5..5`.
- Unified heading-level helpers used by Docling and Hancom transformers into a shared utility.
- Updated Docling heading inference to:
  - use `meta.level` as base level when present,
  - accept single-segment numbering (`"3 Title"`) as level 1,
  - use `max(baseLevel, numberingLevel)` when numbering exists.
- Added Hancom heading base-level inference from heading text numbering and optional `content.level`, then applied offset/clamp consistently.
- Fixed `BulletedParagraphUtilsTest` to pass `SemanticTextNode` to `getLabelRegex(...)`.
- Updated Python APIs to type `hybrid_timeout` and `hybrid_heading_offset` as integers and serialize them safely.

## Hybrid heading offset behavior
- Input validation now happens in core config setter with a clear exception message for out-of-range values.
- Runtime heading level remains clamped to PDF-style heading range `1..6` after applying offset.
- Docling and Hancom now share the same clamp and numbering parsing rules through `HybridHeadingLevelUtils`.

## Tests
- Added/updated tests for:
  - Config heading offset range acceptance/rejection.
  - Docling single-segment numbering and `meta.level` + numbering precedence.
  - Hancom numeric-prefix inference and `content.level` + numbering precedence.
  - Bulleted paragraph regex API usage with the correct node type.
