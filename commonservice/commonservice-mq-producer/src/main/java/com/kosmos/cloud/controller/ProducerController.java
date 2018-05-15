package com.kosmos.cloud.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kosmos.cloud.common.code.ResponseCode;
import com.kosmos.cloud.common.code.ResponseVO;
import com.kosmos.cloud.producer.ProducerService;

@RestController
@RequestMapping(value = "producer")
public class ProducerController {
	
	@Autowired
	private ProducerService producerService;
	
	/**
	 * 通过get方式发送字符串消息
	 * @param name 路径参数
	 * @return 成功|失败
	 */
	@RequestMapping(value = "/send/{name}", method = RequestMethod.GET)
	public ResponseVO send(@PathVariable(value = "name", required = true) String name) {
		Object o = new Object();
		Message msg = MessageBuilder.withPayload(name.getBytes()).build();
		boolean result = producerService.sendMessage().send(msg);
		if(result){
			return ResponseCode.buildEnumResponseVO(ResponseCode.RESPONSE_CODE_SUCCESS, false);
		}
		return ResponseCode.buildEnumResponseVO(ResponseCode.RESPONSE_CODE_FAILURE, false);
	}
	
	/**
	 * 通过post方式发送字符串消息
	 * @param name 路径参数
	 * @return 成功|失败
	 */
	@RequestMapping(value = "/sendObj", method = RequestMethod.POST)
	public ResponseVO sendObj(@RequestBody JSONObject jsonObj) {
		Object o = new Object();
		Message msg = MessageBuilder.withPayload(jsonObj).build();
		boolean result = producerService.sendMessage().send(msg);
		if(result){
			return ResponseCode.buildEnumResponseVO(ResponseCode.RESPONSE_CODE_SUCCESS, false);
		}
		return ResponseCode.buildEnumResponseVO(ResponseCode.RESPONSE_CODE_FAILURE, false);
	}
}
