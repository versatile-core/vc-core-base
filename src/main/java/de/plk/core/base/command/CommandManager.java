package de.plk.core.base.command;

import de.plk.core.api.command.*;
import de.plk.core.base.utils.Manager;

/**
 * @author SoftwareBuilds
 * @since 09.08.2023 12:37
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class CommandManager<P> extends Manager<AbstractCommand> implements ICommandManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerAllCommands() {
        getAll().forEach(AbstractCommand::register);
    }
}
