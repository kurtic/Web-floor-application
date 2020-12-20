package com.Diachenko.WebFloorHelper.serlets;

import com.Diachenko.WebFloorHelper.database.entities.Layout;
import com.Diachenko.WebFloorHelper.database.model.Model;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.Diachenko.WebFloorHelper.tools.DataFormat.*;
import static com.Diachenko.WebFloorHelper.tools.roomValidate.*;


public class RoomServlet extends HttpServlet {
    private String coordinates;
    private String urlDB = "jdbc:mysql://localhost/FloorLayoutDB?useUnicode=true&useJDBCCompliantTimezone" +
            "Shift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String userDB = "root";
    private String passwordDB = "root";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getServletPath();
        if (action.equals("/coordinatesList")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/coordinatesList.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (action.startsWith("/showPage")) {
            try {
                try {
                    show(Integer.parseInt(action.substring(9)), req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (action.startsWith("/delete")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/deletePage.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (action.startsWith("/edit")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/editPage.jsp");
            try {
                requestDispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if (servletPath.equals("/showPage")) {
            setCoordinates(request, response);
        } else if (servletPath.startsWith("/addToDatabase")) {
            setCoordinatesForDatabase(request, response);
        } else if (servletPath.startsWith("/delete")) {
            try {
                deleteLayout(Integer.parseInt(servletPath.substring(7)), request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (servletPath.startsWith("/edit")) {
            try {
                editLayout(Integer.parseInt(servletPath.substring(5)), request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (servletPath.startsWith("/showPage")) {
            try {
                show(Integer.parseInt(servletPath.substring(5)), request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (servletPath.startsWith("/validateRoom")) {
            String requestBody = extractPostRequestBody(request);
            ArrayList<Integer> arrayList = coordinatesFromJson(requestBody);
            validate(response, arrayList, requestBody);
        }
    }

    private void show(HttpServletRequest request, HttpServletResponse response) {
        String name;
        String coordinates;
        name = request.getParameter("name");
        coordinates = request.getParameter("values");
        request.setAttribute("n", name);
        request.setAttribute("c", coordinates);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/showPage.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void show(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Model model = new Model(urlDB, userDB, passwordDB);
        List<Layout> layouts = model.searchById();
        String name = null;
        String coordinates = null;

        for (Layout l : layouts) {
            if (l.getId() == id) {
                name = l.getName();
                coordinates = l.getCoordinates();
            }
        }
        request.setAttribute("n", name);
        request.setAttribute("c", coordinates);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/showPage.jsp");
        requestDispatcher.forward(request, response);
    }

    private void validate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String coordinates = request.getParameter("values");
        ArrayList<Integer> coordinatesList = getCoordinates(coordinates);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/errorPage.jsp");
        if (amountCorners(coordinatesList)) {
            request.setAttribute("error", "Error! There must be at least 4 corners");
            dispatcher.forward(request, response);
        } else if (!corners90(coordinatesList)) {
            request.setAttribute("error", "Error! All corners should be 90 degrees");
            dispatcher.forward(request, response);
        } else if (cornerRepeating(coordinatesList)) {
            request.setAttribute("error", "Error! Corners should not be repeated");
            dispatcher.forward(request, response);
        } else if (diagonal(coordinatesList)) {
            request.setAttribute("error", "Error! Diagonal walls are present");
            dispatcher.forward(request, response);
        } else {
            show(request, response);
        }
    }

    private void validate(HttpServletResponse response, ArrayList<Integer> coordinatesList, String requestBody) throws IOException {
        PrintWriter writer = response.getWriter();
        JSONObject jsonError = new JSONObject();
        if (amountCorners(coordinatesList)) {
            jsonError.put("error", "Error! There must be at least 4 corners");
            writer.println(jsonError);
            writer.flush();
        } else if (!corners90(coordinatesList)) {
            jsonError.put("error", "All corners should be 90 degrees");
            writer.println(jsonError);
            writer.flush();
        } else if (cornerRepeating(coordinatesList)) {
            jsonError.put("error", "Corners should not be repeated");
            writer.println(jsonError);
            writer.flush();
        } else if (diagonal(coordinatesList)) {
            jsonError.put("error", "Diagonal walls are present");
            writer.println(jsonError);
            writer.flush();
        } else {
            writer.println(requestBody);
            writer.flush();
        }
    }

    private String extractPostRequestBody(HttpServletRequest request) throws IOException {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }

    private void setCoordinates(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        coordinates = request.getParameter("values");
        if (templateChecking(coordinates)) {
            validate(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/errorPage.jsp");
            request.setAttribute("error", "Error! The data does not match the template");
            dispatcher.forward(request, response);
        }
    }

    private void setCoordinatesForDatabase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        coordinates = request.getParameter("values");
        if (templateChecking(coordinates)) {
            addToDatabase(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/errorPage.jsp");
            request.setAttribute("error", "Error! The data does not match the template");
            dispatcher.forward(request, response);
        }
    }

    private void addToDatabase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String coordinates = request.getParameter("values");
        ArrayList<Integer> coordinatesList = getCoordinates(coordinates);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/errorPage.jsp");
        if (amountCorners(coordinatesList)) {
            request.setAttribute("error", "Error! Coordinates must have at least 4 corners");
            dispatcher.forward(request, response);
        } else if (!corners90(coordinatesList)) {
            request.setAttribute("error", "Error! All corners should be 90 degrees");
            dispatcher.forward(request, response);
        } else if (cornerRepeating(coordinatesList)) {
            request.setAttribute("error", "Error! Corners should not be repeated");
            dispatcher.forward(request, response);
        } else if (diagonal(coordinatesList)) {
            request.setAttribute("error", "Error! Diagonal walls are present");
            dispatcher.forward(request, response);
        } else {
            Layout layout = new Layout(name, coordinates);
            Model model = new Model(urlDB, userDB, passwordDB);
            try {
                model.add(layout);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
                request.setAttribute("add", "add");
                request.setAttribute("name", request.getParameter("name"));
                requestDispatcher.forward(request, response);
            } catch (SQLException throwables) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/errorPage.jsp");
                request.setAttribute("error", "Database isn't working, try again later");
                requestDispatcher.forward(request, response);
            }

        }

    }

    private void deleteLayout(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Model model = new Model(urlDB, userDB, passwordDB);
        model.delete(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/coordinatesList.jsp");
        requestDispatcher.forward(request, response);
    }

    private void editLayout(int id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        coordinates = request.getParameter("values");
        if (templateChecking(coordinates)) {
            ArrayList<Integer> coordinatesList = getCoordinates(coordinates);
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/errorPage.jsp");
            if (amountCorners(coordinatesList)) {
                request.setAttribute("error", "Error! At least 4 corners should be present");
                dispatcher.forward(request, response);
            } else if (!corners90(coordinatesList)) {
                request.setAttribute("error", "Error! All corners should be 90 degrees");
                dispatcher.forward(request, response);
            } else if (cornerRepeating(coordinatesList)) {
                request.setAttribute("error", "Error! Corners shouldn't be repeated");
                dispatcher.forward(request, response);
            } else if (diagonal(coordinatesList)) {
                request.setAttribute("error", "Error! There are diagonal walls");
                dispatcher.forward(request, response);
            } else {
                Model model = new Model(urlDB, userDB, passwordDB);
                String name = request.getParameter("name");
                String coordinates = request.getParameter("values");
                model.edit(id, name, coordinates);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/coordinatesList.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/errorPage.jsp");
            request.setAttribute("error", "Error! The data does not match the template");
            dispatcher.forward(request, response);
        }
    }
}