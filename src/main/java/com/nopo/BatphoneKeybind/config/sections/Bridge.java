package com.nopo.BatphoneKeybind.config.sections;

import com.google.gson.annotations.Expose;
import com.nopo.BatphoneKeybind.core.config.annotations.ConfigEditorBoolean;
import com.nopo.BatphoneKeybind.core.config.annotations.ConfigEditorDropdown;
import com.nopo.BatphoneKeybind.core.config.annotations.ConfigOption;

public class Bridge {
    @Expose
    @ConfigOption(
            name = "Enable Custom Bridge Bot Overlay",
            desc = "Turns on the Custom Bridge Bot Overlay for the Guild 'Throwers Not Allowed'"
    )
    @ConfigEditorBoolean
    public boolean bridgeBot = true;

    @Expose
    @ConfigOption(
            name = "Bridge Bot Name Color",
            desc = "Changes the Color of the Name of the Bridge Bot in Game."
    )
    @ConfigEditorDropdown(
            values = {"Black", "Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Gold",
                    "Gray", "Dark Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White"}
    )
    public int bridgePrefixColor = 2;

    @Expose
    @ConfigOption(
            name = "Text Name Color",
            desc = "Changes the Color of the Text that comes after the Bridge Bot Name."
    )
    @ConfigEditorDropdown(
            values = {"Black", "Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Gold",
                    "Gray", "Dark Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow", "White"}
    )
    public int bridgeNameColor = 11;
}
