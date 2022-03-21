package game.core.settings;

import game.util.buffer.EnumBuffer;

public class VideoSettingsHandler extends SettingsHandler {
    @Setting(name="vsync")
    EnumBuffer<Active> vsync;
}
