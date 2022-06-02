package com.proyecto.demo.service.Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	
	public String saveImage(MultipartFile file) {
		if(!file.isEmpty()) {
			Path directorioImagenes=Paths.get("src//main//resources//static//img");
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			try {
				byte[] bytesImg = file.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+file.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				return file.getOriginalFilename();
				
			}catch(IOException e) {
				
				e.printStackTrace();
			}
		}
		
		return "default.jpg";
	}
	
	public void deleteImage(String nombre) {
		String ruta = "img//";
		File file = new File(ruta+nombre);
		file.delete();
	}
}
