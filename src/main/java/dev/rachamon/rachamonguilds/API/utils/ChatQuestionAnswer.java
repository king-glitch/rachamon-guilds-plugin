package dev.rachamon.rachamonguilds.api.utils;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.function.Consumer;

/**
 * The type Chat question answer.
 */
public class ChatQuestionAnswer {

    private final Text text;
    private Consumer<Player> action;

    /**
     * Instantiates a new Chat question answer.
     *
     * @param text the text
     */
    public ChatQuestionAnswer(Text text) {
        this.text = text;
    }

    private ChatQuestionAnswer(Text text, Consumer<Player> action) {
        this.text = text;
        this.action = action;
    }

    /**
     * Factory method for creating an answer.
     *
     * @param name   The display-text of this answer. This will be shown to the player as a clickable               text
     *               object.
     * @param action The result of this answer. This will be executed in the event that the player               clicks
     *               this answer.
     * @return The answer.
     */
    public static ChatQuestionAnswer of(Text name, Consumer<Player> action) {
        return new ChatQuestionAnswer(name, action);
    }

    /**
     * Gets text.
     *
     * @return The display text of this answer.
     */
    public Text getText() {
        return text;
    }

    /**
     * Gets action.
     *
     * @return The consumer which will be executed upon answering a question with this answer.
     */
    Consumer<Player> getAction() {
        return action;
    }

    /**
     * Sets action.
     *
     * @param action the action
     */
    public void setAction(Consumer<Player> action) {
        this.action = action;
    }

    /**
     * Execute.
     *
     * @param source The player who has answered the question
     */
    public void execute(Player source) {
        action.accept(source);
    }
}