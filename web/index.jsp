<%-- 
    Document   : index
    Created on : Feb 7, 2014, 3:50:32 PM
    Author     : gcDataTechnology
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Web Restaurant</title>
        <style type="text/css">
            
        </style>
    </head>
    <body>
        <h1>Web Menu</h1>
         <h3>Daily Special...Feature coming soon</h3>
        <div>
            
            
               
                    <%
                        if(request.getAttribute("menuAction")!=null && request.getAttribute("menuAction").equals("selectItems")){
                            out.print("<form id='frmMenu' name='frmMenu' method='POST' action='menu?menuAction=placeOrder'>");
                            //out.print("<select id='menuItems' name='menuItems'>");
                            Map m = (HashMap)request.getAttribute("menu");
                            for(Object s:m.keySet()){
                                List item=(ArrayList)m.get(s);
                                out.print("<label>" + s + "</label>"
                                        + "<input name='itemSelected' type='checkbox' value='" + item.get(0) + "' /> &nbsp;$" + item.get(2));
                                out.print( "  " + item.get(1) + "<br/><br/>" );
                                /*need better formating
               //use this line   //out.print("<option value='" + item.get(0) + "' >" + s + " " + item.get(1) + "  $" + item.get(2) +"</option>");
                                //out.print("<option value='" + item.get(0) + "' >" + s + "</option>");
                                //out.println("<ul><li>"+ item.get(1) + "  $" + item.get(2) + "</li></ul>" );
                               // out.print("<option value='" + item.get(0) + "' >" + s + "<ul><li> " + item.get(1) + "  $" + item.get(2) +"</li></ul></option>");
                               //out.print("<option value='" + s + "' >" + s + "</option>");
                               */
                            }
                            //out.print(" </select><br/><input type='submit' value='Place Order'/>");
                            out.print("<input type='submit' value='Place Order'/>");
                        }else{
                           out.print("<form id='frmGetMenu' name='frmGetMenu' method='POST' action='menu?menuAction=getMenu'>"+
                                     "<input id='btnSeeMenu' name='btnSeeMenu' type='submit' value='See Menu'/>"+
                                     "</form>");
                        }
                        
                    %>
               
            </form>
            
        </div>
    </body>
</html>
