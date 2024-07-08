package med.voll.api.domain.consulta;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;
import org.hibernate.annotations.CollectionId;

import java.time.LocalDateTime;

@Table(name="consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime fecha;

    @Column(name = "activo_cancelado")
    @Enumerated(EnumType.STRING)
    private MotivoCanselamiento motivoCanselamiento;


    public Consulta(Medico m, Paciente p, LocalDateTime fecha){
        this.medico = m;
        this.paciente = p;
        this.fecha = fecha;
    }
    public void cancelar(MotivoCanselamiento motivo){
        this.motivoCanselamiento = motivo;
    }
}
















