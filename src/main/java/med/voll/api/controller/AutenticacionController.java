package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuarios.DatosAutenticacionUsuario;
import med.voll.api.domain.usuarios.Usuario;
import med.voll.api.infra.segurity.DatosJWTToken;
import med.voll.api.infra.segurity.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticacionUsuario(@RequestBody @Valid DatosAutenticacionUsuario dau){
        Authentication AuthToken = new UsernamePasswordAuthenticationToken(dau.login(),dau.clave());
        var usuarioAutenticado =  authenticationManager.authenticate(AuthToken);
        var JWTToken = tokenService.generateToken((Usuario) usuarioAutenticado.getPrincipal());
        return  ResponseEntity.ok(new DatosJWTToken(JWTToken) );
    }
}






