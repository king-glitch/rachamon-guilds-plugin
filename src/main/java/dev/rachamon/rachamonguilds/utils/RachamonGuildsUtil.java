package dev.rachamon.rachamonguilds.utils;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.serializer.TextSerializers;

public class RachamonGuildsUtil {
    public static Text toText(String string) {
        return TextSerializers.FORMATTING_CODE.deserialize(string);
    }

    public static String toColor(String str) {
        return str.replaceAll("(&([A-Fa-fK-Ok-oRr0-9]))", "§$2");
    }
}
