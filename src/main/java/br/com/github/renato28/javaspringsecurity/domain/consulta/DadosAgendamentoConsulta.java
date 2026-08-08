package br.com.github.renato28.javaspringsecurity.domain.consulta;

import br.com.github.renato28.javaspringsecurity.domain.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        Long id,
        Long idMedico,

        @NotNull
        String paciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
