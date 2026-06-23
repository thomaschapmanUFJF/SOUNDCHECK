/*  Rafael Magno Campos da Silva - 202435012
    Gabriel Haddad Cyrino Gadioli - 202435026
    Thomas Adam Chapman - 202476030 */

package br.ufjf.dcc.soundcheck.controller;

import br.ufjf.dcc.soundcheck.model.*;
import br.ufjf.dcc.soundcheck.model.exceptions.LoginException;
import java.io.IOException;

public class LoginController {
    public static Usuario autenticar(String email, String senha) {
        try {
            for (Usuario u : Persistencia.carregarUsuarios())
                if (u.getEmail().equals(email) && u.getSenha().equals(senha)) return u;
        } catch (IOException e) { e.printStackTrace(); }
        throw new LoginException("Email ou senha inválidos");
    }
}
