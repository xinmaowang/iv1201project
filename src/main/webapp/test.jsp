<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<script language="javascript">
    function validate() {
        var uname = document.getElementById("loginform:uname");
        if ( uname.value == "")
        {
            alert("Please enter username!");
            return false;
        }
        var pwd = document.getElementById("loginform:pwd");
        if ( pwd.value == "")
        {
            alert("Please enter password!");
            return false;
        }
        return true;
    }
</script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <f:view>
            <h1>Login</h1>
            <h:form id="loginform" onsubmit="return validate()">
                <table>
                    <tr>
                        <td>Username : </td>
                        <td><h:inputText  id="uname" value="#{UserBean.uname}"></h:inputText></td>
                    </tr>
                    <tr>
                        <td>Password : </td>
                        <td><h:inputSecret id="pwd" value="#{UserBean.pwd}"></h:inputSecret></td>
                    </tr>
                </table>
                <p/>
                <h:commandButton action="#{UserBean.login}" value="Login" />
            </h:form>
        </f:view>
    </body>
</html>