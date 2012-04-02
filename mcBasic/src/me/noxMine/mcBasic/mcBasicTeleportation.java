package me.noxMine.mcBasic;

import java.io.File;
import java.io.IOException;

import me.noxMine.mcBasic.db.mB_warps;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class mcBasicTeleportation {

	public static void teleport(Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.teleport")||mcBasic.permission().has(player, "mcBasic.mod"))
		{
			if (args.length==1)
			{
				Player telplayer=mcBasic.getInstance().getServer().getPlayer(args[0]);
				String[] coord=args[0].split(",");
				if(telplayer!=null)
				{
					player.teleport(telplayer);
				}
				else if(!coord[0].equals(args[0]))
				{
					Location telloc= new Location(player.getWorld(),Double.valueOf(coord[0]),Double.valueOf(coord[1]),Double.valueOf(coord[2]));
					player.teleport(telloc);
				}
				else Messages.wronguse(player);
			}
			else
			{
				Player telplayer =mcBasic.getInstance().getServer().getPlayer(args[args.length-1]);
				if (telplayer!=null)
				{
					int i=0;
					Player curplayer;
					while(i<args.length-1)
					{
						curplayer=mcBasic.getInstance().getServer().getPlayer(args[i]);
						if(curplayer!=null)
						{
							curplayer.teleport(telplayer);
							curplayer.sendMessage(ChatColor.YELLOW + "Teleportiert zu " + telplayer.getName());
						}
						i++;
					}
					player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.YELLOW + (args.length-1)+ " Spieler wurden teleportiert.");
				}
			}
		}
		else Messages.restricted(player);
	}

	public static void setwarp(Player player, String[] args) 
	{
		if(mcBasic.permission().has(player, "mcBasic.setwarp"))
		{
			if(args.length<3&&args.length>0)
			{
				boolean restriction=false;
				
				if(args.length==2)
				{
					if(args[1].equalsIgnoreCase("admin"))
					{
						restriction=true;
					}
					else Messages.wronguse(player);
				}
				
				if(mcBasic.getInstance().getDatabase().find(mB_warps.class).where().ieq("name", args[0]).findRowCount()==0)
				{
					mB_warps warp = new mB_warps();
					String name = args[0];
					Location location = player.getLocation();
					warp.setName(name);
					warp.setRestriction(restriction);
					warp.setLocation(location);
					
					mcBasic.getInstance().getDatabase().save(warp);
					
					player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.YELLOW + "Der Warp "+args[0]+" wurde erfolgreich gesetzt!");
					
					
				}
				else player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.DARK_RED + "Der Warp "+args[0]+" existiert bereits!");
			}
			else Messages.wronguse(player);
		}
		else Messages.restricted(player);
		
	}

	public static void warp(Player player, String[] args) 
	{
		if(mcBasic.permission().has(player, "mcBasic.warp"))
		{
			if(args.length==1)
			{
				if(mcBasic.getInstance().getDatabase().find(mB_warps.class).where().ieq("name", args[0]).findRowCount()==1)
				{
					mB_warps warp = mcBasic.getInstance().getDatabase().find(mB_warps.class).where().ieq("name", args[0]).findUnique();
					boolean restriction = warp.getRestriction();
					Location location = warp.getLocation();
					if(restriction)
					{
						if(mcBasic.permission().has(player, "mcBasic.warp.admin"))
						{
							player.teleport(location);
							player.sendMessage(ChatColor.YELLOW + "Du wurdest zum Warp "+args[0]+ " gebracht.");
						}
						else Messages.restricted(player);
					}
					else
					{
						player.teleport(location);
						player.sendMessage(ChatColor.YELLOW + "Du wurdest zum Warp "+args[0]+ " gebracht.");
					}
				}
				else player.sendMessage(ChatColor.RED + "Der Warp "+args[0]+" existiert nicht!");
			}
			else Messages.wronguse(player);
		}
		else Messages.restricted(player);
	}
	
	public static void deleteWarp(Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.delwarp"))
		{
			if(mcBasic.getInstance().getDatabase().find(mB_warps.class).where().ieq("name", args[0]).findRowCount()==1)
			{
				mB_warps warp = mcBasic.getInstance().getDatabase().find(mB_warps.class).where().ieq("name", args[0]).findUnique();
				mcBasic.getInstance().getDatabase().delete(warp);
				player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.DARK_RED + "Der Warp "+ args[0] + " wurde gelöscht!");
			}
			else player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.DARK_RED + "Der Warp "+args[0]+" existiert nicht!");
		}
		else Messages.restricted(player);
	}

	public static void setLocation(Player player, String dir, String form)
	{
		File configFile = new File("plugins/mcBasic/config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
		
		Location loc =player.getLocation();
		String world = loc.getWorld().getName();
		String x = String.valueOf(loc.getX());
		String y = String.valueOf(loc.getY());
		String z = String.valueOf(loc.getZ());
		String yaw = String.valueOf(loc.getYaw());
		String pitch = String.valueOf(loc.getPitch());
		
		String location= new String(world+","+x+","+y+","+z+","+yaw+","+pitch);
			
		config.set(dir, location);
		 
		 try
		 {
			 config.save(configFile);
			 if(form.equals("spawn")) Messages.setspawnmsg(player,world);
			 if(form.equals("firstspawn")) Messages.setfirstspawnmsg (player);
			 if(form.equals("jail")) Messages.setjailmsg (player);
			 if(form.equals("unjail")) Messages.setunjailmsg(player);
		 }
		 
		 catch (IOException e) 
		  {
					System.out.println("[mcBasic] Error: Can't save config file:");
					e.printStackTrace();
		  }
		 			 
		 }
		 
	 
	
}
