# AGENTS

## Project Overview
- Java algorithms practice project.
- Java verfsion: 
- Build tool: Gradle.
- Source code: `src/main/java/com/podopryhora/algoworkout`.
- Tests: `src/test/java/com/podopryhora/algoworkout` (JUnit 5).

## Conventions
- Keep code simple and readable; prefer clear naming over cleverness.
- Enforce Java 25 syntax and features.
- Use ASCII only unless a file already contains Unicode.
- Add short, meaningful comments at each key step in the algorithm.
- Put algorithm/data-structure descriptions at class level.
- Always include time and space complexity in class-level Javadoc.
- Method Javadoc should stick to standard tags (`@param`, `@return`) unless there is a specific reason to add more.

## Testing
- Run tests with `./gradlew test` when changes affect behavior.
- Typical focus: search/algorithm edge cases and null/empty inputs.

## Notes for Agents
- Avoid destructive git commands.
- Prefer iterative solutions when recursion could risk stack depth.
- Keep Javadoc concise and consistent across similar classes.
