package me.noxMine.mcBasic;



import java.io.File;
import java.util.List;

import me.noxMine.mcBasic.db.mB_jailedplayer;
import me.noxMine.mcBasic.db.mB_mutedplayer;
import me.noxMine.mcBasic.db.mB_warnings;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class mcBasicWarning {
	
	public static void mute(Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.warning.mute")||mcBasic.permission().has(player, "mcBasic.mod"))
		{
			if(args.length==1)
			{
				String muteuser = args[0];
				if (mcBasic.getInstance().getDatabase().find(mB_mutedplayer.class).where().ieq("name", muteuser).findRowCount() == 0) 
				{
					mB_mutedplayer mutedplayer = new mB_mutedplayer();
					mutedplayer.setName(muteuser);
					mcBasic.getInstance().getDatabase().save(mutedplayer);
					Player muteplayer=mcBasic.getInstance().getServer().getPlayer(muteuser);
					player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.YELLOW + "Der Spieler "+muteuser+" wurde gemuted!");
					if(muteplayer!=null) muteplayer.sendMessage(ChatColor.RED + "Du wurdest gemuted!");
					
					
				}
				else player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Der Spieler "+muteuser+" ist bereits gemuted!");
			}
			else Messages.wronguse(player);
		}
		else Messages.restricted(player);
			
	}
	
	public static void unmute(Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.warning.unmute")||mcBasic.permission().has(player, "mcBasic.mod"))
		{
			
			if(args.length==1)
			{
				String muteuser = args[0];
				if (mcBasic.getInstance().getDatabase().find(mB_mutedplayer.class).where().ieq("name", muteuser).findRowCount() == 1) 
				{
					mB_mutedplayer mutedplayer = mcBasic.getInstance().getDatabase().find(mB_mutedplayer.class).where().ieq("name", muteuser).findUnique();
					mcBasic.getInstance().getDatabase().delete(mutedplayer);
					player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.YELLOW + "Der Spieler "+muteuser+" wurde unmuted!");
					Player muteplayer= mcBasic.getInstance().getServer().getPlayer(muteuser);
					if(muteplayer!=null) muteplayer.sendMessage(ChatColor.GREEN + "Du wurdest unmuted. Bitte halte dich an die Regeln!");
								
				}
				else player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Der Spieler "+muteuser+" ist nicht gemuted!");
			}
			else Messages.wronguse(player);
		}
		else Messages.restricted(player);
	}
	
	public static void kick(Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.warning.kick")||mcBasic.permission().has(player, "mcBasic.mod"))
		{
			if(args.length==1)
			{
				Player kickplayer=mcBasic.getInstance().getServer().getPlayer(args[0]);
				if(kickplayer!=null)
				{
					kickplayer.kickPlayer("Du wurdest gekickt");
				}
				else player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Der Spieler "+args[0]+" befindet sich nicht auf dem Server!");
			}
			else Messages.wronguse(player);
				
		}
		else Messages.restricted(player);
	}

	public static void jail(Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.warning.jail")||mcBasic.permission().has(player, "mcBasic.mod"))
		{
	      	if(args.length==1)
	    	{
	      		File configFile = new File("plugins/mcBasic/config.yml");
				FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
	      		
	      		String jaileduser = args[0];
	    		Player jailplayer=mcBasic.getInstance().getServer().getPlayer(jaileduser);
	    		
	    		if(jailplayer!=null)
	    		{
	    		  		if(mcBasic.getInstance().getDatabase().find(mB_jailedplayer.class).where().ieq("name",jaileduser).findRowCount()==0)
	    		  			{
	    		  			String[] group = PermissionsEx.getUser(jailplayer).getGroupsNames();
	    		  			mB_jailedplayer jailedplayer = new mB_jailedplayer();
	    		  			jailedplayer.setName(jaileduser);
	    		  			jailedplayer.setPlayerGroups(group);
	    		  			jailedplayer.setStatus(true);
	    		  			try
	    		  			{
	    		  			mcBasic.getInstance().getDatabase().save(jailedplayer);
	    		  			}
	    		  			catch (Exception ex)
	    		  			{
	    		  				System.out.println("Error: " + ex.getCause());
	    		  			}
	    		  			String jaildir = "Jail.Jaillocation";
	    	    			String jaillocation = config.getString(jaildir);
	    	    			String jailgroupdir = "Jail.Jailgroup";
	    	    			String jailgroup = config.getString(jailgroupdir);
	    	    			
	    	    			String[] jailloc = jaillocation.split(",");
	        				
	        				Location tploc = new Location(mcBasic.getInstance().getServer().getWorld(jailloc[0]),Double.valueOf(jailloc[1]), Double.valueOf(jailloc[2]), Double.valueOf(jailloc[3]),Float.valueOf(jailloc[4]),Float.valueOf(jailloc[5]));
	    	    			jailplayer.teleport(tploc);
	    	    			String[] jailedgroup = new String[1];
	    	    			jailedgroup[0] = jailgroup;
	    	    			PermissionsEx.getUser(jailplayer).setGroups(jailedgroup);
	    	    			jailplayer.sendMessage(ChatColor.RED + "Du wurdest ins Gefängnis gesperrt!");
	    	    			player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.YELLOW + "Der Spieler " + ChatColor.RED + jaileduser + ChatColor.YELLOW + " wurde in das Gefängnis gesperrt!");
	    		
	    		  			} else player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Der Spieler befindet sich bereits im Gefängnis!");
	    		  	} else player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Der Spieler muss online sein um ins Gefängnis gesperrt zu werden!");
	    	} else Messages.wronguse(player);
	    			 
	    }else Messages.restricted(player);
	    	
	}
	
	public static void unjail (Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.warning.unjail")||mcBasic.permission().has(player, "mcBasic.mod"))
		{
		    	
		    	if(args.length==1)
		    	{
		    		
		    		File configFile = new File("plugins/mcBasic/config.yml");
					FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
		    		
		    		String unjailuser = args[0];
		    		Player unjailplayer=mcBasic.getInstance().getServer().getPlayer(unjailuser);	    		
		    		
		    		if(mcBasic.getInstance().getDatabase().find(mB_jailedplayer.class).where().ieq("name",unjailuser).findRowCount()==1)
		    		{
		    			mB_jailedplayer jailedplayer = mcBasic.getInstance().getDatabase().find(mB_jailedplayer.class).where().ieq("name",unjailuser).findUnique();
		    			boolean status = jailedplayer.getStatus();
		    			if(status)
		    			{
		    				if (unjailplayer!=null)
		    				{
		    					String[] unjailgroup = jailedplayer.getPlayerGroups();
		    					String unjaildir = "Jail.Unjaillocation";
			    				String unjaillocation = config.getString(unjaildir);
			    				String[] unjailloc = unjaillocation.split(",");
			    				
			    				Location unjailtploc = new Location(mcBasic.getInstance().getServer().getWorld(unjailloc[0]),Double.valueOf(unjailloc[1]), Double.valueOf(unjailloc[2]), Double.valueOf(unjailloc[3]));
				    			unjailplayer.teleport(unjailtploc);
				    			PermissionsEx.getUser(unjailplayer).setGroups(unjailgroup);
				    			unjailplayer.sendMessage(ChatColor.GREEN + "Du wurdest freigelassen, bitte halte dich an die Regeln!");
				    			player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Der Spieler " + ChatColor.GOLD + unjailuser + ChatColor.DARK_RED + " wurde freigelassen!");
			    				
				    			mcBasic.getInstance().getDatabase().delete(jailedplayer);
		    				}
		    				else
		    				{
		    				
		    				jailedplayer.setStatus(false);
		    				mcBasic.getInstance().getDatabase().save(jailedplayer);
		    				player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Der Spieler " + ChatColor.GOLD + unjailuser + ChatColor.DARK_RED + " ist nicht online!");
	    					player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Er wird freigelassen, sobald er den Server betritt!");
		    				}
		    			
		    			
		    			
		    			}
		    			else player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Der Spieler " + ChatColor.GOLD + unjailuser + ChatColor.DARK_RED + " wurde bereits freigelassen!");
		    		} else player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Der Spieler " + ChatColor.GOLD + unjailuser + ChatColor.DARK_RED + " befindet sich nicht im Gefängnis!");
		    	} else Messages.wronguse(player);		 
		    } else Messages.restricted(player);
	}
	
	public static void setjail (Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.warning.setjail"))
		 {
			String form="jail";
			String dir="Jail.Jaillocation";
			
			mcBasicTeleportation.setLocation(player,dir,form);
		 }			 
	 else Messages.restricted(player);
	}
	
	public static void setunjail (Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.warning.setjail"))
		 {
			String form="unjail";
			String dir="Jail.Unjaillocation";
			
			mcBasicTeleportation.setLocation(player, dir, form);
		}
		else Messages.restricted(player);
		
	}

	public static boolean jailcheck(Player player)
	{
		boolean foundjail=false;
		
		File configFile = new File("plugins/mcBasic/config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
		
		if(mcBasic.getInstance().getDatabase().find(mB_jailedplayer.class).where().ieq("name",player.getName()).findRowCount()==1)
		{
			mB_jailedplayer jailedplayer = mcBasic.getInstance().getDatabase().find(mB_jailedplayer.class).where().ieq("name",player.getName()).findUnique();
			boolean status= jailedplayer.getStatus();
			foundjail=true;
			if(!status)
			{
				String[] unjailgroup = jailedplayer.getPlayerGroups();
				String unjaildir = "Jail.Unjaillocation";
				String unjaillocation = config.getString(unjaildir);
				String[] unjailloc = unjaillocation.split(",");
				
				Location unjailtploc = new Location(mcBasic.getInstance().getServer().getWorld(unjailloc[0]),Double.valueOf(unjailloc[1]), Double.valueOf(unjailloc[2]), Double.valueOf(unjailloc[3]));
    			player.teleport(unjailtploc);
    			PermissionsEx.getUser(player).setGroups(unjailgroup);
    			player.sendMessage(ChatColor.GREEN + "Du wurdest freigelassen, bitte halte dich an die Regeln!");
    			player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Der Spieler " + ChatColor.GOLD + player.getName() + ChatColor.DARK_RED + " wurde freigelassen!");
				foundjail=false;
				
    			mcBasic.getInstance().getDatabase().delete(jailedplayer);
			}
			else player.sendMessage(ChatColor.RED + "Du befindest dich im Gefaegnis!");
		
		}
		return foundjail;
	}

	public static boolean warncheck(Player player)
	{
		boolean foundwarn=false;
			
		if(mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name",player.getName()).where().ieq("status", "false").findRowCount()>0)
		{
			foundwarn=true;
			if(mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name",player.getName()).where().ieq("status", "false").findRowCount()==1)
			{
				mB_warnings warning = mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name", player.getName()).where().ieq("status", "false").findUnique();
				String reason = warning.getReason();
				player.sendMessage(ChatColor.RED + "Du hast eine Verwarnung erhalten! Grund: " + ChatColor.GOLD + reason);
	    		player.sendMessage(ChatColor.DARK_PURPLE + "Gib '/warns' in den Chat ein um deine Verwarnungen anzuzeigen oder '/accept', um die Verwarnung zu akzeptieren!");
				
			}
			else
			{
				List<mB_warnings> warnings = mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name", player.getName()).where().ieq("status", "false").findList();
				int size =warnings.size();
				
				mB_warnings warning = new mB_warnings();
				int i=0;
				String reason;
				player.sendMessage(ChatColor.RED + "Du hast Verwarnungen erhalten! \n");
				while(i<size)
				{
					warning=warnings.get(i);
					reason=warning.getReason();
					
					player.sendMessage(ChatColor.RED + String.valueOf(i+1) + ". Verwarnung. Grund: " + ChatColor.GOLD + reason + "\n");
					i++;
					
				}
				player.sendMessage(ChatColor.DARK_PURPLE + "Gib '/warns' in den Chat ein um deine Verwarnungen anzuzeigen oder '/accept', um die Verwarnung zu akzeptieren!");
			}
		}
		
		return foundwarn;
	}
	
	public static void warnplayer(Player player, String[] args) 
	{
		if(mcBasic.permission().has(player, "mcBasic.warning.warn")||mcBasic.permission().has(player, "mcBasic.mod")){
	    	
			if (args.length >= 2) {
	    		
	    		String warnuser = args[0];
	    		String warnreason = "";
	    		int index = 1;
	    		Player warnplayer = mcBasic.getInstance().getServer().getPlayer(warnuser);
	    		
	    		
	    		while (index < args.length) {
                    warnreason = warnreason.concat(" " + args[index]);
                    index++;
                }
	    		
	    		mB_warnings warning = new mB_warnings();
	    		warning.setName(warnuser);
	    		warning.setStatus(false);
	    		warning.setReason(warnreason);
	    		
	    		mcBasic.getInstance().getDatabase().save(warning);
	    		
	    		if(warnplayer!=null)
	    		{
	    			warnplayer.sendMessage(ChatColor.RED + "Du hast eine Verwarnung erhalten! Grund: " + ChatColor.GOLD + warnreason);
	    			warnplayer.sendMessage(ChatColor.DARK_PURPLE + "Gib '/warns' in den Chat ein um deine Verwarnungen anzuzeigen oder '/accept', um die Verwarnung zu akzeptieren!");
	    			
	    		}
	    		
	    		player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.RED + "Der Spieler " + ChatColor.GOLD + warnuser + ChatColor.RED +  " hat eine Verwarnung bekommen. Grund: " + ChatColor.GOLD + warnreason);
	       	} else Messages.wronguse(player);
	    			 
	    } 	else Messages.restricted(player);
	    		
		
	}

	public static void warns(Player player, String[] args) 
	{
		if (args.length == 0) {
			
			player.sendMessage(ChatColor.RED + "Deine Verwarnungen: \n");
			if(mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name",player.getName()).findRowCount()>0)
			{
			List<mB_warnings> warnings = mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name", player.getName()).findList();
			int size =warnings.size();
			
			mB_warnings warning = new mB_warnings();
			int i=0;
			int id;
			String reason;
			
			while(i<size)
			{
				warning=warnings.get(i);
				id =warning.getId();
				reason=warning.getReason();
				player.sendMessage(ChatColor.RED + String.valueOf(i+1) + ". Verwarnung. Grund: " + ChatColor.GOLD + reason + " (#" + id + ")\n");
				i++;
				
			}
			player.sendMessage(ChatColor.RED + "Anzahl deiner Verwarnungen: " + ChatColor.GOLD + String.valueOf(i));
			
			}
			else player.sendMessage(ChatColor.GREEN + "Keine \n");
			
			
			
			
		}
		else if (args.length == 1) {
		
			if(mcBasic.permission().has(player, "mcBasic.warning.warns")||mcBasic.permission().has(player, "mcBasic.mod"))
			{
				player.sendMessage(ChatColor.RED + "Verwarnungen von " + ChatColor.GOLD + args[0] + ": \n");
				if(mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name",args[0]).findRowCount()>0)
				{
					List<mB_warnings> warnings = mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name", args[0]).findList();
					int size =warnings.size();
					
					mB_warnings warning = new mB_warnings();
					int i=0;
					int id;
					String reason;
					
					while(i<size)
					{
						warning=warnings.get(i);
						id =warning.getId();
						reason=warning.getReason();
						player.sendMessage(ChatColor.RED + String.valueOf(i+1) + ". Verwarnung. Grund: " + ChatColor.GOLD + reason + " (#" + id + ")\n");
						i++;
						
					}
					player.sendMessage(ChatColor.RED + "Anzahl der Verwarnungen: " + ChatColor.GOLD + String.valueOf(i));
				} else player.sendMessage(ChatColor.GREEN + "Keine \n");
			} else Messages.restricted(player);
		} else Messages.wronguse(player);
	}

	public static void delwarn(Player player, String[] args) 
	{
		if(mcBasic.permission().has(player, "mcBasic.warning.delwarn"))
		{
			if (args.length == 2) {
				
				String user = args[0];
				String id = args[1];
				
				if(mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name", user).where().ieq("id", id).findRowCount()==1)
				{
					mB_warnings warning = mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name", user).where().ieq("id", id).findUnique();
					mcBasic.getInstance().getDatabase().delete(warning);
					player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.RED + "Die Verwarnung von Spieler " + ChatColor.GOLD + user + ChatColor.RED + " mit der ID " + ChatColor.GOLD + "(#" + id + ") " + ChatColor.RED + "wurde gelöscht!" );
				} else player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.RED + "Die Verwarnung des Spielers " + ChatColor.GOLD + user + ChatColor.RED + " mit der ID " + ChatColor.GOLD + "(#" + id + ") " + ChatColor.RED + "wurde nicht gefunden!");
			} else Messages.wronguse(player);
		} else Messages.restricted(player);
	}

	public static void accept(Player player, String[] args)
	{
		if(mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name",player.getName()).findRowCount()>0)
		{
			if(mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name",player.getName()).findRowCount()==1)
			{
				mB_warnings warning = mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name",player.getName()).findUnique();
				warning.setStatus(true);
				mcBasic.getInstance().getDatabase().save(warning);
				player.sendMessage(ChatColor.RED + "Die Verwarnung wurde akzeptiert. Bitte halte dich ab sofort an die Regeln!");
			}
			else
			{
				List<mB_warnings> warnings = mcBasic.getInstance().getDatabase().find(mB_warnings.class).where().ieq("name", player.getName()).findList();
				int size =warnings.size();
				
				mB_warnings warning = new mB_warnings();
				int i=0;
				
				while(i<size)
				{
					warning=warnings.get(i);
					warning.setStatus(true);
					mcBasic.getInstance().getDatabase().save(warning);
					i++;
					
				}
				player.sendMessage(ChatColor.RED + "Die Verwarnungen wurden akzeptiert. Bitte halte dich ab sofort an die Regeln!");
			}
		}
		else player.sendMessage(ChatColor.GREEN + "Du hast keine Verwarnungen, die akzeptiert werden müssen!");
		
	}

	
}
