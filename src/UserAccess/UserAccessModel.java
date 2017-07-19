package UserAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import CommonClasses.Database;

public class UserAccessModel
{
	public void addVisitingLog(String ipAddress)
	{
		try
		{
			Connection con = new Database().getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO `visiting_log` (`ip`) VALUES (?)");
			ps.setString(1, ipAddress);

			ps.executeUpdate();

			con.close();
		}
		catch (Exception ex)
		{
			if (ex.getMessage().contains("Duplicate"))
			{
				try
				{
					Connection con = new Database().getConnection();
					PreparedStatement ps = con.prepareStatement("SELECT Counter FROM `visiting_log` WHERE `IP`=?");
					ps.setString(1, ipAddress);

					ResultSet rs = ps.executeQuery();

					int counter = 1;
					if (rs.next())
					{
						counter = rs.getInt(1);
						counter++;
					}

					ps = con.prepareStatement("UPDATE `visiting_log` SET Counter=? WHERE `IP`=?");
					ps.setInt(1, counter);
					ps.setString(2, ipAddress);

					ps.executeUpdate();

					con.close();
				}
				catch (Exception ex1)
				{
					ex.printStackTrace();
				}
			}
		}
	}
	
	public UserAccessDAO getUserAccessInfo(String email, String password)
	{
		Connection con=new Database().getConnection();
		UserAccessDAO userAccessInfo=null;
		
		try
		{
			PreparedStatement ps = con.prepareStatement("SELECT `id`, `name`, `level`, `modified_by`, `modification_date` FROM user WHERE status=1 and email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				userAccessInfo=new UserAccessDAO(rs.getString("id"), rs.getString("name"), email, password, rs.getInt("level"), rs.getString("modified_by"), rs.getTimestamp("modification_date"));
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return userAccessInfo;
	}

	public int addNewUser(String name, String email, String password)
	{
		int status = 0;
		try
		{
			Connection con = new Database().getConnection();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO `user` (`name`, `email`, `password`, `modified_by`) VALUES (?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, email);

			status = ps.executeUpdate();

			con.close();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			if (ex.getMessage().contains("Duplicate"))
			{
				if(handleDuplicateUser(name, email, password))
					return 1;
			}
			return 0;
		}

		return status;
	}

	private Boolean handleDuplicateUser(String name, String email, String password)
	{
		try
		{
			Connection con = new Database().getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT status FROM `user` WHERE `email`=?");
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			int status = -1;
			if (rs.next())
			{
				status=rs.getInt(1);
			}
			con.close();
			
			if (status == 0)
			{
				try
				{
					con = new Database().getConnection();
					ps = con.prepareStatement("UPDATE `user` SET status=1, name=?, password=?, modified_by=? WHERE `email`=?");
					ps.setString(1, name);
					ps.setString(2, password);
					ps.setString(3, email);
					ps.setString(4, email);

					status = ps.executeUpdate();

					con.close();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}

				if (status > 0)
				{
					return true;
				}
				return false;

			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	
	
}
