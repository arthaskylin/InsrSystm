package dao.pkg;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class BaseDao<T, Tb>
{
	// T泛型：变量类型的参数化，
	// 用Object强制转换的话， 要事先知道各个Object具体类型是什么，才能做出正确转换
	public static final String SQL_INSERT = "insert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_DELETE = "delete";
	private Class<T> entityClass;
	private Class<Tb> pClass;
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	protected JdbcTemplate jdbcTemplate;

	public void save(T entity)
	{
		String sql = this.SQl(SQL_INSERT);
		Object[] obj = this.setArgs(entity, SQL_INSERT);
		System.out.println(sql);
		for (int i = 0; i < obj.length; i++) {
			System.out.println("--------------------");
			System.out.println(obj[i]);
			System.out.println("--------------------");
		}
		int[] objTypes = this.setArgsTypes(entity, SQL_INSERT);
		 jdbcTemplate.update(sql, obj, objTypes);
	}

	public void upate(T entity)
	{
		String sql = this.SQl(SQL_UPDATE);
		Object[] obj = this.setArgs(entity, SQL_UPDATE);
		int[] objTypes = this.setArgsTypes(entity, SQL_UPDATE);
		System.out.println(sql);
		System.out.println(sql);
		jdbcTemplate.update(sql, obj, objTypes);
	}

	public void delete(T entity)
	{
		String sql = this.SQl(SQL_DELETE);
		Object[] obj = this.setArgs(entity, SQL_DELETE);
		int[] objTypes = this.setArgsTypes(entity, SQL_DELETE);
		jdbcTemplate.update(sql, obj, objTypes);
	}

	public List<T> findAll()
	{
		String sql = "SELECT * FROM " + entityClass.getSimpleName();
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		return (List<T>) jdbcTemplate.query(sql, rowMapper);
	}

	public T findById(Serializable id)
	{
		
		String sql = "SELECt * FROM  " + entityClass.getSimpleName() + "  where id=?";
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(entityClass);
		if (jdbcTemplate.query(sql, rowMapper, id).size() > 0)
			return jdbcTemplate.query(sql, rowMapper, id).get(0);
		return null;
	}

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	public BaseDao() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		entityClass = (Class<T>) type.getActualTypeArguments()[0];
		System.out.println(entityClass);
		ParameterizedType typep = (ParameterizedType) getClass().getGenericSuperclass();
		pClass = (Class<Tb>) typep.getActualTypeArguments()[0];

	}

	public String SQl(String sqlFla)
	{ // 拼sql语句
		StringBuffer sb = new StringBuffer();
		// 获取改bean所有的字段
		Field[] field = entityClass.getDeclaredFields();
		// 获得类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段
		if (SQL_INSERT.equals(sqlFla)) {
			sb.append("INSERT INTO ").append(entityClass.getSimpleName()).append(" (");
			System.out.println(sb);
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);// 在用反射时访问私有变量
				sb.append(field[i].getName()).append(",");
			}
			System.out.println(sb);
			sb.deleteCharAt(sb.length() - 1);
			sb.append(" ) ").append("VALUES (");
			for (int i = 0; i < field.length; i++) {
				sb.append("?,");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(" ) ");
		} else if (SQL_UPDATE.equals(sqlFla)) {
			sb.append("UPDATE ").append(entityClass.getSimpleName() + " SET ");
			for (int i = 0; i < field.length; i++) {
				field[i].setAccessible(true);
				
				String name=field[i].getName();
				System.out.println("name-----------");
				System.out.println(name);
				if (field[i].getName().equalsIgnoreCase("idntfr")) {
					continue;
				}
				sb.append(field[i].getName()).append(" = ").append("?,");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(" WHERE idntfr=?");
		} else if (SQL_DELETE.equals(sqlFla)) {
			sb.append("DELETE FROM ").append(entityClass.getSimpleName());
			sb.append(" WHERE id=?");
		}
		return sb.toString();
	}

	private Object[] setArgs(T entity, String sqlFla)
	{
		Field[] fields = entityClass.getDeclaredFields();

		if (SQL_INSERT.equals(sqlFla)) {
			Object[] obj = new Object[fields.length];
			for (int i = 0; i < obj.length; i++) {
				fields[i].setAccessible(true);
				try {
					obj[i] = fields[i].get(entity);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return obj;
		} else if (SQL_UPDATE.equals(sqlFla)) {
			Object[] obj = new Object[fields.length];
			int id = 0;
			for (int i = 0; i < obj.length; i++) {
				fields[i].setAccessible(true);
				try {
					obj[i] = fields[i].get(entity);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
			Object[] objArr = new Object[fields.length];
			System.arraycopy(obj, 1, objArr, 0, fields.length - 1);
			objArr[obj.length - 1] = obj[0];
			return objArr;
			
		} else if (SQL_DELETE.equals("delete")) {
			Object[] obj = new Object[1];
			fields[0].setAccessible(true);
			//
			try {
				obj[0] = fields[0].get(entity);
				//获取属性的值用get(Object obj)的方法，但是获取私有属性的时候必须先设置Accessible为true，然后才能获取
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return obj;
		}
		return null;
	}

	// ----------------------------------------------------------------------------------
	// 设置值对应的类型
	private int[] setArgsTypes(T entity, String sqlFlag)
	{
		Field[] fields = entityClass.getDeclaredFields();
		if (sqlFlag.equals(SQL_INSERT)) {
			int[] argTypes = new int[fields.length];
			try {
				for (int i = 0; argTypes != null && i < argTypes.length; i++) {
					fields[i].setAccessible(true);
					if (fields[i].get(entity).getClass().getName().equals("java.lang.String")) {
						argTypes[i] = Types.VARCHAR;
					} else if (fields[i].get(entity).getClass().getName().equals("java.lang.Double")) {
						argTypes[i] = Types.DECIMAL;
					} else if (fields[i].get(entity).getClass().getName().equals("java.lang.Integer")) {
						argTypes[i] = Types.INTEGER;
					} else if (fields[i].get(entity).getClass().getName().equals("java.util.Date")) {
						argTypes[i] = Types.DATE;
					} else if (fields[i].get(entity).getClass().getName().equals("java.sql.Timestamp")) {
						argTypes[i] = Types.TIMESTAMP;
					} else if (fields[i].get(entity).getClass().getName().equals("java.math.BigDecimal")) {
						argTypes[i] = Types.DECIMAL;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			int[] tempArgTypes = new int[fields.length];
			int[] argTypes = new int[fields.length];
			try {
				for (int i = 0; tempArgTypes != null && i < tempArgTypes.length; i++) {
					fields[i].setAccessible(true); // 暴力反射
					if (fields[i].get(entity).getClass().getName().equals("java.lang.String")) {
						tempArgTypes[i] = Types.VARCHAR;
					} else if (fields[i].get(entity).getClass().getName().equals("java.lang.Double")) {
						tempArgTypes[i] = Types.DECIMAL;
					} else if (fields[i].get(entity).getClass().getName().equals("java.lang.Integer")) {
						tempArgTypes[i] = Types.INTEGER;
					} else if (fields[i].get(entity).getClass().getName().equals("java.util.Date")) {
						tempArgTypes[i] = Types.DATE;
					} else if (fields[i].get(entity).getClass().getName().equals("java.sql.Timestamp")) {
						tempArgTypes[i] = Types.TIMESTAMP;
					} else if (fields[i].get(entity).getClass().getName().equals("java.math.BigDecimal")) {
						tempArgTypes[i] = Types.DECIMAL;
					}
				}
				System.arraycopy(tempArgTypes, 1, argTypes, 0, tempArgTypes.length - 1); // 数组拷贝
				argTypes[argTypes.length - 1] = tempArgTypes[0];

			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;

		} else if (sqlFlag.equals(SQL_DELETE)) {
			int[] argTypes = new int[1]; // 长度是1
			try {
				fields[0].setAccessible(true); // 暴力反射
				if (fields[0].get(entity).getClass().getName().equals("java.lang.String")) {
					argTypes[0] = Types.VARCHAR;
				} else if (fields[0].get(entity).getClass().getName().equals("java.lang.Integer")) {
					argTypes[0] = Types.INTEGER;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return argTypes;
		}
		return null;
	}

}
