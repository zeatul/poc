package com.hawk.ecom.picture.controller;


import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.hawk.ecom.picture.response.UploadFileInfo;
import com.hawk.ecom.picture.response.UploadResponse;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;


@RequestMapping("/ecom/fileUpload")
@CrossOrigin
@Controller
public class FileUploadController {
	
	@Autowired
    FastFileStorageClient fastFileStorageClient;

	@ResponseBody
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/fileUpload controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
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
			String fileExtName = filename.substring(index);
			StorePath storePath = fastFileStorageClient.uploadFile(null,file.getInputStream(), file.getSize(), fileExtName);
			UploadFileInfo uploadFileInfo = new UploadFileInfo();
			uploadFileInfo.setFilename(filename);
			uploadFileInfo.setGroup(storePath.getGroup());
			uploadFileInfo.setPath(storePath.getPath());
			uploadFileInfo.setFilesize(file.getSize());
			uploadFileInfoList.add(uploadFileInfo);
		}
		
		uploadResponse.setSize(size);
		return SuccessResponse.build(uploadResponse);
	}
	

	@ResponseBody
	@RequestMapping(value = "/upload3", method = POST)
	public WebResponse<UploadResponse> upload3(MultipartHttpServletRequest request) throws Exception{
		UploadResponse uploadResponse = new UploadResponse();
		long size = 0;
		
		
		uploadResponse.setSize(size);
		return SuccessResponse.build(uploadResponse);
	}
	
	
	


}
