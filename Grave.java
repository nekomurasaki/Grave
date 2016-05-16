package com.gmail.yutaha2724.Gravechest;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Grave extends JavaPlugin implements Listener{

	@Override
	public void  onEnable() {
		getLogger().info("プラグインが読み込まれました");
		getServer().getPluginManager().registerEvents(this, this);
	}


	@Override
	public void onDisable(){
		getLogger().info("プラグインが停止しました");
	}


@EventHandler
public void onPlayerDeath (PlayerDeathEvent event){
	Player player = event.getEntity();
	Location loc =  player.getLocation();
	loc.setY(loc.getY());
	org.bukkit.block.Block b = loc.getBlock();

	b.setType(Material.CHEST);
	b.getState().update(true);
	Chest s = (Chest)b.getState();
	Inventory i = s.getBlockInventory();
	ItemStack[] inv = player.getInventory().getContents();
	 for(ItemStack items : inv){
		 if(items != null){
			 i.addItem(new ItemStack(items.clone()));
			 event.getDrops().clear();
			 Location location = player.getLocation();
			 location.setY(location.getY() + 1);
			 org.bukkit.block.Block k = location.getBlock();
			 k.setType(Material.SIGN_POST);
			 Sign sign = (Sign) k.getState();
			 String Name = player.getDisplayName() ;
			 sign.setLine(1, Name + ("'s Grave"));
			 sign.update(true);
		 }
	 }
}










}
