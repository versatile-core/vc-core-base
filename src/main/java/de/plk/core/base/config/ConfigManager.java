package de.plk.core.base.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.plk.core.api.code.NotNull;
import de.plk.core.api.config.IConfig;
import de.plk.core.api.config.IConfigManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author SoftwareBuilds
 * @since 06.08.2023 01:03
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class ConfigManager<T> implements IConfigManager<T> {

    /**
     * The gson reader and writer for json files.
     */
    @NotNull
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    /**
     * {@inheritDoc}
     */
    @Override
    public IConfig<T> loadConfiguration(@NotNull File file) {
        try (FileReader reader = new FileReader(file)) {
            return GSON.fromJson(reader, new TypeToken<Config<T>>() {}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean saveConfiguration(@NotNull IConfig<T> config, @NotNull File file) {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(GSON.toJson(config));
            writer.flush();

            return true;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public IConfig<T> createConfig() {
        return new Config<>();
    }

}
