package com.hawk.ecom.picture.controller;


import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.hawk.ecom.muser.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.muser.service.MallAuthService;
import com.hawk.ecom.picture.response.UploadFileInfo;
import com.hawk.ecom.picture.response.UploadResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;

import ch.qos.logback.classic.Logger;


@RequestMapping("/ecom/fileUpload")
@CrossOrigin
@Controller
public class FileUploadController {
	
	@Autowired
	private MallAuthService authService;
	
	
	@Autowired
    FastFileStorageClient fastFileStorageClient;

	@ResponseBody
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/fileUpload controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	

	public void test(Object o) throws FileNotFoundException{
		
		Logger logger = LoggerFactory.getLogger("com.ccb.acten");
		
		logger
		
		if (o == null){
			throw new Exception("参数为空");
		}
		throw new FileNotFoundException();
	}
	
	public void test2() {
		try {
			logger.info(); file
			                stdout.
			                 jms 
			Logger.debug();
			if （） 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value = "/", method = GET)
	public String showUploadPages(HttpServletRequest request) throws Exception {
		
		return "uploadForm";
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/upload1", method = POST)
//	public WebResponse<UploadResponse> upload1(HttpServletRequest request,@RequestPart("file") MultipartFile file[]) throws Exception{
//		UploadResponse uploadResponse = new UploadResponse();
//		List<UploadFileInfo> uploadFileInfoList = new ArrayList<UploadFileInfo>();
//		uploadResponse.setFiles(uploadFileInfoList);
//		long size = 0;
//		for (MultipartFile file : files ){
//			size+=file.getSize();
//			String filename = file.getOriginalFilename();
//			int index = filename.lastIndexOf(".");
//			if (index == -1){
//				throw new RuntimeException("文件名没有后缀的扩展名");
//			}
//			String fileExtName = filename.substring(index);
//			StorePath storePath = fastFileStorageClient.uploadFile(null,file.getInputStream(), file.getSize(), fileExtName);
//			UploadFileInfo uploadFileInfo = new UploadFileInfo();
//			uploadFileInfo.setFilename(filename);
//			uploadFileInfo.setGroup(storePath.getGroup());
//			uploadFileInfo.setPath(storePath.getPath());
//			uploadFileInfo.setFilesize(file.getSize());
//			uploadFileInfoList.add(uploadFileInfo);
//		}
//		
//		uploadResponse.setSize(size);
//		return SuccessResponse.build(uploadResponse);
//	}
	
	@ResponseBody
	@RequestMapping(value = "/upload", method = POST)
	public WebResponse<UploadResponse> upload2(HttpServletRequest request,@RequestPart("file") MultipartFile[] files) throws Exception{
	//需要验证权限，把代码放开	
//		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
//			throw new IllegalAccessRuntimeException();
//		}
		
		UploadResponse uploadResponse = new UploadResponse();
		List<UploadFileInfo> uploadFileInfoList = new ArrayList<UploadFileInfo>();
		uploadResponse.setFiles(uploadFileInfoList);
		long size = 0;
		for (MultipartFile file : files ){
			size+=file.getSize();
			String filename = file.getOriginalFilename();
			int index = filename.lastIndexOf(".");
			if (index == -1){
				throw new RuntimeException("文件名没有后缀的扩展名");
			}
			String fileExtName = filename.substring(index+1);
			StorePath storePath = fastFileStorageClient.uploadFile(null,file.getInputStream(), file.getSize(), fileExtName);
			UploadFileInfo uploadFileInfo = new UploadFileInfo();
			uploadFileInfo.setFullPath(storePath.getFullPath());
			uploadFileInfo.setFilesize(file.getSize());
			uploadFileInfo.setFilename(filename);
			uploadFileInfoList.add(uploadFileInfo);
		}
		
		uploadResponse.setSize(size);
		return SuccessResponse.build(uploadResponse);
	}
	

//	@ResponseBody
//	@RequestMapping(value = "/upload3", method = POST)
//	public WebResponse<UploadResponse> upload3(MultipartHttpServletRequest request) throws Exception{
//		UploadResponse uploadResponse = new UploadResponse();
//		long size = 0;
//		
//		
//		uploadResponse.setSize(size);
//		return SuccessResponse.build(uploadResponse);
//	}
	
	
	


}
