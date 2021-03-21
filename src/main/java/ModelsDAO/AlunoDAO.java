package ModelsDAO;/* geralmente você tem um DAO pra cada model que você cria, ou em projetos um pouco mais organizados
 * com níveis de dados um pouco mais complexos trabalha com DAOs genéricos onde você cria uma interface
 * utiliza-se de um contrato da nossa interface e através daquelas assinaturas definidas na interface
 * você vai conseguir implementar um mesmo padrão, uma mesma estrutura dentro de diversas classes DAO.
 * Ou ainda você pode criar uma classe abstrata que visa implementar de fato implementar algum trecho
 * de código que vai manipular os seus dados. Existem diversas variações de padrões, mas vamos utilizar
 * o padrão mais básico(e mais simples) que é uma DAO pra cada model que visa abstrair a manipulação de
 * dados a onde certas classes não precisarão saber o que está acontecendo, só precisarão enviar os nossos
 * dados.
 */

import Database.ConnectionFactory;
import Models.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    /* Agora que já conseguimos ter a nossa conexão com o banco em qualquer local fica fácil de chamá-la
     * baseado nisso então podemos chamar a conexão dentro desta classe.
     */

    //a primeira coisa a fazer é criar o atributo connection
    private Connection connection = null;
    private PreparedStatement statement = null;

    //ao criar uma nova instância de ModelsDAO.AlunoDAO automaticamente a minha connection irá criar um novo objeto
    public AlunoDAO(){

        //está recebendo uma nova instância que vai trazer contigo a possibilidade de conexão junto ao mysql
        connection = new ConnectionFactory().getConnection();
        /* a partir deste momento eu já tenho minha conexão disponível dentro desta minha classe para
         * manipular os meus dados através de comandos DML (INSERT, UPDATE e DELETE.)
         */
    }

    //temos 4 métodos para 4 itens do menu que estão disponíveis

    //se eu estou listando, espero receber uma lista como retorno
    /* então eu crio uma List(utiliza uma interface List), vou me basear na model Models.Aluno já que estou dentro
     * do meu DAO de movie (ModelsDAO.AlunoDAO)
     */
    public List<Aluno> findAll() throws SQLException {
        //automaticamente a minha String query vai receber um script sql
        String query = "SELECT * FROM aluno";
        //feito isso eu vou atribuir a uma lista Models.Aluno que será igual a uma nova ArrayList
        List<Aluno> alunos = new ArrayList<Aluno>();
        /* como que eu faço para que eu consiga incluir o meu resultado dentro dessa lista?
         * Antes de fazer esse processo é necessário criarmos nossso statement e partir disso
         * começar a trabalhar de fato com a atribuição da lista
         */

        //vamos atualizar com a query sql
        statement = connection.prepareStatement(query);

        /* vou dizer que eu desejo executar a minha query, porém ela será com retorno de ResultSet.
         * ResultSet é a forma que tenho pra capturar um retorno do meu banco de dados pra dentro
         * do JAVA
         */
        try (ResultSet res = statement.executeQuery()) {

            /* dessa forma irá me retornar um iterable(que é um tipo de lista) então posso usar um while
             * pra percorrer essa lista e posso adicionar dentro da minha lista alunos
             */
            while (res.next()) {
                alunos.add(new Aluno(res.getInt("idaluno"), res.getString("nome")));
            }
        }

        //feito isso tenho que retornar alunos
        return alunos;

        /* Resumindo o que aconteceu nessa função inteira: basicamente criamos uma listagem completa, onde
         * utilizo o meu PreparedStatement, busco os meus resultados usando a minha Interface ResultSet,
         * faço uma iteração adicionando todos os itens dentro do ArrayList e devolvendo ela para onde quiser
         */
    }

    /* método usado para poder alterar o aluno, para alterar o aluno correto primeiramente preciso pegar
     * o id do aluno que vou alterar
     */
    public Aluno findById(Integer id) throws SQLException {
        //crio a minha query selecionando tudo da tabela aluno filtrando apenas por um id
        String query = "SELECT * FROM aluno WHERE idaluno = ?";

        statement = connection.prepareStatement(query);

        //aqui vou setar o meu valor
        statement.setInt(1, id);

        //logo depois eu faço o meu ResultSet
        ResultSet res = statement.executeQuery();

        //meu Models.Aluno será criado para ser devolvido então crio uma instância nula
        Aluno aluno = null;
        while (res.next()){
            //atribuo uma nova instância aqui dentro onde vou passar o meu id e o name do aluno
            aluno = new Aluno(res.getInt("idaluno"), res.getString("nome"));
        }

        return aluno;
    }

    /* para iniciar a criação, precisamos voltar para aquela parte de statement onde fizemos o uso de nosso
     * statement e entender como eles funcionam. VERIFICAR IMAGEM tipos-de-statements. Dentro do meu DAO eu
     * vou ter que utilizar o PreparedStatement, porque a todo momento eu vou ter uma alteração
     */

    /* a criação eu posso ou não esperar receber alguma coisa, vai depender de como deseja trabalhar
     * caso eu esteja adicionando algo eu tenho que saber o que vou adicionar. Então vou me basear que
     * estou adicionando uma nova model Models.Aluno porque estou dentro do meu DAO de Models.Aluno (ModelsDAO.AlunoDAO)
     */
    public void insert(Aluno aluno) throws SQLException {
        //lembrando que o id já está como autoincrement
        String query = "INSERT INTO aluno (nome) VALUES (?)";

        //mais uma vez vamos usar o PreparedStatement e agora de fato vamos usar ele como deve ser utilizado
        statement = connection.prepareStatement(query);


        //no lugar da interrogação eu vou passar os dados de forma dinâmica em runtime, para fazer isso:
        statement.setString(1, aluno.getName()); //capturo através do meu getName
        /* passei o index que está localizado, ou seja, a onde eu desejo inserir, no meu caso só tenho uma
         * interrogação então eu sei que é na minha primeira posição e o que eu desejo inserir
         */

        //por fim, mando executar o meu PreparedStatement (executar a minha ação)
        statement.execute();

        /* Resumindo o que aconteceu nessa função inteira: basicamente nós fizemos uma query sql dizendo que
         * eu vou inserir um valor e não disse qual é esse valor (por isso ficou com interrogação), porém
         * ao fazer o update do meu PreparedStatement eu disse que estou setando um valor dinâmico durante o
         * tempo de execução e esse valor vai vir dinâmico e logo depois eu executei a minha ação.
         */
    }

    /* a parte de update eu posso ou não retornar dado. Tenho que primeiramente receber o meu dado antigo
     * e o meu dado novo que quero trabalhar
     */
    public void update(Aluno alunoOld, Aluno alunoNew) throws SQLException {
        /* vamos indicar o que eu quero fazer no update, neste caso só temos o nosso name onde vamos
         * receber o dado de maneira dinâmica (usando a interrogação) e o id será um dado dinâmico também
         */
        String query = "UPDATE aluno SET nome = ? WHERE idaluno = ?";


        statement = connection.prepareStatement(query);

        //seto meus valores
        statement.setString(1, alunoNew.getName());
        statement.setInt(2, alunoNew.getId());
        statement.execute();
    }

    // a parte de deletar eu vou receber o que de fato eu desejo deletar
    public void remove(Aluno aluno) throws SQLException {
        String query = "DELETE FROM aluno WHERE idaluno = ?"; //para remover em tempo de execução usa interrogação

        statement = connection.prepareStatement(query);

        //logo depois seto o meu valor dinâmico
        statement.setInt(1, aluno.getId()); //se usa o setInt já que estou trabalhando com o meu id (interrogação) e eu sei que é inteiro
        //quero trabalhar com índice 1 por que só tenho uma opção

        //finalizando
        statement.execute();

        //Assim estamos prontos para nosso remove
    }
}
/* Agora temos nossa classe DAO implementada de forma simples se comparado a outras formas de implementação
 * que a gente pode trabalhar, porém é uma classe que já está totalmente montada e funcional.
 * Feito isso encerramos toda a parte de infraestrutura pra montar nosso objetivo que é poder manipular as
 * tabelas do banco (neste caso específico uma tabela)
 */