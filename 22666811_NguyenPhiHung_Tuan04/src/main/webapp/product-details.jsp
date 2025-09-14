<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Detail</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans">
<div class="container mx-auto px-4 py-8">
    <h2 class="text-2xl font-bold text-gray-800 mb-6">Product Detail</h2>
    <c:if test="${not empty product}">
        <div class="bg-white rounded-lg shadow-md p-6 max-w-lg mx-auto">
            <img src="${pageContext.request.contextPath}/images/${product.imgUrl}"
                 alt="${product.model}"
                 class="w-full h-64 object-cover rounded-md mb-6"/>
            <ul class="space-y-3 text-gray-700">
                <li><span class="font-semibold">ID:</span> ${product.id}</li>
                <li><span class="font-semibold">Model:</span> ${product.model}</li>
                <li><span class="font-semibold">Description:</span> ${product.description}</li>
                <li><span class="font-semibold">Quantity:</span> ${product.quantity}</li>
                <li><span class="font-semibold">Price:</span> $${product.price}</li>
            </ul>
        </div>
    </c:if>

    <div class="mt-6">
        <a href="${pageContext.request.contextPath}/products"
           class="inline-block text-white bg-blue-600 hover:bg-blue-700 px-4 py-2 rounded-md transition duration-300">
            Back to Product List
        </a>
    </div>
</div>
</body>
</html>