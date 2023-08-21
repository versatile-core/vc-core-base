package de.plk.core.base.language.message;

import de.plk.core.api.language.message.IMessage;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:13
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Message implements IMessage {

    /**
     * The key of message.
     */
    private final String key;

    /**
     * The message itself.
     */
    private final String message;

    /**
     * Construct a message object.
     *
     * @param key     The key.
     * @param message The message.
     */
    public Message(String key, String message) {
        this.key = key;
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }
}
