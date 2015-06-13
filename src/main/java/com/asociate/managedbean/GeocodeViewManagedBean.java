/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.managedbean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.ReverseGeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
 
/**
 *
 * @author Ventura
 */
@ManagedBean(name="geocodeViewMB")
public class GeocodeViewManagedBean {
     
    private MapModel geoModel;
    
    private String centerGeoMap = "38.923436,-6.336959";
    
    /**
     *
     */
    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
    }
     
    /**
     *
     * @param event
     */
    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();
         
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
             
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
        }
    }

    /**
     *
     * @return
     */
    public MapModel getGeoModel() {
        return geoModel;
    }

    /**
     *
     * @param geoModel
     */
    public void setGeoModel(MapModel geoModel) {
        this.geoModel = geoModel;
    }

    /**
     *
     * @return
     */
    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    /**
     *
     * @param centerGeoMap
     */
    public void setCenterGeoMap(String centerGeoMap) {
        this.centerGeoMap = centerGeoMap;
    }

    
    
}
