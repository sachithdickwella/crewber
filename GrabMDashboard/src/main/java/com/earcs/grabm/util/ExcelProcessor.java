package com.earcs.grabm.util;

import com.earcs.grabm.util.pojo.CrewMember;
import com.earcs.grabm.util.pojo.Flight;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 *
 * @author Sachith Dickwella
 */
public class ExcelProcessor {

    private final Iterator<Row> row_iterator;

    private interface FlightWorkSheet {

        public static final int INDEX_AIRLINE = 3;
        public static final int INDEX_FLIGHTNUMBER = 6;
        public static final int INDEX_SUFFIX = 8;
        public static final int INDEX_DEPATUREDATETIME = 13;
        public static final int INDEX_DEPATUREAIRPORT = 15;
        public static final int INDEX_ARRIVALDATETIME = 17;
        public static final int INDEX_ARRIVALAIRPORT = 19;
    }

    private interface CrewWorkSheet {

        public static final int INDEX_CREWTYPE = 0;
        public static final int INDEX_MEMBERNO = 1;
        public static final int INDEX_DESIGNATIONSHORTNAME = 3;
        public static final int INDEX_FIRSTNAME = 4;
        public static final int INDEX_LASTNAME = 5;
    }

    public ExcelProcessor(File file) throws IOException, InvalidFormatException {
        final Workbook workbook = WorkbookFactory.create(file);
        final XSSFSheet worksheet = (XSSFSheet) workbook.getSheetAt(0);

        this.row_iterator = worksheet.rowIterator();
    }

    /**
     * Iterate worksheet rows.
     *
     * @return
     */
    public List<Flight> rowIterator_FlightSchedule() {
        final List<Flight> flights = new ArrayList<>();
        row_iterator.forEachRemaining((Row row) -> {

            Cell airlineCell = row.getCell(FlightWorkSheet.INDEX_AIRLINE);
            Cell flightNumberCell = row.getCell(FlightWorkSheet.INDEX_FLIGHTNUMBER);
            Cell suffixCell = row.getCell(FlightWorkSheet.INDEX_SUFFIX);
            Cell departureDateTimeCell = row.getCell(FlightWorkSheet.INDEX_DEPATUREDATETIME);
            Cell departureAirportCell = row.getCell(FlightWorkSheet.INDEX_DEPATUREAIRPORT);
            Cell arrivalDateTimeCell = row.getCell(FlightWorkSheet.INDEX_ARRIVALDATETIME);
            Cell arrivalAirportCell = row.getCell(FlightWorkSheet.INDEX_ARRIVALAIRPORT);

            Object airline = getCellValue(airlineCell, false),
                    flightNumber = getCellValue(flightNumberCell, false),
                    suffix = getCellValue(suffixCell, false),
                    departureAirport = getCellValue(departureAirportCell, false),
                    arrivalAirport = getCellValue(arrivalAirportCell, false),
                    departureDateTime = getCellValue(departureDateTimeCell, true),
                    arrivalDateTime = getCellValue(arrivalDateTimeCell, true);

            if (airline != null && !airline.toString().trim().equals("")
                    && flightNumber != null && !flightNumber.toString().trim().equals("")
                    && departureDateTime != null && !departureDateTime.toString().trim().equals("")
                    && arrivalDateTime != null && !arrivalDateTime.toString().trim().equals("")
                    && departureAirport != null && !departureAirport.equals("")
                    && arrivalAirport != null && !arrivalAirport.equals("")) {

                try {
                    flightNumber = String.valueOf((int) Double.parseDouble(flightNumber.toString().trim()));
                } catch (NumberFormatException ex) {
                    /**
                     * Nothing goes here.
                     */
                }

                if (airline.equals("UL")
                        && (departureAirport.equals("CMB") || arrivalAirport.equals("CMB"))) {

                    Flight flight = new Flight();
                    flight.setFlightNumber(airline.toString().trim()
                            + flightNumber
                            + (suffix != null ? suffix.toString().trim() : ""));
                    flight.setDepartureDateTime((Date) departureDateTime);
                    flight.setDepartureFrom(departureAirport.toString().trim());
                    flight.setArriveDateTime((Date) arrivalDateTime);
                    flight.setArriveTo(arrivalAirport.toString().trim());

                    flights.add(flight);
                }
            }
        });

        return flights;
    }

