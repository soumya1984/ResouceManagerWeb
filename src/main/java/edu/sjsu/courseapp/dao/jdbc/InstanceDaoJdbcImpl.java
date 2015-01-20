package edu.sjsu.courseapp.dao.jdbc;
/**
 * 
 * @author Sudip
 * 
 */
import java.util.ArrayList;
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

import edu.sjsu.courseapp.dao.InstanceDAO;
import edu.sjsu.courseapp.domain.Instance;
import edu.sjsu.courseapp.domain.Rate;

@Repository("InstanceDaoJdbcImpl")
public class InstanceDaoJdbcImpl implements InstanceDAO {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	private SimpleJdbcInsert jdbcInsert;

	private InstanceRowMapper instanceRowMapper;

	@PostConstruct
	public void setup() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		namedTemplate = new NamedParameterJdbcTemplate(dataSource);
		jdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("instance")
				.usingColumns("instanceid", "cloudid", "name", "status",
						"type", "os", "cpu", "memory", "storage", "publicip",
						"privateip", "uptime", "bill", "userid");
		instanceRowMapper = new InstanceRowMapper();

	}

	@Override
	public int getInstanceCount() {
		String sql = "select count(*) from instance";
		return jdbcTemplate.queryForInt(sql);
	}

	public void insertInstance(List<Instance> listinstance) {
		for (Instance instance : listinstance) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", instance.getName());
			map.put("cloudid", instance.getCloudid());
			map.put("status", instance.getStatus());
			map.put("type", instance.getType());
			map.put("os", instance.getOs());
			map.put("cpu", instance.getCpu());
			map.put("memory", instance.getMemory());
			map.put("storage", instance.getStorage());
			map.put("publicip", instance.getPublicip());
			map.put("privateip", instance.getPrivateip());
			map.put("uptime", instance.getUptime());
			map.put("bill", instance.getBill());
			map.put("userid", instance.getUserid());
			int newId = jdbcInsert.execute(map);
			instance.setInstanceid(newId);
		}
	}

	@Override
	public String findInstanceNameById(int id) {
		String sql = "select name from instance where instanceid=?";
		return jdbcTemplate.queryForObject(sql, String.class, id);
	}

	@Override
	public Instance findInstanceByName(String instanceName) {
		int instancesFound;
		String sql = "select * from instance where name=?";
		List<Instance> instanceList = jdbcTemplate.query(sql,
				instanceRowMapper, instanceName);

		instancesFound = instanceList.size();
		if (instancesFound == 1) {

			return instanceList.get(0);

		} else if (instancesFound == 0) {

			return null;

		}
		return (Instance) instanceList;
		// throw new
		// ProductDaoException("Multiple Clouds Found with Same Name");
	}

	@Override
	public void deleteInstance(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Instance> getInstanceallList() {
		updateBillOfAllInstances();
		String sql = "select * from instance";
		List<Instance> instanceList = jdbcTemplate
				.query(sql, instanceRowMapper);
		return instanceList;
	}
	
	
	public int updateInstance(Instance instance, int userid) {
		String sql = "update instance set userid=:userid where instanceid=:id";
		int id;
		MapSqlParameterSource params;
		int rowsAffected;

		id = instance.getInstanceid();

		params = new MapSqlParameterSource("id", id);
		params.addValue("userid", userid);
		rowsAffected = namedTemplate.update(sql, params);
		return rowsAffected;
	}

	@Override
	public List<Instance> getInstances(int userid) {
		String sql = "select name from instance where userid=?";
		List<Instance> instanceList = jdbcTemplate
				.query(sql, instanceRowMapper,  userid);
		return instanceList;
	}
	
	
	public int updateBillOfAllInstance(int instanceid, double billPerInstance) {

		String sql = "update instance set bill=:billPerInstance where instanceid=:instanceid";
		MapSqlParameterSource params;
		params = new MapSqlParameterSource("instanceid", instanceid);
		params.addValue("billPerInstance", billPerInstance);
		int rowsAffected = namedTemplate.update(sql, params);
		return rowsAffected;
	}
	
	public int updateBillOfAllInstances() {
		String sql = "update instance i set bill=(select ( (TIME_TO_SEC(i.uptime) / 60) * ((i.cpu * (select costpermin from rate r where r.type=i.type and r.component="
				+ "\"cpu\""
				+ "))  + (i.memory * (select costpermin from rate r where r.type=i.type and r.component="
				+ "\"memory\""
				+ ")) +  (i.storage * (select costpermin from rate r where r.type=i.type and r.component="
				+ "\"storage\"" + ")) )))";
		MapSqlParameterSource params;
		params = new MapSqlParameterSource();
		int rowsAffected = namedTemplate.update(sql, params);
		return rowsAffected;
	}

}
