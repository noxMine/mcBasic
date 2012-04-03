package me.noxMine.mcBasic;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {

	public static void restricted(Player player)
	{
		 player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Du besitzt keine Berechtigung für diesen Befehl!");
	}
	
	public static void wronguse(Player player)
	{
		player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.DARK_RED + "Falsche Verwendung des Befehls!");
	}
	
	public static void firstwelcome(Player player)
	{
		player.sendMessage(ChatColor.DARK_AQUA+"Herzlich Willkommen auf Mc-Craft!");
		player.sendMessage(ChatColor.GRAY + "Du bist zum ersten Mal hier, also schau dich doch erstmal um!  Wenn du Baurechte erhalten willst, lies die Regeln auf:  ");
		player.sendMessage(ChatColor.DARK_PURPLE+"www.mc-craft.eu");
		player.sendMessage(ChatColor.AQUA + "Viel Spass auf unserem Server!");
	}

	public static void joinmessage(Player player) 
	{
		String world = player.getLocation().getWorld().getName();
		
		player.sendMessage(ChatColor.DARK_AQUA+"Willkommen zurück!");
		player.sendMessage(ChatColor.GRAY + "Du befindest dich in " + ChatColor.GOLD + world + ChatColor.GRAY + "!");
		player.sendMessage(ChatColor.AQUA + "Viel Spass auf Mc-Craft!");
		
	}
	
	public static void help(Player player, String[] args) 
	{
		int seitennr;
		if(args.length==0)
		{
		seitennr=1;
		player.sendMessage(ChatColor.BLUE + "--Hilfe--Plugin--Seite: "+ChatColor.RED + seitennr + ChatColor.BLUE + "--");
		player.sendMessage(ChatColor.AQUA + "Grundbefehle:");
		player.sendMessage(ChatColor.AQUA + "/spawn - Teleportiert dich direkt in die Stadtmitte");
		player.sendMessage(ChatColor.AQUA + "/msg [S] [T] - Schreibt eine private Nachricht [T] an [Spieler]");
		player.sendMessage(ChatColor.AQUA + "/tell - Gleiche Funktion wie /msg");
		player.sendMessage(ChatColor.AQUA + "/pe new [Grund] - Erstellt ein Ticket für Admins");
		player.sendMessage(ChatColor.AQUA + "[Grund] z.B. Griefing und mach es an der Stelle auf wo es ist.");
		player.sendMessage(ChatColor.RED + "Gib /hilfe "+(seitennr+1)+" ein um zur "+(seitennr+1)+". Seite zu gelangen.");
		}
		else if (args.length == 1) {
			// TSET DER ARGUMENTS				
				int page = 0;
				boolean isint=true;
				try{
				page = Integer.valueOf(args[0]);
				}
				catch(NumberFormatException n)
				{
					System.out.println(n.toString());
					player.sendMessage(ChatColor.RED + "Falsche Verwendung! Richtig: /hilfe <seitenzahl>");
					isint=false;
				}
				if(isint){
				switch(page) {
				case 1:
					seitennr=1;
					player.sendMessage(ChatColor.BLUE + "--Hilfe--Plugin--Seite: "+ChatColor.RED + seitennr + ChatColor.BLUE + "--");
					player.sendMessage(ChatColor.AQUA + "Grundbefehle:");
					player.sendMessage(ChatColor.AQUA + "/spawn - Teleportiert dich direkt in die Stadtmitte");
					player.sendMessage(ChatColor.AQUA + "/msg [S] [T] - Schreibt eine private Nachricht [T] an [Spieler]");
					player.sendMessage(ChatColor.AQUA + "/tell - Gleiche Funktion wie /msg");
					player.sendMessage(ChatColor.AQUA + "/pe new [Grund] - Erstellt ein Ticket für Admins");
					player.sendMessage(ChatColor.AQUA + "[Grund] z.B. Griefing und mach es an der Stelle auf wo es ist.");
					player.sendMessage(ChatColor.RED + "Gib /hilfe "+(seitennr+1)+" ein um zur "+(seitennr+1)+". Seite zu gelangen.");
					break;
				case 2:
					seitennr=2;
					player.sendMessage(ChatColor.BLUE + "--Hilfe--Plugin--Seite: "+ChatColor.RED + seitennr + ChatColor.BLUE + "--");
					player.sendMessage(ChatColor.AQUA + "Home - Plugin");
					player.sendMessage(ChatColor.AQUA + "/sethome - Erstellt am aktuellen Punkt ein Homepunkt");
					player.sendMessage(ChatColor.AQUA + "/sethome [N] - Setzt am aktuellen Punkt  ein Homepunkt mit Namen[N]"); 						
					player.sendMessage(ChatColor.AQUA + "/home - Teleportiert dich zum Home Punkt");
					player.sendMessage(ChatColor.AQUA + "/home [N] - Teleport zum Homepunkt mit [Namen]");
					player.sendMessage(ChatColor.AQUA + "/invitehome [S] [N] - Lädt Spieler [P] zu deinem Home [N] ein.");
					player.sendMessage(ChatColor.AQUA + "/uninvite [S] - Lädt den Spieler [P] wieder aus");
					player.sendMessage(ChatColor.AQUA + "/deletehome - Löscht deinen Home Punkt");
					player.sendMessage(ChatColor.AQUA + "/deletehome [N] - Löscht Homepunkt mit Namen[N]");
					player.sendMessage(ChatColor.AQUA + "/listhome - Zeigt alle deine Homes an ");
					player.sendMessage(ChatColor.RED + "Gib /hilfe "+(seitennr+1)+" ein um zur "+(seitennr+1)+". Seite zu gelangen");
					break;
				case 3:
					seitennr=3;
					player.sendMessage(ChatColor.BLUE + "--Hilfe--Plugin--Seite: "+ChatColor.RED + seitennr + ChatColor.BLUE + "--");
					player.sendMessage(ChatColor.AQUA + "Protection (LWC) - Plugin");
					player.sendMessage(ChatColor.AQUA + "/cprivate - Verschließt Truhen und Türen");
					player.sendMessage(ChatColor.AQUA + "/cprivate [S1] [S2] - Verschließt für Spieler1[S1] und für Spieler2[S2]");
					player.sendMessage(ChatColor.AQUA + "/cpassword [PW] - Verschließt Truhen und Türen mit Password [PW]");
					player.sendMessage(ChatColor.AQUA + "/cunlock [PW] - Schließt verschlossenes mit Passwort[PW] auf.");
					player.sendMessage(ChatColor.AQUA + "/cremove - Entfernt den Schutz von Türen und Truhen");
					player.sendMessage(ChatColor.RED + "Gib /hilfe "+(seitennr+1)+" ein um zur "+(seitennr+1)+". Seite zu gelangen");
					break;
				case 4:
					seitennr=4;
					player.sendMessage(ChatColor.BLUE + "--Hilfe--Plugin--Seite: "+ChatColor.RED + seitennr + ChatColor.BLUE + "--");
					player.sendMessage(ChatColor.AQUA + "Wirtschaftsplugin - iConomy");
					player.sendMessage(ChatColor.AQUA + "/money - Zeigt deinen Kontostand an");
					player.sendMessage(ChatColor.AQUA + "/money pay [S] [G] - Überweist Spieler[S] Betrag [G]");
					player.sendMessage(ChatColor.AQUA + "Chest-Shop Du kannst ein Schild aufstellen mit:");
					player.sendMessage(ChatColor.AQUA + "1. Zeile :    [NamedesVerkäufers]");
					player.sendMessage(ChatColor.AQUA + "2. Zeile :  [Anzahl z.B. 64 (stack)]");
					player.sendMessage(ChatColor.AQUA + "3. Zeile :  B 100:S 100 (Kaufen für 100, Verkaufen für 100)");
					player.sendMessage(ChatColor.AQUA + "4. Zeile :  [NamedeszuverkaufendenGegenstandes]");						
					player.sendMessage(ChatColor.RED + "Gib /hilfe "+(seitennr+1)+" ein um zur "+(seitennr+1)+". Seite zu gelangen");
					break;
				case 5:
					seitennr=5;
					player.sendMessage(ChatColor.BLUE + "--Hilfe--Plugin--Seite: "+ChatColor.RED + seitennr + ChatColor.BLUE + "--");
					player.sendMessage(ChatColor.AQUA + "McWarns/McHilfe - Plugin");
					player.sendMessage(ChatColor.AQUA + "/warns - Zeigt deine Verwarnungen an");
					player.sendMessage(ChatColor.AQUA + "Du wirst nicht einfach so verwarnt. Dafür gibt es Gründe.");
					player.sendMessage(ChatColor.AQUA + "/accept - Damit aktzeptierst du deine Verwarnung");	
					player.sendMessage(ChatColor.AQUA + "/hilfe [Nr] - Geht in der Hilfe auf die Seitennummer [NR]");
					player.sendMessage(ChatColor.RED + "Ende der Hilfe Funktion. Fehlt was? Dann berichte im Forum.");
					player.sendMessage(ChatColor.RED + "http://mc-craft.eu");
					break;
				default:
					player.sendMessage(ChatColor.RED + "Die Seite existiert nicht!");
					
				
		}
				}
		
	}
		else Messages.wronguse(player);

	
}

	public static void setspawnmsg(Player player, String world) 
	{
		player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.GREEN + "Der Spawnpunkt für " + world + " wurde gesetzt");
	}

	public static void setfirstspawnmsg(Player player) 
	{
		player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.GREEN + "Der FirstSpawn wurde gesetzt!");
		
	}

	public static void setjailmsg(Player player) 
	{
		player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.GREEN + "Die Jail Location wurde gesetzt!");
		
	}

	public static void setunjailmsg(Player player) 
	{
		 player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.GREEN + "Die Unjail Location wurde gesetzt!");
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------- Messages of mcBasicGeneral----------------------------------------------------------------------
	//----------------------------------------------------------------------------------------------------------------------------------------------

	public static void generalTime(Player player, String world, int timevalue) 
	{
		player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.YELLOW + "Die Zeit in "+world+" wurde auf "+timevalue+" Uhr gestellt.");
		mcBasic.getInstance().getServer().broadcastMessage(ChatColor.YELLOW + "Die Zeit in "+world+" wurde auf "+timevalue+" Uhr gestellt.");
		
	}

	public static void generalWeather(Player player, String world, boolean active) 
	{
		if(active)
		{
			player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.YELLOW + "Der Sturm in "+world+" wurde aktiviert!");
			mcBasic.getInstance().getServer().broadcastMessage(ChatColor.YELLOW + "Ein Sturm in "+world+" wurde beschworen!");
		}
		else
		{
			player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.YELLOW + "Der Sturm in "+world+" wurde deaktiviert!");
			mcBasic.getInstance().getServer().broadcastMessage(ChatColor.YELLOW + "Der Sturm in "+world+" wurde gestoppt!");
		}
		
	}

	public static void generalItem(Player player, String name, boolean isid, int amount)
	{
		if(amount==1)
		{			
		if(isid) player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.YELLOW + "Das Item mit der ID "+name+" wurde zu deinem Inventar hinzugefügt!");
		else player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.YELLOW + "Das Item "+name+" wurde zu deinem Inventar hinzugefügt!");
		}
		else
		{
			if(isid) player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.YELLOW + amount+ " Items mit der ID "+name+" wurden zu deinem Inventar hinzugefügt!");
			else player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.YELLOW + amount+ " "+name+" wurden zu deinem Inventar hinzugefügt!");
		}
		
	}
	
	public static void generalWrongItem(Player player)
	{
		player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.RED + "Item wurde nicht gefunden!");
	}

	public static void generalClear(Player player) 
	{
		player.sendMessage(ChatColor.GRAY+"[mcBasic] "+ChatColor.YELLOW +"Inventar wurde gelöscht!");
	}

	public static void generalCart(Player player) 
	{
		player.sendMessage(ChatColor.YELLOW+"Es wurde ein Minecart zu deinem Inventar hinzugefügt!");
	}

	public static void generalBroadcast(String broadcastmsg) {
		mcBasic.getInstance().getServer().broadcastMessage(ChatColor.DARK_RED + "[Ankündigung]" + ChatColor.RED+ broadcastmsg);
		
	}

	public static void generalGamemode(Player player, boolean survival) 
	{
		if(survival) player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.YELLOW + "Gamemode wurde auf Survival gesetzt!");
		else player.sendMessage(ChatColor.GRAY + "[mcBasic] " + ChatColor.YELLOW + "Gamemode wurde auf Creative gesetzt!");
		
	}

	public static void changechannelmsg(Player player, String ch) 
	{
		if(ch.equalsIgnoreCase("h")) player.sendMessage(ChatColor.DARK_AQUA + "Du hast den"+ChatColor.DARK_PURPLE+" Hilfe-Channel "+ChatColor.DARK_AQUA +"betreten!");
		else if(ch.equalsIgnoreCase("g")) player.sendMessage(ChatColor.DARK_AQUA + "Du hast den"+ChatColor.DARK_PURPLE+" Global-Channel "+ChatColor.DARK_AQUA +"betreten!");
		else if(ch.equalsIgnoreCase("t")) player.sendMessage(ChatColor.DARK_AQUA + "Du hast den"+ChatColor.DARK_PURPLE+" Handels-Channel "+ChatColor.DARK_AQUA +"betreten!");
		else if(ch.equals("0"))player.sendMessage(ChatColor.DARK_AQUA + "Du hast den Channel verlassen!");
		else player.sendMessage(ChatColor.DARK_AQUA + "Du hast den Channel "+ChatColor.DARK_PURPLE+ch+ChatColor.DARK_AQUA +" betreten!");
		
	}

	public static void channelmsg(Player player, String ch, String msg, String senduser, String sendprefix) 
	{
		if(ch.equalsIgnoreCase("h")) player.sendMessage(ChatColor.LIGHT_PURPLE + "[H]"+ sendprefix + ChatColor.WHITE+" "+ senduser + ": "+ msg);
		else if(ch.equalsIgnoreCase("g")) player.sendMessage(ChatColor.DARK_GRAY +"[G]"+ sendprefix + ChatColor.WHITE+" "+ senduser + ": "+ msg);
		else if(ch.equalsIgnoreCase("t")) player.sendMessage(ChatColor.GREEN + "[T]"+ sendprefix + ChatColor.WHITE+" "+ senduser + ": "+ msg);
		else if(ch.equals("0")) player.sendMessage(sendprefix + ChatColor.WHITE+" "+ senduser + ": "+ msg);
		else player.sendMessage(ChatColor.GRAY + "["+ch+"]"+ sendprefix + ChatColor.WHITE+" "+ senduser+" "+  ": " +msg);
		
		
	}
	
	public static String colormsg(String message)
	{
		message = message.replace("&0", ChatColor.BLACK.toString());
		message = message.replace("&1", ChatColor.DARK_BLUE.toString());
	    message = message.replace("&2", ChatColor.DARK_GREEN.toString());
	    message = message.replace("&3", ChatColor.DARK_AQUA.toString());
	    message = message.replace("&4", ChatColor.DARK_RED.toString());
	    message = message.replace("&5", ChatColor.DARK_PURPLE.toString());
	    message = message.replace("&6", ChatColor.GOLD.toString());
	    message = message.replace("&7", ChatColor.GRAY.toString());
	    message = message.replace("&8", ChatColor.DARK_GRAY.toString());
	    message = message.replace("&9", ChatColor.BLUE.toString());
	    message = message.replace("&a", ChatColor.GREEN.toString());
	    message = message.replace("&b", ChatColor.AQUA.toString());
	    message = message.replace("&c", ChatColor.RED.toString());
	    message = message.replace("&d", ChatColor.LIGHT_PURPLE.toString());
	    message = message.replace("&e", ChatColor.YELLOW.toString());
	    return message;
	}

	public static void joinchannelmsg(Player sendplayer, String senduser,
			String ch) 
	{
		sendplayer.sendMessage(ChatColor.DARK_AQUA + senduser + " hat den Channel betreten.");
	}

	public static void leavechannelmsg(Player sendplayer, String senduser,
			String oldch) 
	{
		sendplayer.sendMessage(ChatColor.DARK_AQUA + senduser + " hat den Channel verlassen.");
	}
	
}
