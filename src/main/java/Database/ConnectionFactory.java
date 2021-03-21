package Database;/*toda vez que eu quiser trabalhar com alguma coisa relacionada a meu banco de dados irei
 * criar uma connection factory, que basicamente vai criar uma nova conexão toda vez que precisar.
 * A cada vez que eu chamá-la eu vou ter uma nova conexão em aberto e assim vou poder utilizar em
 * qualquer local e a qualquer momento que eu desejar. Não importa muito onde ela está e nem mesmo
 * de onde ela vem, toda vez que eu chamar já vou ter essa conexão pronta para uso.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    /* Factory é um DesignPattern(padrão de projeto) que estamos implementando aqui que prega que o
     * encapsulamento da construção de objetos compostos(complicados). Então através destes encapsulamentos
     * nós conseguimos criar e trazer uma nova conexão a todo momento que for necessário trabalhando com
     * nosso design pattern Factory
     */

    //vou começar definindo alguns atributos pré-definidos
    private final String URL = "jdbc:mysql://localhost:3306/sistema_academia?serverTimeZone=UTC";
    private final String USER = "root";
    private final String PASSWORD = "123456";
    private final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver"; /* como estou usando um driver do mysql
     * para Java maior ou igual a versão 5.1 posso mudar para "com.mysql.cj.jdbc.Driver" se for menor
     * deve usar com.mysql.jdbc.Driver */

    private Connection createConnection(){
        Connection connection = null;

        try {
            Class.forName(DRIVER_CLASS);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

    public Connection getConnection(){
        return createConnection();
    }
}
