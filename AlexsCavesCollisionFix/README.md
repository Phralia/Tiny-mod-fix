# Alex's Caves Collision Fix

Tiny NeoForge 1.21.1 mod that prevents the server hang caused by Alex's Caves `ac_collide` mixin on ItemEntities.

## What it does
When you break cobwebs from a Webber Zombie (or any situation that drops many items), Alex's Caves was running extremely expensive collision checks (magnet + moving blocks) on every ItemEntity. This could freeze the server thread for several minutes.

This mod short-circuits the `Entity.collide` method **only for ItemEntities**, so the heavy Alex's Caves code never runs on dropped items. Everything else (players, mobs, magnets, etc.) still uses the original Alex's Caves behavior.

## How to use

### Option A – Build it yourself (recommended)

1. Install a NeoForge 1.21.1 MDK (or use an existing mod development environment).
2. Copy the `src` folder and `build.gradle` / `settings.gradle` if you have them, or create a new NeoForge project and paste these files in.
3. Run `./gradlew build`
4. Take the jar from `build/libs/` and put it in your `mods` folder together with Alex's Caves.

### Option B – Ask someone to build it for you
Send the whole `AlexsCavesCollisionFix` folder to anyone who can build NeoForge mods.

## Files included
- `src/main/java/com/example/acfix/ACCollisionFix.java` – main mod class
- `src/main/java/com/example/acfix/mixin/EntityCollisionFixMixin.java` – the actual fix
- `src/main/resources/META-INF/neoforge.mods.toml`
- `src/main/resources/accollisionfix.mixins.json`

## Notes
- Requires Alex's Caves 2.0.10+ and NeoForge 21.1.x
- Only affects ItemEntities – magnets, living entities, etc. are untouched
- If items start falling through the floor in rare cases, tell me and I can make a more precise version
