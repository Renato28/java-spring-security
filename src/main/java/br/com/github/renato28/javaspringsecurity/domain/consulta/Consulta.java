package br.com.github.renato28.javaspringsecurity.domain.consulta;

import br.com.github.renato28.javaspringsecurity.domain.medico.Medico;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

    private LocalDateTime data;

    @Deprecated
    public Consulta() {

    }

    public Consulta(Medico medico, DadosAgendamentoConsulta dados) {
        modificarDados(medico, dados);
    }

    public void modificarDados(Medico medico, DadosAgendamentoConsulta dados) {
        this.medico = medico;
        this.paciente = dados.paciente();
        this.data = dados.data();
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
