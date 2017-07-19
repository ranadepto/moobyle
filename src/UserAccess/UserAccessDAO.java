package UserAccess;

import java.sql.Timestamp;

public class UserAccessDAO
{
	String id, name, email, password;
	int level;
	String modified_by;
	Timestamp modification_date;
	public UserAccessDAO(String id, String name, String email, String password, int level, String modified_by,
			Timestamp modification_date)
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.level = level;
		this.modified_by = modified_by;
		this.modification_date = modification_date;
	}
	public String getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public String getEmail()
	{
		return email;
	}
	public String getPassword()
	{
		return password;
	}
	public int getLevel()
	{
		return level;
	}
	public String getModified_by()
	{
		return modified_by;
	}
	public Timestamp getModification_date()
	{
		return modification_date;
	}
}
