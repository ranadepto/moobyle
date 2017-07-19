package Mail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import CommonClasses.Database;
import CommonClasses.Email;

public class MailModel
{
	public int addNewContactForm(MailDAO e)
	{
		int status = 0;
		try
		{
			Connection con = new Database().getConnection();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO `contact_form` (`Name`, `Email`, `Telephone`, `Message`) VALUES (?,?,?,?)");
			ps.setString(1, e.getName());
			ps.setString(2, e.getEmail());
			ps.setString(3, e.getTelephone());
			ps.setString(4, e.getMessage());

			status = ps.executeUpdate();

			con.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return status;
	}

	public String addNewSubscription(String email, HttpServletRequest request)
	{
		int status = 0;
		try
		{
			Connection con = new Database().getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO `subscription` (`Email`) VALUES (?)");
			ps.setString(1, email);

			status = ps.executeUpdate();

			con.close();
		}
		catch (Exception ex)
		{
			if (ex.getMessage().contains("Duplicate"))
			{
				return handleDuplicateState(email, request);
			}
		}

		if (status > 0)
		{
			new Email().send(email, "Moobyle Subscription",
					"We are glad about your interest on us. Thanks for subscribing us."
							+ "\nBy following this link you can unsuscribe again: "+request.getRequestURL()+"?action=unsubscribe&Email="
							+ email + "\n\n\nThanks & Regards,\nThe Moobyle Team");
			return "We are glad about your interest on us. Thanks for subscribing us.";
		}
		return "Failed to subscribe. Please try again.";
	}

	private String handleDuplicateState(String email, HttpServletRequest request)
	{
		try
		{
			Connection con = new Database().getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT Status FROM `subscription` WHERE `Email`=?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			int status = 0;
			if (rs.next())
			{
				status=rs.getInt(1);
			}
			con.close();
			
			if (status == 1)
			{
				return "The email address " + email
						+ " is already in our subscription list.\\nThanks for your interest on us.";
			}
			else if (status == 0)
			{
				try
				{
					con = new Database().getConnection();
					ps = con.prepareStatement("UPDATE `subscription` SET Status=1 WHERE `Email`=?");
					ps.setString(1, email);

					status = ps.executeUpdate();

					con.close();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}

				if (status > 0)
				{
					new Email().send(email, "Moobyle Subscription",
							"We are glad that you are back. Thanks for subscribing us again."
									+ "\nBy following this link you can unsuscribe again: "+request.getRequestURL()+"?action=unsubscribe&Email="
									+ email + "\n\n\nThanks & Regards,\nThe Moobyle Team");
					return "We are glad that you are back.\\nThanks for subscribing us again.";
				}
				return "Failed to subscribe. Please try again.";

			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return "Failed to subscribe. Please try again.";
		}
		return "Failed to subscribe. Please try again.";
	}

	public String unsubscribe(String email)
	{
		int status = 0;
		try
		{
			Connection con = new Database().getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE `subscription` SET Status=0 WHERE `Email`=?");
			ps.setString(1, email);

			status = ps.executeUpdate();

			con.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		if (status > 0)
		{
			return "We are sorry that you are leaving us.\\nHope that you will join again.";
		}
		return "Failed to unsubscribe. Please try again.";
	}

}
