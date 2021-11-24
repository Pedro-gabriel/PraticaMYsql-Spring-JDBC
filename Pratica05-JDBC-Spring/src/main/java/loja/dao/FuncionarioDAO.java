package loja.dao;

import loja.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository // Class que servira para que injetem nela sempre que necessario
public class FuncionarioDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void salva(Funcionario funcionario){
        String sql = "Insert INTO funcionario(cpf, matricula, nome, email, telefone) VALUES (:cpf, :matricula, :nome, :email, :telefone)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("cpf",funcionario.getCpf())
                .addValue("matricula",funcionario.getMatricula())
                .addValue("nome",funcionario.getNome())
                .addValue("email",funcionario.getEmail())
                .addValue("telefone",funcionario.getTelefone());
                jdbcTemplate.update(sql, params);
        //
        //Connection conn = null;
       //PreparedStatement ps = null; //instrução SQL pré-compilada.
//        try{
            //criação da conexão com o banco;
//            conn = ConnectionFactory.createConnectionMYSQL();
//
//            ps = (PreparedStatement) conn.prepareStatement(sql);
//            ps.setString(1, funcionario.getCpf());
//            ps.setString(2, funcionario.getMatricula());
//            ps.setString(3, funcionario.getNome());
//            ps.setString(4, funcionario.getEmail());
//            ps.setString(5, funcionario.getTelefone());
//
//            // execut query
//            ps.execute();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally { // fecha o ps e conn
//            try{
//                if (ps != null){
//                    ps.close();
//                }
//                if(conn != null){
//                    conn.close();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
        //}

    }

    public void updade(Funcionario funcionario){
        String sql = "UPDATE funcionario SET cpf = :cpf, matricula = :matricula, nome = :nome, email = :email, telefone = :telefone WHERE id = :id";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("cpf",funcionario.getCpf())
                .addValue("matricula",funcionario.getMatricula())
                .addValue("nome",funcionario.getNome())
                .addValue("email",funcionario.getEmail())
                .addValue("telefone",funcionario.getTelefone())
                .addValue("id",funcionario.getId());
        jdbcTemplate.update(sql, params);

//        Connection conn = null;
//        PreparedStatement ps = null;
//
//        try {
//            // criar conexao com oo banco
//            conn = ConnectionFactory.createConnectionMYSQL();
//            ps = (PreparedStatement) conn.prepareStatement(sql);
//
//            ps.setString(1, funcionario.getCpf());
//            ps.setString(2, funcionario.getMatricula());
//            ps.setString(3, funcionario.getNome());
//            ps.setString(4, funcionario.getEmail());
//            ps.setString(5, funcionario.getTelefone());
//
//            //Pegando o id para ser atualizado
//            ps.setInt(6, funcionario.getId());
//
//            ps.execute();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if (ps != null) {
//                    ps.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }

    }

    public void delete(int id){
        String sql = "DELETE FROM funcionario Where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        jdbcTemplate.update(sql, params);
//        Connection conn = null;
//        PreparedStatement ps =null;
//        try{
//            conn = ConnectionFactory.createConnectionMYSQL();
//            ps = (PreparedStatement) conn.prepareStatement(sql);
//            ps.setInt(1, id);
//            ps.execute();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//
//                if (ps != null) {
//                    ps.close();
//                }
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
    }

    private Funcionario map(ResultSet rset) throws SQLException{
        Funcionario funcionario = new Funcionario();
        //recuperando os dados
        funcionario.setId(rset.getInt("id"));
        funcionario.setCpf(rset.getString("cpf"));
        funcionario.setMatricula(rset.getString("matricula"));
        funcionario.setNome(rset.getString("nome"));
        funcionario.setEmail(rset.getString("email"));
        funcionario.setTelefone(rset.getString("telefone"));
        return funcionario;
    }
    public List<Funcionario> getFucionario(){
        String sql = "SELECT * FROM funcionario";
        return jdbcTemplate.query(sql, (rs, rowNum) -> map(rs));
        // ruwNum mandando as linhas para o map

//        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        // recuperar os dados do banco
//        ResultSet rset = null;
//
//        try{
//            conn = ConnectionFactory.createConnectionMYSQL();
//            ps = (PreparedStatement) conn.prepareStatement(sql);
//            rset = ps.executeQuery();
//
//            while(rset.next()){
//                Funcionario funcionario = new Funcionario(); // voltando como instancia
//
//                //recuperando os dados
//                funcionario.setId(rset.getInt("id"));
//                funcionario.setCpf(rset.getString("cpf"));
//                funcionario.setMatricula(rset.getString("matricula"));
//                funcionario.setNome(rset.getString("nome"));
//                funcionario.setEmail(rset.getString("email"));
//                funcionario.setTelefone(rset.getString("telefone"));
//
//                funcionarios.add(funcionario);
//
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try{
//                if(rset != null){
//                    rset.close();
//                }
//                if (ps != null){
//                    ps.close();
//                }
//                if(conn != null){
//                    conn.close();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//            return funcionarios;
//        }

    }

    @EntityScan
    public static class Funcionario{

        private int id;
        private String cpf;
        private String matricula;
        private String nome;
        private String email;
        private String telefone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }
    }
}
