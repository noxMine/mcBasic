package me.noxMine.mcBasic;


import java.io.File;
import me.noxMine.mcBasic.db.mB_firstspawnplayer;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class mcBasicSpawn {

	public static void spawn(Player player)
	{
		if(mcBasic.permission().has(player, "mcBasic.general.spawn"))
		{
			File configFile = new File("plugins/mcBasic/config.yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
			String curworld = player.getLocation().getWorld().getName();
			String spawndir = new String("Spawn.Spawnlocation_"+curworld);
			
			String location=config.getString(spawndir);
			String[] loc = location.split(",");
			Location spawnloc = new Location(mcBasic.getInstance().getServer().getWorld(loc[0]),Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]),Float.valueOf(loc[4]),Float.valueOf(loc[5]));
			player.teleport(spawnloc);
				
		}else Messages.restricted(player);
		
	}
	// .
	public static void setspawn(Player player)
	{
		if(mcBasic.permission().has(player, "mcBasic.spawn.setspawn"))
		 {
			String form="spawn";
			String locworld = player.getLocation().getWorld().getName();
			String dir = new String("Spawn.Spawnlocation_"+locworld);
			
			mcBasicTeleportation.setLocation(player,dir,form);
			
		 }
		else Messages.restricted(player);
	}

	public static void firstspawn(Player player)
	{
		if(mcBasic.permission().has(player, "mcBasic.spawn.firstspawn"))
		{
			File configFile = new File("plugins/mcBasic/config.yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
			String firstspawndir = "Firstspawn.Location";
			String location = config.getString(firstspawndir);
			String[] loc = location.split(",");
			Location spawnloc = new Location(mcBasic.getInstance().getServer().getWorld(loc[0]),Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]),Float.valueOf(loc[4]),Float.valueOf(loc[5]));
			player.teleport(spawnloc);
			
			
		}
		
	else
	 {
		Messages.restricted(player);
	 }
	}
	
	public static void setfirstspawn (Player player)
	{
		if(mcBasic.permission().has(player, "mcBasic.spawn.setfirstspawn"))
		 {
			String form="firstspawn";
			String dir="Firstspawn.Location";
			
			mcBasicTeleportation.setLocation(player, dir, form);
						 
			 }
		else Messages.restricted(player);
	}

	public static void password(Player player, String[] args)
	{
		String[] promotegroup =new String[1];
		if(mcBasic.permission().has(player, "mcBasic.password"))
		{
			
			File configFile = new File("plugins/mcBasic/config.yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
			String passworddir="Promote.Password";
			String password= config.getString(passworddir);
			if(args.length==1)
			{
				if(args[0].equalsIgnoreCase(password)){
				
				promotegroup[0]=config.getString("Promote.Promotegroup");
				PermissionsEx.getUser(player).setGroups(promotegroup);
				player.sendMessage(ChatColor.GREEN + "Richtig! Du bist nun " + promotegroup[0] +", viel Spass!");
				}
				else player.sendMessage(ChatColor.RED + "Falsches Passwort!");
				}
			else player.sendMessage(ChatColor.RED + "Falsche Verwendung! Richtig: /pw passwort");
		}
		else player.sendMessage(ChatColor.RED + "Du bist bereits " + promotegroup[0] +"!");
	}

	public static void spawnmob(Player player, String[] args) 
	{
		if(mcBasic.permission().has(player, "mcBasic.spawn.spawnmob"))
		{
			if(args.length==1)
			{
				
				 
				EntityType mob = EntityType.fromName(args[0]);
				if(mob!=null)
				{
				player.getLocation().getWorld().spawnCreature(player.getLocation(), mob );
				player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.YELLOW + "Es wurde ein " +mob.getName()+" gespawnt!");
				}
				else Messages.wronguse(player);
			}
			
			else if(args.length==2)
			{
				
				boolean isint=true;
				int amount = 1;
				try
				{
				amount = Integer.parseInt(args[1]);
				
				}
				catch (NumberFormatException n)
				{
					isint=false;
					Messages.wronguse(player);
				}
				if(isint)
				{
				int id=0;
				
				EntityType mob = EntityType.fromName(args[0]);
				if(mob!=null)
				{
					;
					while(id<amount)
				{
					player.getLocation().getWorld().spawnCreature(player.getLocation(), mob );
					id++;
				}
				player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.YELLOW + "Es wurden "+amount+" "+mob.getName()+" gespawnt!");
				}
				else Messages.wronguse(player);
				
				}
			}
			
			else Messages.wronguse(player);
		}
		else Messages.restricted(player);
		
	}

	public static boolean firstspawncheck(Player player)
	{
		boolean foundspawn=true;
		
		try
		{		
		if (mcBasic.getInstance().getDatabase().find(mB_firstspawnplayer.class).where().ieq("name", player.getName()).findRowCount() == 0)
		{
			foundspawn=false;
			File configFile = new File("plugins/mcBasic/config.yml");
			FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
			String firstspawndir = "Firstspawn.Location";
			String location = config.getString(firstspawndir);
			String[] loc = location.split(",");
			Location spawnloc = new Location(mcBasic.getInstance().getServer().getWorld(loc[0]),Double.valueOf(loc[1]), Double.valueOf(loc[2]), Double.valueOf(loc[3]),Float.valueOf(loc[4]),Float.valueOf(loc[5]));
			player.teleport(spawnloc);
			
			mB_firstspawnplayer firstplayer = new mB_firstspawnplayer();
			firstplayer.setName(player.getName());
			mcBasic.getInstance().getDatabase().save(firstplayer);
			System.out.println("[mcBasic] Player "+player.getName()+" added to Database");
			Messages.firstwelcome(player);
		}
		}
		
		catch (Exception e) {
    		    		
    		System.out.println("Could not add player  - " + e.getMessage() + " in " + e.getCause());

    	}
		return foundspawn;
	}

}
