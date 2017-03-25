package com.gmail.gaelitoelquesito;


import com.boydti.fawe.bukkit.wrapper.AsyncWorld;
import com.boydti.fawe.object.FawePlayer;
import com.sk89q.worldedit.regions.Region;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;
import java.util.Scanner;

public class RandomBiome extends JavaPlugin {

	private static final Biome[] biomes = new Biome[] {Biome.PLAINS, Biome.FOREST, Biome.EXTREME_HILLS, Biome.TAIGA, Biome.DESERT, Biome.MUSHROOM_ISLAND, Biome.OCEAN};


	@Override
	public void onEnable () {
		getCommand("").setExecutor(this);
	}


	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
		if (!sender.hasPermission("randombiome.execute")) return true;
		FawePlayer player = FawePlayer.wrap(sender);
		if (player.getSelection() == null) return true;

		Region selection = player.getSelection();
		Random random    = new Random();

		AsyncWorld world = AsyncWorld.wrap(((Player) sender).getWorld());
		for (int x = selection.getMinimumPoint().getBlockX() ; x <= selection.getMaximumPoint().getBlockX() ; x++) {
			for (int z = selection.getMinimumPoint().getBlockZ() ; z <= selection.getMaximumPoint().getBlockZ() ; z++) {
				world.setBiome(x, z, biomes[random.nextInt(biomes.length - 1)]);
			}
		}
		world.commit();
		player.sendMessage("Done!");
		return true;
	}


	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		String  string  = scanner.next();
		if (!isInteger(string)) {
			return;
		}
		double i = new Integer(string);
		System.out.println(Math.pow(i, i));
	}


	public static boolean isInteger (String string) {
		try {
			new Integer(string);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
