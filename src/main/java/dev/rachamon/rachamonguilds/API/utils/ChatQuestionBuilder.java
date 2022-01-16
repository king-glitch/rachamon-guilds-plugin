package dev.rachamon.rachamonguilds.api.utils;

import org.spongepowered.api.text.Text;

public class ChatQuestionBuilder {

    private final ChatQuestion question;

    public ChatQuestionBuilder(Text question) {
        this.question = new ChatQuestion(question);
    }

    /**
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
     */
    public ChatQuestionBuilder topDecoration(Text text) {
        this.question.decorationTop = text;
        return this;
    }

    /**
     * Set the bottom decoration for this ChatQuestion.
     *
     * @param text the text representation of the decoration
     */
    public ChatQuestionBuilder bottomDecoration(Text text) {
        this.question.decorationBottom = text;
        return this;
    }

    /**
     * @return The {@link ChatQuestion} object.
     */
    public ChatQuestion build() {
        return question;
    }

}