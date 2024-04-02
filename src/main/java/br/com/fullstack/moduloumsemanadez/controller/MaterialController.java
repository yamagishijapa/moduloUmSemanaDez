package br.com.fullstack.moduloumsemanadez.controller;

import br.com.fullstack.moduloumsemanadez.model.Material;
import br.com.fullstack.moduloumsemanadez.request.MaterialRequest;
import br.com.fullstack.moduloumsemanadez.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping
    public ResponseEntity<?> criarMaterial(@RequestBody @Validated MaterialRequest material) {
        return materialService.criarMaterial(material);
    }

    @GetMapping
    public List<Material> listarMaterial() {
        return materialService.listarMaterial();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return materialService.deletarMaterial(id);
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<?> atualizarMaterial(@PathVariable Long materialId,
                                             @RequestParam(value = "descricao", required = false) String descricao,
                                             @RequestParam(value = "caminhoArquivo", required = false) String caminhoArquivo) {
        return materialService.atualizarMaterial(materialId, descricao, caminhoArquivo);
    }
}
