package br.com.tacuem.utilidades;

import jakarta.servlet.http.HttpServletRequest;

public class UrlUtilidade {
    public static String getApplicationUrl(HttpServletRequest request){
        String appUrl = request.getRequestURL().toString();
        return appUrl.replace(request.getServletPath(), "");

    }
}
