package com.koitt.board.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.koitt.board.model.CommonException;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public void remove(HttpServletRequest request, String repo, String filename) throws CommonException, UnsupportedEncodingException {
		String decodeFilename = URLDecoder.decode(filename, "UTF-8");		
		String path = request.getServletContext().getRealPath(repo);
		
		// 서버에 저장된 파일을 불러와서 객체화 시킴
		File file = new File(path, decodeFilename);
		
		// 파일이 존재하면 파일을 삭제한다.
		if (file.exists()) {
			file.delete();
			
		} else {
			throw new CommonException("E50: " + path + "/" + decodeFilename + "파일이 존재하지 않습니다.");
		}
	}
}
