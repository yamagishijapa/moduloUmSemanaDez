package br.com.fullstack.moduloumsemanadez.service;

import br.com.fullstack.moduloumsemanadez.model.Agenda;
import br.com.fullstack.moduloumsemanadez.model.Material;
import br.com.fullstack.moduloumsemanadez.repository.AgendaRepository;
import br.com.fullstack.moduloumsemanadez.repository.MaterialRepository;
import br.com.fullstack.moduloumsemanadez.request.MaterialRequest;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;
    
    @Autowired
    private AgendaRepository agendaRepository;

    public ResponseEntity<?> criarMaterial(MaterialRequest materialRequest) {
        Material material = validaMaterial(materialRequest);

        if(!validaCaminhoArquivo(material.getCaminhoArquivo())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Caminho para arquivo inválido: " + materialRequest.getCaminhoArquivo() + ". Informe uma URL ou caminho de diretório válido.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(materialRepository.save(material));
    }

    public List<Material> listarMaterial(){
        return materialRepository.findAll();
    }

    public ResponseEntity<?> deletarMaterial(Long id) {
        if(!materialRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O material com ID " + id + " não foi encontrado.");
        }

        materialRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> atualizarMaterial(Long materialId, String descricao, String caminhoArquivo) {
        if (!materialRepository.existsById(materialId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material com ID " + materialId + " não encontrado.");
        }

        if(caminhoArquivo != null && !validaCaminhoArquivo(caminhoArquivo)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Caminho para arquivo inválido: " + caminhoArquivo + ". Informe uma URL ou caminho de diretório válido.");
        }
        materialRepository.updateMaterial(materialId, descricao, caminhoArquivo);

        return ResponseEntity.ok(materialRepository.findById(materialId));
    }

    private Material validaMaterial(MaterialRequest materialRequest){
        if (!agendaRepository.existsById(materialRequest.getAgendaId())) {
            throw new NoSuchElementException("Agenda não encontrada com ID: " + materialRequest.getAgendaId());
        }
        Optional<Agenda> agenda = agendaRepository.findById(materialRequest.getAgendaId());

        return mapDtoToEntity(materialRequest, agenda.get());
    }

    private boolean urlValida(String urlString) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(urlString);
    }

    private boolean diretorioValido(String pathString) {
        String regex = "^(file:\\/\\/[a-zA-Z0-9._-]+(\\/[a-zA-Z0-9._-]+)+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pathString);
        return matcher.matches();
    }

    private boolean validaCaminhoArquivo(String caminhoArquivo){
        return urlValida(caminhoArquivo) || diretorioValido(caminhoArquivo);
    }


    private Material mapDtoToEntity(MaterialRequest materialRequest, Agenda agenda){
        return new Material(agenda,
                materialRequest.getDescricao(),
                materialRequest.getCaminhoArquivo());
    }
}
