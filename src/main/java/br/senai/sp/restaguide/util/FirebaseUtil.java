package br.senai.sp.restaguide.util;

import java.io.IOException;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
@Service
public class FirebaseUtil {
	//variável para guardar as credenciais do Firebese
	private Credentials credencias;
	//variável para acessar o storage
	private Storage storage;
 // constante para o nome bucket
	private final String BUCKET_NAME = "restagui-2a8da.appspot.com";
	private final String PREFIX = "https://firebasestorage.googleapis.com/v0/b/"+BUCKET_NAME+"/o/";
	private final String SUFFIX = "?alt=media";
	private final String DOWNLOAD_URL = PREFIX + "%s"+ SUFFIX;
	
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
	public String uploadFile(MultipartFile arquivo) throws IOException {
		String nomeArquivo = UUID.randomUUID().toString() + getExtensao(arquivo.getOriginalFilename());
		BlobId blobId = BlobId.of(BUCKET_NAME, nomeArquivo);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
		storage.create(blobInfo, arquivo.getBytes());
		return String.format(DOWNLOAD_URL, nomeArquivo);
	
	}
	public void deletar(String nomeArquivo) {
		nomeArquivo = nomeArquivo.replace(PREFIX, "").replace(SUFFIX, "");
		Blob blob = storage.get(BlobId.of(BUCKET_NAME, nomeArquivo));
		storage.delete(blob.getBlobId());
	}
	private String getExtensao(String nomeArquivo) {
		// TODO Auto-generated method stub
		return nomeArquivo.substring(nomeArquivo.lastIndexOf('.'));

	}
	
	
}
