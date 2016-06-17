package com.grabm.tracker;

import com.grabm.entity.LatestTracking;
import com.grabm.entity.TrackingHistory;
import com.grabm.util.GrabMConstant;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

public final class ServerHandler implements Runnable, GrabMConstant {

    private final Logger logger = Logger.getLogger(ServerHandler.class);
    private final Socket socket;
    private final InputStream is;
    private final OutputStream os;

    public ServerHandler(Socket socket) throws IOException {
        this.socket = socket;
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    public void start() {
        TRACKER_SERVICE.submit(this);
    }

    @Override
    public void run() {
        LatestTracking newTracking = null;
        while (true) {
            byte[] readBuffer = new byte[1024];
            try {
                DatabaseHandler<TrackingHistory> thh = new DatabaseHandler<>(TrackingHistory.class);
                DatabaseHandler<LatestTracking> lth = new DatabaseHandler<>(LatestTracking.class);
                if (newTracking != null) {
                    thh.create(translate(newTracking));
                    lth.createOrUpdate(newTracking);
                    ONGOING_TRACKINGS.put(newTracking.getImie(), newTracking);
                }

                int readBytes = is.read(readBuffer, 0, readBuffer.length);
                if (readBytes < 0) {
                    System.err.println("No data found from the input stream, Handler shutting down.");
                    break;
                }
                if (!ServerStatus.isRunning) {
                    System.err.println("Server is not running. Handler shutting down.");
                    break;
                }

                String response = new String(readBuffer).trim();
                System.out.println(response);
                newTracking = decode(response);

                if (response.startsWith("##")) {
                    String packet = "LOAD";
                    byte[] writeBuffer = packet.getBytes();
                    os.write(writeBuffer, 0, writeBuffer.length);
                    os.flush();
                } else {
                    String packet = "**," + response + ",B;";
                    byte[] writeBuffer = packet.getBytes();
                    os.write(writeBuffer, 0, writeBuffer.length);
                    os.flush();
                }

            } catch (IOException ex) {
                logger.error(EXCEPTION_TRACKER, ex);
                break;
            }
        }

        try {
            socket.close();
            System.out.println("Transaction socket closed.");
        } catch (Exception ex) {
            logger.error(EXCEPTION_TRACKER, ex);
        }
    }

    public LatestTracking decode(String response) {
        Matcher parser = PATTERN.matcher(response);
        if (!parser.matches()) {
            return null;
        }
        int index = 1;
        LatestTracking tracker = new LatestTracking();
        tracker.setImie(parser.group(index++));
        tracker.setAlarm(parser.group(index++));

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        /**
         * Set date.
         */
        calendar.set(Calendar.YEAR, 2000 + Integer.parseInt(parser.group(index++)));
        calendar.set(Calendar.MONTH, Integer.parseInt(parser.group(index++)) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(parser.group(index++)) - 1);
        /**
         * Set time.
         */
        calendar.set(Calendar.HOUR, Integer.parseInt(parser.group(index++)));
        calendar.set(Calendar.MINUTE, Integer.parseInt(parser.group(index++)));
        calendar.set(Calendar.SECOND, Integer.parseInt(parser.group(index++)));
        calendar.set(Calendar.MILLISECOND, Integer.parseInt(parser.group(index++)));

        tracker.setTrackerDateTime(calendar.getTime());
        if ((parser.group(index++).compareTo("A") == 0)) {
            tracker.setValidity(true);
        } else {
            tracker.setValidity(false);
        }

        Double latitude = Double.valueOf(parser.group(index++));
        latitude += Double.valueOf(parser.group(index++)) / 60;
        if (parser.group(index++).compareTo("S") == 0) {
            latitude = -latitude;
        }
        tracker.setLatitude(latitude);

        Double longitude = Double.valueOf(parser.group(index++));
        longitude += Double.valueOf(parser.group(index++)) / 60;
        if (parser.group(index++).compareTo("W") == 0) {
            longitude = -longitude;
        }
        tracker.setLongitude(longitude);
        tracker.setSpeed(Double.valueOf(parser.group(index++)));
        return tracker;
    }

    /**
     * Regular expressions pattern
     */
    private static final Pattern PATTERN = Pattern.compile(
            "imei:"
            + "([\\d]+)," // IMEI 
            + "([^,]+)," // Alarm 
            + "(\\d{2})(\\d{2})(\\d{2})[\\d]+,"// Date 
            + "[^,]*,"
            + "[FL],"// F - full / L - low 
            + "([\\d]{2})([\\d]{2})([\\d]{2}).([\\d]{3}),"// Time (HHMMSS.SSS) 
            + "([AV]),"// Validity 
            + "([\\d]{2})([\\d]{2}.[\\d]{4}),"// Latitude (DDMM.MMMM) 
            + "([NS]),"
            + "([\\d]{3})([\\d]{2}.[\\d]{4}),"// Longitude (DDDMM.MMMM) 
            + "([EW]),"
            + "([\\d]+.[\\d]{2}),"// Speed 
            + ".*");

    public TrackingHistory translate(LatestTracking lt) {
        final TrackingHistory th = new TrackingHistory();
        th.setImie(lt.getImie());
        th.setAlarm(lt.getAlarm());
        th.setTrackerDateTime(lt.getTrackerDateTime());
        th.setValidity(lt.isValidity());
        th.setLatitude(lt.getLatitude());
        th.setLongitude(lt.getLongitude());
        th.setSpeed(lt.getSpeed());
        return th;
    }
}
