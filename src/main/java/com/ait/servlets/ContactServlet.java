package com.ait.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContactServlet
 */
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("contact.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuilder theQueryString = new StringBuilder();
		Map<String, String[]> map = request.getParameterMap();
		map.forEach((key, value) -> {
			theQueryString.append(key + "=" + Arrays.asList(value) + "&");
		});

		theQueryString.setLength(theQueryString.length() - 1);

		URL url = new URL("https://fabform.io/f/MK2_1DD");
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

		// For POST only - START
		httpURLConnection.setDoOutput(true);
		OutputStream os = httpURLConnection.getOutputStream();
		os.write(theQueryString.toString().replace("[", "").replace("]", "").getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = httpURLConnection.getResponseCode();

		if (201 == HttpURLConnection.HTTP_OK) {
			response.sendRedirect("ThankYou");
		} else {
			this.doGet(request, response);
		}

	}

}
