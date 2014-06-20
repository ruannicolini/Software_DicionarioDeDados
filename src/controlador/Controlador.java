/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import dao.BdDao;
import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import negocio.BD;

/**
 *
 * @author Ruan
 */
public class Controlador {
    BdDao bdDao;
    public Controlador() throws Exception, SQLException  {
        bdDao = new BdDao();
    }
    
    public void listarBancos(JComboBox combo) throws SQLException, Exception{
        List<BD> lista = bdDao.ConsultarTodosBD();
        combo.setModel( new DefaultComboBoxModel( lista.toArray() ) );
    }
    
}
