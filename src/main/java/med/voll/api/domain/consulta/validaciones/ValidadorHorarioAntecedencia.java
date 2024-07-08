package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("validadorHorarioAntecedenciaCancelamiento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamientoDeConsulta {
    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosCancelamientoConsulta datos){
        var consulta = repository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaEnHora = Duration.between(ahora,consulta.getFecha()).toHours();

        if(diferenciaEnHora < 24 ){
            throw new ValidationException("Consulta solo puede ser cancelada con antecedencia minimo de 24 Horas.");
        }
    }
}
























