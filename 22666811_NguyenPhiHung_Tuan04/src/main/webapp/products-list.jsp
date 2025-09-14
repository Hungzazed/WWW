<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Listing</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans">
<div class="container mx-auto px-4 py-8">
    <div class="flex justify-end mb-6">
        <a href="cart" class="text-white bg-blue-600 hover:bg-blue-700 px-4 py-2 rounded-md transition duration-300">
            View Cart
        </a>
    </div>
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        <c:forEach items="${products}" var="p">
            <div class="product-class bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition duration-300">
                <h3 class="text-lg font-semibold text-gray-800 mb-2">${p.model}</h3>
                <img src="images/${p.imgUrl}" class="hinh w-full h-48 object-cover rounded-md mb-4" alt="${p.model}">
                <p class="text-gray-600 mb-4">Price: $${p.price}</p>
                <form action="${pageContext.request.contextPath}/cart" method="post" class="space-y-4">
                    <div class="flex items-center space-x-2">
                        <label for="quantity-${p.id}" class="text-sm text-gray-600">Quantity:</label>
                        <input type="number" id="quantity-${p.id}" name="quantity" value="1" min="1" class="w-16 border border-gray-300 rounded-md p-1 focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <input type="hidden" name="id" value="${p.id}">
                    <input type="hidden" name="price" value="${p.price}">
                    <input type="hidden" name="model" value="${p.model}">
                    <input type="hidden" name="action" value="add">
                    <div class="flex space-x-4">
                        <input type="submit" name="addToCart" value="Add To Cart" class="w-full bg-green-500 text-white py-2 rounded-md hover:bg-green-600 transition duration-300 cursor-pointer">
                        <a href="${pageContext.request.contextPath}/products?id=${p.id}" class="w-full text-center bg-gray-200 text-gray-800 py-2 rounded-md hover:bg-gray-300 transition duration-300">
                            Product Detail
                        </a>
                    </div>
                </form>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>