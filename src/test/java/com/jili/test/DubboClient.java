package com.jili.test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;


public class DubboClient {

	private  ApplicationConfig application;
	public DubboClient(String appName){
		// 当前应用配置
		application = new ApplicationConfig();
		application.setName(appName);
	}
	
	public <T> T createObj(String url,String version,Class<T> clz){
		
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress(url);
		registry.setProtocol("zookeeper");
		
		ReferenceConfig<T> reference = new ReferenceConfig<T>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
		reference.setApplication(application);
		reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
		reference.setInterface(clz);
		reference.setVersion(version);
		reference.setRetries(0);
		reference.setCheck(false);
		reference.setTimeout(30000);
		reference.setProtocol("dubbo");
		
		return reference.get();
	}
	
	public <T> T createObjHessian(String url,String version,Class<T> clz){
		
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress(url);
		registry.setProtocol("zookeeper");
		
		ReferenceConfig<T> reference = new ReferenceConfig<T>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
		reference.setApplication(application);
		reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
		reference.setInterface(clz);
		reference.setVersion(version);
		reference.setRetries(0);
		reference.setCheck(false);
		reference.setTimeout(30000);
		reference.setProtocol("hessian");
		
		return reference.get();
	}
	
	public Object createObj(String url,String version,Class clz,String group){
		
		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress(url);
		registry.setProtocol("zookeeper");
		
		ReferenceConfig reference = new ReferenceConfig(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
		reference.setApplication(application);
		reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
		reference.setInterface(clz);
		reference.setVersion(version);
		reference.setGroup(group);
		reference.setRetries(0);
		reference.setCheck(false);
		reference.setTimeout(30000);
		reference.setProtocol("dubbo");
		
		return reference.get();
	}
	
	
	public static void main(String[] args) {
		
//		DubboClient d = new DubboClient();
//		AuthService authService = (AuthService)d.createObj("10.20.5.224:2181", "1.0.0-DEV", AuthService.class);
//		
//		AuthWithdrawParamBo AuthWithdrawParamBo = new AuthWithdrawParamBo();
//		
//		QueryOrderReturnBo result = authService.queryAuthWithdraw(AuthWithdrawParamBo);
//		
//		System.out.println(JSON.toJSON(result));
		
	}
	
}
