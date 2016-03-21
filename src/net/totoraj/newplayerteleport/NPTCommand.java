package net.totoraj.newplayerteleport;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class NPTCommand implements TabExecutor {

	private NewPlayerTeleport plugin;

	public NPTCommand (NewPlayerTeleport plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) {
		if (args.length == 0) {
			sender.sendMessage("/npt <reload|set|tp [Player]>");
			return true;
		}

		// setコマンド
		if (args[0].equals("set")) {
			// ゲーム内以外ははじく
			if(!(sender instanceof Player)) {
				sender.sendMessage("ゲーム内から実行してください");
				return true;
			}
			Player target = (Player) sender;
			if (plugin.setStartLocation(target.getLocation())) {
				sender.sendMessage("座標を登録しました");
				sender.sendMessage(target.getLocation().toString());
			}
			else {
				sender.sendMessage("座標が有効ではありません");
			}
			return true;
		}

		// tpコマンド
		if (args[0].equals("tp")) {
			// 利用不可なら終了
			if (!plugin.isAvailable()) {
				sender.sendMessage("座標が有効ではありません");
				return true;
			}
			// プレイヤー指定なしの場合
			if (args.length == 1) {
				// ゲーム内以外ははじく
				if (!(sender instanceof Player)) {
					sender.sendMessage("ゲーム内から実行してください");
					return true;
				}
				((Player) sender).teleport(plugin.getStartLocation());
				return true;
			}

			// プレイヤー指定ありの場合
			Player target = plugin.getServer().getPlayer(args[1]);
			if (target == null) {
				sender.sendMessage("対象が存在しません");
				return true;
			}
			target.teleport(plugin.getStartLocation());
			return true;
		}

		// reloadコマンド
		if (args[0].equals("reload")) {
			plugin.reloadConfig();
			if (plugin.loadConfig()) {
				sender.sendMessage("config.ymlをリロードしました");
				sender.sendMessage(plugin.getStartLocation().toString());
			}
			else {
				sender.sendMessage("config.ymlの内容が不正です");
			}
			return true;
		}

		sender.sendMessage("/npt <reload|set|tp [Player]>");
		return true;
	}


	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label,
			String[] args) {
		if (args.length == 1) {
			String prefix = args[0].toLowerCase();
			ArrayList<String> commands = new ArrayList<String>();
			if ("tp".startsWith(prefix)) {
				commands.add("tp");
			}
			if ("set".startsWith(prefix)) {
				commands.add("set");
			}
			if ("reload".startsWith(prefix)) {
				commands.add("reload");
			}
			return commands;
		}
		return null;
	}
}
