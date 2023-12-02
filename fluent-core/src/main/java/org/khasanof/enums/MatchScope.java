package org.khasanof.enums;

/**
 * Author: Nurislom
 * <br/>
 * Date: 18.06.2023
 * <br/>
 * Time: 21:51
 * <br/>
 * Package: org.khasanof.core.enums
 * <br/>
 * <br/>
 *
 * MatchScope is used in annotations that accept enum updates. The purpose of this Annotation is to show how to match a
 * given update value. Defaults to EQUALS if no value is specified.
 */
public enum MatchScope {

    /**
     * END_WITH checks that the start of the incoming update matches the specified value.
     * <p>
     * <br/>
     *
     * Using Example:
     *
     * <pre>
     *    &#064;HandleMessage(value = "start", scope = MatchScope.START_WITH)
     * </pre>
     */
    START_WITH,

    /**
     * END_WITH checks that the end of the incoming update matches the specified value.
     * <p>
     * <br/>
     *
     * Using Example:
     *
     * <pre>
     *    &#064;HandleMessage(value = "end", scope = MatchScope.END_WITH)
     * </pre>
     */
    END_WITH,

    /**
     * CONTAINS checks whether the incoming update contains the specified value.
     * <p>
     * <br/>
     *
     * Using Example:
     *
     * <pre>
     *    &#064;HandleMessage(value = "abs", scope = MatchScope.CONTAINS)
     * </pre>
     */
    CONTAINS,

    /**
     * EQUALS checks the incoming update to match the given value.
     * <p>
     * <br/>
     *
     * Using Example:
     *
     * <pre>
     *    &#064;HandleMessage(value = "/start", scope = MatchScope.EQUALS)
     * </pre>
     *
     */
    EQUALS,

    /**
     * REGEX checks that the update received matches the given regex.
     * <p>
     * <br/>
     *
     * Using Example:
     *
     * <pre>
     *    &#064;HandleDocument(
     *             value = "([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.jpeg|.png|.pdf)$",
     *             match = MatchScope.REGEX,
     *             scope = DocumentScope.FILE_NAME
     *     )
     * </pre>
     *
     */
    REGEX,

    /**
     * EQUALS_IGNORE_CASE checks that the incoming update matches the ignore case
     * <p>
     * <br/>
     *
     * Using Example:
     *
     * <pre>
     *    &#064;HandleMessage(value = "boom", scope = MatchScope.EQUALS_IGNORE_CASE)
     * </pre>
     *
     */
    EQUALS_IGNORE_CASE,

    /**
     * EXPRESSION checks whether the incoming update matches this written expression.
     * <p>
     * <br/>
     *
     * Using Example:
     *
     * <pre>
     *    &#064;HandleMessage(value = "START_WITH('abs', value)", scope = MatchScope.EXPRESSION)
     * </pre>
     *
     */
    EXPRESSION,

    /**
     * VAR_EXPRESSION checks whether the incoming update matches this written expression.
     * In addition, we can use this VAR_EXPRESSION to enter the updated variables as parameters to the method.
     * This new feature use only message handlers!
     * <p>
     * <br/>
     *
     * Using Example:
     *
     * <pre>
     *    &#064;HandleMessage(value = "/name {name:[a-z]}", scope = MatchScope.VAR_EXPRESSION)
     * </pre>
     *
     * You write the name of the first variable in {} brackets, then you put ':' and you write a regular expression,
     * it checks that everything matches
     *
     */
    VAR_EXPRESSION
}
