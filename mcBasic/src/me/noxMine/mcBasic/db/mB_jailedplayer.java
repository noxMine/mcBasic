package me.noxMine.mcBasic.db;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotNull;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity()
@Table(name = "mB_jailedplayer")
public class mB_jailedplayer implements Serializable 
{
	
    @Id
    private int id;

    
    @NotNull
    @Length(max = 25)
    private String name;
    
    @NotNull
    @Length(max = 25)
    private String PlayerGroup;
	
	private boolean status;
	
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
	
	public String[] getPlayerGroups()
	{
		String[] rgroup = new String[1];
		rgroup[0]=PlayerGroup;
		return rgroup;
	}
	
	public void setPlayerGroups(String[] playergroup)
	{
		this.PlayerGroup=playergroup[0];
	}
	
	public String getPlayerGroup()
	{
		return PlayerGroup;
	}
	
	public void setPlayerGroup(String PlayerGroup)
	{
		this.PlayerGroup=PlayerGroup;
	}
	
	
	
	public boolean getStatus()
	{
		return status;
	}
	
	public void setStatus(boolean status)
	{
		this.status=status;
	}
	
	public boolean isStatus()
	{
		return status;
	}
}
