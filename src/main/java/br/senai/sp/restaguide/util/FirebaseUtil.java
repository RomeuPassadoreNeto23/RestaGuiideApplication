package br.senai.sp.restaguide.util;

import java.io.IOException;

import javax.management.RuntimeErrorException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class FirebaseUtil {
	//variável para guardar as credenciais do Firebese
	private Credentials credencias;
	//variável para acessar o storage
	private Storage storage;
 // constante para o nome bucket
	private final String BUCKET_NAME = "restagui-2a8da.appspot.com";
	private final String PREFIX = "https://firebasestorage.googleapis.com/v0/b/"+BUCKET_NAME+"/o/";
	private final String SUFFIX = "?alt=media";
	private final String DOWNLOAD_URL = PREFIX + "%S"+ SUFFIX;
	public FirebaseUtil() {
		// buscar as credencias(arquivoJso)
		Resource resource = new ClassPathResource("chaveFirebase.json");
		// ler o arquivo para obter as credenciasis
		try {
			credencias = GoogleCredentials.fromStream(resource.getInputStream());
			storage = StorageOptions.newBuilder().setCredentials(credencias).build().getService();
		} catch (IOException e) {
		  throw new RuntimeException(e.getMessage());
		}
		
		
	}
	
}
