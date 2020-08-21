package com.lifeknight.autoboopphysa.mod;

import com.google.gson.JsonParser;
import com.lifeknight.autoboopphysa.utilities.Chat;
import com.lifeknight.autoboopphysa.utilities.Internet;
import com.lifeknight.autoboopphysa.utilities.Miscellaneous;
import com.lifeknight.autoboopphysa.variables.LifeKnightBoolean;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static net.minecraft.util.EnumChatFormatting.WHITE;

@net.minecraftforge.fml.common.Mod(modid = Core.MOD_ID, name = Core.MOD_NAME, version = Core.MOD_VERSION, clientSideOnly = true)
public class Core {
    public static final String
            MOD_NAME = "Auto Boop Physa",
            MOD_VERSION = "1.0",
            MOD_ID = "autoboopphysa";
    public static final EnumChatFormatting MOD_COLOR = WHITE;
    public static final ExecutorService THREAD_POOL = Executors.newCachedThreadPool(new LifeKnightThreadFactory());
    public static boolean onHypixel = false;
    public static final LifeKnightBoolean runMod = new LifeKnightBoolean("Mod", "Main", true);
    public static Configuration configuration;
    private final static JsonParser JSON_PARSER = new JsonParser();
    private final static String PHYSA_UUID = "aea1237202704b70868c34558ec2bd19";
    private static String physaUsername = "physa";

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

        THREAD_POOL.submit(() -> {
            try {
                String newUsername =
                        JSON_PARSER.parse(Internet.getWebsiteContent("https://sessionserver.mojang.com/session/minecraft/profile/" + PHYSA_UUID + "?unsigned=false")).getAsJsonObject().get("name").getAsString();
                if (!physaUsername.equals(newUsername)) {
                    Miscellaneous.info("New username for physa; %s -> %s", physaUsername, newUsername);
                }
            } catch (Exception exception) {
                Miscellaneous.logError("Tried to fetch data for physa's username, error: %s", exception.getMessage());
            }
        });
    }

    @SubscribeEvent
    public void onChatMessageReceived(ClientChatReceivedEvent event) {
        if (onHypixel) {
            String message = " " + EnumChatFormatting.getTextWithoutFormattingCodes(event.message.getFormattedText()).toLowerCase() + " ";
            if (message.contains(" " + physaUsername + " ") && message.contains(" joined.") && !message.contains(":")) {
                Chat.sendChatMessage("/boop " + physaUsername, Chat.NORMAL);
            }
        }
    }
}