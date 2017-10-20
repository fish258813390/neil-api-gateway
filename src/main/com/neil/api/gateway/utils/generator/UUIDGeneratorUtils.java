package neil.api.gateway.utils.generator;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * UUID生成器
 */
public class UUIDGeneratorUtils {
    /**
     * 生成oracle样式的32位长全大写UUID
     *
     * @return
     */
    public static String getOracleUUID() {
        return RandomStringUtils.randomAlphanumeric(32).toUpperCase();
    }
}
