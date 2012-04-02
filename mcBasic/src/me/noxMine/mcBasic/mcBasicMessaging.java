package me.noxMine.mcBasic;

import java.util.HashMap;
import java.util.List;

import me.noxMine.mcBasic.db.mB_channelplayers;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class mcBasicMessaging {

	public static HashMap<CommandSender, CommandSender> replies = new HashMap<CommandSender, CommandSender>();
	
	public static void tell(CommandSender sender, Player player, String[] args)
	{
		if (args.length>1){
		
			Player tellplayer = mcBasic.getInstance().getServer().getPlayer(args[0]);
			if (tellplayer!=null&&tellplayer!=player)
			{
				CommandSender recipient = mcBasic.getInstance().getServer().getPlayer(args[0]);
				int i=1;
				String message="";
				while (i < args.length) {
		            message = message + " " + args[i];
		            i++;
					}
				tellplayer.sendMessage(ChatColor.LIGHT_PURPLE + "(" + player.getName() + " schreibt):"+ ChatColor.GRAY + message);
				player.sendMessage(ChatColor.LIGHT_PURPLE + "(Du -> " + tellplayer.getName() + "):" + ChatColor.GRAY + message);
				System.out.println("[mcBasic] ("+player.getName()+"->"+tellplayer.getName()+") "+message);
				replies.put(sender, recipient);
				replies.put(recipient, sender);
			}
			else
				{
					player.sendMessage(ChatColor.RED + "Spieler nicht gefunden!");
				}
		
		
        
		}
		else
		{
			player.sendMessage(ChatColor.RED + "Falsche Verwendung! Richtig: /tell <spieler> <nachricht>");
		}
	}

	public static void me (Player player, String[] args)
	{
		if(args.length>0)
		{
			String message="";
			int i=0;
			while(i<args.length)
			{
				message= message +" "+ args[i];
				i++;
			}
			mcBasic.getInstance().getServer().broadcastMessage( "* "+player.getName()+message);
		}
		else Messages.wronguse(player);
	}

	public static void reply(CommandSender sender, Player player, String[] args) 
	{
		if (args.length > 0) {
			if (replies.containsKey(sender)) {
				CommandSender recipient = replies.get(sender);
				if (recipient instanceof Player && !((Player)recipient).isOnline()) {
					sender.sendMessage(ChatColor.RED+"Der Spieler "+recipient.getName()+" ist nicht online!");
					replies.remove(sender);
					
				}
				String msg = "";
				for (int i = 0; i < args.length; ++i) {
					msg += args[i] + " ";
				}
				
				recipient.sendMessage(ChatColor.LIGHT_PURPLE + "(" + sender.getName() + " schreibt): "+ ChatColor.GRAY + msg);
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "(Du -> " + recipient.getName() + "): " + ChatColor.GRAY + msg);
				System.out.println("[mcBasic] ("+sender.getName()+"->"+recipient.getName()+") "+msg);
				replies.put(recipient, sender);
				
			} else {
				sender.sendMessage(ChatColor.RED+"Du kannst niemandem antworten.");
				
			}
		} else Messages.wronguse(player);
		
		
	}

	public static void channel(Player player, String[] args) 
	{
		if(args.length==1)
		{
			if(mcBasic.permission().has(player, "mcBasic.messaging.channel.change")) changechannel(player,args);
			else Messages.restricted(player);
		}
		else if(args.length==2)
		{
			if(mcBasic.permission().has(player, "mcBasic.messaging.channel.create")) createchannel(player,args);
			else Messages.restricted(player);
		}
		else Messages.wronguse(player);
		
	}

	private static void createchannel(Player player, String[] args) 
	{
		if(mcBasic.getInstance().getDatabase().find(mB_channelplayers.class).where().ieq("channel", args[1]).findRowCount()==0)
		{
			savechannel(player, args[1]);
		}
		else player.sendMessage(ChatColor.RED + "Der Channel "+args[1]+ " existiert bereits!");
		
	}

	private static void changechannel(Player player, String[] args) 
	{
		if(args[0].equalsIgnoreCase("g"))
		{
			String ch = "G";
			savechannel(player, ch);
		}
		else if (args[0].equalsIgnoreCase("h"))
		{
			String ch = "H";
			savechannel(player, ch);
		}
		else if(args[0].equalsIgnoreCase("t"))
		{
			String ch = "T";
			savechannel(player, ch);
		}
		else
		{
			if(mcBasic.getInstance().getDatabase().find(mB_channelplayers.class).where().ieq("channel", args[0]).findRowCount()>0)
			{
				savechannel(player, args[0]);
			}
			else player.sendMessage(ChatColor.RED + "Der Channel "+args[0]+ " existiert nicht!");
		}
			
		
	}

	private static void savechannel(Player player, String ch) 
	{
		if(mcBasic.getInstance().getDatabase().find(mB_channelplayers.class).where().ieq("name", player.getName()).findRowCount()==1)
		{
			mB_channelplayers chplayer = mcBasic.getInstance().getDatabase().find(mB_channelplayers.class).where().ieq("name", player.getName()).findUnique();
			chplayer.setChannel(ch);
			mcBasic.getInstance().getDatabase().save(chplayer);
			Messages.changechannelmsg(player,ch);
		}
		else
		{
			mB_channelplayers chplayer = new mB_channelplayers();
			chplayer.setName(player.getName());
			chplayer.setChannel(ch);
			mcBasic.getInstance().getDatabase().save(chplayer);
			Messages.changechannelmsg(player,ch);
		}
		
	}

	public static void channelcheck(PlayerChatEvent event) 
	{
		Player player = event.getPlayer();
		if(mcBasic.getInstance().getDatabase().find(mB_channelplayers.class).where().ieq("name", player.getName()).findRowCount()==1)
		{
			String msg = event.getMessage();
			event.setCancelled(true);
			mB_channelplayers chplayer = mcBasic.getInstance().getDatabase().find(mB_channelplayers.class).where().ieq("name", player.getName()).findUnique();
			String ch = chplayer.getChannel();
			List<mB_channelplayers> players = mcBasic.getInstance().getDatabase().find(mB_channelplayers.class).where().ieq("channel", ch).findList();
			int i =0;
			int size = players.size();
			Player sendplayer;
			String senduser=player.getName();
			String sendprefix = Messages.colormsg(PermissionsEx.getUser(senduser).getPrefix());
			while(i < size)
			{
				sendplayer = mcBasic.getInstance().getServer().getPlayer(players.get(i).getName());
				if(sendplayer!=null)
				{
								
				Messages.channelmsg(sendplayer,ch,msg,senduser,sendprefix);
				i++;
				}
			}
		}
			
	}

	

}