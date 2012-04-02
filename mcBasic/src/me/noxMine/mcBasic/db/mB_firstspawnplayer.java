package me.noxMine.mcBasic.db;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotNull;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity()
@Table(name = "mB_firstspawnplayer")
public class mB_firstspawnplayer implements Serializable 
{
	@Id
    private int id;

    
    @NotNull
    @Length(max = 25)
    private String name;

	
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
}
