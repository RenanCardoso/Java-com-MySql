package Models;
import Modules.Controllers.AlunoController;

public class Aluno extends AlunoController {

    private Integer id;
    private String name;

    /* uma model é basicamente o espelho do nosso banco de dados. Basicamente cada instância que eu
     * criar da minha Models.Aluno vai ser um novo registro que vou gerar dentro do meu banco de dados na tabela movie
     */

    //construtor
    public Aluno(){}

    public Aluno(String name){
        this.name = name;
    }

    public Aluno(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
