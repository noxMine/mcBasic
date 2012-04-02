package me.noxMine.mcBasic.db;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotNull;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity()
@Table(name = "mB_warnings")
public class mB_warnings implements Serializable 
{
	@Id
    private int id;

    
    @NotNull
    @Length(max = 25)
    private String name;
    
	private boolean status;
	
	@NotNull
	@Length(max = 200)
	private String reason;
    
    
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
	
	public String getReason()
	{
		return reason;
	}
	
	public void setReason(String reason)
	{
		this.reason=reason;
	}
}
