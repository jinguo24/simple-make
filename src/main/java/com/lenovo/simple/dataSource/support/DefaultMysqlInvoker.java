package com.lenovo.simple.dataSource.support;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lenovo.simple.configure.table.DbColumn;
import com.lenovo.simple.configure.table.Table;
import com.lenovo.simple.dataSource.AbstractDataSourceInvoker;

public final class DefaultMysqlInvoker extends AbstractDataSourceInvoker {

	private static final Log log = LogFactory.getLog(DefaultMysqlInvoker.class);
	
	/**
	 * SELECT COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT FROM information_schema.columns
	 *	WHERE  table_name = 'bus_area' ; 
	 */
	@Override
	protected String generanteSql(Table table) {
		StringBuffer s = new StringBuffer();
		s.append("SELECT COLUMN_NAME as columnName,DATA_TYPE as dataType,COLUMN_COMMENT as columnComment,COLUMN_KEY columnKey, ")
			.append("EXTRA as extra FROM information_schema.columns WHERE  table_name = '")
			.append(table.getName()).append("';");
		return s.toString();
	}

	@Override
	protected String getDriverClass() {
		return "com.mysql.jdbc.Driver";
	}

	private static final String[] StringArrays = new String[]{"char","varchar","longtext","mediumtext","text","tinytext"};
	private static final String[] IntegerArrays = new String[]{"int","mediumint","smallint","tinyint","bigint"};
	//	private static final String[] IntegerArrays = new String[]{"int","mediumint"};
//	private static final String[] ShortArrays = new String[]{"smallint","tinyint"};
//	private static final String[] LongArrays = new String[]{"bigint"};
	private static final String[] FloatArrays = new String[]{"float"};
	private static final String[] DoubleArrays = new String[]{"double","numeric"};
	private static final String[] DecimalArrays = new String[]{"decimal"};
	private static final String[] DateArrays = new String[]{"date","datetime","time","timestamp","year"};
	private static final String[] BooleanArrays = new String[]{"boolean"};
	
	@Override
	public String getFieldType(String dbtype) {
		if (Arrays.asList(StringArrays).contains(dbtype)) {
			return "java.lang.String";
		}
		if (Arrays.asList(IntegerArrays).contains(dbtype)) {
			return "java.lang.Integer";
		}
//		if (Arrays.asList(ShortArrays).contains(dbtype)) {
//			return "java.lang.Short";
//		}
//		if (Arrays.asList(LongArrays).contains(dbtype)) {
//			return "java.lang.Long";
//		}
		if (Arrays.asList(FloatArrays).contains(dbtype)) {
			return "java.lang.Float";
		}
		if (Arrays.asList(DoubleArrays).contains(dbtype)) {
			return "java.lang.Double";
		}
		if (Arrays.asList(DecimalArrays).contains(dbtype)) {
			return "java.math.BigDecimal";
		}
		if (Arrays.asList(DateArrays).contains(dbtype)) {
			return "java.util.Date";
		}
		if (Arrays.asList(BooleanArrays).contains(dbtype)) {
			return "java.lang.Boolean";
		}
		return null;
	}

	private boolean isNumber(String dbtype) {
		if (Arrays.asList(StringArrays).contains(dbtype)) {
			return false;
		}
		if (Arrays.asList(IntegerArrays).contains(dbtype)) {
			return true;
		}
//		if (Arrays.asList(ShortArrays).contains(dbtype)) {
//			return true;
//		}
//		if (Arrays.asList(LongArrays).contains(dbtype)) {
//			return true;
//		}
		if (Arrays.asList(FloatArrays).contains(dbtype)) {
			return true;
		}
		if (Arrays.asList(DoubleArrays).contains(dbtype)) {
			return true;
		}
		if (Arrays.asList(DecimalArrays).contains(dbtype)) {
			return true;
		}
		if (Arrays.asList(DateArrays).contains(dbtype)) {
			return false;
		}
		if (Arrays.asList(BooleanArrays).contains(dbtype)) {
			return false;
		}
		return false;
	}

	@Override
	protected DbColumn getColumn(ResultSet rs) {
		try {
			String columnName = rs.getString("columnName");
			String dataType = rs.getString("dataType");
			String columnComment = rs.getString("columnComment");
			String columnKey = rs.getString("columnKey");
			String extra = rs.getString("extra");
			DbColumn dc = new DbColumn();
			dc.setColumnName(columnName);
			dc.setDataType(dataType);
			dc.setColumnComment(columnComment);
			dc.setNumber(isNumber(dataType));
			if ("PRI".equals(columnKey)) {
				dc.setPk(true);
			}else{
				dc.setPk(false);
			}
			if ("auto_increment".equals(extra)) {
				dc.setAutoIncreate(true);
			}else {
				dc.setAutoIncreate(false);
			}
			return dc;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
