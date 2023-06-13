package com.jili.test;

import com.bailianpay.rating.api.MemRatingService;
import com.bailianpay.rating.bo.MemberRatingRequest;
import com.bailianpay.rating.bo.MemberRatingResponse;

public class memberRating {
	private MemRatingService memRatingService;
	{
		DubboClient dubboClient = new DubboClient("biz");
		memRatingService = (MemRatingService) dubboClient.createObj("172.26.39.200:3180,172.26.39.200:3280,172.26.39.200:3380", "3.0.0", MemRatingService.class);
	}
	public static void main(String[] args) {
		memberRating test = new memberRating();
		test.rating();
		// TODO Auto-generated method stub
		}
	public void rating() {
		MemberRatingRequest request = new MemberRatingRequest();
		request.setCustomerType("CT");
		request.setMemberNo("202023052200001193");
		System.out.println("request :"+request);
		MemberRatingResponse response = memRatingService.rating(request);
//		MemberRatingResponse response = memRatingService.query("1","1");
		System.out.println("response:"+response);
	}


}
