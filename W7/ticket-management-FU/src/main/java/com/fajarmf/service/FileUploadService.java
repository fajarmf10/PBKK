package com.fajarmf.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	void uploadFile(MultipartFile file) throws IOException;
}
