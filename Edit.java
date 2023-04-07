public void editar(Pessoa colaborador)
{
    String sql = "UPDATE colaborador SET nome=?, cpf=?, rg=? WHERE lotação=?";
    try {
        PreparedStatement stmt = this.conn.PrepareStatement(sql);
        stmt.setString(1, colaborador.getNome());
        stmt.setString(2, colaborador.getCpf());
        stmt.setString(3, colaborador.getRg());
        stmt.setString(4, colaborador.getLotação());
        stmt.execute ();
    } catch (Exception e) {
        // TODO: handle exception
        System.out.println("Erro ao editar cadastro:" + e.getMessage());

    }
}