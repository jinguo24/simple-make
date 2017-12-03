package com.lenovo.simple.dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.lenovo.simple.configure.DataSource;
import com.lenovo.simple.configure.table.DbColumn;
import com.lenovo.simple.configure.table.Table;

public abstract class AbstractDataSourceInvoker implements DataSourceInvoker{

	private static final Log log = LogFactory.getLog(AbstractDataSourceInvoker.class);
	@Override
	public List<DbColumn> queryTable(DataSource datasource,Table table) {
		String sql = generanteSql(table);
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		 try {
			Class.forName(getDriverClass()) ;
			con = DriverManager.getConnection(datasource.getUrl()
					,datasource.getName(),datasource.getPassword());
			st = con.createStatement();
			rs = st.executeQuery(sql);
			List<DbColumn> clist = new ArrayList<DbColumn>();
			while(rs.next()){//判断是否还有下一行  
				DbColumn dc = getColumn(rs);
				if ( null != dc) {
					clist.add(dc);
				}
	        }  
			return clist;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			log.error(e);
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e);
			return null;
		}
		finally {
			if ( null != rs) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
			
			if ( null != st) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if ( null != con) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		 }
	}
	
	protected abstract String generanteSql(Table entity);
	
	protected abstract String getDriverClass();
	
	protected abstract DbColumn getColumn(ResultSet rs);
}
