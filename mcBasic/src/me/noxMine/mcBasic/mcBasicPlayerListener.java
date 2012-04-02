package me.noxMine.mcBasic;

import me.noxMine.mcBasic.db.mB_mutedplayer;
import me.noxMine.mcBasic.db.mB_warnings;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class mcBasicPlayerListener implements Listener {
	
	public final mcBasic plugin;
	
	public mcBasicPlayerListener (final mcBasic instance)
	{
		plugin=instance;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		String joinuser = event.getPlayer().getName();
		Player joinplayer = mcBasic.getInstance().getServer().getPlayer(joinuser);
		boolean foundspawn=false;
		boolean foundjail=false;
		boolean foundwarn=false;
		
		foundspawn=mcBasicSpawn.firstspawncheck(joinplayer);
		
		if(foundspawn)
		{
			foundjail=mcBasicWarning.jailcheck(joinplayer);
		}
		
		if(foundspawn&&!foundjail)
		{
			foundwarn=mcBasicWarning.warncheck(joinplayer);
		}
		if(foundspawn&&!foundjail&&!foundwarn)
		{
			Messages.joinmessage(joinplayer);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerChat (PlayerChatEvent event)
	{
		Player eventplayer=event.getPlayer();
		String eventuser=eventplayer.getName();
		if (mcBasic.getInstance().getDatabase().find(mB_mutedplayer.class).where().ieq("name", eventuser).findRowCount() == 1)
		{
			eventplayer.sendMessage(ChatColor.RED + "Du bist gemuted!");
			event.setCancelled(true);
		}
		else
		{
			mcBasicMessaging.channelcheck(event);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerMove(PlayerMoveEvent event)
	{
		String joinuser = event.getPlayer().getName();
		Player joinplayer= mcBasic.getInstance().getServer().getPlayer(joinuser);
			
		if(mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name",joinuser).where().ieq("status", "0").findRowCount()>0)
		{
			joinplayer.teleport(joinplayer.getLocation());
		}			
	}
	
	
	public void onPlayerQuit(PlayerQuitEvent event) {
    	if (mcBasicMessaging.replies.containsKey((CommandSender)event.getPlayer())) 
    	{
    		mcBasicMessaging.replies.remove((CommandSender)event.getPlayer());
    	}
    }
}
