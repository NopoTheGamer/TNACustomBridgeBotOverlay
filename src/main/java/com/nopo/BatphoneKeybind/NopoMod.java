package com.nopo.BatphoneKeybind;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nopo.BatphoneKeybind.commands.Commands;
import com.nopo.BatphoneKeybind.config.NopoConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Mod(modid = "tnaMod", name = "TNA Mod", version = "1.0", clientSideOnly = true)
public class NopoMod {

    public static NopoMod INSTANCE = null;

    public GuiScreen openGui = null;
    public long lastOpenedGui = 0;

    public Commands commands;

    public NopoConfig config;

    private File configFile;

    public File getConfigFile() {
        return this.configFile;
    }

    public void newConfigFile() {
        this.configFile = new File(NopoMod.INSTANCE.getNopoDir(), "nopoConfig.json");
    }

    private static final long CHAT_MSG_COOLDOWN = 200;
    private long lastChatMessage = 0;
    private long secondLastChatMessage = 0;
    private String currChatMessage = null;

    private File nopoDir;

    public File getNopoDir() {return this.nopoDir;}

    private final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

    @Mod.EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) {
        INSTANCE = this;

        nopoDir = new File(event.getModConfigurationDirectory(), "nopo");
        nopoDir.mkdirs();

        configFile = new File(nopoDir, "nopoConfig.json");

        if (configFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(configFile), StandardCharsets.UTF_8))) {
                config = gson.fromJson(reader, NopoConfig.class);
            } catch (Exception ignored) {}
        }

        this.commands = new Commands();

        if (config == null) {
            config = new NopoConfig();
            saveConfig();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            File tmp = new File(nopoDir, "tmp");
            if (tmp.exists()) {
                for (File tmpFile : tmp.listFiles()) {
                    tmpFile.delete();
                }
                tmp.delete();
            }
            saveConfig();
        }));

    }

    public void saveConfig() {
        try {
            configFile.createNewFile();

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFile), StandardCharsets.UTF_8))) {
                writer.write(gson.toJson(config));
            }
        } catch (Exception ignored) {
        }
    }

    public static KeyBinding[] keyBindings = new KeyBinding[1];

    @Mod.EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new Events());
        MinecraftForge.EVENT_BUS.register(this);
        keyBindings[0] = new KeyBinding("Open Maddox Menu", Keyboard.KEY_M, "Maddox mod");
        for (KeyBinding keyBinding : keyBindings) {
            ClientRegistry.registerKeyBinding(keyBinding);
        }
    }

    public void sendChatMessage(String message) {
        if (System.currentTimeMillis() - lastChatMessage > CHAT_MSG_COOLDOWN) {
            secondLastChatMessage = lastChatMessage;
            lastChatMessage = System.currentTimeMillis();
            Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
            currChatMessage = null;
        } else {
            currChatMessage = message;
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().thePlayer == null) {
            openGui = null;
            currChatMessage = null;
            return;
        }
        long currentTime = System.currentTimeMillis();

        if (openGui != null) {
            if (Minecraft.getMinecraft().thePlayer.openContainer != null) {
                Minecraft.getMinecraft().thePlayer.closeScreen();
            }
            Minecraft.getMinecraft().displayGuiScreen(openGui);
            openGui = null;
            lastOpenedGui = System.currentTimeMillis();
        }
        if (currChatMessage != null && currentTime - lastChatMessage > CHAT_MSG_COOLDOWN) {
            lastChatMessage = currentTime;
            Minecraft.getMinecraft().thePlayer.sendChatMessage(currChatMessage);
            currChatMessage = null;
        }
    }
}
