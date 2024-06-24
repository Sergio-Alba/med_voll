package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    // Post para enviar datos a la base de datos
    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosDeRegistroMedico datosDeRegistroMedico){
        medicoRepository.save(new Medico(datosDeRegistroMedico));
        System.out.println("se a guardado en la base de datos");
    }
    // Get pata obtener los datos de la base de datos
    @GetMapping
    public Page<DatosListadoMedico> listadoMedico(@PageableDefault(size = 2 ) Pageable pageable){
        return medicoRepository.findAll(pageable).map(DatosListadoMedico::new);
    }
    // Put para actualizar los datos de la base de datos
    @PutMapping
    @Transactional
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatosMedico(datosActualizarMedico);
    }
}
