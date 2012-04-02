package me.noxMine.mcBasic;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class mcBasicGeneral {
	
	public static void time (Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.mod")||mcBasic.permission().has(player, "mcBasic.general.time"))
		{
			
			if(args.length==1)
			{
				boolean isint = true;
				int timevalue = 0;
				
				if(args[0].equalsIgnoreCase("day")) timevalue = 8;
				
				else if(args[0].equalsIgnoreCase("night")) timevalue = 22;
				
				else timevalue = Integer.parseInt(args[0]);
								
				try
		         {
					player.getWorld().setTime((timevalue- 8) % 24 * 1000);
					String world = player.getWorld().getName();
					Messages.generalTime(player, world, timevalue);
										
		         }
		         catch(NumberFormatException e)
		         {
		        	 isint=false;
		         }
		        								
				if(!isint) Messages.wronguse(player);
				
				
			}
			else if (args.length==2)
			{
				World w = mcBasic.getInstance().getServer().getWorld(args[0]);
				if(w!=null)
				{
					boolean isint=true;
					int timevalue = 0;
					
					if(args[0].equalsIgnoreCase("day")) timevalue = 8;
					
					else if(args[0].equalsIgnoreCase("night")) timevalue = 22;
					
					else timevalue = Integer.parseInt(args[0]);
										
					try
			         {
						w.setTime((timevalue- 8) % 24 * 1000);
						Messages.generalTime(player, w.getName(), timevalue);
					 }
			         catch(NumberFormatException e)
			         {
			            isint=false;
			         }
									
					if(!isint) Messages.wronguse(player);
				
				} else Messages.wronguse(player);
			
			} else Messages.wronguse(player);
		
		} else Messages.restricted(player);
	}
	
	public static void weather (Player player, String[] args)
	{
		if(PermissionsEx.getPermissionManager().has(player, "mcBasic.mod")||PermissionsEx.getPermissionManager().has(player, "mcBasic.general.weather"))
		{
			boolean active;
			if(args.length==1)
			{
				if(args[0].equalsIgnoreCase("sunny"))
				{
					World w = player.getWorld();
					w.setThundering(false);
					w.setStorm(false);
					w.setWeatherDuration(1000000);
					active=false;
					Messages.generalWeather(player, w.getName(), active);
				}
				else if(args[0].equalsIgnoreCase("stormy"))
				{
					World w = player.getWorld();
					w.setThundering(true);
					w.setStorm(true);
					w.setWeatherDuration(1000000);
					active=true;
					Messages.generalWeather(player, w.getName(), active);
				}
			}
			else if(args.length==2)
			{
				World w = mcBasic.getInstance().getServer().getWorld(args[0]);
				if(w!=null)
				{
					if(args[1].equalsIgnoreCase("sunny"))
					{
						w.setThundering(false);
						w.setStorm(false);
						w.setWeatherDuration(1000000);
						active=false;
						Messages.generalWeather(player, w.getName(), active);
					}
					else if(args[1].equalsIgnoreCase("stormy"))
					{
						w.setThundering(true);
						w.setStorm(true);
						w.setWeatherDuration(1000000);
						active=true;
						Messages.generalWeather(player, w.getName(), active);
					}
				
				} else Messages.wronguse(player);
								
			} else Messages.wronguse(player);
			
		} else Messages.restricted(player);
	}

	public static void item (Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.general.item"))
		{
			if(args.length==1)
			{
				String[] itemdat = args[0].split(":");
				if(itemdat.length>1)
				{
					if(itemdat.length==3)
					{
						int amount=1;
						enchantItem(player,itemdat,amount);
					}
					else Messages.wronguse(player);
					
				}
				else
				{
				Material mat;
				boolean isid;
				try
				{
					int id = Integer.parseInt(args[0]);
					mat = Material.getMaterial(id);
					isid=true;
				}
				catch (NumberFormatException ex)
				{
					String name = args[0];
					mat = Material.matchMaterial(name);
					isid=false;
				}
				
				if(mat!=null)
				{
				ItemStack item = new ItemStack(mat,1);
				player.getInventory().addItem(item);
				Messages.generalItem(player, args[0], isid, 1);
				}
				else Messages.generalWrongItem(player);
				}
			}
			else if (args.length==2)
			{
				String[] itemdat = args[0].split(":");
				if(itemdat.length>1)
				{
					if(itemdat.length==3)
					{
						int amount=0;
						boolean success=true;
						try
						{
							amount = Integer.parseInt(args[1]);
						}
						catch (NumberFormatException ex)
						{
							Messages.wronguse(player);
							success=false;
						}
						if(success) enchantItem(player,itemdat,amount);
					
					} else Messages.wronguse(player);
									
				}
				else
				{
				Material mat;
				boolean isid;
				boolean isamount=false;
				int amount=0;
				try
				{
					amount = Integer.parseInt(args[1]);
					isamount=true;
				}
				catch (NumberFormatException ex)
				{
					Messages.wronguse(player);
				}
				if(isamount){
				try
				{
					int id = Integer.parseInt(args[0]);
					mat = Material.getMaterial(id);
					isid=true;
				}
				catch (NumberFormatException ex)
				{
					String name = args[0];
					mat = Material.matchMaterial(name);
					isid=false;
				}
				if(mat!=null)
				{
				ItemStack item = new ItemStack(mat,amount);
				player.getInventory().addItem(item);
				Messages.generalItem(player, args[0], isid, amount);
				} else Messages.generalWrongItem(player);
			} else Messages.wronguse(player);
		
				}
	} else Messages.wronguse(player);
		
	
	} else Messages.restricted(player);
	}
	
	public static void clear (Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.general.clear"))
		{
			if(args.length==0)
			{
				player.getInventory().clear();
				Messages.generalClear(player);
				
			}
			else if(args.length==1)
			{
				String user =args[0];
				Player clearplayer = mcBasic.getInstance().getServer().getPlayer(user);
				if(clearplayer!=null)
				{
					clearplayer.getInventory().clear();
					Messages.generalClear(player);
				}
				else player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.GREEN + "Der Spieler " + user + " ist nicht online!");
			}
			else Messages.wronguse(player);
		}
		else Messages.restricted(player);
	}
	
	public static void cart (Player player, String[] args)
	{
		if(args.length==0)
		{
			player.getInventory().addItem(new ItemStack(Material.MINECART));
			Messages.generalCart(player);
		}
		else Messages.wronguse(player);
			
	}

	public static void broadcast (Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.mod")||mcBasic.permission().has(player, "mcBasic.general.broadcast"))
		{
			
			if(args.length>0)
			{
				String broadcastmsg ="";
				int i=0;
				while(i<args.length)
				{
					broadcastmsg= broadcastmsg +" "+args[i];
					i++;
				}
				Messages.generalBroadcast(broadcastmsg);			
						
			} else Messages.wronguse(player);
			
		} else Messages.restricted(player);
	}

	public static void playerlist (Player player, String[] args)
	{
		if(mcBasic.permission().has(player, "mcBasic.general.who"))	
		{
			if(args.length==0)
			{
				Player[] onlineplayer = mcBasic.getInstance().getServer().getOnlinePlayers();
				int i=0;
				int j=0;
				int k=0;
				int l=0;
				int number= onlineplayer.length;
				int number2;
				String[] group;
				String pexgroup;
				String maxplayer = String.valueOf(mcBasic.getInstance().getServer().getMaxPlayers());
				
				PermissionGroup[] groups = PermissionsEx.getPermissionManager().getGroups();
				number2=groups.length;
				String[] plist=new String[number2];
				while(l<number2)
				{
					plist[l]="";
					l++;
				}
				while(i<number)
				{
					group=PermissionsEx.getUser(onlineplayer[i]).getGroupsNames();
					j=0;
					while(j<number2)
					{
					pexgroup = groups[j].getName();
					if(pexgroup.equalsIgnoreCase(group[0]))
					{
						plist[j]=plist[j] + " " + onlineplayer[i].getName();
					}
					j++;
					}
					i++;
				}
				player.sendMessage(ChatColor.DARK_AQUA + "Online ("+number+"/"+maxplayer+"):");
				
				while(k<number2)
				{
					if(plist[k]!="")
					{
					player.sendMessage(groups[k].getName() + ": " + plist[k]);
					}
				k++;
				}
				
			} else Messages.wronguse(player);
		
		} else Messages.restricted(player);
	}
	
	public static void gamemode(Player player, String[] args) 
	{
		if(mcBasic.permission().has(player, "mcBasic.general.gamemode"))
		{
			
			if(args.length==1)
			{
			boolean survival;
			if(args[0].equals("0"))
			{
				survival=true;
				player.setGameMode(GameMode.SURVIVAL);
				Messages.generalGamemode(player, survival);
				
			}
			else if (args[0].equals("1"))
			{
				survival=false;
				player.setGameMode(GameMode.CREATIVE);
				Messages.generalGamemode(player,survival);
				
			} else Messages.wronguse(player);
			
			} else Messages.wronguse(player);
		
		} else Messages.restricted(player);
	}

	public static void enchantItem(Player player, String[] itemdat, int amount)
	{
		int itemid=0;
		int enchid=0;
		int levelid=0;
		Material mat;
		
		boolean success=true;
		Enchantment ench;
		try
		{
			itemid= Integer.parseInt(itemdat[0]);
			enchid= Integer.parseInt(itemdat[1]);
			levelid= Integer.parseInt(itemdat[2]);
			
		}
		catch(NumberFormatException ex)
		{
			Messages.wronguse(player);
			success=false;
		}
		if(success)
		{
		ench = Enchantment.getById(enchid);
		mat = Material.getMaterial(itemid);
		ItemStack item = new ItemStack(mat,amount);
		if(levelid>0&&levelid<4)
		{
		if(ench.canEnchantItem(item))
		{
			item.addEnchantment(ench, levelid);
			player.getInventory().addItem(item);
			player.sendMessage(ChatColor.YELLOW + mat.toString() + " mit der Verzauberung "+ ench.getName() +" auf Level " +String.valueOf(levelid)+ " wurde hinzugefügt!");
		}
		else player.sendMessage(ChatColor.RED + "Für dieses Item existiert diese Verzauberung nicht!");
		}
		else player.sendMessage(ChatColor.RED + "Das Level kann nur zwischen 1 und 3 liegen!");
		}
	}
}