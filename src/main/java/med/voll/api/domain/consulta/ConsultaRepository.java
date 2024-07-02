package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}

//CREATE TABLE  consultas(
//        id bigint not null auto_increment,
//        medico_id bigint not null,
//        paciente_id bigint not null,
//        data datatime not null,
//
//        primary key(id),
//
//        constraint fk_consultas_medico_id foreign key(medico_id) references medicos(id),
//        constraint fk_consultas_paciente_id foreign Key(paciente_id) references pacientes(id)
//
//);
//CREATE TABLE pacientes(
//        id bigint not null auto_increment,
//        nombre varchar(100) not null,
//        email varchar(100) not null unique,
//        documento varchar(14) not null unique,
//        calle varchar(100) not null,
//        distrito varchar(100) not null,
//        complemento varchar(100) ,
//        numero varchar(20),
//        cuidad varchar(100) not null,
//        telefono varchar(20) not null,
//        activo tinyint not null,
//
//        primary key(id)
//);

















