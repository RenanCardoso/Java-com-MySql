public class Movie {

    private Integer id;
    private String name;

    /* uma model é basicamente o espelho do nosso banco de dados. Basicamente cada instância que eu
     * criar da minha Movie vai ser um novo registro que vou gerar dentro do meu banco de dados na tabela movie
     */

    //construtor
    public Movie(){}

    public Movie(String name){
        this.name = name;
    }

    public Movie(Integer id, String name){
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
