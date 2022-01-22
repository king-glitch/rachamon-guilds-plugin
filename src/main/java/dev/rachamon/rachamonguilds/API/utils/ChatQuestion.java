package dev.rachamon.rachamonguilds.api.utils;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import javax.annotation.Nonnull;
import java.util.*;

/**
 * A utility for polling players with arbitrary questions, and offering an arbitrary amount of answers with various
 * actions attached.<br> The questions may be sent to the player either in the form of a chat message ( {@link
 * #pollChat(Player)} ), a book view ( {@link #pollBook(Player)} ), or a combination of the two in the form of a
 * chat-based view button ( {@link #pollViewButton(Player, Text)}* ).
 */
public final class ChatQuestion {

    /**
     * The constant QUESTION_DECORATION_TOP.
     */
    public static Text QUESTION_DECORATION_TOP = Text.of(TextColors.DARK_AQUA, "{", TextColors.AQUA, "ChatQuestion", TextColors.DARK_AQUA, "}\n");
    /**
     * The constant QUESTION_DECORATION_BOT.
     */
    public static Text QUESTION_DECORATION_BOT = Text.of(TextColors.DARK_AQUA, "\n");

    private Text clickToAnswer = Text.of("Click to Answer");
    private Text clickToView = Text.of("Click to View");
    private Text mustBePlayer = Text.of("You must be player to answer this question");
    private Text alreadyResponse = Text.of("You have already responded to that question!");

    private static final Map<UUID, ChatQuestion> questions = new HashMap<>();
    private final UUID id;
    private final Text question;
    private final List<ChatQuestionAnswer> answers;
    /**
     * The Decoration top.
     */
    public Text decorationTop;
    /**
     * The Decoration bottom.
     */
    public Text decorationBottom;

    /**
     * Instantiates a new Chat question.
     *
     * @param question the question
     */
    public ChatQuestion(Text question) {
        this.question = question;
        this.answers = new LinkedList<>();
        this.id = UUID.randomUUID();
        decorationTop = Text.EMPTY;
        decorationBottom = Text.EMPTY;
    }

    /**
     * Of chat question builder.
     *
     * @param question the question
     * @return the chat question builder
     */
    public static ChatQuestionBuilder of(Text question) {
        return new ChatQuestionBuilder(question);
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets chat question.
     *
     * @return the chat question
     */
    public Text getChatQuestion() {
        return question;
    }

    /**
     * Gets answers.
     *
     * @return the answers
     */
    public List<ChatQuestionAnswer> getAnswers() {
        return answers;
    }

    /**
     * Add answer.
     *
     * @param answer the answer
     */
    void addAnswer(ChatQuestionAnswer answer) {
        if (!this.answers.contains(answer)) {
            this.answers.add(answer);
        }
    }

    /**
     * Get the question as an interactable Text object. Above it will be placed the QUESTION_DECORATION_TOP, and after
     * it the QUESTION_DECORATION_BOT.
     *
     * @return the Text object.
     */
    public Text asText() {
        Text.Builder builder = Text.builder();
        builder.append(decorationTop);
        builder.append(question);
        builder.append(Text.of("\n"));

        for (ChatQuestionAnswer answer : answers) {
            builder.append(Text.of(TextStyles.RESET, TextColors.RESET, "["));
            builder.append(Text.builder().append(answer.getText()).onHover(TextActions.showText(Text.of(clickToAnswer))).onClick(TextActions.executeCallback(source -> {
                if (!(source instanceof Player)) {
                    source.sendMessage(mustBePlayer);
                    return;
                }

                if (questions.containsKey(this.id)) {
                    answer.execute((Player) source);
                    questions.remove(this.id);
                } else {
                    source.sendMessage(Text.of(TextColors.RED, alreadyResponse));
                }
            })).build());
            builder.append(Text.of(TextStyles.RESET, TextColors.RESET, "] "));
        }

        builder.append(decorationBottom);
        return builder.build();
    }

    /**
     * Registers this question. Required to ensure the player cannot answer the question multiple times.
     */
    public void register() {
        questions.put(id, this);
    }

    /**
     * Poll a player with this question via chat message ( See: {@link #asText()} ).
     *
     * @param player The player to be polled.
     */
    public void pollChat(@Nonnull Player player) {
        register();
        player.sendMessage(this.asText());
    }

    /**
     * Poll a player with this question via book view.
     *
     * @param player The player to be polled.
     */
    public void pollBook(@Nonnull Player player) {
        register();
        player.sendBookView(BookView.builder().addPage(asText()).build());
    }

    /**
     * Poll a player with an interactable Text object. Clicking it will result in a BookView appearing and the player
     * having to respond to the question.
     *
     * @param player     The player to be polled.
     * @param buttonText The display text of the button.
     */
    public void pollViewButton(@Nonnull Player player, @Nonnull Text buttonText) {
        register();
        Text text = Text.builder().append(buttonText).onHover(TextActions.showText(Text.of(TextColors.AQUA, clickToView))).onClick(TextActions.executeCallback(source -> {
            if (source instanceof Player) {
                this.pollBook((Player) source);
            }
        })).build();

        player.sendMessage(text);
    }

    /**
     * Sets click to answer.
     *
     * @param clickToAnswer the click to answer
     */
    public void setClickToAnswer(Text clickToAnswer) {
        this.clickToAnswer = clickToAnswer;
    }

    /**
     * Sets click to view.
     *
     * @param clickToView the click to view
     */
    public void setClickToView(Text clickToView) {
        this.clickToView = clickToView;
    }

    /**
     * Sets must be player.
     *
     * @param mustBePlayer the must be player
     */
    public void setMustBePlayer(Text mustBePlayer) {
        this.mustBePlayer = mustBePlayer;
    }

    /**
     * Sets already response.
     *
     * @param alreadyResponse the already response
     */
    public void setAlreadyResponse(Text alreadyResponse) {
        this.alreadyResponse = alreadyResponse;
    }
}
