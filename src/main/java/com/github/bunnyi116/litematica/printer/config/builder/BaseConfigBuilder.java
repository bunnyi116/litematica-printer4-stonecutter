package com.github.bunnyi116.litematica.printer.config.builder;

import com.github.bunnyi116.litematica.printer.I18n;
import com.github.bunnyi116.litematica.printer.mixin_extension.ConfigExtension;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBase;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public abstract class BaseConfigBuilder<T extends ConfigBase<?>, B extends BaseConfigBuilder<T, B>> {
    protected final I18n i18n;
    protected final CopyOnWriteArrayList<Consumer<IConfigBase>> valueChangeCallbacks = new CopyOnWriteArrayList<>();
    protected String nameKey;
    protected String descKey;
    protected @Nullable BooleanSupplier visible;

    public BaseConfigBuilder(I18n i18n) {
        this.i18n = i18n;
        this.nameKey = i18n.getConfigNameKey();
        this.descKey = i18n.getConfigDescKey();
        this.visible = null;
    }

    public BaseConfigBuilder(String translateKey) {
        this(I18n.of(translateKey));
    }

    public B translationAlias(String alias) {
        I18n i18n = I18n.of(alias);
        this.nameKey = i18n.getConfigNameKey();
        this.descKey = i18n.getConfigDescKey();
        return (B) this;
    }

    public B setNameKey(String name) {
        this.nameKey = name;
        return (B) this;
    }

    public B setDescKey(String comment) {
        this.descKey = comment;
        return (B) this;
    }

    public B setVisible(boolean visible) {
        this.visible = visible ? ConfigExtension.printer$TRUE : ConfigExtension.printer$FALSE;
        return (B) this;
    }

    public B setVisible(@Nullable BooleanSupplier visible) {
        this.visible = visible;
        return (B) this;
    }

    /**
     * 动态可见性：条件成立时，该选项才会出现在配置界面中。
     *
     * <p>条件会在客户端 tick 中被重新求值，因此它所依赖的其它配置项一旦改变，
     * 本选项就会自动显示 / 隐藏，无需重开界面。条件里抛异常时按“可见”处理。</p>
     *
     * <pre>{@code
     * integer("workMultiRange")
     *         .visibleWhen(() -> Configs.WORK_MODE.getOptionListValue() == WorkMode.MULTI)
     *         .build();
     * }</pre>
     */
    public B visibleWhen(@Nullable BooleanSupplier visible) {
        return this.setVisible(visible);
    }

    public B addValueChangeListener(Consumer<IConfigBase> callback) {
        if (callback != null) {
            this.valueChangeCallbacks.add(callback);
        }
        return (B) this;
    }

    protected T buildExtension(T config) {
        buildI18n(config);
        buildVisible(config);
        buildValueChangeCallbacks(config);
        return config;
    }

    protected void buildI18n(T config) {
        ConfigExtension extension = (ConfigExtension) config;
        extension.printer$setTranslateNameKey(this.nameKey);
        extension.printer$setTranslateCommentKey(this.descKey);
    }

    protected void buildVisible(T config) {
        if (visible != null) {
            ConfigExtension extension = (ConfigExtension) config;
            extension.printer$setVisible(this.visible);
        }
    }

    protected void buildValueChangeCallbacks(T config) {
        if (config instanceof ConfigExtension extension && !this.valueChangeCallbacks.isEmpty()) {
            for (Consumer<IConfigBase> callback : this.valueChangeCallbacks) {
                extension.printer$addValueChangeListener(callback);
            }
        }
    }

    public abstract T build();
}