package br.med.maisvida.rest.controller.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.med.maisvida.rest.dto.ArquivoDTO;

@RestController
public class ArquivoUploadRestController {

	@PostMapping("/arquivo/upload")
	public ResponseEntity<?> uploadFile(@RequestBody(required = true) @NotNull ArquivoDTO arquivo, HttpServletRequest request) {

		File tempFile = null;
		try {
			tempFile = File.createTempFile("UPLOAD_CONTRATO_"+arquivo.getCnpj()+"_", null);
			try (FileOutputStream fileOutputStream = new FileOutputStream(tempFile)) {
				byte[] imageByte = Base64.getDecoder().decode(arquivo.getArquivoBase64());
				fileOutputStream.write(imageByte);
				return ResponseEntity.ok().body("Arquivo salvo com sucesso");
			} catch (Exception e) {
				return ResponseEntity.badRequest().body("Erro ao salvar o arquivo = " + e.getMessage());
			}
		} catch (IOException e1) {
			return ResponseEntity.badRequest().body("Erro ao salvar o arquivo = " + e1.getMessage());
		}

	}
	
}
