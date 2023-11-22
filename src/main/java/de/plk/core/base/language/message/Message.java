package de.plk.core.base.language.message;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.language.message.IMessage;
import de.plk.core.base.utils.Identification;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:13
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Message extends Identification implements IMessage {

    /**
     * The message.
     */
    @NotNull
    private final String message;

    /**
     * Construct a message object.
     *
     * @param identification The identification string.
     */
    public Message(@NotNull String identification, @NotNull String message) {
        super(identification);

        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }

}
