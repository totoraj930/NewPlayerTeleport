package net.totoraj.newplayerteleport;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener{
	private NewPlayerTeleport plugin;

	public PlayerListener (NewPlayerTeleport plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin (PlayerJoinEvent event) {
		Player player = event.getPlayer();
		// 利用不可ならなにもしない
		if (!plugin.isAvailable()) {
			return;
		}
		// 初回接続だったらテレポート
		if (!player.hasPlayedBefore()) {
			player.teleport(plugin.getStartLocation());
		}
	}
}
