package dev.rachamon.rachamonguilds.api.utils;

import org.spongepowered.api.text.Text;

/**
 * The type Chat question builder.
 */
public class ChatQuestionBuilder {

    private final ChatQuestion question;

    /**
     * Instantiates a new Chat question builder.
     *
     * @param question the question
     */
    public ChatQuestionBuilder(Text question) {
        this.question = new ChatQuestion(question);
    }

    /**
     * Add answer chat question builder.
     *
     * @param answer A possible {@link ChatQuestionAnswer} to this question.
     * @return The builder for chaining.
     */
    public ChatQuestionBuilder addAnswer(ChatQuestionAnswer answer) {
        question.addAnswer(answer);
        return this;
    }

    /**
     * Set the top decoration for this ChatQuestion.
     *
     * @param text The text representation of the decoration
     * @return the chat question builder
     */
    public ChatQuestionBuilder topDecoration(Text text) {
        this.question.decorationTop = text;
        return this;
    }

    /**
     * Set the bottom decoration for this ChatQuestion.
     *
     * @param text the text representation of the decoration
     * @return the chat question builder
     */
    public ChatQuestionBuilder bottomDecoration(Text text) {
        this.question.decorationBottom = text;
        return this;
    }

    /**
     * Build chat question.
     *
     * @return The {@link ChatQuestion} object.
     */
    public ChatQuestion build() {
        return question;
    }

}