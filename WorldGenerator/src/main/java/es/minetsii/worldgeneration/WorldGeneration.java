package es.minetsii.worldgeneration;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldGeneration extends JavaPlugin implements Listener {

	@Override
	public void onEnable () {
		Bukkit.createWorld(new WorldCreator("GenerationTest").generator(new Generator()));
		Bukkit.getPluginManager().registerEvents(this, this);
	}


	@Override
	public ChunkGenerator getDefaultWorldGenerator (String worldName, String id) {
		return new Generator();
	}


	@EventHandler
	public void join (PlayerJoinEvent event) {
		event.getPlayer().teleport(new Location(Bukkit.getWorld("GenerationTest"), 0, 100, 0));
	}
}
