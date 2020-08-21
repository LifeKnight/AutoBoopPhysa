package com.lifeknight.autoboopphysa.utilities;

import net.minecraftforge.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

import static com.lifeknight.autoboopphysa.mod.Core.MOD_NAME;

public class Miscellaneous {

	public static void info(String info, Object... data) {
		FMLLog.info(MOD_NAME + " > " + info, data);
	}

	public static void logWarn(String warn, Object... data) {
		FMLLog.log(Level.WARN, MOD_NAME + " > " + warn, data);
	}

	public static void logError(String error, Object... data) {
		FMLLog.log(Level.ERROR, MOD_NAME + " > " + error, data);
	}
}
