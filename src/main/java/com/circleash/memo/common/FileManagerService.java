package com.circleash.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	//수정 될일도 없고 수정 되어서는 안되는 곳에는 final과 변수이름을 대문자로 만든다.
	public final static String FILE_UPLOAD_PATH ="C:\\Users\\JE\\OneDrive\\바탕 화면\\Marondal\\springTest\\upload\\images/";
	//userId를 전달 요청
	public static String saveFile(int userId, MultipartFile file) {
		// 파일 경로
		// 1. 올린 사람의 id로 구분해서 저장
		// 2. 올린 시간을 기준으로 구분한다.
		// /43_281281298129/test.png를 만들어서 저장
		// /id_밀리초
		// 1970년 1월 1일 기준으로 몇 밀리초(1/1000)가 지났는지
		
//		Logger logger = LoggerFactory.getLogger(this.getClass());
		
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		
		
		//C:\\Users\\JE\\OneDrive\\바탕 화면\\Marondal\\springTest\\upload\\images/43_281281298129/
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		//디렉토리 생성
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			//디렉토리 생성 에러
//			logger.error("[FileManagerService saveFile] 디렉토리 생성 에러");
			return null;
		}
		
		//byte[]
		try {
			byte[] bytes = file.getBytes();
			//파일 경로
			Path path = Paths.get(filePath + file.getOriginalFilename());
			//파일 저장
			Files.write(path, bytes);
			
		} catch (IOException e) {
//			logger.error("[FileManagerService saveFile] 파일 생성 실패");
			e.printStackTrace();
			return null;
		}
		
		// 파일을 접근할 수 있는 경로
		// <img src="/images/43_281281298129/test.png">
		
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}

}
