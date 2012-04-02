package me.noxMine.mcBasic;


import java.util.ArrayList;
import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import me.noxMine.mcBasic.db.mB_channelplayers;
import me.noxMine.mcBasic.db.mB_firstspawnplayer;
import me.noxMine.mcBasic.db.mB_jailedplayer;
import me.noxMine.mcBasic.db.mB_mutedplayer;
import me.noxMine.mcBasic.db.mB_warnings;
import me.noxMine.mcBasic.db.mB_warps;
import me.noxMine.mcBasic.Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class mcBasic extends JavaPlugin{
	
	
	private static mcBasic instance;
    
	
    public static mcBasic getInstance() {
    	 if (instance == null) {
             instance = new mcBasic();
         }
         return instance;
		}
	
    
    public final mcBasicPlayerListener Listener = new mcBasicPlayerListener(this);
    
    
	@Override
    public void onDisable()
	{
		System.out.println("[mcBasic] disabled");
	}
	
	
	@Override
	public void onEnable()
	{
		if (instance == null) {
            instance = this;
        }
		
		System.out.println("[mcBasic] v" + this.getDescription().getVersion() + " enabled" );
		
		setupDatabase();
		setupConfig();
		checkImport();
		this.getServer().getPluginManager().registerEvents(Listener,this);
	}
	
	
	private void setupDatabase() 
	{
	   	try {
    		int found = getDatabase().find(mB_firstspawnplayer.class).findRowCount();
    		int found2 = getDatabase().find(mB_warps.class).findRowCount();
    		int found3 = getDatabase().find(mB_warnings.class).findRowCount();
    		int found4 = getDatabase().find(mB_mutedplayer.class).findRowCount();
    		int found5 = getDatabase().find(mB_jailedplayer.class).findRowCount();
    		int found6 = getDatabase().find(mB_channelplayers.class).findRowCount();
    				
    		if (found == 1 && found2 == 1 && found3 == 1 && found4 == 1 && found5 == 1 && found6 == 1) {
    			System.out.println("All Databases are ready!");
    		}
    		} catch (Exception ex) {
    			System.out.println("Installing database for " + getDescription().getName() + " due to first time usage!");
    			installDDL();
    		}
    }
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		
		if (sender instanceof Player) Commands.onCommandfromPlayer(sender, cmd, label, args);
		else Commands.onCommandfromConsole(sender, cmd, label, args);
		
		return true;
	}
	

	public void setupConfig()
	{
		boolean success =new File("plugins/mcBasic/").mkdir();
		if(success) System.out.println("[mcBasic] Directory 'plugins/mcBasic/' created");
				
		File configfile = new File("plugins/mcBasic/config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(configfile);
		if(!configfile.exists())
		{
			String world = this.getServer().getWorlds().get(0).getName();
			String firstspawndir = "Firstspawn.Location";
			String firstspawn = defaultLocation();
			String spawndir = new String("Spawn.Spawnlocation_"+world);
			String spawn = defaultLocation();
			String jaildir = "Jail.Jaillocation";
			String jailloc = defaultLocation();
			String unjaildir = "Jail.Unjaillocation";
			String unjailloc = defaultLocation();
			String jailgroupdir = "Jail.Jailgroup";
			String jailgroup = "Prisoner";
			String passworddir = "Promote.Password";
			String password = "Password";
			String groupdir = "Promote.Promotegroup";
			String group = "Member";
			String importdir = "Import.ImportfromFile";
			String importfile = "false";
			config.set(firstspawndir, firstspawn);
			config.set(spawndir, spawn);
			config.set(jaildir,jailloc);
			config.set(unjaildir,unjailloc);
			config.set(jailgroupdir, jailgroup);
			config.set(passworddir, password);
			config.set(groupdir, group);
			config.set(importdir, importfile);
			try {
				config.save(configfile);
				System.out.println("[mcBasic] ConfigFile created.");
				}
				catch (IOException e) {
				System.out.println("[mcBasic] Can't create config file, see info below:");
				e.printStackTrace();
				}
				}
			
		}
		
	
	public static PermissionManager permission()
		{
			if(instance.getServer().getPluginManager().isPluginEnabled("PermissionsEx"))
			{
				return PermissionsEx.getPermissionManager();
			}
			else
			{
				Logger.getLogger("Minecraft").warning("PermissionsEx plugin not found.");
				return null;
			}
			
				
		}

	
	public String defaultLocation()
	{
		String world = this.getServer().getWorlds().get(0).getName();
		Location loc = this.getServer().getWorld(world).getSpawnLocation();
		String x = String.valueOf(loc.getX());
		String y = String.valueOf(loc.getY());
		String z = String.valueOf(loc.getZ());
		String yaw = String.valueOf(loc.getYaw());
		String pitch = String.valueOf(loc.getPitch());
		String Location = new String(world + "," + x +"," + y + "," + z + "," + yaw + "," + pitch);
		
		return Location;
		
	}
	
	
    @Override
    /**
     * Gets the database classes
     * @return A list of classes
     */
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(mB_firstspawnplayer.class);
        list.add(mB_jailedplayer.class);
        list.add(mB_mutedplayer.class);
        list.add(mB_warnings.class);
        list.add(mB_warps.class);
        list.add(mB_channelplayers.class);

        return list;
    }
    
    public void checkImport()
    {
    	File configfile = new File("plugins/mcBasic/config.yml");
		FileConfiguration config = YamlConfiguration.loadConfiguration(configfile);
		if(config.getBoolean("Import.ImportfromFile"))
		{
			System.out.println("[mcBasic] Preparing Import from File System");
			File warningfile = new File ("plugins/mcBasic/Warning/Warn/warnings.dat");
			File playerfile = new File ("plugins/mcBasic/Spawn/players.dat");
			boolean found=false;
			if(warningfile.exists())
			{
				found=true;
				System.out.println("[mcBasic] Found 'warnings.dat'");
				System.out.println("[mcBasic] Import 'warnings.dat' into Database");
				
				try
				{
				FileReader warnread = new FileReader(warningfile);
				BufferedReader warnbread = new BufferedReader(warnread);
				
				String warnfile = warnbread.readLine();
				int count=0;
				while(warnfile!=null)
				{
					String[] warndat = warnfile.split(",");
					mB_warnings warning = new mB_warnings();
					warning.setName(warndat[0]);
					warning.setStatus(Boolean.valueOf(warndat[2]));
					warning.setReason(warndat[3]);
					mcBasic.getInstance().getDatabase().save(warning);
					System.out.println("[mcBasic] Warning for " +warndat[0]+" imported to Database");
					
					count++;
					warnfile = warnbread.readLine();
					
				}
				System.out.println("[mcBasic] "+ String.valueOf(count) + " Warnings successfully added to Database");
				String importdir = "Import.ImportfromFile";
				String importfile = "false";
				config.set(importdir, importfile);
				config.save(configfile);
				System.out.println("[mcBasic] Importmode disabled");
				warnbread.close();
				warnread.close();
				}
				catch (IOException ex)
				{
					System.out.println("[mcBasic] Error ocurring while import file, see info below:");
					System.out.println(ex.toString());
				}
				
			}
			if(playerfile.exists())
			{
				found=true;
				System.out.println("[mcBasic] Found 'players.dat'");
				System.out.println("[mcBasic] Import 'players.dat' into Database");
				
				try
				{
				FileReader playerread = new FileReader(playerfile);
				BufferedReader playerbread = new BufferedReader(playerread);
				
				String playerdat =playerbread.readLine();
				int count=0;
				while(playerdat!=null)
				{
					mB_firstspawnplayer playerd = new mB_firstspawnplayer();
					playerd.setName(playerdat);
					mcBasic.getInstance().getDatabase().save(playerd);
					System.out.println("[mcBasic] Player " + playerdat + " imported into Database");
					
					count++;
					playerdat = playerbread.readLine();
					
				}
				System.out.println("[mcBasic] "+ String.valueOf(count) + " Players successfully added to Database");
				String importdir = "Import.ImportfromFile";
				String importfile = "false";
				config.set(importdir, importfile);
				config.save(configfile);
				System.out.println("[mcBasic] Importmode disabled");
				
				}
				catch (IOException ex)
				{
					System.out.println("[mcBasic] Error ocurred while imported file, see info below:");
					System.out.println(ex.toString());
				}
			}
			if(!found) System.out.println("[mcBasic] No File for import found");
		}
	}

}
