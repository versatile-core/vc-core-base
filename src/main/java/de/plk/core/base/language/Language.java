package de.plk.core.base.language;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.config.IConfig;
import de.plk.core.api.language.ILanguage;
import de.plk.core.api.language.message.IMessage;
import de.plk.core.api.utils.IManager;
import de.plk.core.base.utils.Identification;
import de.plk.core.base.utils.Manager;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:11
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class Language extends Identification implements ILanguage {

    /**
     * The message manager.
     */
    @NotNull
    private final IManager<IMessage> messageManager;

    /**
     * The language name.
     */
    @NotNull
    private final String name;

    /**
     * Construct a language.
     *
     * @param name The language name.
     * @param messageConfig The message config.
     */
    public Language(@NotNull String languageIdentification, @NotNull String name, @NotNull IConfig<IMessage> messageConfig) {
        super(languageIdentification);

        this.name = name;
        this.messageManager = new Manager<>(messageConfig);
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
    public IManager<IMessage> getMessageManager() {
        return messageManager;
    }

}
