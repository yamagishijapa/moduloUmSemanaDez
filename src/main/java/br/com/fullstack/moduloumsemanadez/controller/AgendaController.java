package br.com.fullstack.moduloumsemanadez.controller;

import br.com.fullstack.moduloumsemanadez.model.Agenda;
import br.com.fullstack.moduloumsemanadez.request.AgendaRequest;
import br.com.fullstack.moduloumsemanadez.response.errorValidation.ValidationErrorDetails;
import br.com.fullstack.moduloumsemanadez.service.AgendaService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @PostMapping
    public ResponseEntity<?> criarAgenda(@RequestBody @Validated AgendaRequest agenda) {
        return agendaService.criarAgenda(agenda);
    }

    @GetMapping
    public List<Agenda> listarAgenda() {
        return agendaService.listarAgenda();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return agendaService.deletarAgenda(id);
    }

    @PutMapping("/{agendaId}")
    public ResponseEntity<?> atualizarAgenda(@PathVariable Long agendaId,
                                             @RequestParam(value = "dataAgenda", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataAgenda,
                                             @RequestParam(value = "status", required = false) Integer status) {
        return agendaService.atualizarAgenda(agendaId, dataAgenda, status);
    }

    @GetMapping("/aluno-id/{alunoId}")
    public List<Agenda> listarAgendaPorIdAluno(@PathVariable Long alunoId) {
        return agendaService.listarAgendaPorIdAluno(alunoId);
    }

    @GetMapping("/tutor-id/{tutorId}")
    public List<Agenda> listarAgendaPorIdTutor(@PathVariable Long tutorId) {
        return agendaService.listarAgendaPorIdTutor(tutorId);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<List<ValidationErrorDetails>> handleHttpMessageNotReadableException(InvalidFormatException ex) {
        List<ValidationErrorDetails> errors = new ArrayList<>();

        String campo = "Erro de formatacao de data";
        String errorMessage;
        if (ex.getCause() instanceof DateTimeParseException) {
            errorMessage = "As datas informadas devem estar no formato yyyy-MM-dd (exemplo: 2024-11-16).";
        } else {
            errorMessage = ex.getMessage();
        }
        errors.add(new ValidationErrorDetails(campo, errorMessage));
        return ResponseEntity.badRequest().body(errors);
    }
}
