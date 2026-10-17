package com.github.bunnyi116.litematica.printer.guide;

import com.github.bunnyi116.litematica.printer.guide.action.Action;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;

@Getter
public class GuideResult {
    public static GuideResult EMPTY = new Builder().build();
    public static GuideResult SKIP = new Builder().skipOtherGuides().build();

    private final @Nullable Action action;
    private final boolean skip;
    private final @Nullable BlockPos iterationNextBlockPos;

    private GuideResult(@Nullable Action action, boolean skip, @Nullable BlockPos iterationNextBlockPos) {
        this.action = action;
        this.skip = skip;
        this.iterationNextBlockPos = iterationNextBlockPos;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }


    @Getter
    public static class Builder {
        private @Nullable Action action;
        private boolean skip;
        private @Nullable BlockPos iterationNextBlockPos;

        public Builder skipOtherGuides() {
            return this.setSkip(true);
        }

        public Builder setAction(@Nullable Action action) {
            this.action = action;
            return this;
        }

        public Builder setSkip(boolean skipOtherGuide) {
            this.skip = skipOtherGuide;
            return this;
        }

        public Builder setIterationNextBlockPos(@Nullable BlockPos iterationNextBlockPos) {
            this.iterationNextBlockPos = iterationNextBlockPos;
            return this;
        }

        public GuideResult build() {
            return new GuideResult(this.action, this.skip, this.iterationNextBlockPos);
        }
    }
}
