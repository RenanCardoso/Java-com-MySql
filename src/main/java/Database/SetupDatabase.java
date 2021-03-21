package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDatabase {
    public static void main(String[] args) {

        /* esta classe é uma classe static que inicia o objeto de uma classe cujo seu nome será passado
         * como argumento dentro dessa função. Basicamente vai funcionar em todas as linguagens da JVM
         * onde todas elas irão carregar classes específicas na memória durante o tempo de execução do
         * programa. Entretando existe algumas classes que não necessariamente precisa estar alocado
         * na memória em determinado momento e por isso usamos o Class.forName (que é um classLoader)
         * para que a gente coloque determinada classe e instância da classe dentro da memória
         */
        try {
            /* como estou usando um driver do mysql para Java maior ou igual a versão 5.1 posso mudar para
             * "com.mysql.cj.jdbc.Driver" se for menor deve usar com.mysql.jdbc.Driver
             */
            Class.forName("com.mysql.cj.jdbc.Driver"); //aqui carrego pra dentro da memória
            System.out.println("Carregado com sucesso");
        } catch (ClassNotFoundException e) {
            System.out.println("Falha ao carregar");
            e.printStackTrace();
        }

        //precisamos criar uma nova conexão
        //vou usar uma connection que é uma interface do meu pacote sql
        Connection connection = null;

        //com este try catch vou tratar exceções de sql
        try {
            /* dentro do meu try ele vai tentar iniciar a minha conexão através de um driver manager
             * tem que passar uma url específica, que é padrão para todas as conexões com JDBC
             * na url tem que passar as informações necessárias para a conexão. É preciso acrescentar
             * também um timezone que é uma querystring dentro da minha url de conexão
             */
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_academia?serverTimeZone=UTC", "root", "123456");
            /* as informações são o seu host e a porta, um usuário e senha (não obrigatórios mas pode
             * optar por colocar) e o nome do banco de dados que você vai trabalhar
             */
        } catch (SQLException e){
            e.printStackTrace();
        }

        //agora vamos testar a conexão, se for diferente de null então houve uma conexão
        if(connection == null){
            System.out.println("Falhar ao conectar ao banco de dados");
            return;
        }

        //toda vez que for trabalhar com comandos SQL é necessário criar statements conforme abaixo

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /* statement criado trabalhando com a conexão. Agora consigo atrelar o meu comando SQL dentro deste meu statement
         * Vamos entender o que é o statement: basicamente é uma forma que você tem para acessar
         * o banco de dados e a partir deste acesso você consegue interagir com comandos SQL
         * Os passos são bem simples, você cria o seu statement, cria o seu script SQL, atualiza o seu
         * statement e aí você faz a interação através do seu statement junto ao seu banco de dados
         */

        //crio agora meu comando sql, feito isso já tenho uma tabela e dois campos criados
        String sql = "CREATE TABLE IF NOT EXISTS aluno (idaluno INTEGER NOT NULL AUTO_INCREMENT, nome VARCHAR(255) NOT NULL, PRIMARY KEY (id))";
        /* Lembrando que deve melhorar o CREATE adicionando um if not exists apenas para evitar que ele fique
         * criando esta tabela e gere alguns erros
         */

        //vamos testar o comando DROP TABLE para apagar a tabela
        //String sql = "DROP TABLE movie";

        //com base no meu sql vou atualizar o meu statement. Para atualizá-lo basta eu capturá-lo e rodar o comando:
        try {
            statement.executeUpdate(sql); // este comando é destinado para comandos DDL
            /* statement.executeQuery(sql); este comando é destinado para comandos DML
             * Apesar da linguagem SQL ser uma única linguagem, ela é dividida em tipos de acordo com a funcionalidade
             * dos comandos. DDL e DML são tipos de linguagem SQL. A DDL, Data Definition Language ou LInguagem de
             * Definição de Dados, apesar do nome não interage com os dados e sim com os objetos do banco. São comandos
             * desse tipo o CREATE, o ALTER e o DROP. Já a DML, Data Manipulation Language, ou Linguagem de Manipulação
             * de Dados. interage diretamente com os dados dentro das tabelas. São comandos do DML o INSERT, UPDATE e DELETE.
             */
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        /* automaticamente ele vai associar essa minha query e executar. Executando eu já tenho um retorno ou não
         * vai depender do que eu espero. E quando eu tenho retorno vai utilizar uma outra classe que vai ajudar
         * a trabalhar com este retorno. Neste caso como estou trabalhando com comandos DDL que visam criar algo
         * dentro do meu banco de dados eu não preciso retornar nada, basicamente só vou atualizar(executeQuery)
         * e ao terminar a execução do programa eu terei uma nova tabela criada no banco de dados
         */
    }
}
