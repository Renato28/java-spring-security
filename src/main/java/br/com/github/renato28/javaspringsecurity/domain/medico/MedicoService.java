package br.com.github.renato28.javaspringsecurity.domain.medico;

import br.com.github.renato28.javaspringsecurity.domain.RegraDeNegocioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Page<DadosListagemMedico> listar(Pageable paginacao) {
        return medicoRepository.findAll(paginacao)
                .map(DadosListagemMedico::new);
    }

    @Transactional
    public void cadastrar(DadosCadastroMedico dados) {
        if (medicoRepository.isJaCadastrado(dados.email(), dados.crm(), dados.id())) {
            throw new RegraDeNegocioException("E-mail ou CRM já cadastrado para outro médico");
        }

        if (dados.id() == null) {
            medicoRepository.save(new Medico(dados));
        } else {
            var medico = medicoRepository.findById(dados.id()).orElseThrow();
            medico.atualizadosDados(dados);
        }
    }

    public DadosCadastroMedico carregarPorId(Long id) {
        var medico = medicoRepository.findById(id).orElseThrow();
        return new DadosCadastroMedico(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade());
    }

    public void excluir(Long id) {
        medicoRepository.deleteById(id);
    }

    public List<DadosListagemMedico> listarPorEspecialidade(Especialidade especialidade) {
        return medicoRepository.findByEspecialidade(especialidade)
                .stream()
                .map(DadosListagemMedico::new).toList();
    }
}