    public List<CrewMember> rowIterator_CrewList() {
        final List<CrewMember> crewList = new ArrayList<>();
        row_iterator.forEachRemaining((Row row) -> {

            Cell crewTypeCell = row.getCell(CrewWorkSheet.INDEX_CREWTYPE);
            Cell memberNoCell = row.getCell(CrewWorkSheet.INDEX_MEMBERNO);
            Cell designationShortNameCell = row.getCell(CrewWorkSheet.INDEX_DESIGNATIONSHORTNAME);
            Cell firstNameCell = row.getCell(CrewWorkSheet.INDEX_FIRSTNAME);
            Cell lastNameCell = row.getCell(CrewWorkSheet.INDEX_LASTNAME);

            Object crewType = getCellValue(crewTypeCell, false),
                    memberNo = getCellValue(memberNoCell, false),
                    designationShortName = getCellValue(designationShortNameCell, false),
                    firstName = getCellValue(firstNameCell, false),
                    lastName = getCellValue(lastNameCell, false);

            if (crewType != null && !crewType.toString().trim().equals("")
                    && memberNo != null && !memberNo.toString().trim().equals("")
                    && designationShortName != null && !designationShortName.toString().trim().equals("")
                    && firstName != null && !firstName.toString().trim().equals("")
                    && lastName != null && !lastName.equals("")) {

                CrewMember crewMember = new CrewMember();
                crewMember.setLevel(crewType.toString().trim().charAt(0));

                String mem_no = memberNo.toString().trim();
                if (mem_no.contains(".")) {
                    crewMember.setMemberNumber(mem_no.substring(0, mem_no.indexOf(".")));
                } else {
                    crewMember.setMemberNumber(mem_no);
                }

                crewMember.setDesignation(designationShortName.toString().trim());
                crewMember.setFirstName(firstName.toString().trim());
                crewMember.setLastName(lastName.toString().trim());

                crewList.add(crewMember);
            }
        });

        return crewList;
    }

    /**
     * Validate excel cell data.
     *
     * @param cell
     * @param isDate
     * @return
     */
    protected Object getCellValue(Cell cell, boolean isDate) {
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    return cell.getStringCellValue();
                case Cell.CELL_TYPE_NUMERIC:
                    if (isDate) {
                        Date date = cell.getDateCellValue();

                        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                        calendar.clear();
                        /**
                         * Set date.
                         */
                        calendar.set(Calendar.YEAR, Integer.parseInt(new SimpleDateFormat("yyyy").format(date)));
                        calendar.set(Calendar.MONTH, Integer.parseInt(new SimpleDateFormat("M").format(date)));
                        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(new SimpleDateFormat("d").format(date)));
                        /**
                         * Set time.
                         */
                        calendar.set(Calendar.HOUR, Integer.parseInt(new SimpleDateFormat("H").format(date)));
                        calendar.set(Calendar.MINUTE, Integer.parseInt(new SimpleDateFormat("m").format(date)));
                        calendar.set(Calendar.SECOND, Integer.parseInt(new SimpleDateFormat("s").format(date)));

                        return calendar.getTime();
                    } else {
                        return cell.getNumericCellValue();
                    }
                case Cell.CELL_TYPE_BOOLEAN:
                    return cell.getBooleanCellValue();
                case Cell.CELL_TYPE_ERROR:
                    return cell.getErrorCellValue();
                case Cell.CELL_TYPE_BLANK:
                    return "";
            }
        }
        return null;
    }
}
