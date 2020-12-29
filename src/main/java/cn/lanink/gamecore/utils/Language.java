package cn.lanink.gamecore.utils;

import cn.nukkit.utils.Config;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lt_name
 */
public class Language {

    private final Config config;
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    public Language(File file) {
        this(new Config(file, Config.PROPERTIES));
    }

    public Language(File file, int type) {
        this(new Config(file, type));
    }

    public Language(Config config) {
        this.config = config;
    }

    public String translateString(String key) {
        return this.translateString(key, new Object[]{});
    }

    public String translateString(String key, Object... params) {
        String string = this.cache.computeIfAbsent(key,
                str -> this.config.getString(key, "§c Language reading error! "));
        if (params != null && params.length > 0) {
            for (int i = 1; i < params.length + 1; i++) {
                string = string.replace("%" + i, Objects.toString(params[i-1]));
            }
        }
        return string;
    }


}
