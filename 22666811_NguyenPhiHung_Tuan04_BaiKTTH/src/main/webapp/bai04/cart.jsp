<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-white font-sans">
<div class="max-w-7xl mx-auto px-4">
    <!-- Header -->
    <header class="flex items-center justify-between py-6 border-b border-gray-300">
        <h1 class="text-3xl font-bold text-gray-800">IUH BOOKSTORE</h1>
        <nav class="space-x-4">
            <a href="#" class="text-gray-700 hover:text-blue-600">HOME</a>
            <a href="#" class="text-gray-700 hover:text-blue-600">EXAMPLES</a>
            <a href="#" class="text-gray-700 hover:text-blue-600">SERVICES</a>
            <a href="#" class="text-gray-700 hover:text-blue-600">PRODUCTS</a>
            <a href="#" class="text-gray-700 hover:text-blue-600">CONTACT</a>
        </nav>
    </header>

    <!-- Content -->
    <div class="flex mt-6">
        <!-- Sidebar -->
        <aside class="w-1/4 pr-6 border-r border-gray-300">
            <h2 class="text-xl font-semibold text-gray-800 mb-4">ABOUT US</h2>
            <p class="text-sm text-gray-600 mb-6">About us information will be here...
                <a href="#" class="text-blue-500">Read More »</a>
            </p>

            <h2 class="text-xl font-semibold text-gray-800 mb-2">SEARCH SITE</h2>
            <input type="text" placeholder="Search..." class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring focus:border-blue-300">

            <p class="mt-4 text-sm text-blue-600">Shopping cart (${bookcart.items.size()})</p>
        </aside>

        <!-- Cart Table -->
        <main class="w-3/4 pl-6">
            <h2 class="text-xl font-semibold text-gray-800 mb-4">YOUR SHOPPING CART</h2>

            <c:if test="${empty bookcart.items}">
                <p class="text-gray-600 text-lg">Your cart is empty!</p>
            </c:if>

            <c:if test="${not empty bookcart.items}">
                <div class="overflow-x-auto border border-gray-300">
                    <table class="min-w-full text-sm text-left text-gray-700">
                        <thead class="bg-gray-200 text-xs uppercase">
                        <tr>
                            <th class="px-4 py-2 border">Product ID</th>
                            <th class="px-4 py-2 border">Product name</th>
                            <th class="px-4 py-2 border">Price</th>
                            <th class="px-4 py-2 border">Qty</th>
                            <th class="px-4 py-2 border">Total</th>
                            <th class="px-4 py-2 border">Remove</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${bookcart.items}">
                            <tr class="border-t hover:bg-gray-50">
                                <td class="px-4 py-2 border">prod${item.book.id}</td>
                                <td class="px-4 py-2 border">${item.book.title} - Tác giả ${item.book.author}</td>
                                <td class="px-4 py-2 border">${item.book.price}</td>
                                <td class="px-4 py-2 border">${item.quantity}</td>
                                <td class="px-4 py-2 border">${item.book.price * item.quantity}</td>
                                <td class="px-4 py-2 border">
                                    <form action="${pageContext.request.contextPath}/bookcart" method="post">
                                        <input type="hidden" name="action" value="remove"/>
                                        <input type="hidden" name="id" value="${item.book.id}"/>
                                        <input type="submit" value="Remove" class="text-blue-600 hover:underline"/>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <p class="mt-4 text-right font-semibold text-gray-800">
                    Total price: <span class="ml-2 text-black">(VNĐ) ${bookcart.total}</span>
                </p>

                <div class="mt-6 flex space-x-4">
                    <form action="${pageContext.request.contextPath}/checkouts" method="get">
                        <button type="submit" class="bg-gray-800 text-white px-4 py-2 rounded hover:bg-black">Checkout</button>
                    </form>
                    <a href="${pageContext.request.contextPath}/books"
                       class="bg-gray-300 text-gray-800 px-4 py-2 rounded hover:bg-gray-400">
                        Continue shopping
                    </a>
                </div>
            </c:if>
        </main>
    </div>
</div>
</body>
</html>
