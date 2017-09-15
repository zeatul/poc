package com.github.wxpay.sdk;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.hawk.framework.utility.tools.CodecTools;

public class WXPayConfigImpl extends WXPayConfig{

    public String getNotifyUrl() {
		return notifyUrl;
	}

	private byte[] certData;
    
    private String appId;
    
    private String mchId;
    
    private String key;
    
    private String notifyUrl;
   
//    public WXPayConfigImpl(String certPath) throws Exception{
////        String certPath = "D://CERT/common/apiclient_cert.p12";
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();
//    }
    
    public WXPayConfigImpl(String cert , String appId, String mchId,String key ,String notifyUrl)throws Exception{
    	this.certData = CodecTools.toByteFronHexString(cert);
    	this.appId = appId;
    	this.mchId = mchId;
    	this.key = key;
    	this.notifyUrl = notifyUrl;
    }

    

    public String getAppID() {
    	return this.appId;
    }

    public String getMchID() {
    	return this.mchId;
    }

    public String getKey() {

    	return this.key;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }
}
