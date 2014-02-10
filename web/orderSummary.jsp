<%-- 
    Document   : orderSummary
    Created on : Feb 9, 2014, 6:10:04 PM
    Author     : gcDataTechnology
--%>

<%@page import="java.util.LinkedHashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Summary</title>
    </head>
    <body>
        <h1>Order Summary</h1>
        <div>
        <%
            LinkedHashMap order = (LinkedHashMap)request.getAttribute("menu");
            for(Object o: order.keySet()){
                out.print(o + "&nbsp; &nbsp;");
                out.print(order.get(o) + "</br>");
            }

            //no longer a string array it is now a LinkedHashMap
            /*
           String[] order = (String[])request.getAttribute("menu");
            if(order!=null){
                for(int i=0;i<order.length;i++){
                    if(order[i]!=null){
                        out.print(order[i]);
                    }
                }
            }
                    */
        %>
        </div>
    </body>
</html>
