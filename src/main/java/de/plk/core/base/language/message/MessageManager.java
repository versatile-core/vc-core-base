package de.plk.core.base.language.message;

import de.plk.core.api.language.message.IMessage;
import de.plk.core.api.language.message.IMessageManager;
import de.plk.core.base.utils.Manager;

import java.io.File;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 19:57
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class MessageManager extends Manager<IMessage> implements IMessageManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public void attachMessages(File file) {
        // TODO implementation
    }
}
