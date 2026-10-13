package com.example.acfix.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Prevents the Alex's Caves ac_collide hang.
 *
 * Alex's Caves injects into Entity.collide and runs expensive magnet / moving-block
 * collision logic (MagnetUtil.getMovingBlockCollisions + extra getEntityCollisions).
 * On ItemEntities (especially ones dropped from Webber Zombie cobwebs) this can
 * loop / take minutes and freeze the entire server thread.
 *
 * This mixin runs at high priority and, for ItemEntities only, short-circuits
 * the collide method so the heavy AC code never executes.
 */
@Mixin(value = Entity.class, priority = 2500)
public abstract class EntityCollisionFixMixin {

    @Inject(
        method = "collide",
        at = @At("HEAD"),
        cancellable = true
    )
    private void accollisionfix$preventACHangOnItems(Vec3 movement, CallbackInfoReturnable<Vec3> cir) {
        Entity self = (Entity) (Object) this;

        // Only affect dropped items – this is what was hanging the server
        if (self instanceof ItemEntity) {
            // Return the movement unchanged.
            // This skips both vanilla complex collision *and* the AC injection
            // for this call, which is safe for ItemEntities (they don't need
            // magnet attachment or moving-block collisions).
            cir.setReturnValue(movement);
        }
    }
}
