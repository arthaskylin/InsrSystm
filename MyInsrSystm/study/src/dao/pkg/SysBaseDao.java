package dao.pkg;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SysBaseDao<T> {
	private Class<T> entityObjClass;
	Map<String, Object> paraMap = null;
	public static final String SQL_INSERT = "insert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_DELETE = "delete";
	protected JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	public SysBaseDao() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		//Type 是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
		entityObjClass = (Class<T>) type.getActualTypeArguments()[0];
		//GetActualTypeArguments()这个方法的返回值是一个Type的数组
		System.out.println(entityObjClass);
	}

	public void save(T entityObjObj) {
		String sql = this.makeSQl(SQL_INSERT);
		System.out.println(sql);
		Object[] value = this.setValueArgs(entityObjObj, SQL_INSERT);
		for (int i = 0; i < value.length; i++) {
			System.out.println("--------------------");
			System.out.println(value[i]);
			System.out.println("--------------------");
		}
		int[] objTypes = this.setArgsTypes(entityObjObj, SQL_INSERT);
		jdbcTemplate.update(sql, value, objTypes);
	}

	public void upate(T entityObjObj) {
		String sql = this.makeSQl(SQL_UPDATE);
		Object[] value = this.setValueArgs(entityObjObj, SQL_UPDATE);
		int[] valueType = this.setArgsTypes(entityObjObj, SQL_UPDATE);
		System.out.println(sql);
		jdbcTemplate.update(sql, value, valueType);
	}

	public void delete(T entityObjObj) {
		String sql = this.makeSQl(SQL_DELETE);
		Object[] obj = this.setValueArgs(entityObjObj, SQL_DELETE);
		int[] valueType = this.setArgsTypes(entityObjObj, SQL_DELETE);
		jdbcTemplate.update(sql, obj, valueType);
	}

	public List<T> findAll() {
		String sql = "SELECT * FROM " + entityObjClass.getSimpleName();
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityObjClass);
		return (List<T>) jdbcTemplate.query(sql, rowMapper);
	}

	public T findByKey() {

		StringBuffer sqlString = new StringBuffer();
		sqlString.append("SELECt * FROM  ").append(entityObjClass.getSimpleName()).append(" where ");
		for (Entry<String, Object> str : paraMap.entrySet()) {
			String key = str.getKey();
			Object value = str.getValue();
			sqlString.append(key).append("=").append(value).append(" and ");
		}
		sqlString.delete(sqlString.length() - 5, sqlString.length() - 1);
		String sql = sqlString.toString();
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityObjClass);
		if (jdbcTemplate.query(sql, rowMapper).size() > 0)
		{
			return jdbcTemplate.query(sql, rowMapper).get(0);
		}
		return null;
	}

	public String makeSQl(String processType) {
		// 拼sql语句
		String sql = null;
		if (SQL_INSERT.equals(processType)) {
			StringBuffer sqlTmp = makeSQL_INSERT();
			sql = sqlTmp.toString();
		} else if (SQL_UPDATE.equals(processType)) {
			StringBuffer sqlTmp = makeSQL_UPDATE();
			sql = sqlTmp.toString();
		} else if (SQL_DELETE.equals(processType)) {
			StringBuffer sqlTmp = makeSQL_DELETE();
			sql = sqlTmp.toString();
		}
		return sql;
	}

	public StringBuffer makeSQL_INSERT() {
		// UPDATE Cpprsn SET name = ?,idntfr = ?,birthDate = ?,age = ?,sex = ?,occ_code
		// = ? WHERE id=?
		StringBuffer sqlString = new StringBuffer();
		Field[] field = entityObjClass.getDeclaredFields();
		// getDeclaredFields:获取自己声明的各种字段，包括public，protected，private。
		sqlString.append("INSERT INTO ").append(entityObjClass.getSimpleName()).append(" (");
		// INSERT INTO Cpprsn (
		for (int i = 0; i < field.length; i++) {
			field[i].setAccessible(true);// 在用反射时访问私有变量
			sqlString.append(field[i].getName()).append(",");
		}
		// INSERT INTO Cpprsn (name,idntfr,birthDate,age,sex,occ_code,

		sqlString.deleteCharAt(sqlString.length() - 1);
		sqlString.append(" ) ").append("VALUES (");
		for (int i = 0; i < field.length; i++) {
			sqlString.append("?,");
		}
		sqlString.deleteCharAt(sqlString.length() - 1);
		sqlString.append(" ) ");
		return sqlString;
	}

	public StringBuffer makeSQL_UPDATE() {
		/*
		 * map中key为类中的 属性 value为该属性的值
		 */
		StringBuffer sqlString = new StringBuffer();
		Field[] field = entityObjClass.getDeclaredFields();
		sqlString.append("UPDATE ").append(entityObjClass.getSimpleName() + " SET ");

		for (int i = 0; i < field.length; i++) {
			field[i].setAccessible(true);
			if (paraMap.containsKey(field[i].getName())) {
				continue;
			}
			sqlString.append(field[i].getName()).append(" = ").append("?,");
		}
		sqlString.deleteCharAt(sqlString.length() - 1);
		sqlString.append(" WHERE ");
		for (Entry<String, Object> str : paraMap.entrySet()) {
			String key = str.getKey();
			// String value = str.getValue();
			sqlString.append(key).append("=? and ");
		}
		sqlString.delete(sqlString.length() - 5, sqlString.length() - 1);
		return sqlString;
	}

	public StringBuffer makeSQL_DELETE() {
		StringBuffer sqlString = new StringBuffer();
		sqlString.append("DELETE FROM ").append(entityObjClass.getSimpleName()).append(" WHERE ");
		for (Entry<String, Object> str : paraMap.entrySet()) {
			String key = str.getKey();
			// String value = str.getValue();
			sqlString.append(key).append("=? and ");
		}
		sqlString.delete(sqlString.length() - 5, sqlString.length() - 1);
		return sqlString;
	}

	private Object[] setValueArgs(T entityObjObj, String ProcessType) {
		Field[] fields = entityObjClass.getDeclaredFields();
		if (SQL_INSERT.equals(ProcessType)) {

			Object[] obj = new Object[fields.length];
			for (int i = 0; i < obj.length; i++) {
				fields[i].setAccessible(true);
				try {
					obj[i] = fields[i].get(entityObjObj);

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return obj;
		} else if (SQL_UPDATE.equals(ProcessType)) {
			Object[] obj = new Object[fields.length];
			for (int i = 0; i < obj.length; i++) {
				fields[i].setAccessible(true);
				try {
					Object value = fields[i].get(entityObjObj);
					// 获取属性的值用get(Object obj)的方法，但是获取私有属性的时候必须先设置setAccessible(true)然后才能获取
					if (paraMap.containsKey(fields[i].getName())) {
						continue;
					}
					if (value != null) {

						obj[i] = value;
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return obj;
		} else if (SQL_DELETE.equals("delete")) {
			int siz = paraMap.size();
			int i = 0;
			Object[] obj = new Object[siz];
			for (Entry<String, Object> str : paraMap.entrySet()) {
				Object value = str.getValue();
				obj[i] = value;
				i++;
			}
			return obj;
		}
		return null;

	}

	private int[] setArgsTypes(T entityObj, String ProcessType) {
		Field[] fields = entityObjClass.getDeclaredFields();
		if (ProcessType.equals(SQL_INSERT)) {
			int[] argTypes = new int[fields.length];
			try {
				for (int i = 0; argTypes != null && i < argTypes.length; i++) {
					fields[i].setAccessible(true);
					if (fields[i].get(entityObj).getClass().getName().equals("java.lang.String")) {
						argTypes[i] = Types.VARCHAR;
					} else if (fields[i].get(entityObj).getClass().getName().equals("java.lang.Double")) {
						argTypes[i] = Types.DECIMAL;
					} else if (fields[i].get(entityObj).getClass().getName().equals("java.lang.Integer")) {
						argTypes[i] = Types.INTEGER;
					} else if (fields[i].get(entityObj).getClass().getName().equals("java.util.Date")) {
						argTypes[i] = Types.DATE;
					} else if (fields[i].get(entityObj).getClass().getName().equals("java.sql.Timestamp")) {
						argTypes[i] = Types.TIMESTAMP;
					} else if (fields[i].get(entityObj).getClass().getName().equals("java.math.BigDecimal")) {
						argTypes[i] = Types.DECIMAL;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;
		} else if (ProcessType.equals(SQL_UPDATE)) {
			int[] argTypes = new int[fields.length];
			try {
				for (int i = 0; argTypes != null && i < argTypes.length; i++) {
					fields[i].setAccessible(true);
					if (fields[i].get(entityObj).getClass().getName().equals("java.lang.String")
							&& fields[i].get(entityObj) != null) {
						argTypes[i] = Types.VARCHAR;
					} else if (fields[i].get(entityObj).getClass().getName().equals("java.lang.Double")
							&& fields[i].get(entityObj) != null) {
						argTypes[i] = Types.DECIMAL;
					} else if (fields[i].get(entityObj).getClass().getName().equals("java.lang.Integer")
							&& fields[i].get(entityObj) != null) {
						argTypes[i] = Types.INTEGER;
					} else if (fields[i].get(entityObj).getClass().getName().equals("java.util.Date")
							&& fields[i].get(entityObj) != null) {
						argTypes[i] = Types.DATE;
					} else if (fields[i].get(entityObj).getClass().getName().equals("java.sql.Timestamp")
							&& fields[i].get(entityObj) != null) {
						argTypes[i] = Types.TIMESTAMP;
					} else if (fields[i].get(entityObj).getClass().getName().equals("java.math.BigDecimal")
							&& fields[i].get(entityObj) != null) {
						argTypes[i] = Types.DECIMAL;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;
		} else if (ProcessType.equals(SQL_DELETE)) {
			int siz = paraMap.size();
			int[] argTypes = new int[siz];
			try {
				for (Entry<String, Object> str : paraMap.entrySet()) {
					String key = str.getKey();
					for (int i = 0; i < fields.length; i++) {
						String name = fields[i].getName();
						key.equals(name);
						fields[i].setAccessible(true);
						if (fields[i].get(entityObj).getClass().getName().equals("java.lang.String")) {
							argTypes[0] = Types.VARCHAR;
						} else if (fields[0].get(entityObj).getClass().getName().equals("java.lang.Double")) {
							argTypes[0] = Types.DECIMAL;
						} else if (fields[0].get(entityObj).getClass().getName().equals("java.lang.Integer")) {
							argTypes[0] = Types.INTEGER;
						} else if (fields[0].get(entityObj).getClass().getName().equals("java.lang.Timestamp")) {
							argTypes[0] = Types.TIMESTAMP;
						} else if (fields[0].get(entityObj).getClass().getName().equals("java.lang.BigDecimal")) {
							argTypes[0] = Types.DECIMAL;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;
		}
		return null;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
