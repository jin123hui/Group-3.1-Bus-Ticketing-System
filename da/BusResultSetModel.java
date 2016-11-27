package da;

import javax.swing.table.AbstractTableModel;
import java.sql.*;

import javax.swing.JOptionPane;

public class BusResultSetModel extends AbstractTableModel {

    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private boolean connectedToDatabase = false;
    private String url = "jdbc:derby://localhost:1527/CJHDB";
    private String username = "cjh";
    private String password = "cjh";
    private String tableName = "BUSTYPE";

    public BusResultSetModel() {

        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            connectedToDatabase = true;
            
            getAllRecords();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Class getColumnClass(int column) throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            String className = metaData.getColumnClassName(column + 1);
            return Class.forName(className);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Object.class;
    }

    @Override
    public int getColumnCount() {
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            return metaData.getColumnCount();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String getColumnName(int column) throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            return metaData.getColumnName(column + 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    @Override
    public int getRowCount() {
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        return numberOfRows;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
            throws IllegalStateException {
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        try {
            rs.absolute(rowIndex + 1);
            return rs.getObject(columnIndex + 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void getAllRecords() {
        try {
            String query = "Select * from " + tableName;
            rs = stmt.executeQuery(query);
            metaData = rs.getMetaData();

            rs.last();
            numberOfRows = rs.getRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setQuery(String query)
            throws IllegalStateException, SQLException {
        if (!connectedToDatabase) {
            throw new IllegalStateException("Not Connected to Database");
        }

        rs = stmt.executeQuery(query);
        metaData = rs.getMetaData();

        // determine number of rows in ResultSet
        rs.last();                      // move to last row
        numberOfRows = rs.getRow();     // get row number
        // notify JTable that model has changed
        fireTableStructureChanged();
    }
    
    
    public void showAllRecord() throws SQLException {
        String displayrecord = "Select * from member ";
        rs = stmt.executeQuery(displayrecord);
        metaData = rs.getMetaData();
                rs.last();
                numberOfRows = rs.getRow();
                // notify JTable that model has changed
                fireTableStructureChanged();
    }
   
    public void disconnectFromDatabase() {
        if (connectedToDatabase) {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                connectedToDatabase = false;
            }
        }
    }
}
