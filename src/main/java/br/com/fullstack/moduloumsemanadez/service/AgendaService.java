package br.com.fullstack.moduloumsemanadez.service;

import br.com.fullstack.moduloumsemanadez.enumeration.StatusAgenda;
import br.com.fullstack.moduloumsemanadez.model.Agenda;
import br.com.fullstack.moduloumsemanadez.model.Aluno;
import br.com.fullstack.moduloumsemanadez.model.Tutor;
import br.com.fullstack.moduloumsemanadez.repository.AgendaRepository;
import br.com.fullstack.moduloumsemanadez.repository.AlunoRepository;
import br.com.fullstack.moduloumsemanadez.repository.TutorRepository;
import br.com.fullstack.moduloumsemanadez.request.AgendaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;
    
    @Autowired
    private  AlunoRepository alunoRepository;
    
    @Autowired
    private TutorRepository tutorRepository;

    public ResponseEntity<?> criarAgenda(AgendaRequest agendaRequest) {
        Agenda agenda = validaAgenda(agendaRequest);
        if(validaDataAgendamento(agendaRequest.getDataAgenda())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data " + agendaRequest.getDataAgenda() + " é uma data inválida. Informe uma data futura ao dia de hoje.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaRepository.save(agenda));
    }

    public List<Agenda> listarAgenda(){
        return agendaRepository.findAll();
    }

    public ResponseEntity<?> deletarAgenda(Long id) {
        if(!agendaRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O agenda com ID " + id + " não foi encontrado.");
        }

        agendaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> atualizarAgenda(Long agendaId, LocalDate dataAgenda, Integer status) {
        if (!agendaRepository.existsById(agendaId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agenda com ID " + agendaId + " não encontrado.");
        }

        if(dataAgenda != null && validaDataAgendamento(dataAgenda)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data " + dataAgenda + " é uma data inválida. Informe uma data futura ao dia de hoje.");
        }

        if(status != null && !validaStatus(status))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O status deve ser um dos listados: 1 - AGENDADO, 2 - CONFIRMADO, 3 - CANCELADO.");
        }

        StatusAgenda statusAgenda = null;
        if(status != null){
            statusAgenda = StatusAgenda.fromValue(status);
        }
        agendaRepository.updateAgenda(agendaId, dataAgenda, statusAgenda);

        return ResponseEntity.ok(agendaRepository.findById(agendaId));
    }

    public boolean validaStatus(Integer status){
        return status >= 1 && status <= 3;
    }

    private boolean validaDataAgendamento(LocalDate dataAgenda){
        if(dataAgenda.isBefore(LocalDate.now())){
            return true;
        } else
            return false;
    }

    private Agenda validaAgenda(AgendaRequest agendaRequest){
        if (!alunoRepository.existsById(agendaRequest.getAlunoId())) {
            throw new NoSuchElementException("Aluno não encontrado com ID: " + agendaRequest.getAlunoId());
        }
        Optional<Aluno> livro = alunoRepository.findById(agendaRequest.getAlunoId());

        if (!tutorRepository.existsById(agendaRequest.getTutorId())) {
            throw new NoSuchElementException("Tutor não encontrado com ID: " + agendaRequest.getTutorId());
        }
        Optional<Tutor> membro = tutorRepository.findById(agendaRequest.getTutorId());

        return mapDtoToEntity(agendaRequest, livro.get(), membro.get());
    }

    private Agenda mapDtoToEntity(AgendaRequest agendaRequest, Aluno aluno, Tutor tutor){
        StatusAgenda statusAgenda = StatusAgenda.fromValue(agendaRequest.getStatus());
        return new Agenda(aluno, tutor,
                agendaRequest.getDataAgenda(),
                statusAgenda,
                agendaRequest.getTema(),
                agendaRequest.getDescricao());
    }
}
