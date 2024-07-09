package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.paciente.DatosRegistroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("deberia retornar nulo cuando el medico se encuentra en consulta con otro paciente en ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario1() {
        //GIVEN
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.TUESDAY))
                .atTime(10,0);

        var medico = registrarMedico("Jose","joselito@medvoll.com","123132",Especialidad.CARDIOLOGIA) ;
        var paciente = registrarPaciente("Snowt", "jhontSnot@gmail.com","123456");
        registrarConsulta(medico,paciente,proximoLunes10H);
        //WHEN
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA, proximoLunes10H);

        //THEN
        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("deberia retornar un medico cuando realice la consulta el la base de datos para ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario2() {
        //GIVER
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.TUESDAY))
                .atTime(10,0);

        var medico = registrarMedico("Jose","joselito@medvoll.com","123132",Especialidad.CARDIOLOGIA) ;
        //WHEN
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA, proximoLunes10H);
        //THEN
        assertThat(medicoLibre).isEqualTo(medico);
    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha){
        em.persist(new Consulta(null, medico , paciente, fecha, null));
    }
    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad){
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return  medico;
    }
    private Paciente registrarPaciente(String nombre, String email, String documento){
        var paciente = new Paciente(datoPaciente(nombre,email,documento));
        em.persist(paciente);
        return paciente;
    }
    private DatosDeRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad){
        return new DatosDeRegistroMedico(
                nombre,
                email,
                "3928924",
                documento,
                especialidad,
                datosDireccion()
        );
    }
    private DatosRegistroPaciente datoPaciente(String nombre, String email, String documento){
        return new DatosRegistroPaciente(
                nombre,
                email,
                "234123421",
                documento,
                datosDireccion()
        );
    }
    private DatosDireccion datosDireccion(){
        return new DatosDireccion(
                " loca",
                "azul",
                "acapulpo",
                "321",
                "12"
        );
    }
//    @Test
//    void findActivoById() {
//    }
}
