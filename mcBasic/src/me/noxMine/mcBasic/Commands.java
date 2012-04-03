package me.noxMine.mcBasic;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Commands 
{
	public static void onCommandfromPlayer(CommandSender sender, Command cmd, String label, String[] args) 
	{
		Player player = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("spawn"))
				{
					mcBasicSpawn.spawn(player);
				}
		
		if (cmd.getName().equalsIgnoreCase("setspawn"))
				{
					mcBasicSpawn.setspawn(player);
			 	}
		
		if (cmd.getName().equalsIgnoreCase("time"))
				{
					mcBasicGeneral.time(player, args);
				}
		
		if (cmd.getName().equalsIgnoreCase("weather"))
				{
					mcBasicGeneral.weather(player, args);
				}
		
		if (cmd.getName().equalsIgnoreCase("broadcast")||cmd.getName().equalsIgnoreCase("bc"))
		{
			mcBasicGeneral.broadcast(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("hilfe"))
		{
			Messages.help(player,args);
		}
		
		if (cmd.getName().equalsIgnoreCase("setfirstspawn"))
		{
			mcBasicSpawn.setfirstspawn(player);
		}
		
		if (cmd.getName().equalsIgnoreCase("firstspawn"))
		{
			mcBasicSpawn.firstspawn(player);
		}
		
		if (cmd.getName().equalsIgnoreCase("teleport")||cmd.getName().equalsIgnoreCase("tp"))
		{
			mcBasicTeleportation.teleport(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("tell")||cmd.getName().equalsIgnoreCase("msg"))
		{
			mcBasicMessaging.tell(sender,player,args);
		}
		
		if(cmd.getName().equalsIgnoreCase("reply")||cmd.getName().equalsIgnoreCase("r"))
		{
			mcBasicMessaging.reply(sender,player,args);
		}
		
		if(cmd.getName().equalsIgnoreCase("mute"))
		{
			mcBasicWarning.mute(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("unmute"))
		{
			mcBasicWarning.unmute(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("k"))
		{
			mcBasicWarning.kick(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("item")||cmd.getName().equalsIgnoreCase("i"))
		{
			mcBasicGeneral.item(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("clear"))
		{
			mcBasicGeneral.clear(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("jail"))
		{
			mcBasicWarning.jail(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("unjail"))
		{
			mcBasicWarning.unjail(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("setjail"))
		{
			mcBasicWarning.setjail(player,args);
		}
		
		if(cmd.getName().equalsIgnoreCase("setunjail"))
		{
			mcBasicWarning.setunjail(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("cart"))
		{
			mcBasicGeneral.cart(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("pw"))
		{
			mcBasicSpawn.password(player,args);
		}
		
		if(cmd.getName().equalsIgnoreCase("spawnmob"))
			{
			mcBasicSpawn.spawnmob(player,args);
			}
		
		if(cmd.getName().equalsIgnoreCase("me"))
		{
			mcBasicMessaging.me(player,args);
		}
		
		if(cmd.getName().equalsIgnoreCase("who")||cmd.getName().equalsIgnoreCase("list"))
		{
			mcBasicGeneral.playerlist(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("gm"))
		{
			mcBasicGeneral.gamemode(player,args);
		}
		
		if(cmd.getName().equalsIgnoreCase("setwarp"))
		{
			mcBasicTeleportation.setwarp(player, args);
		}
		
		if(cmd.getName().equalsIgnoreCase("delwarp"))
		{
			mcBasicTeleportation.deleteWarp(player, args);
		}
		if(cmd.getName().equalsIgnoreCase("warp"))
		{
			mcBasicTeleportation.warp(player,args);
		}
		
		if (cmd.getName().equalsIgnoreCase("warn") ) 
		{
			mcBasicWarning.warnplayer(player,args);
		}
		    	
				
		if(cmd.getName().equalsIgnoreCase("warns"))
		{
    		mcBasicWarning.warns(player,args);
		}    	
					
					
		if(cmd.getName().equalsIgnoreCase("delwarn"))
		{
		    mcBasicWarning.delwarn(player,args);
		}
		    	
		   	
		if(cmd.getName().equalsIgnoreCase("accept"))
		{
		    mcBasicWarning.accept(player,args);	
		}
		
		if(cmd.getName().equalsIgnoreCase("channel")||cmd.getName().equalsIgnoreCase("ch"))
		{
			mcBasicMessaging.channel(player,args);
		}
		if(cmd.getName().equalsIgnoreCase("leavechannel")||cmd.getName().equalsIgnoreCase("lch")||cmd.getName().equalsIgnoreCase("l"))
		{
			mcBasicMessaging.leavechannels(player,args);
		}
				
	}
	
	public static void onCommandfromConsole(CommandSender sender, Command cmd, String label, String[] args) 
	{
				
		
		
	}
	
}
