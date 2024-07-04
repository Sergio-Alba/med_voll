package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.Direccion;

public record DatosDetallesPacientes(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        Direccion direccion
) {
    public DatosDetallesPacientes(Paciente paciente){
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDocumento(),
                paciente.getTelefono(),
                paciente.getDireccion()
        );
    }
}
