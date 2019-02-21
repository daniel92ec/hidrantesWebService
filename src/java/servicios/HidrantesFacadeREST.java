/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Daniel
 */
@Stateless
@Path("servicios.hidrantes")
public class HidrantesFacadeREST extends AbstractFacade<Hidrantes> {

    @PersistenceContext(unitName = "HidrantesPU")
    private EntityManager em;

    public HidrantesFacadeREST() {
        super(Hidrantes.class);
    }
    
    public static double distance(double lat1, double lat2, double lon1,
        double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }
    
    @GET
    @Path("/registro")
    @Produces({"application/json;charset=utf-8"})
    public Response registrar(@QueryParam("latitud") String latitud,@QueryParam("longitud") String longitud) throws JSONException, Exception {
      
       JSONArray jsonArray= new JSONArray();
            
        String respuesta = "";
        String obs = "";
        List<Hidrantes> listaHidrantes = new ArrayList<>();
        List<Hidrantes> listaHidrantesCercanos = new ArrayList<>();
        
        Query hidrantes = em.createQuery("SELECT o FROM Hidrantes o");
        listaHidrantes = hidrantes.getResultList();
        
        for (Hidrantes h : listaHidrantes){
                 
            if ( distance(Double.valueOf(latitud), h.getLatitud().doubleValue(), Double.valueOf(longitud), h.getLongitud().doubleValue())< 350){
                listaHidrantesCercanos.add(h);
                JSONObject aux=new JSONObject();
                aux.put("id", h.getId());
                aux.put("longitude", h.getLongitud());
                aux.put("latitude", h.getLatitud());
                aux.put("description", h.getInterseccion());
                aux.put("name", h.getPrincipal());
                 jsonArray.put(aux);
            }
        }
        System.out.println(listaHidrantesCercanos);
        return Response.ok(jsonArray.toString(), "application/json").encoding("UTF-8").build();
       
    }
    
    

//    @POST
//    @Override
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void create(Hidrantes entity) {
//        super.create(entity);
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public void edit(@PathParam("id") Integer id, Hidrantes entity) {
//        super.edit(entity);
//    }
//
//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") Integer id) {
//        super.remove(super.find(id));
//    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Hidrantes find(@PathParam("id") Integer id) {
        return super.find(id);
    }

//    @GET
//    @Override
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Hidrantes> findAll() {
//        return super.findAll();
//    }
//
//    @GET
//    @Path("{from}/{to}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Hidrantes> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces(MediaType.TEXT_PLAIN)
//    public String countREST() {
//        return String.valueOf(super.count());
//    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
