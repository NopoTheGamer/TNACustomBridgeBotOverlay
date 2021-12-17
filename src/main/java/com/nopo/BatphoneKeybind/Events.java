package com.nopo.BatphoneKeybind;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Events {

    static String lastMaddoxCommand = "/cb placeholder";
    static double lastMaddoxTime = 0;

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Unload event) {
        NopoMod.INSTANCE.saveConfig();
    }

    private static final Pattern BOT_MESSAGE = Pattern.compile("Guild > TNABridgeBot \\[(.*)]: (.*): (.*)");

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());

        //if (message.contains(":")) return;

        //System.out.println(message);
        if (NopoMod.INSTANCE.config.bridge.bridgeBot) {
            Matcher matcher = BOT_MESSAGE.matcher(message);
            if (matcher.matches()) {
                event.setCanceled(true);
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(
                        EnumChatFormatting.values()[NopoMod.INSTANCE.config.bridge.bridgePrefixColor] + "Bridge > " +
                                EnumChatFormatting.values()[NopoMod.INSTANCE.config.bridge.bridgeNameColor]
                                + matcher.group(2) + EnumChatFormatting.WHITE + ": " + matcher.group(3)));
            }
        }

    }
}
