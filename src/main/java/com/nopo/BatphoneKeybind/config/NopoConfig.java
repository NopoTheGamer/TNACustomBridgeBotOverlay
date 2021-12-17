package com.nopo.BatphoneKeybind.config;

import com.google.gson.annotations.Expose;
import com.nopo.BatphoneKeybind.config.sections.Bridge;
import com.nopo.BatphoneKeybind.core.GuiScreenElementWrapper;
import com.nopo.BatphoneKeybind.core.config.Config;
import com.nopo.BatphoneKeybind.core.config.annotations.Category;
import net.minecraft.client.Minecraft;

public class NopoConfig extends Config {
    
    @Override
    public void executeRunnable(int runnableId) {
        String activeConfigCategory = null;
        if (Minecraft.getMinecraft().currentScreen instanceof GuiScreenElementWrapper) {
            GuiScreenElementWrapper wrapper = (GuiScreenElementWrapper) Minecraft.getMinecraft().currentScreen;
            if (wrapper.element instanceof NopoConfigEditor) {
                activeConfigCategory = ((NopoConfigEditor) wrapper.element).getSelectedCategoryName();
            }
        }

        /*switch (runnableId) {
            case 0:
                ClientCommandHandler.instance.executeCommand(Minecraft.getMinecraft().thePlayer, "/neumap");
                return;
            case 1:
                editOverlay(activeConfigCategory, OverlayManager.miningOverlay, mining.overlayPosition);
                return;
            case 2:
                Minecraft.getMinecraft().displayGuiScreen(new GuiPositionEditor(
                        BatphoneKeybind.INSTANCE.config.mining.drillFuelBarPosition,
                        BatphoneKeybind.INSTANCE.config.mining.drillFuelBarWidth, 12, () -> {
                }, () -> {}, () -> BatphoneKeybind.INSTANCE.openGui = new GuiScreenElementWrapper(NopoConfigEditor.editor)));
                return;
            case 3:
                editOverlay(activeConfigCategory, OverlayManager.farmingOverlay, skillOverlays.farmingPosition);
                return;
            case 4:
                editOverlay(activeConfigCategory, OverlayManager.petInfoOverlay, petOverlay.petInfoPosition);
                return;
            case 5:
                editOverlay(activeConfigCategory, OverlayManager.timersOverlay, miscOverlays.todoPosition);
                return;
            case 6:
                BatphoneKeybind.INSTANCE.openGui = new NEUOverlayPlacements();
                return;
            case 7:
                BatphoneKeybind.INSTANCE.openGui = new GuiInvButtonEditor();
                return;
            case 8:
                BatphoneKeybind.INSTANCE.openGui = new GuiEnchantColour();
                return;
            case 9:
                editOverlay(activeConfigCategory, OverlayManager.bonemerangOverlay, itemOverlays.bonemerangPosition);
                return;
            case 10:
                editOverlay(activeConfigCategory, OverlayManager.crystalHollowOverlay, mining.crystalHollowOverlayPosition);
                return;
            case 11:
                editOverlay(activeConfigCategory, OverlayManager.miningSkillOverlay, skillOverlays.miningPosition);
                return;
            case 12:
                ClientCommandHandler.instance.executeCommand(Minecraft.getMinecraft().thePlayer, "/dn");
                return;
            case 13:
                ClientCommandHandler.instance.executeCommand(Minecraft.getMinecraft().thePlayer, "/pv");
                return;
            case 14:
                editOverlay(activeConfigCategory, OverlayManager.fishingSkillOverlay, skillOverlays.fishingPosition);
                return;
            case 16:
                ClientCommandHandler.instance.executeCommand(Minecraft.getMinecraft().thePlayer, "/neusouls clear");
                return;
            case 17:
                ClientCommandHandler.instance.executeCommand(Minecraft.getMinecraft().thePlayer, "/neusouls unclear");
        }*/
    }

    @Expose
    @Category(
            name = "Custom Bridge Bot",
            desc = "Throwers Not Allowed Custom Bridge Bot"
    )
    public Bridge bridge = new Bridge();
    
    @Expose
    public Hidden hidden = new Hidden();
    
    public static class Hidden {
        @Expose
        public boolean idkhiddensmth = false;
    }

}