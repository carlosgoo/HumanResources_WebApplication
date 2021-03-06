/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package sv.bitlab.manejadores;


import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sv.bitlab.controladores.TipoUsuarioControlador;
import sv.bitlab.controladores.UsuarioControlador;
import sv.bitlab.entidades.TipoUsuario;
import sv.bitlab.entidades.Usuario;

import sv.bitlab.utilidades.EncriptacionTexto;

/**
 *
 * @author Oscar
 */
@ManagedBean
@SessionScoped
public class UsuarioManejador extends ManejadorAbstracto<Usuario> {
    private static final Logger log = LoggerFactory.getLogger(UsuarioManejador.class);
    
    private List<TipoUsuario>tipoUsList;
    private TipoUsuarioControlador tipoUsControl = new TipoUsuarioControlador();
    private UsuarioControlador usuarioControlador;
    private String contrasena;
    private String codigo;
    
    public UsuarioManejador() {
        super(Usuario.class);
        usuarioControlador =  new UsuarioControlador();
    }
    
    //Precargar entidades para llenar el selectOneMenu option
    @PostConstruct
    public void CargarTipoUsList(){
        tipoUsList = tipoUsControl.encontrarEntidades();
    }
    
    //encriptando la contrasena de usuarios hacia la base de datos
    @Override
    public void guardarEntidad() {
        log.info("Guardando la identidad");
        if(getEntidadSeleccion().getUsrContrasena() == null || !getEntidadSeleccion().getUsrContrasena().equals(contrasena)){
            EncriptacionTexto encriptacionTexto = new  EncriptacionTexto();
            getEntidadSeleccion().setUsrContrasena(encriptacionTexto.getTextoEncriptado(contrasena));
        }
        super.guardarEntidad();
    }
    
    
    public void completarInformacion(Usuario usr){
        log.info("ingresando a crear la identidad");
        setEntidadSeleccion(usr);
        this.contrasena = getEntidadSeleccion().getUsrContrasena();
    }
    
    public void validarAcceso(){
        
    }
    
    @Override
    public UsuarioControlador getControlador() {
        return usuarioControlador;
    }
    
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<TipoUsuario> getTipoUsList() {
        return tipoUsList;
    }

    public void setTipoUsList(List<TipoUsuario> tipoUsList) {
        this.tipoUsList = tipoUsList;
    }

    public TipoUsuarioControlador getTipoUsControl() {
        return tipoUsControl;
    }

    public void setTipoUsControl(TipoUsuarioControlador tipoUsControl) {
        this.tipoUsControl = tipoUsControl;
    }

    public UsuarioControlador getUsuarioControlador() {
        return usuarioControlador;
    }

    public void setUsuarioControlador(UsuarioControlador usuarioControlador) {
        this.usuarioControlador = usuarioControlador;
    }
    
    
    
}
