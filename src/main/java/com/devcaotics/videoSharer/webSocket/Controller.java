package com.devcaotics.videoSharer.webSocket;

import  org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/rest")
@CrossOrigin("https://videosharer.onrender.com/")
public class Controller{
 
    // Estrutura de dados para armazenar o arquivo em memória
    private static class ArquivoMemoria {
        private final byte[] conteudo;
        private final String nomeOriginal;
        private final String contentType;

        public ArquivoMemoria(byte[] conteudo, String nomeOriginal, String contentType) {
            this.conteudo = conteudo;
            this.nomeOriginal = nomeOriginal;
            this.contentType = contentType;
        }

        public byte[] getConteudo() { return conteudo; }
        public String getNomeOriginal() { return nomeOriginal; }
        public String getContentType() { return contentType; }
    }

    // Mapa thread-safe para gerenciar o armazenamento em memória usando UUID como identificador
    private final ConcurrentHashMap<String, ArquivoMemoria> armazenamentoMemoria = new ConcurrentHashMap<>();

    // 1. Endpoint para Enviar (Upload) o arquivo
    @PostMapping(path = "/files/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadArquivo(@RequestParam("file") MultipartFile file,@PathVariable String id) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, selecione um arquivo válido.");
        }

        try {

            // Cria o objeto e salva no mapa em memória
            ArquivoMemoria arquivo = new ArquivoMemoria(
                    file.getBytes(),
                    file.getOriginalFilename(),
                    file.getContentType()
            );
            armazenamentoMemoria.put(id, arquivo);

            // Retorna o identificador gerado
            return ResponseEntity.status(HttpStatus.CREATED).body(id);

            } catch (IOException e) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo.");
            }
    }

    // 2. Endpoint para Buscar (Download) o arquivo pelo Identificador
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> downloadArquivo(@PathVariable String id) {
        ArquivoMemoria arquivo = armazenamentoMemoria.get(id);

        // Se o arquivo não existir na memória, retorna 404 Not Found
        if (arquivo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Define os headers de resposta para download do arquivo com o nome correto
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(arquivo.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNomeOriginal() + "\"")
                .body(arquivo.getConteudo());
      }

  @GetMapping("/ids")
  public ResponseEntity<?> init(){ 
    
      return ResponseEntity.ok(ServiceHolder.getIds());
    
  }

}
