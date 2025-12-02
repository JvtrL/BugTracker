<p align="center">
  <img src="https://img.shields.io/badge/Java-POO-orange?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/Bug%20Tracker-Sistema%20de%20Gerenciamento-blueviolet?style=for-the-badge" />
</p>

<h1 align="center">🪲 Bug Tracker – Sistema de Gerenciamento de Bugs</h1>

<p align="center">
  Projeto desenvolvido para a disciplina de <strong>Programação Orientada a Objetos</strong>, 
  aplicando herança, polimorfismo, abstração, interfaces, threads, pacotes, leitura e escrita de arquivos.
</p>

<p align="center">
  <strong>Java • POO • Threads • Arquivos • Engenharia de Software</strong>
</p>

<hr>

# 🪲 Bug Tracker – Sistema de Gerenciamento de Bugs (Java, POO)

Este projeto é um **sistema de rastreamento de bugs** desenvolvido em Java, seguindo todos os princípios da **Programação Orientada a Objetos** exigidos pela disciplina.  

O objetivo é permitir o registro, consulta, filtragem, atualização e remoção de bugs, com persistência em arquivo e execução de tarefas automáticas por meio de threads.

---

# 📌 Funcionalidades Principais

- Registrar novos bugs (Crítico ou Menor)
- Listar todos os bugs cadastrados
- Filtrar por:
  - Status (Aberto, Em andamento, Resolvido, Fechado)
  - Prioridade (Baixa, Média, Alta, Crítica)
  - Desenvolvedor responsável
- Atualizar status de bugs
- Remover bugs
- Persistência automática em arquivo `.txt`
- **Auto-save a cada 30 segundos** usando Threads
- Notificações automáticas para bugs críticos (via interface)
- Geração automática de ID

# 🧱 Estrutura do Projeto

O projeto segue uma arquitetura organizada em pacotes, conforme boas práticas e exigências da disciplina:
```
src/main/java/br/bugtracker
├── aplicacao
│ └── Main.java
├── arquivos
│ ├── BugRepositorio.java
│ └── BugFormatadorArquivo.java
├── tarefas
│ └── TarefaAutoSalvar.java
├── servicos
│ └── GerenciadorDeBugs.java
└── modelo
├── Bug.java
├── BugCritico.java
├── BugMenor.java
├── Desenvolvedor.java
├── Notificavel.java
├── Prioridade.java
└── StatusBug.java
```

# 🧩 Conceitos de POO utilizados

### ✔ Encapsulamento  
Atributos privados com getters e setters.

### ✔ Herança  
`BugCritico` e `BugMenor` herdam de `Bug`.

### ✔ Polimorfismo  
Chamadas como `bug.calcularImpacto()` executam versões diferentes do método.

### ✔ Classe Abstrata  
`Bug` é abstrata e define comportamento base para os outros bugs.

### ✔ Interface  
`Notificavel` é implementada por `BugCritico`.

### ✔ Enum  
`Prioridade` e `StatusBug`.

---

# 📝 Persistência (Leitura e Escrita de Arquivos)

O sistema salva automaticamente no arquivo:
```
dados/bugs.txt
```
Operações que geram salvamento:

- Registrar bug  
- Atualizar status  
- Remover bug  
- Ao sair  
- **Auto-save a cada 30s** com Threads

Utiliza Java NIO:

- `Files.readAllLines`
- `Files.write`
- `Path`
- `Paths`

---

# ⏱ Threads

### ✔ TarefaAutoSalvar (implements Runnable)

Executa periodicamente salvamentos automáticos do sistema sem travar o funcionamento do menu.

---

# 🧪 Exemplo de Menu
```
===== BUG TRACKER =====
1 - Registrar novo bug
2 - Listar todos os bugs
3 - Listar bugs por status
4 - Listar bugs por prioridade
5 - Atualizar status de um bug
6 - Remover bug
0 - Sair

---
```
# 📂 Exemplo de Registro no Arquivo
```
CRITICO;1;Erro no Login;Sistema fecha ao logar;ALTA;ABERTO;10;Joao;joao@gmail.com
```
O arquivo é formatado automaticamente pela classe `BugFormatadorArquivo`.

---

# 👨‍🏫 Requisitos da disciplina atendidos

| Requisito | Status |
|----------|--------|
| Classes, atributos e métodos | ✔ |
| Herança | ✔ |
| Polimorfismo | ✔ |
| Interface ou classe abstrata | ✔ (ambas) |
| Encapsulamento | ✔ |
| Threads | ✔ |
| Leitura e escrita de arquivos | ✔ |
| Organização em pacotes | ✔ |
| Projeto na área de Engenharia de Software | ✔ |


# 📜 Licença

Projeto acadêmico – uso educacional.

---
# 🤝 Autores

**Rafael Mello Barbosa da Silva**  
**Kaio Henrique Pereira**  
**Túlio Henrique Gonçalves Simões**  
**João Vitor Lima da Silveira**

Disciplina: **C06 - Programação Orientada a Objetos**  
Professor: **Christopher Lima**
```
