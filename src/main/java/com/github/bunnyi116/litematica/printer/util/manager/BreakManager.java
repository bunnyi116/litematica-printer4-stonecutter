package com.github.bunnyi116.litematica.printer.util.manager;

import com.github.bunnyi116.litematica.printer.Context;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

@Getter
@SuppressWarnings({"DuplicatedCode", "UnusedReturnValue"})
public class BreakManager {
    private final Context context;

    // ========== 主槽位 ==========
    private BlockPos primaryPos = null;
    private long primaryStartTick = 0L;
    private boolean isPrimaryBreaking = false;

    // ========== 延迟槽位 ==========
    private BlockPos delayedPos = null;
    private long delayedStartTick = 0L;
    private boolean isDelayedActive = false;

    public BreakManager(Context context) {
        this.context = context;
    }

    // ===================== 配置（后续可接配置项） =====================
    public boolean isEnableDoubleMine() {
        return true;
    }

    public boolean isEnableInstantBreak() {
        return true;
    }

    public float getInstantBreakThreshold() {
        return 0.7F;
    }

    public boolean isEnableLocalPredict() {
        return true;
    }

    public void reset() {

    }

    public void tick() {
    }

    public boolean isWorking() {
        return false;
    }

    public enum Result {
        COMPLETED,
        IN_PROGRESS,
        ABORTED,
        FAILED,
        QUEUED,
        TIMEOUT
    }

    // ===================== 状态查询 =====================

    /**
     * 破坏一个方块。
     */
    public Result breakBlock(BlockPos pos, Direction direction) {
        return Result.COMPLETED;
    }
}