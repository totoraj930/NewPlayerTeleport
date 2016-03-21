package net.totoraj.newplayerteleport;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class NewPlayerTeleport extends JavaPlugin {
	private HashMap<String, TabExecutor> COMMANDS;
	private Location LOCATION;
	private boolean AVAILABLE;
	private FileConfiguration CONFIG;

	public boolean loadConfig () {
		CONFIG = this.getConfig();
		String worldUUID = CONFIG.getString("world", null);
		double x, y, z;
		x = CONFIG.getDouble("x", 0.0);
		y = CONFIG.getDouble("y", 0.0);
		z = CONFIG.getDouble("z", 65);
		float yaw, pitch;
		yaw = (float) CONFIG.getDouble("yaw", 0.0);
		pitch = (float) CONFIG.getDouble("pitch", 0.0);

		if (worldUUID == null) {
			return false;
		}

		LOCATION = new Location(
				this.getServer().getWorld(UUID.fromString(worldUUID)),
				x, y, z, yaw, pitch);

		return true;
	}

	public boolean setStartLocation (@Nonnull Location location) {
		CONFIG.set("world", location.getWorld().getUID().toString());
		CONFIG.set("x", location.getX());
		CONFIG.set("y", location.getY());
		CONFIG.set("z", location.getZ());
		CONFIG.set("yaw", location.getYaw());
		CONFIG.set("pitch", location.getPitch());
		this.saveConfig();
		LOCATION = location;
		AVAILABLE = true;
		return true;
	}

	public boolean isAvailable () {
		return AVAILABLE;
	}
	public Location getStartLocation () {
		return LOCATION;
	}

	@Override
	public void onEnable() {
		// コンフィグの準備
		this.saveDefaultConfig();
		AVAILABLE = loadConfig();

		// コマンド登録
		COMMANDS = new HashMap<String, TabExecutor>();
		COMMANDS.put("newplayerteleport", new NPTCommand(this));

		// イベントリスナ登録
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		return COMMANDS.get(command.getName()).onCommand(sender, command,
				label, args);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command,
			String label, String[] args) {
		return COMMANDS.get(command.getName()).onTabComplete(sender, command,
				label, args);
	}

	@Override
	public void onDisable() {

	}
}
