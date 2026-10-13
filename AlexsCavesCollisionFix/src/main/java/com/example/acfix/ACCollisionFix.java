package com.example.acfix;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ACCollisionFix.MODID)
public class ACCollisionFix {
    public static final String MODID = "accollisionfix";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public ACCollisionFix(IEventBus modEventBus) {
        LOGGER.info("Alex's Caves Collision Fix loaded - ac_collide will be skipped for ItemEntities to prevent server hangs.");
    }
}
