/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Comment;
import BE.Fireman;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class Comment_Access extends DatabaseConnection{
    
    public Comment_Access() throws IOException
    {
        super();
    }
    
    /**
     * Gets all comments belonging to a given Timesheet
     * @param timeSheetId the ID of the timesheet
     * @return A list of comments belonging to the given Timesheet
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<Comment> getCommentsByTimeSheetId(int timeSheetId) throws SQLServerException, SQLException {
        Connection con = null;
        ArrayList<Comment> comments = new ArrayList();
        try {
            con = getConnection();

            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT FROM CommentTimeSheet "
                                                + "INNER JOIN Comment ON CommentTimeSheet.commentId = Comment.id"
                                                + "INNER JOIN Fireman ON CommentTimeSheet.firemanId = Fireman.employeeId"
                                                + "WHERE timeSheetId = "+ timeSheetId +";");
            while(rs.next())
            {
                //Fireman
                int userId = rs.getInt("employeeId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                boolean teamleader = rs.getBoolean("teamleader");
                boolean driver = rs.getBoolean("driver");
                
                Fireman fireman = new Fireman(userId, firstName, lastName, teamleader, driver);
                
                //Comment
                int cId = rs.getInt("commentId");
                String comment = rs.getString("comment");
                
                Comment c = new Comment(cId, fireman, comment);
                comments.add(c);
            }
            

        } finally {
            if (con != null) {
                con.close();
            }
        }
        return comments;
    }
    
    /**
     * Adds a given Comment to a given timesheet in the database
     * @param c the comment you want to add
     * @param tsId the Id of the timesheet to which you want to add the comment
     */
    public void addCommentToTimesheet(Comment c, int tsId) throws SQLServerException, SQLException{
        Connection con = null;
        try
        {
            con = getConnection();
            Statement query = con.createStatement();
            int id = query.executeUpdate("Insert into Comment Values ( "
                            + c.getComment()+ ") ",Statement.RETURN_GENERATED_KEYS);
            c.setId(id);
            
            query.executeUpdate("Insert into TimeSheetComment Values ( "
                            + tsId+ ", "
                            + c.getFireman().getID()+ ", "
                            + c.getId()+  ") ");

        }
        finally
        {
            if(con != null)
            {
                con.close();
            }
        }
    }
}
