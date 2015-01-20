package edu.sjsu.courseapp.dao.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import edu.sjsu.courseapp.dao.UserDAO;
import edu.sjsu.courseapp.domain.Instance;
import edu.sjsu.courseapp.domain.User;

@Repository("UserDaoJdbcImpl")
public class UserDaoJdbcImpl implements UserDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	private UserRowMapper userRowMapper;


	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("user")
				    .usingColumns("userid", "name","creditcard","emailid", "phone", "totalbill" , "paidbill");
		userRowMapper = new UserRowMapper();

	}

	@Override
	public int getUserCount() {
		String sql = "select count(*) from user";
		return jdbcTemplate.queryForInt(sql);
	}

	public void insertUser(List<User> listuser) {
		for (User user : listuser) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", user.getName());
			map.put("creditcard", user.getCreditcard());
			map.put("emailid",user.getEmailid());
			map.put("phone",user.getPhone());
			map.put("totalbill",user.getTotalbill());
			map.put("paidbill",user.getPaidbill());
			int newId = jdbcInsert.execute(map);
			user.setUserid(newId);
		}
	}
	

	@Override
	public String findUserNameById(int id) {
		String sql = "select name from user where userid=?";
		return jdbcTemplate.queryForObject(sql, String.class, id);
	}

	@Override
	public User findUserByName(String userName) {
		int usersFound;
		String sql = "select * from user where name=?";
		List<User> userList = jdbcTemplate.query(sql, userRowMapper,
				userName);

		usersFound = userList.size();
		if (usersFound == 1) {

			return userList.get(0);

		} else if (usersFound == 0) {

			return null;

		}
		return (User) userList;
		// throw new
		// ProductDaoException("Multiple users Found with Same Name");
	}


	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getUserallList() {
		updateBillOfAllUsers();
		String sql = "select * from user";
		List<User> userList = jdbcTemplate.query(sql, userRowMapper);
		return userList;
	}

	
	@Override
	public int updateBillOfAllUsers() {
		String sql = "update user u set totalbill=(select sum(bill) from instance i where i.userid=u.userid)  ;";
		MapSqlParameterSource params = new MapSqlParameterSource();
		int rowsAffected;
		rowsAffected = namedTemplate.update(sql, params);
		return rowsAffected;
	}

	@Override
	public double getTotalBillOfUser(int userid) {
		// TODO Auto-generated method stub
		String sql = "select totalbill from user where userid=?";
		return jdbcTemplate.queryForObject(sql, Double.class, userid);
	}

}
