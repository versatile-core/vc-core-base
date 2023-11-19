package de.plk.core.base.spigot.hologram;

import de.plk.core.api.spigot.hologram.HologramIdentifierFilter;
import de.plk.core.api.spigot.hologram.IHologram;
import de.plk.core.api.spigot.hologram.IHologramManager;
import de.plk.core.base.utils.Manager;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * @author SoftwareBuilds
 * @since 18.11.2023 20:04
 * Copyright © 2023 | SoftwareBuilds | All rights reserved.
 */
public class HologramManager extends Manager<IHologram> implements IHologramManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public IHologram createHologram(String hologramIdentifier, String[] lines, Location location, boolean small) {
        IHologram hologram = new VersatileHologram(hologramIdentifier);
        hologram.setLines(lines);
        hologram.setLocation(location);
        hologram.setSmall(small);

        if (elements.contains(hologram))
            return hologram;

        add(hologram);

        return hologram;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IHologram getHologramById(HologramIdentifierFilter hologramIdentifierFilter) {
        return getFirstByFilter(hologramIdentifierFilter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void summon(IHologram hologram) {
        String[] lines = hologram.getLines();

        for (int i = 0; i < lines.length; i++) {
            Collection<ArmorStand> livingEntities = hologram.getLocation().getWorld().getEntitiesByClass(ArmorStand.class);

            int tempLineIdentifier = i;
            Optional<ArmorStand> foundedEntity = livingEntities.stream().filter((livingEntity) ->
                    Objects.requireNonNull(livingEntity.getCustomName()).contains(lines[tempLineIdentifier])
            ).findFirst();

            ArmorStand entity = null;

            if (foundedEntity.isPresent()) {
                entity = foundedEntity.get();
                entity.teleport(hologram.getLocation().add(0, i * 0.3D, 0));
            } else {
                entity = (ArmorStand) Objects.requireNonNull(
                        hologram.getLocation().add(0, i * 0.3D, 0).getWorld()
                ).spawnEntity(hologram.getLocation(), EntityType.ARMOR_STAND);

                entity.setCustomNameVisible(true);
                entity.setCustomName(lines[i]);

                entity.setGravity(false);
                entity.setInvisible(true);
                entity.setArms(false);
                entity.setAI(false);
                entity.setSmall(hologram.isSmall());
                entity.setBasePlate(false);
            }
        }
    }

}
