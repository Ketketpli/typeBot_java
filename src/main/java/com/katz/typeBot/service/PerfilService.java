package com.katz.typeBot.service;

import com.katz.typeBot.dto.PerfilRequestDto;
import com.katz.typeBot.dto.PerfilResponseDto;
import com.katz.typeBot.exceptions.PerfilNotFoundException;
import com.katz.typeBot.model.Perfil;
import com.katz.typeBot.repository.PerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilResponseDto createPerfil(PerfilRequestDto perfilRequestDto) {
        Perfil perfil = new Perfil();
        perfil.setNome(perfilRequestDto.nome());
        perfil.setEmail(perfilRequestDto.email());
        perfil.setTelefone(perfilRequestDto.telefone());
        perfil.setLinkedin(perfilRequestDto.linkedin());
        perfil.setCurriculo(perfilRequestDto.curriculo());
        perfil.setEndereco(perfilRequestDto.endereco());
        perfil.setSenioridade(perfilRequestDto.senioridade());
        perfil.setPretensaoSalarial(perfilRequestDto.pretensaoSalarial());
        perfil.setPcd(perfilRequestDto.pcd());

        Perfil savePerfil = perfilRepository.save(perfil);

        return new PerfilResponseDto(
                savePerfil.getId(),
                savePerfil.getNome(),
                savePerfil.getEmail(),
                savePerfil.getTelefone());
    }

    public PerfilRequestDto updatePerfil(Long id, PerfilRequestDto perfilRequestDto) {
        Perfil newPerfil;
        newPerfil = perfilRepository.findById(id).orElseThrow(PerfilNotFoundException::new);
        newPerfil.setNome(perfilRequestDto.nome());
        newPerfil.setTelefone(perfilRequestDto.telefone());
        newPerfil.setLinkedin(perfilRequestDto.linkedin());
        newPerfil.setCurriculo(perfilRequestDto.curriculo());
        newPerfil.setEndereco(perfilRequestDto.endereco());
        newPerfil.setSenioridade(perfilRequestDto.senioridade());
        newPerfil.setPretensaoSalarial(perfilRequestDto.pretensaoSalarial());
        newPerfil.setPcd(perfilRequestDto.pcd());
        Perfil savePerfil = perfilRepository.save(newPerfil);

        return new PerfilRequestDto(
                savePerfil.getNome(),
                savePerfil.getEmail(),
                savePerfil.getTelefone(),
                savePerfil.getLinkedin(),
                savePerfil.getCurriculo(),
                savePerfil.getEndereco(),
                savePerfil.getSenioridade(),
                savePerfil.getPretensaoSalarial(),
                savePerfil.getPcd());
    }

    public void deletePerfilById(Long id) {
        perfilRepository.deleteById(id);
    }

    public List<PerfilResponseDto> getPerfil() {
        List<Perfil> listaDePerfis = perfilRepository.findAll();
        return listaDePerfis.stream()
                .map(perfil -> new PerfilResponseDto(perfil.getId(), perfil.getNome(), perfil.getEmail(), perfil.getTelefone()))
                .toList();
    }

    public List<PerfilRequestDto> getPerfilDetalhado() {
        List<Perfil> listaDePerfisDetalhados = perfilRepository.findAll();
        return listaDePerfisDetalhados.stream()
                .map(perfil -> new PerfilRequestDto(perfil.getNome(),
                        perfil.getEmail(),
                        perfil.getTelefone(),
                        perfil.getLinkedin(),
                        perfil.getCurriculo(),
                        perfil.getEndereco(),
                        perfil.getSenioridade(),
                        perfil.getPretensaoSalarial(),
                        perfil.getPcd()))
                .toList();
    }

    public PerfilResponseDto findByEmail(String email) {
        Perfil perfil = perfilRepository.findByEmail(email).orElseThrow(PerfilNotFoundException::new);

        return new PerfilResponseDto(perfil.getId(), perfil.getNome(), perfil.getEmail(), perfil.getTelefone());
    }

}
