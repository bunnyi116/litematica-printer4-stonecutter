package com.github.bunnyi116.litematica.printer.guide;
import net.minecraft.world.level.block.*;

import java.util.ArrayList;
import java.util.List;

public class GuideManager {
    public static final GuideManager INSTANCE = new GuideManager();
    private final List<GuideRegistration> registrations = new ArrayList<>();

    private GuideManager() {
    }

    @SafeVarargs
    public final void register(Class<? extends Guide> guideClass, Class<? extends Block>... supportedBlocks) {
        registrations.add(new GuideRegistration(guideClass, supportedBlocks));
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Guide> getGuides(SchematicBlockContext context) {
        List<Guide> guides = new ArrayList<>();
        for (GuideRegistration reg : registrations) {
            boolean matches = reg.blockClass.length == 0;
            if (!matches) {
                for (Class<? extends Block> clazz : reg.blockClass) {
                    if (clazz.isInstance(context.requiredState.getBlock())) {
                        matches = true;
                        break;
                    }
                }
            }
            if (matches) {
                try {
                    Guide guide = reg.guideClass
                            .getConstructor(SchematicBlockContext.class)
                            .newInstance(context);
                    if (guide.canExecute()) {
                        guides.add(guide);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return guides;
    }

    @SuppressWarnings("ClassCanBeRecord")
    private static class GuideRegistration {
        public final Class<? extends Guide> guideClass;
        public final Class<? extends Block>[] blockClass;

        public GuideRegistration(Class<? extends Guide> guideClass, Class<? extends Block>[] blockClass) {
            this.guideClass = guideClass;
            this.blockClass = blockClass;
        }
    }
}