package com.babytrak24.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class CustomFileUtils {
	/**
	 * Convert MultiPartFile to ordinary File
	 * 
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 * 
	 *             As there are permission issues in ElasticBeanStalk Tomcat server
	 *             we created the files in the temp folder
	 */
	public static File convertFromMultiPart(MultipartFile multipartFile) throws IOException {

		String tmpDir = System.getProperty("java.io.tmpdir");

		File file = new File(tmpDir + "/" + multipartFile.getOriginalFilename());
		file.createNewFile();

		FileOutputStream fos = new FileOutputStream(file);
		fos.write(multipartFile.getBytes());
		fos.close();

		return file;
	}

}
