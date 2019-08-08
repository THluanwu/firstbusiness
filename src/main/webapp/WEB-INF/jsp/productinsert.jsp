<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/7
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>增加商品</title>
</head>
<body>
<h1>增加商品</h1>



<form action="" method="post" enctype="multipart/form-data">

    <input type="hidden" name="id" ><br/>
    名称<input type="text" name="name" ><br/>
    <%--类别ID<input type="text" name="categoryId" value=""><br/>--%>
    类别:<select name="categoryId">
    <option></option>
    <c:forEach items="${categoryList}" var="category">
        <option >${category.name}</option>
    </c:forEach>
          </select><br/>
    子图<input type="file" name="subimages"><br/>
    价格:<input type="text" name="price" ><br/>
    库存:<input type="text" name="stock" ><br/>
    状态:<input type="text" name="status"><br/>
    <input type="submit"  value="增加"><br/>

</form>

</body>
</html>
