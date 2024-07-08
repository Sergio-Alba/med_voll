package med.voll.api.domain.consulta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosCancelamientoConsulta(

        @NotNull
        Long idConsulta,
        @NotBlank
        MotivoCanselamiento motivoCanselamiento

) {
}
