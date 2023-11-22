package de.plk.core.base.spigot.hologram;

import de.plk.core.api.code.NotNull;
import de.plk.core.api.code.Nullable;
import de.plk.core.api.spigot.hologram.IHologram;
import de.plk.core.api.spigot.hologram.IHologramManager;
import de.plk.core.base.VersatileSpigot;
import de.plk.core.base.utils.Manager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

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
    public IHologram createHologram(@NotNull String hologramIdentifier, @NotNull String[] lines, @NotNull Location location, boolean small) {
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
    public void summon(@NotNull IHologram hologram) {
        final String[] lines = hologram.getLines();

        for (int i = lines.length - 1; i >= 0; i--) {
            World world = Objects.requireNonNull(hologram.getLocation().getWorld());
            Collection<ArmorStand> livingEntities = world.getEntitiesByClass(ArmorStand.class);

            int tempLineIdentifier = i;
            Optional<ArmorStand> foundedEntity = livingEntities.stream().filter((livingEntity) ->
                    Objects.requireNonNull(livingEntity.getCustomName()).contains(lines[tempLineIdentifier])
            ).findFirst();

            ArmorStand entity;

            if (foundedEntity.isPresent()) {
                entity = foundedEntity.get();
                entity.teleport(hologram.getLocation().clone().add(0,  (hologram.isSmall() ? 1 : 2) + i * 0.3D, 0));
            } else {
                entity = (ArmorStand) Objects.requireNonNull(hologram.getLocation().getWorld()).spawnEntity(
                        hologram.getLocation().clone().add(0, (hologram.isSmall() ? 1 : 2) + i * 0.3D, 0),
                        EntityType.ARMOR_STAND
                );

                entity.setCustomNameVisible(true);
                entity.setCustomName(lines[i]);

                if (hologram.getHeadItem() != null && i == 0)
                    addHeadRotation(entity, hologram.getHeadItem(), hologram.getHeadRotation(), hologram.isAnimated());

                entity.setMarker(true);
                entity.setInvulnerable(true);
                entity.setCollidable(false);

                entity.setGravity(false);
                entity.setInvisible(true);
                entity.setArms(false);
                entity.setAI(false);
                entity.setSmall(hologram.isSmall());
                entity.setBasePlate(false);
            }
        }
    }

    /**
     * Rotate the head, ggf. with animation.
     *
     * @param entity The armor stand entity.
     * @param headItem The item placed to be on the head.
     * @param vector The rotation changed vector.
     * @param animated True if head rotation is animated.
     */
    private void addHeadRotation(@NotNull ArmorStand entity, @NotNull ItemStack headItem, @Nullable Vector vector, boolean animated) {
        Objects.requireNonNull(entity.getEquipment()).setHelmet(headItem);

        if (vector != null)
            setStaticRotation(entity, vector);

        // Start rotation.
        if (animated) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    rotateHead(entity);
                }
            }.runTaskTimer(JavaPlugin.getPlugin(VersatileSpigot.class), 0L, 3L);
        }
    }

    /**
     * Set the static rotation of the head.
     *
     * @param armorStand The armor stand.
     * @param vector The vector for rotation changes.
     */
    private void setStaticRotation(@NotNull ArmorStand armorStand, @NotNull Vector vector) {
        final EulerAngle currentPosition = armorStand.getHeadPose();

        armorStand.setHeadPose(new EulerAngle(
                currentPosition.getX() + vector.getX(),
                currentPosition.getY() + vector.getY(),
                currentPosition.getZ() + vector.getZ()
        ));
    }

    /**
     * Rotate the head with an animation.
     *
     * @param armorStand The armor stand reference.
     */
    private void rotateHead(@NotNull ArmorStand armorStand) {
        float currentYaw = (float) armorStand.getHeadPose().getY();
        float newYaw = (float) (currentYaw + 0.05);

        armorStand.setHeadPose(armorStand.getHeadPose().setY(newYaw));
    }

}
