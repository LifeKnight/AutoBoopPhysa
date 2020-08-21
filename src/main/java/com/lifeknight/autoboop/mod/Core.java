package com.lifeknight.autoboop.mod;

import com.lifeknight.autoboop.utilities.Chat;
import com.lifeknight.autoboop.variables.LifeKnightBoolean;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.util.Timer;
import java.util.TimerTask;

import static net.minecraft.util.EnumChatFormatting.AQUA;

@net.minecraftforge.fml.common.Mod(modid = Core.MOD_ID, name = Core.MOD_NAME, version = Core.MOD_VERSION, clientSideOnly = true)
public class Core {
    public static final String
            MOD_NAME = "Auto Boop",
            MOD_VERSION = "1.0",
            MOD_ID = "autoboop";
    public static final EnumChatFormatting MOD_COLOR = AQUA;
    public static boolean onHypixel = false;
    public static final LifeKnightBoolean runMod = new LifeKnightBoolean("Mod", "Main", true);
    public static final LifeKnightBoolean runFriend = new LifeKnightBoolean("Friend", "Main", true);
    public static final LifeKnightBoolean runGuild = new LifeKnightBoolean("Guild", "Main", false);
    public static Configuration configuration;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new ModCommand());

        configuration = new Configuration();
    }

    @SubscribeEvent
    public void onConnect(final FMLNetworkEvent.ClientConnectedToServerEvent event) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Chat.sendQueuedChatMessages();
                onHypixel = !Minecraft.getMinecraft().isSingleplayer() && Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().contains("hypixel.net");
            }
        }, 1000);
    }

    @SubscribeEvent
    public void onChatMessageReceived(ClientChatReceivedEvent event) {
        if (onHypixel && runMod.getValue()) {
            String message = EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getFormattedText()).toLowerCase();

            if (message.endsWith("joined.") && !message.contains(":")) {
                if ((message.startsWith("friend ") && runFriend.getValue()) || (message.startsWith("guild ") && runGuild.getValue())) {
                    String player = message.substring(message.indexOf(">") + 2, message.indexOf("joined.") - 1);
                    Chat.sendChatMessage("/boop " + player, Chat.NORMAL);
                }
            }
        }
    }
}