package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;

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
    @Embedded
    private Direccion direccion;
    private Boolean activo;

    public Paciente(DatosRegistroPaciente d) {
        this.activo = true;
        this.nombre = d.nombre();
        this.email = d.email();
        this.telefono = d.telefono();
        this.documento = d.documento();
        this.direccion = new Direccion(d.direccion());
    }

    public void actualizarDatosPaciente(DatosActualizarPaciente datos) {
        if (datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if (datos.telefono() !=null){
            this.documento = datos.telefono();
        }
        if (datos.direccion() != null){
            this.direccion.actualizarDireccion(datos.direccion());
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}
