# SoundCheck

Sistema de gestão para estúdio musical — reservas de salas de ensaio e aluguel de equipamentos extras.

**Disciplina:** Modelagem de Sistemas — DCC/UFJF  
**Integrantes:**
- Rafael Magno Campos da Silva — 202435012
- Gabriel Haddad Cyrino Gadioli — 202435026
- Thomas Adam Chapman — 202476030

---

## Como executar

**Pré-requisitos:** Java 21, Maven 3.8+

```bash
mvn compile
mvn exec:java
```

---

## Estrutura

```
src/main/java/br/ufjf/dcc/soundcheck/
├── Main.java
├── model/
│   ├── enums/          TipoUsuario, ReservaStatus
│   ├── exceptions/     LoginException
│   ├── Usuario.java    (abstrato)
│   ├── Cliente.java
│   ├── Funcionario.java
│   ├── Gerente.java
│   ├── Endereco.java
│   ├── Sala.java
│   ├── EquipamentoExtra.java
│   ├── Reserva.java
│   └── Persistencia.java
├── controller/
│   ├── LoginController.java
│   ├── ClienteController.java
│   ├── FuncionarioController.java
│   ├── SalaController.java
│   ├── EquipamentoController.java
│   └── ReservaController.java   ← TODO aqui
└── view/
    ├── TelaLogin.java
    ├── TelasCliente/
    │   ├── TelaMenuCliente.java
    │   ├── TelaReserva.java
    │   └── TelaDadosPessoaisCliente.java
    ├── TelasFuncionario/
    │   ├── TelaMenuFuncionario.java
    │   ├── TelaCadastroCliente.java
    │   ├── TelaGerenciarReservas.java
    │   ├── TelaRegistrarSalas.java
    │   └── TelaRegistrarEquipamentos.java
    └── TelasGerente/
        ├── TelaMenuGerente.java
        └── TelaCadastroFuncionario.java
data/                   ← arquivos .txt gerados em runtime
```
