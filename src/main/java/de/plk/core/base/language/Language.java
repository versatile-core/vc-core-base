package de.plk.core.base.language;

import de.plk.core.api.config.IConfig;
import de.plk.core.api.language.ILanguage;
import de.plk.core.api.language.message.IMessage;
import de.plk.core.api.language.message.IMessageManager;
import de.plk.core.base.language.message.MessageManager;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:11
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Language implements ILanguage {

    /**
     * The message manager.
     */
    private final IMessageManager messageManager;

    /**
     * The language name.
     */
    private final String name;

    /**
     * Construct a language.
     *
     * @param name          The language name.
     * @param messageConfig The message config.
     */
    public Language(String name, IConfig<IMessage> messageConfig) {
        this.name = name;
        this.messageManager = new MessageManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IMessageManager getMessageManager() {
        return messageManager;
    }

}
