package com.earcs.grabm.util;

import com.earcs.grabm.pojo.AreaGroup;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Sachith Dickwella
 */
public interface GrabmDashboardConstant {

    /**
     * Exception message for when initialization fails of the application
     * container.
     */
    public static final String EXCEPTION_INITIALIZING = "Application mete data loading fails.";

    public static interface Attributes {

        public default Marshaller getJaxbMarshaller() throws JAXBException {
            JAXBContext jc = JAXBContext.newInstance(GenericWrapper.class, AreaGroup.class);
            Marshaller marshaller = jc.createMarshaller();
            //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //marshaller.setProperty(JAXBContextProperties.MEDIA_TYPE, "application/xml");
            //marshaller.setProperty(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
            return marshaller;
        }

        /**
         * Session USER object reference.
         */
        public final String SESSION_ADMIN = "admin";
        /**
         * Application context PROFILES object reference to insert.
         */
        public final String CONTEXT_PROFILES = "profiles";
        /**
         * Session USER's PROFILE object reference.
         */
        public final String SESSION_PROFILE = "profile";
        /**
         * Application context WEBPAGES object reference to insert.
         */
        public final String CONTEXT_WEBPAGES = "webpages";
        /**
         * Application context FUELTYPES reference.
         */
        public final String CONTEXT_FUELTYPES = "fueltypes";
        /**
         * Application context VEHICLETYPES reference.
         */
        public final String CONTEXT_VEHICLETYPES = "vehicletypes";
        /**
         * Application context VEHICLEBRANDS reference.
         */
        public final String CONTEXT_VEHICLEBRANDS = "vehiclebrands";
        /**
         * Application context VEHICLEMODELS reference.
         */
        public final String CONTEXT_VEHICLEMODELS = "vehiclemodels";
        /**
         * Application context AREAGROUPS reference.
         */
        public final String CONTEXT_AREAGROUP = "areagroup";
        /**
         * Application context COMPANIES reference.
         */
        public final String CONTEXT_COMPANY = "company";
        /**
         * Application context vehicle reference.
         */
        public final String CONTEXT_VEHICLE = "vehicles";
        /**
         * Application context driver reference.
         */
        public final String CONTEXT_DRIVER = "drivers";
        /**
         * Application context future pickups.
         */
        public final String CONTEXT_FUTUREPICKUPDS = "futurepickups";
        /**
         * Application context end user reference.
         */
        public final String CONTEXT_ENDUSER = "endusers";
        /**
         * Application context vehicle driver.
         */
        public final String CONTEXT_VEHICLEDRIVER = "vehicledrivers";
    }

    public enum Method {
        GET, POST, PUT, DELETE
    }

    public interface Bundle {

        /**
         * Remote REST API URL from resource bundle.
         */
        public static final String API_URL = "API_URL";
        /**
         * Remote REST API authorization key from resource bundle.
         */
        public static final String API_KEY = "AUTH_KEY";
        /**
         * Application context path.
         */
        public static final String CONTEXT_PATH = "CONTEXT_PATH";

        /**
         * Fetch values form resource bundle according to keys.
         *
         * @param key
         * @return
         */
        public static String getValue(String key) {
            ResourceBundle bundle = ResourceBundle.getBundle("bundle/info", Locale.US);
            return bundle.getString(key);
        }

        /**
         * Fetch CONTEXT_PATH from resource bundle.
         *
         * @return CONTEXT_PATH
         */
        public static String context() {
            return getValue(CONTEXT_PATH);
        }

        /**
         * Headers map for the REST API.
         *
         * @return
         */
        public static MultivaluedMap<String, Object> getHeaders() {
            MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
            headers.put("Authorization", Collections.singletonList(getValue(API_KEY)));

            return headers;
        }
    }

    /**
     * Return constant when session expired.
     */
    public static final int SESSION_TIMEOUT = -3;
    /**
     * Return constant when validation failed.
     */
    public static final int VALIDATION_FAILED = -2;
    /**
     * Return constant when client input error.
     */
    public static final int CLIENT_ERROR = -4;

    public static final double[] BIA_LATLANG = {7.171684D, 79.886539D};
}
