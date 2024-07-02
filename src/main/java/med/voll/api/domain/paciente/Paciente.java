package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;
import med.voll.api.domain.medico.DatosDeRegistroMedico;

@Table(name="pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente d) {
        this.nombre = d.nombre();
        this.email = d.email();
        this.telefono = d.telefono();
        this.documento = d.documento();
        this.activo = true;
        this.direccion = new Direccion(d.direccion());
    }

    public void actualizarDatosPaciente(DatosActualizarPaciente datosActualizarPaciente) {
        if (datosActualizarPaciente.nombre() != null){
            this.nombre = datosActualizarPaciente.nombre();
        }
        if (datosActualizarPaciente.documento() !=null){
            this.documento = datosActualizarPaciente.documento();
        }
        if (datosActualizarPaciente.direccion() != null){
            this.direccion = direccion.actualizarDireccion(datosActualizarPaciente.direccion());
        }
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
