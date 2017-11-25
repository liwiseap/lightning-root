package com.osc.oscashapp.web.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.osc.oscashapp.properties.memcached.MemcachedProperties;
import com.osc.oscashdao.entity.user.User;
import com.osc.oscashserviceuser.user.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: UseController
 * @author: lizhiming
 * @since: 2017/11/10
 */
@RestController
@RequestMapping("/user")
public class UseController {
	
	protected static Logger LOGGER = LoggerFactory.getLogger(UseController.class);
	
	@Autowired
	private IUserService userService;
	@Autowired
	private MemcachedProperties memcached;

	@ApiOperation(value = "保存用户",notes = "保存用户")
	@ApiImplicitParam(name = "User",value = "用户对象")
	@PostMapping("/saveUser")
	@ResponseBody
	public String saveUser(User user) {

		System.out.println(JSON.toJSONString(user));
		userService.saveUser(user);

		LOGGER.info("保存用户成功!");

		return "保存用户成功";
	}

	@ApiOperation(value = "保存用户",notes = "保存用户")
	@ApiImplicitParam(name = "User",value = "用户对象")
	@PostMapping("/saveUser2")
	@ResponseBody
	public String saveUser2(User user) {

		/**
		 * @RequestBody 仅用于使用JSON格式提交数据
		 */
		System.out.println(JSON.toJSONString(user));
		userService.saveUser(user);

		LOGGER.info("保存用户成功!");

		return "保存用户成功";
	}

	@ApiOperation(value = "获取所有用户",notes = "获取所有用户")
	@GetMapping("/getAllUser")
	public String getAllUser() {

		List<User> users = userService.getAllUser();

		System.out.println(JSON.toJSONString(users));
		LOGGER.info("memcached keyprefix="+memcached.getKeyprefix());
		LOGGER.info("memcached servers="+memcached.getServers());
		LOGGER.trace("获取所有用户 I am trace log.");
		LOGGER.debug("获取所有用户 I am debug log.");
		LOGGER.warn("获取所有用户 I am warn log.");
		LOGGER.error("获取所有用户 I am error log.");

		return JSON.toJSONString(users);

	}
}
