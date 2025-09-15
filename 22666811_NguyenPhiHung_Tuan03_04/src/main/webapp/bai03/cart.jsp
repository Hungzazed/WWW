<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans">
<div class="container mx-auto px-4 py-8">
    <h2 class="text-2xl font-bold text-gray-800 mb-6">Your Cart</h2>

    <c:if test="${empty cart.items}">
        <p class="text-gray-600 text-lg">Your cart is empty!</p>
    </c:if>

    <c:if test="${not empty cart.items}">
        <div class="overflow-x-auto">
            <table class="w-full bg-white rounded-lg shadow-md">
                <thead class="bg-gray-200">
                <tr>
                    <th class="py-3 px-4 text-left text-gray-700 font-semibold">Model</th>
                    <th class="py-3 px-4 text-left text-gray-700 font-semibold">Quantity</th>
                    <th class="py-3 px-4 text-left text-gray-700 font-semibold">Price</th>
                    <th class="py-3 px-4 text-left text-gray-700 font-semibold">Total</th>
                    <th class="py-3 px-4 text-left text-gray-700 font-semibold">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${cart.items}">
                    <tr class="border-b hover:bg-gray-50">
                        <td class="py-4 px-4 text-gray-800">${item.product.model}</td>
                        <td class="py-4 px-4">
                            <form action="${pageContext.request.contextPath}/cart" method="post" class="flex items-center space-x-2">
                                <input type="hidden" name="action" value="update"/>
                                <input type="hidden" name="id" value="${item.product.id}"/>
                                <input type="number" name="quantity" value="${item.quantity}" min="1" class="w-16 border border-gray-300 rounded-md p-1 focus:outline-none focus:ring-2 focus:ring-blue-500"/>
                                <input type="submit" value="Update" class="bg-blue-500 text-white py-1 px-3 rounded-md hover:bg-blue-600 transition duration-300 cursor-pointer"/>
                            </form>
                        </td>
                        <td class="py-4 px-4 text-gray-800">$${item.product.price}</td>
                        <td class="py-4 px-4 text-gray-800">$${item.product.price * item.quantity}</td>
                        <td class="py-4 px-4">
                            <form action="${pageContext.request.contextPath}/cart" method="post">
                                <input type="hidden" name="action" value="remove"/>
                                <input type="hidden" name="id" value="${item.product.id}"/>
                                <input type="submit" value="Remove" class="bg-red-500 text-white py-1 px-3 rounded-md hover:bg-red-600 transition duration-300 cursor-pointer"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <p class="mt-6 text-xl font-semibold text-gray-800"><strong>Total:</strong> $${cart.total}</p>
    </c:if>

    <div class="mt-6">
        <a href="products" class="inline-block text-white bg-blue-600 hover:bg-blue-700 px-4 py-2 rounded-md transition duration-300">
            Continue Shopping
        </a>
    </div>
</div>
</body>
</html>