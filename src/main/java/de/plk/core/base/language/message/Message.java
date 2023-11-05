package de.plk.core.base.language.message;

import de.plk.core.api.language.message.IMessage;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:13
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public record Message(
        String key,
        String message
) implements IMessage {

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
        return null;
    }
}
