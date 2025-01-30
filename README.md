# PowerPath

PowerPath é uma aplicação desenvolvida em Java com o objetivo de auxiliar proprietários de veículos elétricos a gerenciar e otimizar o uso de seus veículos. A aplicação organiza suas funcionalidades em uma arquitetura modular, garantindo escalabilidade, manutenibilidade e integração com APIs externas.

## 🚀 Funcionalidades Principais

1. **Gerenciamento de Carros e Usuários**:
   - Cadastro e validação de usuários e veículos elétricos.
   - Validações específicas, como tipos de carros permitidos.

2. **Planejamento e Otimização**:
   - Regras de negócios para cálculos de consumo, economia e impacto ambiental.
   - Gerenciamento eficiente dos dados de recarga.

3. **API RESTful**:
   - Endpoints para integração com sistemas e consumo de dados de usuários e veículos.
   - Configuração de CORS para segurança.

## 📂 Estrutura de Pacotes

### `bo` (Business Object)
Gerencia as regras de negócio:
- `CarroBO.java`: Regras relacionadas aos veículos.
- `UsuarioBO.java`: Validações e processos de usuários.

### `dao` (Data Access Object)
Gerencia o acesso ao banco de dados:
- `CarroDAO.java` e `UsuarioDAO.java`: Operações de CRUD para carros e usuários.
- `ConnectionFactory.java`: Gerencia conexões com o banco.
- `Repository.java`: Centraliza as operações de persistência.

### `resource` (API REST)
Gerencia os endpoints RESTful:
- `CarroResource.java` e `UsuarioResource.java`: Endpoints para carros e usuários.
- `CorsFilter.java`: Configuração de CORS.

### `to` (Transfer Object)
Define objetos para troca de dados:
- `CarroTO.java`: Representação de dados do veículo.
- `UsuarioTO.java`: Representação de dados do usuário.

### `Exception`
Trata exceções específicas:
- `TipoCarroInvalidoException.java`: Exceção para validação de tipos de carro.

### `resources`
Contém configurações:
- `application.properties`: Configurações do ambiente e banco de dados.

### `Main.java`
Ponto de entrada da aplicação.

## Avisos
- Os endpoints não estão funcionando porque eles estão ligados ao banco de dados Oracle da faculdade, e para não expor dados importantes eu os retirei. Para arrumá-los só precisaria mudar para MySQL, o que eu posso fazer futuramente.
- Aplicação está funcionando em Docker também, fiz a maioria com a ajuda do GPT e gostei muito de usa-lo! Realmente uma boa ferramenta.
