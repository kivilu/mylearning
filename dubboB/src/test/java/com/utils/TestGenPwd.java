package com.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class TestGenPwd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String userid="201411110511572000000930";//WANGYISHAN@163.com
		String pwd = "12344321" ;
		
		String pwdEncry = DigestUtils.md5Hex(pwd+userid);
		System.out.println("pwd:"+pwdEncry);
		
		String data = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><message xmlns=\"http://www.w3school.com.cn\"><head><version>01</version><type>0001</type><channelNo>HM</channelNo><tradDate>20141211</tradDate><tradTime>174740</tradTime><tradFlowNo>20141211112675146832</tradFlowNo><tradNo>ES0007</tradNo></head><body><tranSeqId>20141211112698742615</tranSeqId><merchantNo>1126</merchantNo><alias>ES1126001</alias><submitDate>20141211</submitDate><isNeedNotify>0</isNeedNotify><notifyUrl>https://frontapi.psds.com.cn/frontAPI/entruSettleNotify</notifyUrl><batchUse>04</batchUse><bankId>305</bankId><acctNo>6226202300256390</acctNo><acctName>黄译凯</acctName><acctAttribute>02</acctAttribute><bankProvince>福建</bankProvince><bankCity>泉州</bankCity><cityCode>3970</cityCode><amount>0.64</amount><currencyType>CNY</currencyType><mobilePhone>15805080815</mobilePhone></body></message>";
		String key ="2014ps12yd08";
		
		// 5611402805755436c4e10591fd2536e6
		String mac = DigestUtils.md5Hex(data+key);
		System.out.println("5611402805755436c4e10591fd2536e6 mac:"+mac);
	}

}
