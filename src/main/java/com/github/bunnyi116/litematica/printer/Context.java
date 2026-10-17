package com.github.bunnyi116.litematica.printer;

import com.github.bunnyi116.litematica.printer.util.manager.*;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;

@Getter
@Setter
public class Context {
    private final LookManager lookManager = new LookManager(this);
    private final PlacementManager placementManager = new PlacementManager(this);
    private final BreakManager breakManager = new BreakManager(this);
    private final InventoryManager inventoryManager = new InventoryManager(this);
    private final TestManager testManager = new TestManager(this);

    private boolean enable;
    private long clientTicks;
    private long gameTicks;

    private Context() {
    }

    public void reset() {
        this.lookManager.reset();
        this.breakManager.reset();
        this.testManager.reset();
        this.gameTicks = 0L;

        // TODO: 客户端Ticks不应该被重置
        // this.clientTicks = 0L;
    }

    public LocalPlayer getPlayerOrThrow() {
        LocalPlayer p = Minecraft.getInstance().player;
        if (p == null) throw new IllegalStateException("LocalPlayer is null, not in game");
        return p;
    }

    public ClientLevel getLevelOrThrow() {
        ClientLevel l = Minecraft.getInstance().level;
        if (l == null) throw new IllegalStateException("ClientLevel is null");
        return l;
    }

    public MultiPlayerGameMode getGameModeOrThrow() {
        MultiPlayerGameMode gm = Minecraft.getInstance().gameMode;
        if (gm == null) throw new IllegalStateException("MultiPlayerGameMode is null");
        return gm;
    }

    public ClientPacketListener getConnectionOrThrow() {
        ClientPacketListener c = Minecraft.getInstance().getConnection();
        if (c == null) throw new IllegalStateException("ClientPacketListener is null");
        return c;
    }

    public void debug(Object obj) {
        this.info(obj);
    }

    public void debug(String format, Object... arguments) {
        this.info(format, arguments);
    }

    public void info(Object obj) {
        info("{}", obj.toString());
    }

    public void info(String format, Object... arguments) {
        format = "[" + clientTicks + "] " + format;
        PrinterMod.LOGGER.info(format, arguments);
    }

    private static class Holder {
        private static final Context INSTANCE = new Context();
    }

    public static Context getInstance() {
        return Holder.INSTANCE;
    }
}
