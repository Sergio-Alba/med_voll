package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

//    @PostMapping
//    @Transactional
//    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPaciente datos, UriComponentsBuilder uriBuilder){
//        var paciente = new Paciente(datos);
//        repository.save(paciente);
//
//        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(paciente.getId()).toUri();
//        return ResponseEntity.created(uri).body(new DatosDetallesPacientes(paciente));
//    }
//    @GetMapping
//    public ResponseEntity<Page<DatosListaPacientes>> listar(@PageableDefault(size = 2)){
//        var page = repository.findAll();
//        return ResponseEntity.ok(page);
//    }
}























