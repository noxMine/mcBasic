package me.noxMine.mcBasic.db;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotNull;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import me.noxMine.mcBasic.mcBasic;

import org.bukkit.Location;


@SuppressWarnings("serial")
@Entity()
@Table(name = "mB_warps")
public class mB_warps implements Serializable 
{
	@Id
    private int id;

    
    @NotNull
    @Length(max = 25)
    private String name;
    
    @NotNull
	private boolean restriction;
    
    @NotNull
	private String Worldname;
	
    @NotNull
	private double x;
	
    @NotNull
	private double y;
	
    @NotNull
	private double z;
	
    @NotNull
	private float Yaw;
	
    @NotNull
	private float Pitch;
	
	  
    public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getRestriction()
	{
		return restriction;
	}
	
	public void setRestriction(boolean restriction)
	{
		this.restriction=restriction;
	}
	
	public boolean isRestriction()
	{
		return restriction;
	}
	
	public String getWorldname()
	{
		return Worldname;
	}
	
	public void setWorldname(String worldname)
	{
		this.Worldname=worldname;
	}
	
	public double getX()
	{
		return x;
	}
	
	public void setX(double x)
	{
		this.x=x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public void setY(double y)
	{
		this.y=y;
	}
	
	public double getZ()
	{
		return z;
	}
	
	public void setZ(double z)
	{
		this.z=z;
	}
	
	public float getYaw()
	{
		return Yaw;
	}
	
	public void setYaw(float Yaw)
	{
		this.Yaw=Yaw;
	}
	
	public float getPitch()
	{
		return Pitch;
	}
	
	public void setPitch(float pitch)
	{
		this.Pitch=pitch;
	}
	
	public Location getLocation()
	{
		Location location = new Location(mcBasic.getInstance().getServer().getWorld(Worldname),x,y,z,Yaw,Pitch);
		return location;
	}
	
	public void setLocation(Location location)
	{
		this.Worldname=location.getWorld().getName();
		this.x=location.getX();
		this.y=location.getY();
		this.z=location.getZ();
		this.Yaw=location.getYaw();
		this.Pitch=location.getPitch();
		
	}
}
