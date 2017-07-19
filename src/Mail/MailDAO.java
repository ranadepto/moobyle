package Mail;

import java.sql.Timestamp;

public class MailDAO
{
	String Name, Email, Telephone, message;
	Timestamp modificationDate;
	public MailDAO(String name, String email, String telephone, String message, Timestamp modificationDate)
	{
		this.Name = name;
		this.Email = email;
		this.Telephone = telephone;
		this.message = message;
		this.modificationDate = modificationDate;
	}
	public String getName()
	{
		return Name;
	}
	public String getEmail()
	{
		return Email;
	}
	public String getTelephone()
	{
		return Telephone;
	}
	public String getMessage()
	{
		return message;
	}
	public Timestamp getModificationDate()
	{
		return modificationDate;
	}

}
