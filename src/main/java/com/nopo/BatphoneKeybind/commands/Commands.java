package com.nopo.BatphoneKeybind.commands;

import com.nopo.BatphoneKeybind.NopoMod;
import com.nopo.BatphoneKeybind.config.NopoConfigEditor;
import com.nopo.BatphoneKeybind.core.GuiScreenElementWrapper;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.client.ClientCommandHandler;
import org.apache.commons.lang3.StringUtils;

public class Commands {

    public Commands() {
        ClientCommandHandler.instance.registerCommand(settingsCommand);
    }
    
    SimpleCommand.ProcessCommandRunnable settingsRunnable = new SimpleCommand.ProcessCommandRunnable() {
        public void processCommand(ICommandSender sender, String[] args) {
            if (args.length > 0) {
                NopoMod.INSTANCE.openGui = new GuiScreenElementWrapper(new NopoConfigEditor(NopoMod.INSTANCE.config, StringUtils.join(args, " ")));
            } else {
                NopoMod.INSTANCE.openGui = new GuiScreenElementWrapper(NopoConfigEditor.editor);
            }
        }
    };

    SimpleCommand settingsCommand = new SimpleCommand("tna", settingsRunnable);
}
