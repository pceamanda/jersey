package fiap.scj.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Path("/aluno")
public class AlunoResource {

    private static List<Aluno> alunos;

    static {
        alunos = Arrays.asList(new Aluno("amanda", 123),
                               new Aluno("aluno", 321),
                               new Aluno("teste", 678));
    }

    @GET
    @Produces({APPLICATION_JSON, APPLICATION_XML})
    public List<Aluno> getAlunosRest(){
        return alunos;
    }

    @GET
    @Path("/{ra}")
    @Produces({APPLICATION_JSON, APPLICATION_XML})
    public Response getAlunos(@PathParam("ra") Integer ra){
        Aluno alunoSelecionado =  alunos.stream().filter(
                aluno -> aluno.getRa().equals(ra)).findFirst().orElse(null);

        if(alunoSelecionado == null){
            return Response.noContent().build();
        }

        return Response.ok(alunoSelecionado).build();
    }

}
